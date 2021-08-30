package com.grgbanking.counter.common.lock.enums;

import com.grgbanking.counter.common.lock.constants.LockConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分布式锁名字枚举，所有锁均需在此声明
 * @Author:mcyang
 * @Date:2020/9/24 8:52 上午
 */
@Getter
@AllArgsConstructor
public enum LockNameEnum {

    /**ots加载数据锁*/
    OTS_REDIS_INIT("ots_redis_init"),

    /**mam抢单加锁*/
    MAM_ACTION_RESPONSE("mam_action_response"),

    /**mam工单配置读写锁*/
    MAM_RULE_CFG("mam_rule_cfg"),

    /**mam服务负载均衡处理加锁*/
    MAM_SERVICE_LB("mam_service_lb");

    /**其他需要使用锁的地方在此加上锁名字*/

    private String lockName;


    public String getLockName() {
        return LockConstant.LOCK_PREFIX + lockName;
    }
}
