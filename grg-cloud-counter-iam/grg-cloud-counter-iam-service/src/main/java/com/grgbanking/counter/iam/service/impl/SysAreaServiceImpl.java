package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysAreaDao;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import com.grgbanking.counter.iam.entity.SysAreaUserEntity;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysAreaService;
import com.grgbanking.counter.iam.service.SysAreaUserService;
import com.grgbanking.counter.iam.service.SysOrgService;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.iam.service.redis.SysAreaRedisService;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.base.GrgUser;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysAreaBo;
import com.grgbanking.counter.iam.api.vo.SysAreaVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysAreaEntity> implements SysAreaService {

    @Autowired
    private SysAreaUserService sysAreaUserService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysAreaRedisService sysAreaRedisService;

    @Autowired
    private SysOrgService sysOrgService;

    @Override
    public IPage queryPage(Page page, String name, String areaCode, String isEnabled, String i18nCode) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<>();
        qw.lambda().like(StringUtils.isNotBlank(name), SysAreaEntity::getName, name)
                .like(StringUtils.isNotBlank(areaCode), SysAreaEntity::getAreaCode, areaCode)
                .eq(StringUtils.isNotBlank(isEnabled), SysAreaEntity::getIsEnabled, isEnabled)
                .eq(StringUtils.isNotBlank(i18nCode), SysAreaEntity::getI18nCode, i18nCode)
                .orderByDesc(SysAreaEntity::getAreaPath);
        return this.page(page, qw);
    }

    @Override
    public List<SysAreaVo> queryAllTree(String name, String areaCode, String isEnabled, String i18nCode) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().like(StringUtils.isNotBlank(name), SysAreaEntity::getName, name)
                .like(StringUtils.isNotBlank(areaCode), SysAreaEntity::getAreaCode, areaCode)
                .eq(StringUtils.isNotBlank(isEnabled), SysAreaEntity::getIsEnabled, isEnabled)
                .like(StringUtils.isNotBlank(i18nCode), SysAreaEntity::getI18nCode, i18nCode)
                .orderByDesc(SysAreaEntity::getAreaPath);
        List<SysAreaEntity> datas = this.list(qw);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas, vos, SysAreaVo.class);
        return this.buildTree(vos);
    }

    @Override
    public List<SysAreaVo> queryTreeListNotAdminRole(String name, String areaCode, String isEnabled, String i18nCode, Long userId) {
        List<SysAreaUserEntity> areaUserList = sysAreaUserService.getListByUserId(userId);
        List<Long> areaIdList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(areaUserList)) {
            for (SysAreaUserEntity sysAreaUserEntity : areaUserList) {
                areaIdList.add(sysAreaUserEntity.getAreaId());
            }
        }


        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().like(StringUtils.isNotBlank(name), SysAreaEntity::getName, name)
                .like(StringUtils.isNotBlank(areaCode), SysAreaEntity::getAreaCode, areaCode)
                .eq(StringUtils.isNotBlank(isEnabled), SysAreaEntity::getIsEnabled, isEnabled)
                .like(StringUtils.isNotBlank(i18nCode), SysAreaEntity::getI18nCode, i18nCode)
                .orderByDesc(SysAreaEntity::getAreaPath);
        if (CollectionUtil.isNotEmpty(areaIdList)) {
            qw.lambda().in(SysAreaEntity::getId, areaIdList);
        }
        List<SysAreaEntity> datas = this.list(qw);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas, vos, SysAreaVo.class);
        return this.buildTree(vos);
    }

    @Override
    public List<SysAreaVo> queryTreeByUsername(String username) {
        SysUserEntity user = sysUserService.getUserByUsername(username);
        List<SysAreaVo> redisList = sysAreaRedisService.getListByName(username);
        if (redisList != null && redisList.size() > 0) {
            return this.buildTree(redisList);
        }

        List<SysAreaUserEntity> areaUserList = sysAreaUserService.getListByUserId(user.getId());
        List<Long> areaIdList = new ArrayList<>();
        for (SysAreaUserEntity sysAreaUserEntity : areaUserList) {
            SysAreaEntity areaEntity = this.getById(sysAreaUserEntity.getAreaId());
            if (Operate.ENABLE.code().equals(areaEntity.getIsEnabled())) {
                areaIdList.add(sysAreaUserEntity.getAreaId());
            }
        }
        List<SysAreaEntity> datas = new ArrayList<>();
        if (areaIdList.size() > 0) {
            QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
            qw.lambda().in(SysAreaEntity::getId, areaIdList);
            datas = this.list(qw);
        }

        //List<SysAreaEntity> datas = this.baseMapper.getListByUsername(username);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas, vos, SysAreaVo.class);
        sysAreaRedisService.saveAreaRedis(username, vos);
        return this.buildTree(vos);
    }

    public List<SysAreaVo> queryAreaForAdminTree(String enable) {
        List<SysAreaVo> vos = queryAreaForAdmin(enable);
        return this.buildTree(vos);
    }

    public List<SysAreaVo> queryAreaForAdmin(String enable) {
        QueryWrapper<SysAreaEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysAreaEntity::getIsEnabled, enable);
        List<SysAreaEntity> sysAreaEntities = this.baseMapper.selectList(ew);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(sysAreaEntities, vos, SysAreaVo.class);
        return vos;
    }

    public List<SysAreaVo> queryAreaForAdminEnabled() {
        return queryAreaForAdminTree(Operate.ENABLE.code());
    }

    @Override
    public List<String> getCodeListByIds(List<Long> areaListIds) {
        QueryWrapper<SysAreaEntity> ew = new QueryWrapper<>();
        ew.lambda().select(SysAreaEntity::getAreaCode)
                .in(SysAreaEntity::getId, areaListIds);
        List<SysAreaEntity> sysAreaEntities = this.baseMapper.selectList(ew);
        List<String> areaCodes = sysAreaEntities.stream().map(u -> u.getAreaCode()).collect(toList());
        return areaCodes;
    }

    @Override
    public List<SysAreaVo> queryListByUserId(Long userId) {
        if (sysUserService.isSuperUser(userId)) {
            List<SysAreaVo> sysAreaVos = queryAreaForAdmin(Operate.ENABLE.code());
            return sysAreaVos;
        }

        SysUserEntity user = sysUserService.getById(userId);
        List<SysAreaUserEntity> areaUserList = sysAreaUserService.getListByUserId(user.getId());
        List<Long> areaIdList = new ArrayList<>();
        for (SysAreaUserEntity sysAreaUserEntity : areaUserList) {
            areaIdList.add(sysAreaUserEntity.getAreaId());
        }
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().in(SysAreaEntity::getId, areaIdList);
        List<SysAreaEntity> datas = this.list(qw);

        //List<SysAreaEntity> datas = this.baseMapper.getListByUserId(userId);

        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas,vos,SysAreaVo.class);
        return vos;
    }


    @Override
    public Long countByName(String name, Long id) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        if (StringUtils.isNotBlank(name)) {
            qw.lambda().eq(SysAreaEntity::getName, name);
        }
        if (null != id) {
            qw.lambda().ne(SysAreaEntity::getId, id);
        }
        return this.count(qw);
    }

    @Override
    public Long countByAreaCode(String areaCode, Long id) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        if (StringUtils.isNotBlank(areaCode)) {
            qw.lambda().eq(SysAreaEntity::getAreaCode, areaCode);
        }
        if (null != id) {
            qw.lambda().ne(SysAreaEntity::getId, id);
        }
        return this.count(qw);
    }

    @Override
    public Long countByI18nCode(String I18nCode, Long id) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        if (StringUtils.isBlank(I18nCode)) {
            return 0L;
        }
        qw.lambda().eq(SysAreaEntity::getI18nCode, I18nCode);
        if (null != id) {
            qw.lambda().ne(SysAreaEntity::getId, id);
        }
        return this.count(qw);
    }

    @Override
    public Long countByAreaPath(String areaPath, Long id) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        if (StringUtils.isNotBlank(areaPath)) {
            qw.lambda().eq(SysAreaEntity::getAreaPath, areaPath);
        }
        if (null != id) {
            qw.lambda().ne(SysAreaEntity::getId, id);
        }
        return this.count(qw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdate(SysAreaBo sysAreaBo) {
        this.checkSaveAndUpdateData(sysAreaBo);
        GrgUser grgUser = SecurityContextUtil.getUserInfo();
        if (null != sysAreaBo.getId()) {//修改
            SysAreaEntity oldEntity = baseMapper.selectById(sysAreaBo.getId());
            //如果上级区域有变化
            if (!oldEntity.getParentCode().equals(sysAreaBo.getParentCode())) {
                SysAreaEntity parentArea = this.getByAreaCode(sysAreaBo.getParentCode());
                String areaPath_new = this.buildchildAreaPath(parentArea.getAreaPath());
                this.doChangeChildrenEntity(oldEntity.getAreaPath(), areaPath_new);
                oldEntity.setParentCode(sysAreaBo.getParentCode());
                oldEntity.setAreaPath(areaPath_new);
            }
            oldEntity.setName(sysAreaBo.getName());
            oldEntity.setI18nCode(sysAreaBo.getI18nCode());
            oldEntity.setDescription(sysAreaBo.getDescription());
            oldEntity.setLastUpdateDate(new Date());
            oldEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
            baseMapper.updateById(oldEntity);
            // 清空缓存
            sysAreaRedisService.closeAreaRedis();
        } else {//新增
            SysAreaEntity entity = new SysAreaEntity();
            BeanUtils.copyProperties(sysAreaBo, entity);
            if (null == sysAreaBo.getParentCode()) {
                //上级区域为空，PARENT_CODE为0
                entity.setParentCode("0");
                //上级区域为空：AREA_PATH为AREA_CODE
                entity.setAreaPath(entity.getAreaCode());
            } else {
                //上级区域不为空：AREA_PATH为上级区域的AREA_PATH+_+层级数
                SysAreaEntity parentSysArea = this.getByAreaCode(entity.getParentCode());
                String regPath = this.buildchildAreaPath(parentSysArea.getAreaPath());
                entity.setAreaPath(regPath);
            }
            entity.setId(UUIDUtils.idNumber());
            entity.setCreatedBy(SecurityContextUtil.getUsername());
            entity.setCreationDate(new Date());
            baseMapper.insert(entity);
            sysAreaUserService.saveByArea(entity);
            //新增时候清该用户的缓存
            sysAreaRedisService.closeAreaRedisByUserId(grgUser.getUserId().toString());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByListIds(List<Long> ids) {
        for (Long id : ids) {
            SysAreaEntity areaEntity = this.getById(id);
            QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
            qw.lambda().eq(SysAreaEntity::getParentCode, areaEntity.getAreaCode());
            Long entityCount = this.count(qw);
            String msg = null;
            List<SysAreaUserEntity> sysAreaUserList = new ArrayList<>();
            if (entityCount > 0) {//根据选中表格主键，查询表SYS_ARE是否有子级。如果有，则提示“无法删除存在子节点的区域！”
                msg = "该%s包含子节点区域，不能删除！";
            } else {//查询表SYS_ARE_USER中是否有关联用户。如果有，则提示“无法删除已被用户使用的区域！”
                sysAreaUserList = sysAreaUserService.getListByAreaId(id, SecurityContextUtil.getUserId());
                if (sysAreaUserList.size() > 0) {
                    //msg = "无法删除已被用户使用的区域！";
                    msg = RespI18nConstants.AREA1001.getCode();
                }
            }
            if (StringUtils.isNotBlank(msg)) {
                String companyName = "";
                if (areaEntity != null) {
                    companyName = areaEntity.getName();
                }
                throw new CheckedException(String.format(msg, companyName));
            } else {
                baseMapper.deleteById(id);
                sysAreaUserService.deleteByAreaId(id);
            }
        }
        return true;
    }

    @Override
    public boolean enableByListIds(List<Long> ids) {
        ids.forEach(id -> {
            String msg = null;
            SysAreaEntity areaEntity = this.getById(id);
            //启用时，父节点也得是启用状态
            if (!"0".equals(areaEntity.getParentCode())) {
                QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
                qw.lambda().eq(SysAreaEntity::getAreaCode, areaEntity.getParentCode());
                SysAreaEntity parentEntity = this.baseMapper.selectOne(qw);
                if (Operate.DISABLE.code().equals(parentEntity.getIsEnabled())) {
                    // msg = "父区域未启用，不可操作！";
                    msg = RespI18nConstants.AREA1004.getCode();
                }
            }
            if (StringUtils.isNotBlank(msg)) {
                throw new CheckedException(msg);
            }
            areaEntity.setIsEnabled(Operate.ENABLE.getCode());
            baseMapper.updateById(areaEntity);
            sysAreaRedisService.closeAreaRedis();
        });
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean enable(Long id) {
        SysAreaEntity areaEntity = this.getById(id);
        String msg = null;
        if (!"0".equals(areaEntity.getParentCode())) {
            QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
            qw.lambda().eq(SysAreaEntity::getAreaCode, areaEntity.getParentCode());
            SysAreaEntity parentEntity = this.baseMapper.selectOne(qw);
            if (Operate.DISABLE.code().equals(parentEntity.getIsEnabled())) {
                //msg = "父区域未启用，不可操作！";
                msg = RespI18nConstants.AREA1004.getCode();
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            throw new CheckedException(msg);
        }
        areaEntity.setIsEnabled(Operate.ENABLE.getCode());
        baseMapper.updateById(areaEntity);
        sysAreaRedisService.closeAreaRedis();
        return true;
    }

    @Override
    public boolean disableByListIds(List<Long> ids) {
        ids.forEach(id -> {
            SysAreaEntity areaEntity = this.getById(id);
            //禁用时，子节点也得是禁用状态
            QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
            qw.lambda().eq(SysAreaEntity::getParentCode, areaEntity.getAreaCode());
            qw.lambda().eq(SysAreaEntity::getIsEnabled, Operate.ENABLE.getCode());
            Long count = this.count(qw);
            if (count > 0) {
                //String msg = "子区域未禁用，不可操作！";
                String msg = RespI18nConstants.AREA1005.getCode();
                throw new CheckedException(msg);
            }
            areaEntity.setIsEnabled(Operate.DISABLE.getCode());
            baseMapper.updateById(areaEntity);
        });
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean disable(Long id) {
        SysAreaEntity areaEntity = this.getById(id);
        String msg = null;
        /*List<SysAreaUserEntity> areaUserList = sysAreaUserService.getListByAreaId(id,SecurityContextUtil.getUserId());
        if (null != areaUserList && areaUserList.size() > 0) {
            msg = "该区域有关联启用用户，不可禁用！";
            throw new CheckedException(msg);
        }*/
        //禁用时，子节点也得是禁用状态
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().eq(SysAreaEntity::getParentCode, areaEntity.getAreaCode());
        qw.lambda().eq(SysAreaEntity::getIsEnabled, Operate.ENABLE.getCode());
        Long count = this.count(qw);
        if (count > 0) {
            throw new CheckedException(RespI18nConstants.AREA1005.getCode());
        }
        //
        List<SysOrgEntity> orgList = sysOrgService.getListByAreaId(areaEntity.getId());
        if (orgList.size() > 0) {
            msg = RespI18nConstants.AREA1012.getCode();
            throw new CheckedException(msg);
        }
        areaEntity.setIsEnabled(Operate.DISABLE.getCode());
        baseMapper.updateById(areaEntity);
        sysAreaRedisService.closeAreaRedis();
        return true;
    }

    @Override
    public SysAreaEntity getByAreaCode(String areaCode) {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().eq(SysAreaEntity::getAreaCode, areaCode);
        SysAreaEntity entity = baseMapper.selectOne(qw);
        return entity;
    }

    @Override
    public List<SysAreaVo> queryChildByAreaCode(String areaCode) {
        SysAreaEntity area = this.getByAreaCode(areaCode);
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().likeRight(SysAreaEntity::getAreaPath, area.getAreaPath());
        List<SysAreaEntity> datas = this.list(qw);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas,vos,SysAreaVo.class);
        return vos;
    }

    @Override
    public List<SysAreaVo> queryAllList() {
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        List<SysAreaEntity> datas = this.list(qw);
        List<SysAreaVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(datas,vos,SysAreaVo.class);
        return vos;
    }

    @Override
    public void checkSaveAndUpdateData(SysAreaBo sysAreaBo) {
        //区域编码、区域名称、国际化编码、区域结构path在表SYS_AREA不允许重复；
        Long count = this.countByName(sysAreaBo.getName(), sysAreaBo.getId());//区域名称不能重复
        Assert.state(count > 0, RespI18nConstants.AREA1006.getCode());
        count = this.countByAreaCode(sysAreaBo.getAreaCode(), sysAreaBo.getId());//区域编码不能重复
        Assert.state(count > 0, RespI18nConstants.AREA1007.getCode());
        count = this.countByI18nCode(sysAreaBo.getI18nCode(), sysAreaBo.getId());//国际化编码不能重复
        Assert.state(count > 0, RespI18nConstants.AREA1008.getCode());
        if (null != sysAreaBo.getAreaPath()) {
            count = this.countByAreaPath(sysAreaBo.getAreaPath(), sysAreaBo.getId());//区域结构path不能重复
            Assert.state(count > 0, RespI18nConstants.AREA1009.getCode());
        }
    }

    /**
     * 改动上级区域，子区域也跟着改
     */
    @Transactional(rollbackFor = Exception.class)
    public void doChangeChildrenEntity(String areaPath_old, String areaPath_new) {
        areaPath_old = areaPath_old + "_";
        QueryWrapper<SysAreaEntity> qw = new QueryWrapper<SysAreaEntity>();
        qw.lambda().likeRight(SysAreaEntity::getAreaPath, areaPath_old);
        List<SysAreaEntity> datas = this.list(qw);
        for (SysAreaEntity data : datas) {
            String path = areaPath_new + data.getAreaPath().substring(areaPath_old.length() - 1);
            data.setAreaPath(path);
            baseMapper.updateById(data);
        }

    }

    /**
     * 根据父节点AREA_PATH计算子节点AREA_PATH
     *
     * @param parentAreaPath
     * @return
     */
    public String buildchildAreaPath(String parentAreaPath) {
        String parentChild = parentAreaPath + "_____";
        int value = 1001;
        String maxId = null;
        try {
            maxId = baseMapper.getMaxAreaPath(parentChild);
            if (maxId != null) {
                int lastFlagIndex = maxId.lastIndexOf("_") + 1;
                String valueS = maxId.substring(lastFlagIndex);
                value = Integer.valueOf(valueS) + 1;
            }
        } catch (Exception e) {
            log.error("AreaDaoImpl createAreaId error !", e);
        }
        String result = parentAreaPath + "_" + value;
        return result;
    }

    /**
     * 根据传入区域获取区域树
     */
    @Transactional(readOnly = true)
    public List<SysAreaVo> buildTree(List<SysAreaVo> treeNodes) {
        List<SysAreaVo> result = new ArrayList<SysAreaVo>();
        Map<String, SysAreaVo> mapAll = new LinkedHashMap<String, SysAreaVo>();
        for (SysAreaVo node : treeNodes) {
            mapAll.put(node.getAreaCode(), node);
        }
        Set<Map.Entry<String, SysAreaVo>> entrySet = mapAll.entrySet();
        for (Map.Entry<String, SysAreaVo> entry : entrySet) {
            String pid = entry.getValue().getParentCode();
            SysAreaVo parentOrg = mapAll.get(pid);
            if (parentOrg == null) {
                result.add(entry.getValue());
            } else {
                List<SysAreaVo> children = parentOrg.getChildren() == null ? new ArrayList<SysAreaVo>() : parentOrg.getChildren();
                children.add(entry.getValue());
                parentOrg.setChildren(children);
            }
        }
        return result;
    }

}