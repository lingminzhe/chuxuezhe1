package com.grgbanking.counter.app.grg_account.controller;

import com.grgbanking.counter.app.grg_account.entity.AuthUserEntity;
import com.grgbanking.counter.app.grg_account.service.AuthUserService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;




/**
 * 用户认证信息表
 *
 * @author Ye Kaitao
 * @email ${email}
 * @date 2021-09-01 09:08:03
 */
@RestController
@RequestMapping("grg_account/authuser")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("grg_account:authuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("grg_account:authuser:info")
    public R info(@PathVariable("id") Integer id){
		AuthUserEntity authUser = authUserService.getById(id);

        return R.ok().put("authUser", authUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("grg_account:authuser:save")
    public R save(@RequestBody AuthUserEntity authUser){
		authUserService.save(authUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("grg_account:authuser:update")
    public R update(@RequestBody AuthUserEntity authUser){
		authUserService.updateById(authUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("grg_account:authuser:delete")
    public R delete(@RequestBody Integer[] ids){
		authUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
