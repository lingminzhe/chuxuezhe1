package com.grgbanking.counter.iam.service.redis;

import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.api.vo.SysAreaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zzwei6
 * @Date 2021年1月13日10:20:09
 * @Description 区域公共处理:主要处理缓存
 **/
@Component
public class SysAreaRedisService {

    @Autowired
    public RedisTemplate redisTemplate;


    public void saveAreaRedis(String username, List<SysAreaVo> vo) {
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_AREA_TREE, username), vo, 12, TimeUnit.HOURS);

    }

    /**
     * 清除区域缓存
     */
    public void closeAreaRedis() {
        BankRedisUtil.removeAll(String.format(AppCoreConstants.DEL_AREA_TREE));
    }

    /**
     * 清除区域缓存
     */
    public void closeAreaRedisByUserId(String username) {
        if(redisTemplate.hasKey(String.format(AppCoreConstants.USER_AREA_TREE, username)))
            redisTemplate.delete(String.format(AppCoreConstants.USER_AREA_TREE, username));
    }


    public List<SysAreaVo> getListByName(String username) {
        List<SysAreaVo> areaVos = null;
        if(redisTemplate.hasKey(String.format(AppCoreConstants.USER_AREA_TREE, username)))
            areaVos = (List<SysAreaVo>) redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_AREA_TREE, username));
        return areaVos;
    }
}
