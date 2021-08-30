package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysOrgDao;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import com.grgbanking.counter.iam.entity.SysOrgUserEntity;
import com.grgbanking.counter.iam.service.SysOrgService;
import com.grgbanking.counter.iam.service.SysOrgUserService;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryBo;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryListBo;
import com.grgbanking.counter.iam.api.vo.SysOrgOneVo;
import com.grgbanking.counter.iam.api.vo.SysOrgVo;
import com.grgbanking.counter.iam.service.redis.SysOrgRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 机构service
 *
 * @author lggui1
 * @date 2021年1月6日
 */
@Slf4j
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Autowired
    private SysOrgUserService sysOrgUserService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean save(SysOrgEntity entity, Long userId) {
        checkNotNull(entity);
        checkOther(entity);
        alreadyField(entity);
        //设置orgpath
        formPath(entity);
        entity.setId(UUIDUtils.idNumber());
        entity.setCreationDate(new Date());
        entity.setCreatedBy(SecurityContextUtil.getUsername());
        int num = this.baseMapper.insert(entity);
        //创建人也有对创建的机构的管理权限所以自动报存到关系表
        saveSysOrgUser(userId, entity.getId());
        return num > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysOrgUser(Long userId, Long orgId) {
        SysOrgUserEntity u = new SysOrgUserEntity();
        u.setUserId(userId);
        u.setOrgId(orgId);
        u.setIsLeader("1");
        u.setCreatedBy(SecurityContextUtil.getUsername());
        return sysOrgUserService.save(u);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysOrgEntity entity, Long userId) {
        checkNotNull(entity);
        checkOther(entity);
        alreadyField(entity);
        entity.setLastUpdateDate(new Date());
        entity.setLastUpdatedBy(SecurityContextUtil.getUsername());
        SysOrgEntity itselfSysOrg = this.baseMapper.selectById(entity.getId());
        //父级没有变的情况下不用更新path
        if (itselfSysOrg.getParentCode().equals(entity.getParentCode())) {
            //防止外部传入
            entity.setOrgPath(itselfSysOrg.getOrgPath());
            int num = this.baseMapper.updateById(entity);
            return num > 0;
        }
        formPath(entity);
        int num = this.baseMapper.updateById(entity);
        //更新子级
        updateChildrenPath(itselfSysOrg.getOrgPath(), entity.getOrgPath());
        return num > 0;
    }

    public Integer updateStatus(Long id, String status) {
        /**
         * 1、停用:
         * 1.1父机构停用，必须要它所有的子机构都停用了，才能停用；
         * 1.2停用时，如果该机构有关联启用用户，则不允许停用；
         * 1.3信息项处理：isEnable=0停用。
         * 2、启用：
         * 2.1子机构启用，必须要它的所有父机构启用了，它才能启用；
         * 2.2信息项处理：isEnable=1启用。
         */
        SysOrgEntity entity = this.baseMapper.selectById(id);
        Assert.isNull(entity, RespI18nConstants.COM1004.getCode());
        if (status.equals(Operate.DISABLE.code())) {
            return disable(entity);
        } else {
            return enable(entity);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Resp delete(List list) {
        checkDel(list);
        this.baseMapper.deleteBatchIds(list);
        return Resp.ok();
    }

    public Integer enable(SysOrgEntity entity) {
        SysOrgEntity parentOrg = this.baseMapper.selectOne(new QueryWrapper<SysOrgEntity>().lambda().eq(SysOrgEntity::getOrgCode, entity.getParentCode()));
        Assert.state(parentOrg.getIsEnabled().equals(Operate.DISABLE.code()), RespI18nConstants.ORG1002.getCode());
        entity.setIsEnabled(Operate.ENABLE.code());
        return this.baseMapper.updateById(entity);
    }

    public Integer disable(SysOrgEntity entity) {
        checkChildDisable(entity.getId());
        entity.setIsEnabled(Operate.DISABLE.code());
        return this.baseMapper.updateById(entity);
    }

    public void checkDel(List<Long> id) {
        Assert.state(id.size() < 1, RespI18nConstants.COM1005.getCode());
        Assert.state(checkHaveEntity(id), RespI18nConstants.ORG1003.getCode());
        for (int i = 0; i < id.size(); i++) {
            checkChil(id.get(i), null, RespI18nConstants.ORG1004.getCode());
            checkDelOrgUser(id.get(i), null, RespI18nConstants.ORG1001.getCode());
        }
    }

    @Override
    public List<SysOrgVo> listTree(SysOrgQueryListBo org, Long userId) {
        List<SysOrgVo> vos = manageOrgList(org, userId);
        if (CollectionUtil.isEmpty(vos)) {
            return new ArrayList<>();
        }
        List<SysOrgVo> sysOrgVos = SysOrgRedisService.buildTree(vos);
        return sysOrgVos;
    }

    @Override
    public List<SysOrgVo> manageOrgList(SysOrgQueryListBo org, Long userId) {
        List<SysOrgEntity> sysOrgEntities = querByOrglist(org, userId, Operate.LEADER.code());
        if (CollectionUtil.isEmpty(sysOrgEntities)) {
            return new ArrayList<>();
        }
        List<SysOrgVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(sysOrgEntities,vos, SysOrgVo.class);
        return vos;
    }

    @Override
    public List<SysOrgEntity> childListByOrgId(Long orgId, String status, Long userId) {
        SysOrgEntity sysOrgEntity = queryOneById(orgId);
        if (sysOrgEntity == null) {
            return null;
        }
        //超级管理员单独查询
        if (sysUserService.isSuperUser(userId)) {
            return this.baseMapper.childListForAdmin(sysOrgEntity.getOrgPath(), status);
        }
        List<SysOrgEntity> sysOrgEntities = this.baseMapper.childList(sysOrgEntity.getOrgPath(), status, userId);
        return sysOrgEntities;
    }

    @Override
    public List<SysOrgVo> manageOrgListEnable(Long userId) {
        SysOrgQueryListBo entity = new SysOrgQueryListBo();
        entity.setIsEnabled(Operate.ENABLE.code());
        List<SysOrgVo> vos = manageOrgList(entity, userId);
        return vos;
    }

    @Override
    public List<SysOrgVo> manageOrgTreeEnable(Long userId) {
        List<SysOrgVo> vos = manageOrgListEnable(userId);
        List<SysOrgVo> sysOrgVos = SysOrgRedisService.buildTree(vos);
        return sysOrgVos;
    }

    @Override
    public List<SysOrgVo> manageOrgTreeEnable(Long userId, List<Long> areaIdList) {
        SysOrgQueryListBo bo = new SysOrgQueryListBo();
        bo.setIsEnabled(Operate.ENABLE.code());
        bo.setAreaIdList(areaIdList);
        List<SysOrgEntity> sysOrgEntities = querByOrglist(bo, userId, Operate.LEADER.code());
        List<SysOrgVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(sysOrgEntities,vos, SysOrgVo.class);
        List<SysOrgVo> sysOrgVos = SysOrgRedisService.buildTree(vos);
        return sysOrgVos;
    }

    @Override
    public SysOrgEntity queryOneById(Long id) {
        List<SysOrgEntity> sysOrgEntities = quertListById(Arrays.asList(id));
        if (sysOrgEntities == null || sysOrgEntities.size() < 1) {
            return null;
        }
        return sysOrgEntities.get(0);
    }

    @Override
    public SysOrgOneVo queryForUpdateById(Long id) {
        List<SysOrgEntity> sysOrgEntities = quertListById(Arrays.asList(id));
        if (sysOrgEntities == null || sysOrgEntities.size() < 1) {
            return null;
        }
        SysOrgOneVo vo = new SysOrgOneVo();
        BeanUtils.copyProperties(sysOrgEntities.get(0),vo);
        SysOrgEntity entity = queryOneByOrgCode(vo.getParentCode());
        if (entity != null) {
            vo.setParentName(entity.getName());
        }
        return vo;
    }


    /**
     * 通过orgcode查询机构
     *
     * @param orgCode
     * @return
     */
    public SysOrgEntity queryByOrgCode(String orgCode) {
        QueryWrapper<SysOrgEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysOrgEntity::getOrgCode, orgCode);
        return this.baseMapper.selectOne(ew);
    }

    @Override
    public SysOrgEntity queryOneByOrgCode(String orgCode) {
        SysOrgEntity sysOrgEntity = this.baseMapper.selectOne(new QueryWrapper<SysOrgEntity>()
                .lambda().eq(SysOrgEntity::getOrgCode, orgCode));
        return sysOrgEntity;
    }

    @Override
    public List<SysOrgEntity> getListByAreaId(Long areaId) {
        QueryWrapper<SysOrgEntity> query = new QueryWrapper<>();
        query.lambda().eq(SysOrgEntity::getAreaId, areaId);
        return this.baseMapper.selectList(query);
    }

    @Override
    public List<SysOrgEntity> querByOrglist(SysOrgQueryListBo condition, Long userId, String isLeader) {

        return this.baseMapper.querByOrglist(condition, userId, isLeader);
    }

    public List<SysOrgEntity> queryByAreaIds(List<Long> AreaIds, String status) {
        QueryWrapper<SysOrgEntity> query = new QueryWrapper<>();
        query.lambda().in(SysOrgEntity::getAreaId, AreaIds).eq(StringUtils.isNotBlank(status), SysOrgEntity::getIsEnabled, status);
        return this.baseMapper.selectList(query);
    }

    /**
     * 判断实体是否存在
     * 通过传入的ID数据和cout比较
     */
    public boolean checkHaveEntity(List<Long> ids) {
        List<SysOrgEntity> list = quertListById(ids);
        int count = list == null ? 0 : list.size();
        return count == ids.size() ? false : true;
    }

    /**
     * 根据机构ID查询机构列表
     *
     * @param ids
     * @return
     */
    public List<SysOrgEntity> quertListById(List<Long> ids) {
        QueryWrapper<SysOrgEntity> query = new QueryWrapper<>();
        query.lambda().in(SysOrgEntity::getId, ids);
        return this.baseMapper.selectList(query);
    }

    /**
     * 判断能不能停用
     *
     * @param id
     */
    public void checkChildDisable(Long id) {
        checkChil(id, Operate.ENABLE.code(), RespI18nConstants.ORG1005.getCode());
        checkDisableOrgUser(id, null, RespI18nConstants.ORG1006.getCode());
    }

    /**
     * 判断是否存在关联用户
     * 删除是有关联用户不给删除
     * 停用则是用户的所属机构所有是当前机构不给停用
     *
     * @param id     机构iD
     * @param status 用户的启用状态，可null查询所有
     * @param msg    提示信息
     */
    public void checkDelOrgUser(Long id, String status, String msg) {
        String tip = msg != null ? msg : RespI18nConstants.COM1006.getCode();
        List<SysOrgUserEntity> sysOrgUserEntities = sysOrgUserService.queryOrgUserForOrgId(id, status);
        Assert.state(sysOrgUserEntities.size() > 0, tip);
    }

    public void checkDisableOrgUser(Long id, String status, String msg) {
        String tip = msg != null ? msg : RespI18nConstants.COM1006.getCode();
        List<SysOrgUserEntity> sysOrgUserEntities = sysOrgUserService.queryOrgUserForOrgId(id, status);
        List<String> leadCollect = sysOrgUserEntities.stream().map(s -> s.getIsLeader()).collect(toList());
        //机构是所属机构不给停用
        Assert.state(leadCollect.contains(Operate.NO.code()), tip);
    }

    /**
     * 判断是否存在子级存在则抛出提醒到前端
     *
     * @param id     机构iD
     * @param status 子级的启用状态，可null查询所有
     * @param msg    提示信息
     */
    public void checkChil(Long id, String status, String msg) {
        String tip = msg != null ? msg : RespI18nConstants.COM1006.getCode();
        List<SysOrgEntity> sysOrgEntities = listChildById(id, status);
        Assert.state(sysOrgEntities.size() > 0, tip);
    }

    /**
     * 通过id查询他的子级，不包含本身
     *
     * @param id
     * @return
     */
    public List<SysOrgEntity> listChildById(Long id, String status) {
        SysOrgEntity sysOrgEntity = this.baseMapper.selectById(id);
        List<SysOrgEntity> sysOrgEntities = this.baseMapper.selectList(
                new QueryWrapper<SysOrgEntity>().lambda().likeRight(SysOrgEntity::getOrgPath, sysOrgEntity.getOrgPath())
                        .eq(StringUtils.isNotBlank(status), SysOrgEntity::getIsEnabled, status)
                        .ne(SysOrgEntity::getId, id));
        return sysOrgEntities;
    }

    public void formPath(SysOrgEntity entity) {
        // 组织orgpath 两种情况一种是，父级是0paths是他本身orgcode,第二种是则是查询它的下级数据加
        if (StringUtils.isBlank(entity.getParentCode())) {
            entity.setParentCode(Operate.TOP_ORG_PARENT.code());
            entity.setOrgPath(entity.getOrgCode());
        } else {
            //查询它的父级path
            SysOrgEntity sysOrgEntity = queryPathByCode(entity.getParentCode());
            String s = makePath(sysOrgEntity.getOrgPath());
            entity.setOrgPath(s);
        }
    }

    public SysOrgEntity queryPathByCode(String orgcode) {
        QueryWrapper<SysOrgEntity> mp = new QueryWrapper<>();
        mp.lambda().eq(SysOrgEntity::getOrgCode, orgcode);
        return this.baseMapper.selectOne(mp);
    }

    public void updateChildrenPath(String oldPath, String newPath) {
        /**
         * 修改父级的path时子级也要修改
         */
        this.baseMapper.updateChildPath(oldPath, newPath);
    }

    /**
     * 必填校验
     */
    @Override
    public void checkNotNull(SysOrgEntity entity) {
        Assert.isBlank(entity.getOrgCode(), RespI18nConstants.ORG1007.getCode());
        Assert.isBlank(entity.getName(), RespI18nConstants.ORG1011.getCode());
        Assert.isBlank(entity.getIsEnabled(), RespI18nConstants.ORG1008.getCode());
        Assert.isBlank(entity.getI18nCode(), RespI18nConstants.ORG1009.getCode());
        Assert.isNull(entity.getAreaId(), RespI18nConstants.ORG1010.getCode());
    }

    public void checkOther(SysOrgEntity entity) {
        //校验启用状态是0或者1
        Assert.state(!(entity.getIsEnabled().equals(Operate.DISABLE.code()) || entity.getIsEnabled().equals(Operate.ENABLE.code())), "启用状态只是0或1");
        Assert.state(entity.getOrgCode().equals(Operate.TOP_ORG_PARENT.code()), RespI18nConstants.ORG1012.getCode());

    }

    public void alreadyField(SysOrgEntity entity) {
        /**
         * 机构编码不允许为0且不允许重复、机构名称不允许重复、机构全称不允许重复、国际化编码不允许重复
         * 金融机构编码不为空时不能重复
         */
        QueryWrapper<SysOrgEntity> mapper = new QueryWrapper<>();
        mapper.lambda().eq(SysOrgEntity::getI18nCode, entity.getI18nCode()).or()
                .eq(SysOrgEntity::getOrgCode, entity.getOrgCode()).or()
                .eq(SysOrgEntity::getName, entity.getName()).or()
                .eq(StringUtils.isNotBlank(entity.getFullname()), SysOrgEntity::getFullname, entity.getFullname()).or()
                .eq(StringUtils.isNotBlank(entity.getFinCode()), SysOrgEntity::getFinCode, entity.getFinCode());
        List<SysOrgEntity> sysOrgEntities = this.baseMapper.selectList(mapper);
        String msg = null;
        for (SysOrgEntity e : sysOrgEntities) {
            //等于本身时跳过本次循环
            if (entity.getId() != null && entity.getId().equals(e.getId())) {
                continue;
            }
            if (e.getOrgCode().equals(entity.getOrgCode())) {
                msg = FormatStr(msg, RespI18nConstants.ORG1013.getCode(), ",");
            }
            if (e.getName().equals(entity.getName())) {
                msg = FormatStr(msg, RespI18nConstants.ORG1014.getCode(), ",");
            }
            if (e.getI18nCode().equals(entity.getI18nCode())) {
                msg = FormatStr(msg, RespI18nConstants.ORG1015.getCode(), ",");
            }
            if (StringUtils.isNotBlank(entity.getFullname()) && entity.getFullname().equals(e.getFullname())) {
                msg = FormatStr(msg, RespI18nConstants.ORG1016.getCode(), ",");
            }
            //有一个重复都可以返回结果
            Assert.state(StringUtils.isNotEmpty(msg), msg);
        }
    }

    //对字符串拼接符号
    public String FormatStr(String source, String newmsg, String separator) {
        if (StringUtils.isNotBlank(source)) {
            return source + separator + newmsg;
        }
        return newmsg;
    }

    /**
     * 根据父节点rPath计算子节点Path
     *
     * @param parentPath
     * @return
     */
    public String makePath(String parentPath) {
        String parentChild = parentPath + "_____";
        int value = 1;
        try {
            String maxId = baseMapper.getOrgPathsByParent(parentChild);
            if (maxId != null) {
                int lastFlagIndex = maxId.lastIndexOf("_") + 1;
                String valueS = maxId.substring(lastFlagIndex);
                value = Integer.valueOf(valueS) + 1;
            } else {
                return parentPath + "_1001";
            }
        } catch (Exception e) {
            log.error("RegionDaoImpl createRegionId error !", e);
        }
        String tmp = new DecimalFormat("0000").format(value);
        String result = parentPath + "_" + tmp;
        return result;
    }

    @Override
    public List<String> getCodeListByIds(List<Long> manageOrgIds) {
        QueryWrapper<SysOrgEntity> ew = new QueryWrapper<>();
        ew.lambda().select(SysOrgEntity::getOrgCode)
                .in(SysOrgEntity::getId, manageOrgIds);
        List<SysOrgEntity> sysOrgEntities = this.baseMapper.selectList(ew);
        List<String> orgCodes = sysOrgEntities.stream().map(u -> u.getOrgCode()).collect(toList());
        return orgCodes;
    }

    public List<String> queryOrgPathsByUserId(Long userId) {
        if (userId != null) {
            return baseMapper.queryOrgPathsByUserId(userId, Operate.NO.code());
        } else {
            return null;
        }
    }
}