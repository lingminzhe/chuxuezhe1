package com.grgbanking.counter.app.grg_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.app.grg_account.dao.AuthUserDao;
import com.grgbanking.counter.app.grg_account.entity.AuthUserEntity;
import com.grgbanking.counter.app.grg_account.service.AuthUserService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("authUserService")
public class AuthUserServiceImpl extends ServiceImpl<AuthUserDao, AuthUserEntity> implements AuthUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AuthUserEntity> page = this.page(
                new Query<AuthUserEntity>().getPage(params),
                new QueryWrapper<AuthUserEntity>()
        );

        return new PageUtils(page);
    }

}