package com.grgbanking.counter.iam.service.impl;

import com.grgbanking.counter.iam.api.entity.SysMenuEntity;
import com.grgbanking.counter.iam.dao.SysMenuDao;
import com.grgbanking.counter.iam.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    public List<SysMenuEntity> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

}
