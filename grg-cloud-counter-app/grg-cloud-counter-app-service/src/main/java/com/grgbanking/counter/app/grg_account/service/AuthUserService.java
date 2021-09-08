package com.grgbanking.counter.app.grg_account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.app.grg_account.entity.AuthUserEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.Map;

/**
 * 用户认证信息表
 *
 * @author Ye Kaitao
 * @email ${email}
 * @date 2021-09-01 09:08:03
 */
public interface AuthUserService extends IService<AuthUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

