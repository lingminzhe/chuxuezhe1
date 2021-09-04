package com.grgbanking.counter.iam.dubbo;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import com.grgbanking.counter.iam.api.entity.SysMenuEntity;
import com.grgbanking.counter.iam.api.entity.SysRoleEntity;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import com.grgbanking.counter.iam.auth.social.LoginHandler;
import com.grgbanking.counter.iam.service.SysMenuService;
import com.grgbanking.counter.iam.service.SysRoleService;
import com.grgbanking.counter.iam.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户登录远程处理类
 */
@DubboService
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private Map<String, LoginHandler> loginHandlerMap;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserInfo info(String username) {
        UserInfo userInfo = new UserInfo();
        SysUserEntity sysUser = sysUserService.getOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUserName, username));
        userInfo.setSysUser(sysUser);
        // 设置角色列表 （ID）
        List<Long> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId()).stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));

        // 设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId).stream()
                    .filter(menu -> StrUtil.isNotEmpty(menu.getPermission())).map(SysMenuEntity::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

    @Override
    public UserInfo social(LoginTypeEnum loginTypeEnum, String code) {
        return loginHandlerMap.get(loginTypeEnum.getType()).handle(code);
    }
}
