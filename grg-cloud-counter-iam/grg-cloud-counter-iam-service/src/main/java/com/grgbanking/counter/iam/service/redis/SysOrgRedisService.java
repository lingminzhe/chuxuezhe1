package com.grgbanking.counter.iam.service.redis;

import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.api.bo.SysOrgBo;
import com.grgbanking.counter.iam.api.vo.SysOrgVo;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author lggui1
 * @Date 2021/1/11
 * @Description 机构组织公共处理:主要处理缓存
 **/
@Component
public class SysOrgRedisService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询用户管理机构树（启用状态的）
     * com.grgbanking.platform.system.service.impl.SysOrgServiceImpl#manageOrgListEnable(java.lang.Long)
     *
     * @param userId
     * @param vo
     */
    public void saveCacheListTree(Long userId, List<SysOrgVo> vo) {
        SysUserEntity user = sysUserService.getById(userId);
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_ORG_TREE, user.getUsername()), vo, 12, TimeUnit.HOURS);
    }

    /**
     * 查询用户管理的机构列表
     * @param usermame
     * @param vo
     * @Description
     * com.grgbanking.platform.system.service.impl.SysOrgServiceImpl#listTree(java.lang.Long)
     */
    public void saveCacheList(String usermame, List<SysOrgVo> vo) {
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_ORG_LIST, usermame), vo, 12, TimeUnit.HOURS);
    }

    public void delCacheListTree() {
        BankRedisUtil.removeAll(AppCoreConstants.DEL_ORG_ALL);
        BankRedisUtil.removeAll(AppCoreConstants.DEL_ORG_TREE);
        BankRedisUtil.removeAll(AppCoreConstants.DEL_ORG_LIST);
    }

    //使用：com.grgbanking.platform.system.service.impl.SysOrgServiceImpl#manageOrgListEnable(java.lang.Long)
    public List<SysOrgVo> getCacheListTree(Long userId) {
        List<SysOrgVo> orgVos = null;
        SysUserEntity user = sysUserService.getById(userId);
        if(redisTemplate.hasKey(String.format(AppCoreConstants.USER_ORG_TREE, user.getUsername())))
            orgVos = (List<SysOrgVo>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_ORG_TREE, user.getUsername()));
        return orgVos;
    }

    public List<SysOrgVo> getCacheList(String username) {
        List<SysOrgVo> orgVos = null;
        if(redisTemplate.hasKey(String.format(AppCoreConstants.USER_ORG_LIST, username)))
            orgVos = (List<SysOrgVo>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_ORG_LIST, username));
        return orgVos;
    }

    public  boolean isSaveCach(SysOrgBo bo){
        /**
         * 如果有查询条件说明是要查找数据库，不走redis
         * 返回fale不走缓存
         */

        if(bo!=null){
            if (StringUtils.isNotBlank(bo.getName())||
                    StringUtils.isNotBlank(bo.getIsEnabled())||
                    StringUtils.isNotBlank(bo.getOrgCode())||
                    StringUtils.isNotBlank(bo.getFinCode())||
                    StringUtils.isNotBlank(bo.getI18nCode())){
                return  false;
            }
        }
        return true;
    }

    /**
     * 根据传入机构获取机构树
     */
    public static List<SysOrgVo> buildTree(List<SysOrgVo> treeNodes) {
        List<SysOrgVo> result = new ArrayList<>();
        Map<String, SysOrgVo> mapAll = new LinkedHashMap<>();
        for (SysOrgVo node : treeNodes) {
            mapAll.put(node.getOrgCode(), node);
        }
        Set<Map.Entry<String, SysOrgVo>> entrySet = mapAll.entrySet();
        for (Map.Entry<String, SysOrgVo> entry : entrySet) {
            String pid = entry.getValue().getParentCode();
            SysOrgVo parentDept = mapAll.get(pid);
            if (parentDept == null) {
                result.add(entry.getValue());
            } else {
                List<SysOrgVo> children = parentDept.getChildren() == null ? new ArrayList<>() : parentDept.getChildren();
                children.add(entry.getValue());
                parentDept.setChildren(children);
            }
        }
        return result;
    }
}
