package com.grgbanking.counter.app.grg_account.controller;

import com.grgbanking.counter.app.grg_account.entity.AccountRecordEntity;
import com.grgbanking.counter.app.grg_account.service.AccountRecordService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 银行卡业务操作记录表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@RestController
@RequestMapping("grg_account/accountinfodetails")
public class AccountRecordController {
    @Autowired
    private AccountRecordService accountRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("grg_account:accountinfodetails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("grg_account:accountinfodetails:info")
    public R info(@PathVariable("id") Integer id){
		AccountRecordEntity accountInfoDetails = accountRecordService.getById(id);

        return R.ok().put("accountInfoDetails", accountInfoDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("grg_account:accountinfodetails:save")
    public R save(@RequestBody AccountRecordEntity accountInfoDetails){
		accountRecordService.save(accountInfoDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("grg_account:accountinfodetails:update")
    public R update(@RequestBody AccountRecordEntity accountInfoDetails){
        accountRecordService.updateById(accountInfoDetails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("grg_account:accountinfodetails:delete")
    public R delete(@RequestBody Integer[] ids){
        accountRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
