package com.grgbanking.counter.iam.service.redis;

import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.entity.SysRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author lggui1
 * @Date 2021年1月26日
 * @Description 处理角色缓存
 **/
@Component
public class SysRoleRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param usermame
     * @param vo
     * @Description 保存内部接口的数据
     * com.grgbanking.platform.system.service.impl.SysOrgServiceImpl#listTree(java.lang.Long)
     */
    public void saveCacheList(String usermame, List<SysRoleEntity> vo) {
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_ROLE_LIST, usermame), vo, 12, TimeUnit.HOURS);
    }
    //com.grgbanking.platform.system.controller.SysRoleController.queryRoleList
    public List<SysRoleEntity> getCacheList(String usermame) {
        List<SysRoleEntity> entity = (List<SysRoleEntity>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_ROLE_LIST, usermame));
        return entity;
    }
    public void delCache(){
        BankRedisUtil.removeAll(AppCoreConstants.DEL_ROLE_LIST);
    }
}
