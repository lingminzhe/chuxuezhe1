package com.grgbanking.counter.iam.service.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import com.grgbanking.counter.iam.api.vo.SysI18nDataTypeVo;
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
public class SysI18nRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存缓存数据
     */
    public void saveCacheList(String appType, String i18nLanguageType, List<SysI18nDataTypeVo> voList) {
        JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(voList));
        redisTemplate.opsForValue().set(String.format(AppCoreConstants.APP_I18N_LIST, appType, i18nLanguageType), array, 12, TimeUnit.HOURS);
    }

    /**
     * 查询缓存数据
     */
    public List<SysI18nDataTypeVo> getCacheList(String appType, String i18nLanguageType) {
        List<SysI18nDataTypeVo> sysVo = new ArrayList<>();
        if(redisTemplate.hasKey(String.format(AppCoreConstants.APP_I18N_LIST, appType, i18nLanguageType))) {
            JSONArray arry = (JSONArray)redisTemplate.opsForValue().get(String.format(AppCoreConstants.APP_I18N_LIST, appType, i18nLanguageType));
            sysVo = JSONObject.parseArray(arry.toJSONString(), SysI18nDataTypeVo.class);
        }
        return sysVo;
    }

    /**
     * 根据通配符删除全部缓存
     */
    public void delI18nCaches() {
        BankRedisUtil.removeAll(AppCoreConstants.DEL_I18N_LIST);
    }
}
