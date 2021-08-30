package com.grgbanking.counter.iam.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.vo.SysUserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户首页相关接口
 */
@RestController
@Api(value = "用户首页相关接口", tags = "用户首页相关接口")
@ApiSort(value = 1)
@RequestMapping("/sys")
public class SysLoginController {


    @Autowired
    private SysUserService sysUserService;


    /**
     * 获得当前登录用户信息
     */
    @ApiOperation(value = "获得当前用户的信息", notes = "获得当前用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录账号", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/login/user")
    public Resp<SysUserInfoVo> getUserMessage(String username) {
        username = SecurityContextUtil.isExistUser(username);
        SysUserInfoVo userVo = sysUserService.getUserInfoByUsername(username);
        return Resp.ok(userVo);
    }

    /**
     * 登出接口
     */
    @SysLog("登出接口")
    @ApiOperation(value = "登出接口", notes = "登出接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "access_token", value = "访问令牌", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/oauth/logout")
    public Resp delToken(String token, String access_token, HttpServletRequest request) {

        if (StringUtils.isBlank(token)) {
            token = access_token;

            if (StringUtils.isBlank(token)) {
                String authorization = request.getHeader("Authorization");
                token = authorization.substring(7);
            }
        }
        // TODO mcy
//        OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
//
//        if (accessToken != null) {
//            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//            if (refreshToken != null) {
//                this.tokenStore.removeRefreshToken(refreshToken);
//            }
//            this.tokenStore.removeAccessToken(accessToken);
//        }

        return Resp.ok();
    }
}
