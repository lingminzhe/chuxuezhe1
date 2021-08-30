package com.grgbanking.counter.iam.dubbo;

import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.api.bo.SysUserBo;
import com.grgbanking.counter.iam.api.bo.UserData;
import com.grgbanking.counter.iam.api.dubbo.AuthRemoteService;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysAuthorityService;
import com.grgbanking.counter.iam.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录时查询用户信息的接口实现类
 */
@DubboService
public class AuthRemoteServiceImpl implements AuthRemoteService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Override
    public UserData info(String username) {
        UserData result = new UserData();
        SysUserEntity sysUserEntity = sysUserService.getUserByUsername(username);
        if (sysUserEntity == null) {
            throw new CheckedException(RespI18nConstants.COM1007.getCode());
        }
        if (sysUserEntity.getIsEnabled().equals(CommonConstants.enableFlag_0)) {
            throw new CheckedException(RespI18nConstants.USER1024.getCode());
        }
        List<SysAuthorityEntity> authorities = sysAuthorityService.getAuthorityByUserId(sysUserEntity.getId(), "1");
        SysUserBo userBo = new SysUserBo();
        BeanUtils.copyProperties(sysUserEntity,userBo);
        result.setUserBo(userBo);

        List<String> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(authorities)) {
            for (SysAuthorityEntity sysAuthorityEntity : authorities) {
                authorityList.add(sysAuthorityEntity.getAuthority());
            }
        }
        result.setAuthorities(authorityList);
        return result;
    }
}
