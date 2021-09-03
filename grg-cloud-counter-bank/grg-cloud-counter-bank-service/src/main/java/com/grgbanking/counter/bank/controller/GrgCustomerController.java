package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import com.grgbanking.counter.bank.service.GrgAccountRecordService;
import com.grgbanking.counter.bank.service.GrgCustomerService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;




/**
 * 客户详情表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@Api(tags = "客户信息表")
@Slf4j
@RestController
@RequestMapping("bank/customer")
public class GrgCustomerController {
    @Autowired
    private GrgCustomerService grgCustomerService;

    @Autowired
    private GrgAccountRecordService accountRecordService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询所有客户信息")
    @ApiImplicitParam(name = "null",value = "null",required = true)
    @RequestMapping("/list")
   //@grgAccountService("bank:grgcustomer:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgCustomerService.queryPage(params);

        return Resp.success(page,"page");
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据id查询客户信息")
    @ApiImplicitParam(name = "id",value = "客户Id",required = true)
    @RequestMapping("/info/{id}")
   //@grgAccountService("bank:grgcustomer:info")
    public Resp info(@PathVariable("id") String id){
		GrgCustomerEntity grgCustomer = grgCustomerService.getById(id);

        return Resp.success(grgCustomer, "grgCustomer");
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增客户信息")
    @ApiImplicitParam(name = "CustomerEntity",value = "客户信息",required = true)
    @RequestMapping("/save")
   //@grgAccountService("bank:grgcustomer:save")
    public Resp save(@RequestBody GrgCustomerEntity grgCustomer){
		grgCustomerService.save(grgCustomer);

        return Resp.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改客户信息")
    @ApiImplicitParam(name = "CustomerEntity",value = "客户信息",required = true)
    @RequestMapping("/update")
   //@grgAccountService("bank:grgcustomer:update")
    public Resp update(@RequestBody GrgCustomerEntity grgCustomer){
		grgCustomerService.updateById(grgCustomer);

        return Resp.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据id删除客户信息")
    @ApiImplicitParam(name = "ids",value = "客户Ids",required = true)
    @RequestMapping("/delete")
   //@grgAccountService("bank:grgcustomer:delete")
    public Resp delete(@RequestBody String[] ids){
		grgCustomerService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
