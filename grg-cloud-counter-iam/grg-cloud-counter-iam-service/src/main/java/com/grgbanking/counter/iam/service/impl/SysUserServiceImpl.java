package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.base.GrgUser;
import com.grgbanking.counter.common.security.utils.PlatformAdminRoleIdUtil;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysUserBo;
import com.grgbanking.counter.iam.api.bo.SysUserPasswordBo;
import com.grgbanking.counter.iam.api.bo.SysUserQueryBo;
import com.grgbanking.counter.iam.api.bo.SysUserUpdateBo;
import com.grgbanking.counter.iam.api.vo.*;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysUserDao;
import com.grgbanking.counter.iam.entity.*;
import com.grgbanking.counter.iam.service.*;
import com.grgbanking.counter.iam.service.redis.SysUserRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author lggui1
 * @Date 2021/1/12
 * @Description
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserRedisService sysUserRedisService;
    @Autowired
    private SysAuthorityService sysAuthorityService;
    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysAreaService sysAreaService;

    @Autowired
    private SysAreaUserService sysAreaUserService;

    @Autowired
    private SysOrgUserService sysOrgUserService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysMenuService sysMenuService;

    public Integer status(String username, String enable) {
        SysUserEntity user = getUserByUsername(username);
        Assert.isNull(user, RespI18nConstants.USER1003.getCode());
        user.setLastUpdateDate(new Date());
        user.setLastUpdatedBy(SecurityContextUtil.getUsername());
        if (StringUtils.isBlank(enable)) {
            enable = Operate.ENABLE.code().equals(user.getIsEnabled()) ? Operate.DISABLE.code() : Operate.ENABLE.code();
            user.setIsEnabled(enable);
        } else {
            user.setIsEnabled(enable);
        }
        if (user.getIsEnabled().equals(Operate.DISABLE.code())) {
            checkDisable(user.getId());
        }

        return this.baseMapper.updateById(user);
    }

    public void checkDisable(Long userId) {
        boolean superUser = isSuperUser(userId);
        Assert.state(superUser, RespI18nConstants.USER1004.getCode());
    }

    @Override
    public boolean unlockCache(String username) {
        return sysUserRedisService.unlockCache(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(SysUserBo vo) {
        SysUserEntity user = new SysUserEntity();
        BeanUtils.copyProperties(vo, user);
        //校验
        checkNotNull(vo);
        checkNotAddNull(vo);
        checkFormat(user);
        checkAlreadyHave(user);
        chechOrg(vo.getAreaIdList(), vo.getJoinOrgId(), vo.getManageOrgIdList());
        /**
         * 1、用户信息写入表SYS_USER，username在表中唯一
         * 2、管理区域写入表SYS_AREA_USER，isLeader=1
         * 3、所属机构写入报表SYS_ORG_USER，isLeader=0
         * 4、管理机构写入报表SYS_ORG_USER，isLeader=1
         */
        boolean okUser = saveUser(user);
        Assert.state(!okUser, RespI18nConstants.COM1006.getCode());
        boolean okOrg = saveOrgUser(vo.getJoinOrgId(), vo.getManageOrgIdList(), user.getId());
        Assert.state(!okOrg, RespI18nConstants.COM1006.getCode());
        boolean okArea = saveAreaUser(vo.getAreaIdList(), user.getId());
        Assert.state(!okArea, RespI18nConstants.COM1006.getCode());
        boolean okRole = saveRoleUser(vo.getRoleIdList(), user.getId());
        Assert.state(!okRole, RespI18nConstants.COM1006.getCode());
        return true;
    }

    @Override
    public boolean update(SysUserUpdateBo vo) {
        SysUserEntity user = new SysUserEntity();
        BeanUtils.copyProperties(vo, user);
        //校验
        SysUserBo bo = new SysUserBo();
        BeanUtils.copyProperties(vo, bo);
        checkNotNull(bo);
        checkFormat(user);
        chechOrg(vo.getAreaIdList(), vo.getJoinOrgId(), vo.getManageOrgIdList());
        /**
         * 1、用户信息写入表SYS_USER，username在表中唯一
         * 2、管理区域写入表SYS_AREA_USER，isLeader=1
         * 3、所属机构写入报表SYS_ORG_USER，isLeader=0
         * 4、管理机构写入报表SYS_ORG_USER，isLeader=1
         */
        boolean okUser = updateUser(user);
        Assert.state(!okUser, RespI18nConstants.COM1006.getCode());
        boolean okOrg = saveOrgUser(vo.getJoinOrgId(), vo.getManageOrgIdList(), user.getId());
        Assert.state(!okOrg, RespI18nConstants.COM1006.getCode());
        boolean okArea = saveAreaUser(vo.getAreaIdList(), user.getId());
        Assert.state(!okArea, RespI18nConstants.COM1006.getCode());
        boolean okRole = saveRoleUser(vo.getRoleIdList(), user.getId());
        Assert.state(!okRole, RespI18nConstants.COM1006.getCode());
        return true;
    }


    @Override
    public boolean resetPassword(String username) {
        SysUserEntity user = new SysUserEntity();
        user.setUsername(username);
        //重置时设置的默认密码111111 前端sha256加密后数据
        user.setPassword(new BCryptPasswordEncoder().encode(AppCoreConstants.DEFAULT_PWD));
        return this.baseMapper.restPassword(user) > 0;
    }

    @Override
    public boolean updatePassword(SysUserPasswordBo sysUserPasswordBo) {
        SysUserEntity sysUserEntity = getUserByUsername(sysUserPasswordBo.getUsername());
        boolean matches = new BCryptPasswordEncoder().matches(sysUserPasswordBo.getPassword(), sysUserEntity.getPassword());
        Assert.state(!matches, RespI18nConstants.USER1005.getCode());
        boolean identical = new BCryptPasswordEncoder().matches(sysUserPasswordBo.getNewPassword(), sysUserEntity.getPassword());
        Assert.state(identical, RespI18nConstants.USER1006.getCode());
        String newPassword = new BCryptPasswordEncoder().encode(sysUserPasswordBo.getNewPassword());
        // 更新密码
        return updatePassword(sysUserEntity.getId(), newPassword);

    }

    public boolean updatePassword(Long userId, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getId, userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUser(List<Long> userIds) {
        checkDel(userIds);
        int i = this.baseMapper.deleteBatchIds(userIds);
        //删除机构用户
        sysOrgUserService.delOrgUserByUserId(userIds, null);
        //删除区域用户
        sysAreaUserService.delAreaUserByUserId(userIds);
        //删除角色用户
        sysRoleUserService.delRoleUserByUserId(userIds);
        return i > 0;
    }

    @Override
    public SysUserInfoVo getUserInfoByUsername(String username) {
        // 用户信息基础信息
        SysUserEntity sysUserEntity = getUserByUsername(username);
        SysUserInfoVo user = new SysUserInfoVo();
        BeanUtils.copyProperties(sysUserEntity, user);

        // 默认密码
        boolean isDefault = new BCryptPasswordEncoder().matches(AppCoreConstants.DEFAULT_PWD, sysUserEntity.getPassword());
        user.setDefaultPassword(isDefault ? "1" : "0");

        // 所属机构 todo 所属机构现在只有一个2021年1月25日
        List<SysOrgUserEntity> joinOrg = sysOrgUserService.queryListByUserId(user.getId(), Operate.NO.code());
        if (CollectionUtil.isNotEmpty(joinOrg)) {
            SysOrgEntity entity = sysOrgService.queryOneById(joinOrg.get(0).getOrgId());
            user.setJoinOrgName(entity.getName());
            user.setJoinOrgId(entity.getId());
            user.setJoinOrgCode(entity.getOrgCode());
        }

        // 管理机构
        List<SysOrgUserEntity> manageOrg = sysOrgUserService.queryListByUserId(user.getId(), Operate.YES.code());
        List<Long> manageOrgIds = manageOrg.stream().map(u -> u.getOrgId()).collect(toList());
        if(CollectionUtil.isNotEmpty(manageOrgIds)){
            List<String> manageOrgCodes = sysOrgService.getCodeListByIds(manageOrgIds);;
            user.setManageOrgIdList(manageOrgIds);
            user.setManageOrgCodeList(manageOrgCodes);
        }
        // 角色
        List<SysRoleUserEntity> roleList = sysRoleUserService.queryAllRoleIdByUserId(user.getId());
        List<Long> roleListIds = roleList.stream().map(u -> u.getRoleId()).collect(toList());
        user.setRoleIdList(roleListIds);

        // 管理区域
        List<SysAreaUserEntity> listArea = sysAreaUserService.getListByUserId(user.getId());
        List<Long> areaListIds = listArea.stream().map(u -> u.getAreaId()).collect(toList());
        if(CollectionUtil.isNotEmpty(areaListIds)){
            List<String> areaListCodes = sysAreaService.getCodeListByIds(areaListIds);
            user.setAreaIdList(areaListIds);
            user.setAreaCodeList(areaListCodes);
        }
        return user;
    }

    @Override
    public IPage queryPage(Page page, SysUserQueryBo bo, Long userId) {

        SysOrgEntity entity = sysOrgService.queryOneByOrgCode(bo.getOrgCode());

        String orgPath = "";
        if (entity != null) {
            orgPath = entity.getOrgPath();
        }

        IPage<SysUserQueryVo> sysUserVoNewIPage = this.baseMapper.listPage(page, bo, orgPath, userId, AppCoreConstants.UserType.WORKTICKET.getCode());

        //缓存获取锁定
        List<SysUserQueryVo> formatList = sysUserRedisService.getAllLockedUser(sysUserVoNewIPage.getRecords());

        sysUserVoNewIPage.setRecords(formatList);

        return sysUserVoNewIPage;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrgUser(Long belongOrgIds, List<Long> manageOrgIds, Long userId) {
        boolean ok = sysOrgUserService.saveOrUpdate(userId, Arrays.asList(belongOrgIds), Operate.NO.code());
        boolean ok2 = true;
        if (CollectionUtil.isNotEmpty(manageOrgIds)) {
            ok2 = sysOrgUserService.saveOrUpdate(userId, manageOrgIds, Operate.YES.code());
        }
        return ok && ok2;
    }

    /**
     * 根据用户账号获取导航菜单和可使用的权限标识
     */
    @Override
    public SysNavVo nav(String username) {
        SysUserEntity user = getUserByUsername(username);
        Assert.isNull(user,RespI18nConstants.COM1007.getCode());
        SysNavVo vo = new SysNavVo();
        List<SysMenuTreeVo> menuUseTree = sysMenuService.getMenuUseTree(user.getId());
        vo.setMenuList(menuUseTree);
        List<String> authorityList = this.baseMapper.queryAllAuthority(user.getId(),Operate.ENABLE.getCode(),Operate.DISLEADER.getCode());
        if(CollectionUtil.isNotEmpty(authorityList)){
            vo.setAuthorityList(authorityList);
        }
        return vo;
    }

    public boolean saveAreaUser(List<Long> areaIds, Long userId) {
        return sysAreaUserService.saveOrUpdate(userId, areaIds, Operate.YES.code());
    }

    public boolean saveRoleUser(List<Long> roleIds, Long userId) {
        return sysRoleUserService.saveOrUpdate(userId, roleIds);
    }

    public boolean saveUser(SysUserEntity user) {
        //密码加密
        String passWord = user.getPassword();
        user.setId(UUIDUtils.idNumber());
        user.setPassword(new BCryptPasswordEncoder().encode(passWord));
        user.setCreatedBy(SecurityContextUtil.getUsername());
        user.setCreationDate(new Date());
        return this.save(user);
    }

    public boolean updateUser(SysUserEntity user) {
        SysUserEntity userByOra = this.getUserById(user.getId());
        Assert.isNull(userByOra, RespI18nConstants.USER1003.getCode());
        user.setLastUpdatedBy(SecurityContextUtil.getUsername());
        user.setLastUpdateDate(new Date());

        return this.baseMapper.updateUser(user) > 0;
    }


    @Override
    public SysUserEntity getUserByUsername(String username) {
        QueryWrapper<SysUserEntity> query = new QueryWrapper<>();
        query.lambda().eq(SysUserEntity::getUsername, username);
        return this.baseMapper.selectOne(query);
    }

    @Override
    public SysUserEntity getUserById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<SysUserEntity> getUserByIds(List<Long> ids) {
        if (ids.size() < 1) {
            return null;
        }
        return this.baseMapper.selectBatchIds(ids);
    }

    /***
     * @Description 当前用户不能删除所属机构和他自己相同的数据
     * @param userIds
     * @return
     **/
    public void checkDel(List<Long> userIds) {
        //登录所属组织
        List<SysOrgUserEntity> notLeaderLogin = sysOrgUserService.queryListByUserId(SecurityContextUtil.getUserId(), Operate.NO.code());
        List<Long> Longcollect = notLeaderLogin.stream().map(s -> s.getOrgId()).collect(Collectors.toList());
        //被删除用户所属组织
        List<SysOrgUserEntity> notLeaderOperate = sysOrgUserService.queryListByUserId(userIds, Operate.NO.code());
        List<Long> delcollect = notLeaderOperate.stream().map(s -> s.getOrgId()).collect(Collectors.toList());
        List<Long> intersection = Longcollect.stream().filter(item -> delcollect.contains(item)).collect(toList());
        if (SecurityContextUtil.notAdminRole()) {
            Assert.state(intersection.size() > 0, RespI18nConstants.USER1001.getCode());
        }
        List<Long> list = sysRoleUserService.queryAllRoleIdByUserId(userIds);
        Long superId = PlatformAdminRoleIdUtil.getPlatformAdminRoleId();
        Assert.state(list.contains(superId), RespI18nConstants.USER1007.getCode());


    }

    //判断是否超管角色
    @Override
    public boolean isSuperUser(List<Long> userIds) {
        if (CollectionUtil.isEmpty(userIds)) {
            return false;
        }
        List<Long> list = sysRoleUserService.queryAllRoleIdByUserId(userIds);
        Long superId = PlatformAdminRoleIdUtil.getPlatformAdminRoleId();
        if (list.contains(superId)) {
            return true;
        } else {
            return false;
        }
    }


    //判断是否超管角色
    @Override
    public boolean isSuperUser(Long userId) {
        List<Long> userIds = Arrays.asList(userId);
        return isSuperUser(userIds);
    }

    /**
     * 校验格式
     */
    public void checkFormat(SysUserEntity user) {

        //电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        if (StringUtils.isNotBlank(user.getEmail())) {
            Matcher matcher = regex.matcher(user.getEmail());
            boolean isMatched = matcher.matches();
            Assert.state(!isMatched, RespI18nConstants.USER1008.getCode());
        }
        //电话
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcherTel = pattern.matcher(user.getMobileTelephone());
        boolean isTel = matcherTel.matches();
        Assert.state(!isTel, RespI18nConstants.USER1009.getCode());
        //启用状态
        if (!(user.getIsEnabled().equals(Operate.DISABLE.code()) || user.getIsEnabled().equals(Operate.ENABLE.code()))) {
            Assert.state(true, RespI18nConstants.ROLE1006.getCode());
        }
        //修改时不传账号不需要校验 ,调用之前已经调用了非空校验
        if (StringUtils.isNotBlank(user.getUsername())) {
            Pattern username = pattern.compile("^[0-9A-Za-z]{4,16}$");
            Matcher matcherUsername = username.matcher(user.getUsername());
            boolean isname = matcherUsername.matches();
            Assert.state(!isname, RespI18nConstants.USER1011.getCode());
        }

    }

    //校验非空
    public void checkNotNull(SysUserBo user) {
        Assert.isNull(user, RespI18nConstants.COM1005.getCode());
        Assert.isBlank(user.getName(), RespI18nConstants.USER1012.getCode());
        Assert.isBlank(user.getMobileTelephone(), RespI18nConstants.USER1013.getCode());
        Assert.isBlank(user.getIsEnabled(), RespI18nConstants.USER1014.getCode());
        Assert.state(CollectionUtil.isEmpty(user.getRoleIdList()), RespI18nConstants.USER1015.getCode());
        Assert.state(CollectionUtil.isEmpty(user.getAreaIdList()), RespI18nConstants.USER1016.getCode());
        Assert.state(user.getJoinOrgId() == null, RespI18nConstants.USER1017.getCode());
        Assert.state(CollectionUtil.isEmpty(user.getAreaIdList()), RespI18nConstants.USER1018.getCode());
    }

    //校验非空
    public void checkNotAddNull(SysUserBo user) {
        Assert.isBlank(user.getPassword(), RespI18nConstants.USER1019.getCode());
        Assert.isBlank(user.getUsername(), RespI18nConstants.USER1002.getCode());
    }

    //校验已存在
    public void checkAlreadyHave(SysUserEntity user) {
        SysUserEntity userEntity = getUserByUsername(user.getUsername());
        Assert.state(userEntity != null, RespI18nConstants.USER1020.getCode());
    }

    /**
     * 校验组织
     * 1.判断区域是不是管理区域里面
     * 2.判断机构是不是管理机构里面
     * 3.所属机构是不是属于管理区域下面的机构
     * 4.管理机构和所属机构相同或者是其下级机构
     */
    public void chechOrg(List<Long> manageAreaId, Long belongOrg, List<Long> manageOrg) {


        // 选的区域是当前用户的管理区域的子集
        List<SysAreaVo> areaList = sysAreaService.queryListByUserId(SecurityContextUtil.getUserId());
        List<Long> areaIds = areaList.stream().map(SysAreaVo::getId).distinct().collect(Collectors.toList());
        Assert.state(!areaIds.containsAll(manageAreaId), RespI18nConstants.USER1021.getCode());

        //1、所属机构是选中的管理区域下的机构
        List<SysOrgEntity> sysOrgEntities = sysOrgService.queryByAreaIds(manageAreaId, Operate.ENABLE.code());
        List<Long> mangeAreaOrgIds = sysOrgEntities.stream().map(SysOrgEntity::getId).distinct().collect(Collectors.toList());
        Assert.state(!mangeAreaOrgIds.contains(belongOrg), RespI18nConstants.USER1022.getCode());

        //2、管理机构和所属机构是同一机构或者是其下级机构，-》即管理是所属的子集
        List<SysOrgEntity> childIds = sysOrgService.childListByOrgId(belongOrg, Operate.ENABLE.code(), SecurityContextUtil.getUserId());
        List<Long> belongChildId = childIds.stream().map(SysOrgEntity::getId).distinct().collect(Collectors.toList());
        Assert.state(!belongChildId.containsAll(manageOrg), RespI18nConstants.USER1023.getCode());

    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//
//        SysUserEntity sysUserEntity = getUserByUsername(username);
//
//        if (sysUserEntity == null) {
//            throw new CheckedException(RespI18nConstants.COM1007.getCode());
//        }
//        if (sysUserEntity.getIsEnabled().equals(CommonConstants.enableFlag_0)) {
//            throw new CheckedException(RespI18nConstants.USER1024.getCode());
//        }
//
//        // 改成通过用户关联菜单在关联回来权限 // todo cjia：菜单状态需要提取公共变量
//        List<SysAuthorityEntity> sysAuthorityEntities = sysAuthorityService.getAuthorityByUserId(sysUserEntity.getId(), CommonConstants.enableFlag_1);
//
//        List<String> authorityList = new ArrayList<>();
//
//        authorityList.add(sysUserEntity.getUsername() + ":" + sysUserEntity.getId());
//
//        if (null != sysAuthorityEntities && 0 < sysAuthorityEntities.size()) {
//            for (SysAuthorityEntity sysAuthorityEntity : sysAuthorityEntities) {
//                authorityList.add(sysAuthorityEntity.getAppName() + ":" + sysAuthorityEntity.getAuthority());
//            }
//        }
//
//        String[] authoritys = authorityList.toArray(new String[authorityList.size()]);
//
//        GrgUser grgUser = new GrgUser(sysUserEntity.getId(),sysUserEntity.getUsername(), sysUserEntity.getPassword(), AuthorityUtils.createAuthorityList(authoritys));
//
//        SysUserInfoVo sysUserVo = getUserInfoByUsername(username);
//
//        grgUser.setUserId(sysUserEntity.getId());
//        grgUser.setName(sysUserEntity.getName());
//        grgUser.setAreaIdList(sysUserVo.getAreaIdList());
//        grgUser.setAreaCodeList(sysUserVo.getAreaCodeList());
//        grgUser.setJoinOrgId(sysUserVo.getJoinOrgId());
//        grgUser.setJoinOrgCode(sysUserVo.getJoinOrgCode());
//        grgUser.setJoinOrgName(sysUserVo.getJoinOrgName());
//        grgUser.setManageOrgIdList(sysUserVo.getManageOrgIdList());
//        grgUser.setManageOrgCodeList(sysUserVo.getManageOrgCodeList());
//        grgUser.setRoleIdList(sysUserVo.getRoleIdList());
//        grgUser.setEmail(sysUserEntity.getEmail());
//        grgUser.setPhone(sysUserEntity.getMobileTelephone());
//
//        return grgUser;
//    }


    /**
     * 根据用户ID查询启用状态中的可分配菜单/可使用的权限标识列表
     */
    @Override
    public List<SysAuthorityEntity> queryAuthorityListByUserId(Long userId, String isEnabled, String isLeader) {
        return this.baseMapper.queryAuthorityListByUserId(userId, isEnabled, isLeader);
    }

    public Long getAllUserCount(){
        return  this.count(new QueryWrapper<SysUserEntity>());
    }
}
