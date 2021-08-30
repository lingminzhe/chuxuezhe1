package com.grgbanking.counter.common.lock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分布式锁的时长，包括获取锁过程等待时长还是持有锁的时长
 * @Author:mcyang
 * @Date:2020/9/24 9:36 上午
 */
@Getter
@AllArgsConstructor
public enum LockDurationEnum {
    /**1秒*/
    ONE_SECOND(1),
    /**2秒*/
    TWO_SECOND(2),
    /**5秒*/
    FIVE_SECOND(5),
    /**10秒*/
    TEN_SECOND(10),
    /**20秒*/
    TWENTY_SECOND(20),
    /**30秒*/
    THIRTY_SECOND(30),
    /**1分钟*/
    ONE_MINUTE(60),
    /**2分钟*/
    TWO_MINUTE(120),
    /**3分钟*/
    THREE_MINUTE(180),
    /**5分钟*/
    FIVE_MINUTE(300),
    /**10分钟*/
    TEN_MINUTE(600),
    /**20分钟*/
    TWENTY_MINUTE(1200),
    /**30分钟*/
    THIRTY_MINUTE(1800),
    /**一个小时*/
    ONE_HOUR(3600);

    private int duration;

}
