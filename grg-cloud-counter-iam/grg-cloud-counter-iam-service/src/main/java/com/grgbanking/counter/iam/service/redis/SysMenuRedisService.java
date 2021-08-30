package com.grgbanking.counter.iam.service.redis;

import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.api.vo.SysMenuTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @param
 * @return
 */
@Component
public class SysMenuRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存可使用菜单树缓存数据
     */
    public void saveUseCacheTree(String username, List<SysMenuTreeVo> voTree) {
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_MENU_USE, username), voTree, 12, TimeUnit.HOURS);
    }

    /**
     * 保存可分配菜单树缓存数据
     */
    public void saveAllocCacheTree(String username, List<SysMenuTreeVo> voTree) {
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_MENU_ALLOC, username), voTree, 12, TimeUnit.HOURS);
    }

    /**
     * 查询可使用菜单树缓存数据
     */
    public List<SysMenuTreeVo> getUseCacheTree(String username) {
        List<SysMenuTreeVo> sysVo = (List<SysMenuTreeVo>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_MENU_USE, username));
        if (sysVo != null && sysVo.size() > 0) {
            return sysVo;
        }
        return new ArrayList<>();
    }


    /**
     * 查询可分配菜单树缓存数据
     */
    public List<SysMenuTreeVo> getAllocCacheTree(String username) {
        List<SysMenuTreeVo> sysVo = (List<SysMenuTreeVo>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_MENU_ALLOC, username));
        if (sysVo != null && sysVo.size() > 0) {
            return sysVo;
        }
        return new ArrayList<>();
    }

    /**
     * 根据通配符删除全部缓存
     */
    public void delMenuCaches() {
        BankRedisUtil.removeAll(AppCoreConstants.DEL_MENU_ALL);
    }

}
