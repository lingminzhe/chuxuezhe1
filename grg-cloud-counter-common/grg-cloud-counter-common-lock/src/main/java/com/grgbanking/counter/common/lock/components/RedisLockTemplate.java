package com.grgbanking.counter.common.lock.components;

import com.grgbanking.counter.common.lock.enums.LockDurationEnum;
import com.grgbanking.counter.common.lock.enums.LockNameEnum;
import com.grgbanking.counter.common.lock.enums.LockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis的分布式锁组件，只能使用本类进行锁操作，不允许直接使用Redisson
 *
 * @Author:mcyang
 * @Date:2020/9/24 8:53 上午
 */
@Slf4j
@Component
public class RedisLockTemplate {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 解锁
     *
     * @param lock 锁对象
     */
    public void unlock(RLock lock) {
        if (lock == null) {
            return;
        }
        if (lock.isLocked()) {
            lock.unlock();
        }
    }

    /**
     * 尝试使用抢占模式加锁
     * 获取锁过程一直在等待，直至获取成功，并且需要调用方手动释放锁
     *
     * @param lockName 锁标识
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock lock(LockNameEnum lockName) {
        if (lockName == null) {
            return null;
        }
        RLock lock = redissonClient.getLock(lockName.getLockName());
        lock.lock();
        return lock;
    }

    /**
     * 尝试获得锁，获取不到，立马放弃
     *
     * @param lockName
     * @return
     */
    public RLock tryLock(LockNameEnum lockName) {
        if (lockName == null) {
            return null;
        }
        RLock lock = redissonClient.getLock(lockName.getLockName());
        lock.tryLock();
        return lock;
    }

    /**
     * 尝试使用抢占模式加锁
     * 获取锁过程等待指定时长，超时获取不到锁则放弃
     *
     * @param lockName     锁标识
     * @param waitDuration 等待时长：秒
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock tryLock(LockNameEnum lockName, LockDurationEnum waitDuration) {
        if (lockName == null || waitDuration == null) {
            return null;
        }
        try {
            RLock lock = redissonClient.getLock(lockName.getLockName());
            if (lock.tryLock(waitDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


    /**
     * 尝试使用抢占模式加锁
     * 获取锁过程等待指定时长，超时获取不到锁则放弃，获取锁后持有指定时长后自动释放锁
     *
     * @param lockName     锁标识
     * @param waitDuration 等待时长：秒
     * @param holdDuration 持有锁时长：秒
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock tryLock(LockNameEnum lockName, LockDurationEnum waitDuration, LockDurationEnum holdDuration) {
        if (lockName == null || waitDuration == null || holdDuration == null) {
            return null;
        }
        try {
            RLock lock = redissonClient.getLock(lockName.getLockName());
            if (lock.tryLock(waitDuration.getDuration(), holdDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 尝试使用公平模式加锁
     * 公平模式有先后顺序
     * 获取锁过程一直在等待，直至获取成功，并且需要调用方手动释放锁
     *
     * @param lockName 锁标识
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock lockFair(LockNameEnum lockName) {
        if (lockName == null) {
            return null;
        }
        RLock lock = redissonClient.getFairLock(lockName.getLockName());
        lock.lock();
        return lock;
    }

    /**
     * 尝试使用公平模式枷锁
     * 公平模式有先后顺序
     * 获取不到锁则则立马放弃
     *
     * @param lockName
     * @return
     */
    public RLock tryLockFair(LockNameEnum lockName) {
        if (lockName == null) {
            return null;
        }
        RLock lock = redissonClient.getFairLock(lockName.getLockName());
        lock.tryLock();
        return lock;
    }

