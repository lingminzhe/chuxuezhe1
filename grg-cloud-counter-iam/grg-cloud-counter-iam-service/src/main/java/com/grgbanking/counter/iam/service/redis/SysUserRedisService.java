package com.grgbanking.counter.iam.service.redis;

import cn.hutool.core.collection.CollectionUtil;
import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.api.vo.SysUserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lggui1
 * @Date 2021/1/14
 * @Description
 **/
@Component
@Slf4j
public class SysUserRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * @Description 解除锁定
     * @param username
     * @return
     **/
    public boolean unlockCache(String username) {
        Boolean delete = redisTemplate.delete(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username));
        if (delete) {
            return true;
        }
        return false;
    }


    /***
     * @Description 获取所有锁定用户
     * @param records
     * @return
     **/
    public List<SysUserQueryVo> getAllLockedUser(List<SysUserQueryVo> records) {
        //获取所有锁定的key
        List<String> keysList = BankRedisUtil.getKeys(AppCoreConstants.DEL_LOGIN_LOCKED);
        if (CollectionUtil.isEmpty(keysList)) {
            return records;
        }
        List<String> keyNames = new ArrayList<>();
        //转ID数组
        keysList.forEach(a -> {
            keyNames.add(a.substring(AppCoreConstants.USER_LOGIN_PREFIX.length()));
        });
        List<String> userNames = records.stream().map(SysUserQueryVo::getUsername).collect(Collectors.toList());
        keyNames.retainAll(userNames);
        //交集是空则是没有锁定的
        if (CollectionUtil.isEmpty(keyNames)) {
            return records;
        }
        for (SysUserQueryVo user : records) {
            //这加未锁定会与开始没有找到锁定用户则返回时不一致
            //user.setIsLocked(Operate.UNLOCKED.code());
            if (keyNames.contains(user.getUsername())) {
                Object value = redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_LOGIN_LOCKED, user.getUsername()));
                if (Integer.valueOf(String.valueOf(value)) >= AppCoreConstants.LOCK_NUM) {
                    user.setIsLocked(Operate.LOCKING.code());
                }

            }

        }
        return records;
    }


}
