package com.grgbanking.counter.common.lock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分布式锁类型枚举，比如读锁、写锁
 * @Author:mcyang
 * @Date:2020/9/24 8:52 上午
 */
@Getter
@AllArgsConstructor
public enum LockTypeEnum {

    /**读锁*/
    READ_LOCK,

    /**写锁*/
    WRITE_LOCK;


}