    /**
     * 尝试使用公平模式加锁
     * 公平模式有先后顺序
     * 获取锁过程等待指定时长，超时获取不到锁则放弃
     *
     * @param lockName     锁标识
     * @param waitDuration 等待时长：秒
     * @return 锁对象，成功获取到锁，返回，否则返回空
     */
    public RLock tryLockFair(LockNameEnum lockName, LockDurationEnum waitDuration) {
        if (lockName == null || waitDuration == null) {
            return null;
        }
        try {
            RLock lock = redissonClient.getFairLock(lockName.getLockName());
            if (lock.tryLock(waitDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
        return null;

    }

    /**
     * 尝试使用公平模式加锁
     * 公平模式有先后顺序
     * 获取锁过程等待指定时长，超时获取不到锁则放弃，获取锁后持有指定时长后自动释放锁
     *
     * @param lockName     锁标识
     * @param waitDuration 等待时长：秒
     * @param holdDuration 持有锁时长：秒
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock tryLockFair(LockNameEnum lockName, LockDurationEnum waitDuration, LockDurationEnum holdDuration) {
        if (lockName == null || waitDuration == null || holdDuration == null) {
            return null;
        }
        try {
            RLock lock = redissonClient.getFairLock(lockName.getLockName());
            if (lock.tryLock(waitDuration.getDuration(), holdDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取读/写锁
     * 获取锁过程一直在等待，直至获取成功，并且需要调用方手动释放锁
     *
     * @param lockName     锁标识
     * @param lockTypeEnum 锁类型：读/写
     * @return 锁对象，成功获取到锁，返回，否则返回空
     */
    public RLock readWriteLock(LockNameEnum lockName, LockTypeEnum lockTypeEnum) {
        if (lockName == null || lockTypeEnum == null) {
            return null;
        }
        try {
            RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockName.getLockName());
            RLock lock = lockTypeEnum.compareTo(LockTypeEnum.READ_LOCK) == 0 ? readWriteLock.readLock() : lockTypeEnum.compareTo(LockTypeEnum.WRITE_LOCK) == 0 ? readWriteLock.writeLock() : null;
            if (lock != null) {
                lock.lock();
                return lock;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取读/写锁
     * 获取不到锁则立马放弃
     *
     * @param lockName     锁标识
     * @param lockTypeEnum 锁类型：读/写
     * @return 锁对象，成功获取到锁，返回，否则返回空
     */
    public RLock tryReadWriteLock(LockNameEnum lockName, LockTypeEnum lockTypeEnum) {
        if (lockName == null || lockTypeEnum == null) {
            return null;
        }
        try {
            RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockName.getLockName());
            RLock lock = lockTypeEnum.compareTo(LockTypeEnum.READ_LOCK) == 0 ? readWriteLock.readLock() : lockTypeEnum.compareTo(LockTypeEnum.WRITE_LOCK) == 0 ? readWriteLock.writeLock() : null;
            if (lock != null) {
                lock.tryLock();
                return lock;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取读/写锁
     * 获取锁过程等待指定时长，超时获取不到锁则放弃
     *
     * @param lockName     锁标识
     * @param lockTypeEnum 锁类型：读/写
     * @param waitDuration 等待时长：秒
     * @return 锁对象，成功获取到锁，返回，否则返回空
     */
    public RLock readWriteLock(LockNameEnum lockName, LockTypeEnum lockTypeEnum, LockDurationEnum waitDuration) {
        if (lockTypeEnum == null || lockTypeEnum == null || waitDuration == null) {
            return null;
        }
        try {
            RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockName.getLockName());
            RLock lock = lockTypeEnum.compareTo(LockTypeEnum.READ_LOCK) == 0 ? readWriteLock.readLock() : lockTypeEnum.compareTo(LockTypeEnum.WRITE_LOCK) == 0 ? readWriteLock.writeLock() : null;
            if (lock != null && lock.tryLock(waitDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取读/写锁
     * 获取锁过程等待指定时长，超时获取不到锁则放弃，获取锁后持有指定时长后自动释放锁
     *
     * @param lockName     锁标识
     * @param lockTypeEnum 锁类型：读/写
     * @param waitDuration 等待时长：秒
     * @param holdDuration 持有锁时长：秒
     * @return 锁对象，成功获取到锁，返回锁对象，否则返回空
     */
    public RLock readWriteLock(LockNameEnum lockName, LockTypeEnum lockTypeEnum, LockDurationEnum waitDuration, LockDurationEnum holdDuration) {
        if (lockTypeEnum == null || lockTypeEnum == null || waitDuration == null || holdDuration == null) {
            return null;
        }
        try {
            RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockName.getLockName());
            RLock lock = lockTypeEnum.compareTo(LockTypeEnum.READ_LOCK) == 0 ? readWriteLock.readLock() : lockTypeEnum.compareTo(LockTypeEnum.WRITE_LOCK) == 0 ? readWriteLock.writeLock() : null;
            if (lock != null && lock.tryLock(waitDuration.getDuration(), holdDuration.getDuration(), TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

}
