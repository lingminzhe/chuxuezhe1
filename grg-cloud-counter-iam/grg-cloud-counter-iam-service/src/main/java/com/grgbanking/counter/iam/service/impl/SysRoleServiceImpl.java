package com.grgbanking.counter.iam.service.impl;

import com.grgbanking.counter.iam.api.entity.SysRoleEntity;
import com.grgbanking.counter.iam.dao.SysRoleDao;
import com.grgbanking.counter.iam.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Override
    public List<SysRoleEntity> findRolesByUserId(String userId) {
        return baseMapper.listRolesByUserId(userId);
    }
}
