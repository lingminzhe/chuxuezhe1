package com.grgbanking.counter.iam.service.redis;

import com.grgbanking.counter.common.cache.util.BankRedisUtil;
import com.grgbanking.counter.iam.constans.AppCoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @param
 * @return
 */
@Component
public class SysAuthorityRedisService {

    /**
     * 根据通配符删除全部缓存
     */
    public void delAuthorityCaches() {
        BankRedisUtil.removeAll(AppCoreConstants.DEL_AUTHORITY_LIST);
    }

}
