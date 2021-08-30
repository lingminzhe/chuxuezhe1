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
public enum LockPrefixEnum {

    /**mam终端是否存活*/
    MAM_TERM_ALIVE("mam_term_alive:");


    private String value;


    @Override
    public String toString() {
        return LockConstant.LOCK_PREFIX + value;
    }
}
