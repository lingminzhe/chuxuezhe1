package com.grgbanking.counter.app.grg_account.controller;

import com.grgbanking.counter.app.grg_account.entity.AccountEntity;
import com.grgbanking.counter.app.grg_account.service.AccountService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 银行卡详细表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Api(tags = "银行卡账户信息")
@Slf4j
@RestController
@RequestMapping("grg_account/accountInfo")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询所有银行卡账户信息")
    @GetMapping("/list")
//    @RequiresPermissions("grg_account:accountinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据customer_id查询账户信息")
    @ApiImplicitParam(name = "customer_id",value = "客户Id",required = true)
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){

		AccountEntity accountInfo = accountService.getById(id);

        return R.ok().put("accountInfo", accountInfo);
    }

    @ApiOperation(value = "根据customer_id查询账户信息")
    @ApiImplicitParam(name = "AccountEntity",value = "客户详细信息",required = true)
    @GetMapping("/infoByCustomerId")
    public R infoByCustomerId( AccountEntity account){

        AccountEntity accountInfo = accountService.getAccountById(account);

        return R.ok().put("accountInfo", accountInfo);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增客户信息")
    @ApiImplicitParam(name = "AccountEntity",value = "客户信息",required = true)
    @PostMapping("/save")
    public R save(@RequestBody AccountEntity accountInfo){
        AccountEntity accountInfoEntity = new AccountEntity();
        accountInfoEntity.setId(3);
        accountInfoEntity.setCustomerId(1);
        accountInfoEntity.setCardNo("12345678");
        accountInfoEntity.setCardPwd("123456");
        accountInfoEntity.setCardCertificateType("身份证");
        accountInfoEntity.setCreateDate(new Date());
        accountInfoEntity.setUpdateDate(new Date());


        boolean save = accountService.save(accountInfoEntity);
        if (save == true){
            return R.ok();
        }else {
            return R.error("保存失败");
        }


    }

    /**
     * 账号挂失
     * @param accountInfo
     * @return
     */
    @ApiOperation(value = "根据card_no选择账户挂失")
    @ApiImplicitParam(name = "AccountEntity",value = "卡号",required = true)
    @PostMapping("/updateAccountStatus")
    public R updateCardStatus(@RequestBody AccountEntity accountInfo){
        log.info("挂失流程");
        //账户状态（0：已激活； 1：未激活； 2：已挂失）
        accountService.updateById(accountInfo);

        log.info("修改卡状态成功，目前卡状态为:"+accountInfo.getAccountStatus());

        return R.ok().put("data","修改卡状态成功");
    }
  /*  *//**
     * 账号恢复
     * @param accountInfo
     * @return
     *//*
    @RequestMapping("/updateAccountStatus")
    public R unSuspendCardById(@RequestBody AccountEntity accountInfo){
        log.info("挂失流程");
        //账户状态（0：已激活； 1：未激活； 2：已挂失）
        Integer accountStatus = accountInfo.getAccountStatus();

        if (accountInfo.getAccountStatus() == 2){
            //
            accountInfo.setAccountStatus(0);
            accountInfoService.updateById(accountInfo);
            log.error("修改卡状态成功，目前卡状态为:"+accountInfo.getAccountStatus());
        }else {
            log.error("修改卡状态失败，目前卡状态为:"+accountInfo.getAccountStatus());
            return R.ok().put("data","修改卡状态失败");
        }
        return R.ok().put("data","修改卡状态成功");
    }*/
    /**
     * 删除
     */
    @ApiOperation(value = "根据id删除客户信息")
    @ApiImplicitParam(name = "ids",value = "客户Ids",required = true)
    @DeleteMapping("/delete")//    @RequiresPermissions("grg_account:accountinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        accountService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
