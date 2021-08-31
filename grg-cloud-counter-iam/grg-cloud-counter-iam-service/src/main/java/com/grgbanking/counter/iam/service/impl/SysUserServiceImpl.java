package com.grgbanking.counter.iam.service.impl;

import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import com.grgbanking.counter.iam.dao.SysUserDao;
import com.grgbanking.counter.iam.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

}
