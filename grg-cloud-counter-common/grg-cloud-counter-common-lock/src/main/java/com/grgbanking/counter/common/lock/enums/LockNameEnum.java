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

    /**检查用户视频呼叫锁*/
    CHECK_CUSTOMER_VIDEO_APPLY("cusomter_video_apply");

    /**其他需要使用锁的地方在此加上锁名字*/

    private String lockName;


    public String getLockName() {
        return LockConstant.LOCK_PREFIX + lockName;
    }
}
