package com.grgbanking.counter.iam.service.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.api.vo.SysDictVo;
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
public class SysDictRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存缓存数据
     */
    public void saveCacheList(String appType, List<SysDictVo> voList) {
        JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(voList));
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.APP_DICT_LIST, appType), array, 12, TimeUnit.HOURS);
    }

    /**
     * 查询缓存数据
     */
    public List<SysDictVo> getCacheList(String appType) {
        List<SysDictVo> sysVo = new ArrayList<>();
        if(redisTemplate.hasKey(String.format(AppCoreConstants.APP_DICT_LIST, appType))) {
            JSONArray arry = (JSONArray)redisTemplate.opsForValue().get(String.format(AppCoreConstants.APP_DICT_LIST, appType));
            sysVo = JSONObject.parseArray(arry.toJSONString(), SysDictVo.class);
        }
        return sysVo;
    }

    /**
     * 根据通配符删除全部缓存
     */
    public void delDictCaches() {
        BankRedisUtil.removeAll(AppCoreConstants.DEL_DICT_LIST);

    }
}
