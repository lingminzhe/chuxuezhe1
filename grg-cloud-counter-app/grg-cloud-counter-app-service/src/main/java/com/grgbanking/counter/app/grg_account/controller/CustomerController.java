package com.grgbanking.counter.app.grg_account.controller;

import com.grgbanking.counter.app.grg_account.entity.CustomerEntity;
import com.grgbanking.counter.app.grg_account.service.CustomerService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 联系人信息表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Api(tags = "客户信息表")
@Slf4j
@RestController
@RequestMapping("grg_account/customerInfo")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询所有客户信息")
    @ApiImplicitParam(name = "null",value = "null",required = true)
    @GetMapping("/list")
//    @RequiresPermissions("grg_account:customerinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据id查询客户信息")
    @ApiImplicitParam(name = "id",value = "客户Id",required = true)
    @GetMapping("/info/{id}")
//    @RequiresPermissions("grg_account:customerinfo:info")
    public R info(@PathVariable("id") String id){
		CustomerEntity customerInfo = customerService.getById(id);

        return R.ok().put("customerInfo", customerInfo);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增客户信息")
    @ApiImplicitParam(name = "CustomerEntity",value = "客户信息",required = true)
    @PostMapping("/save")
//    @RequiresPermissions("grg_account:customerinfo:save")
    public R save(@RequestBody CustomerEntity customerInfo){
        log.info("保存信息");

        boolean save = customerService.save(customerInfo);

        if (save == true){
            return R.ok().put("data","更改成功");
        }else {
            return R.error().put("data","更改失败");
        }
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改客户信息")
    @ApiImplicitParam(name = "CustomerEntity",value = "客户信息",required = true)
    @PostMapping("/update")
//    @RequiresPermissions("grg_account:customerinfo:update")
    public R update(@RequestBody CustomerEntity customerInfo){
        //1、判断对象是否为空
        if (customerInfo == null){
            return R.error().put("data","传入的对象为空");
        }
        //2、根据id更新客户信息
        boolean update = customerService.updateById(customerInfo);

        //3、判断是否返回成功
        if (update){
            return R.ok().put("data","更改成功");
        }else {
            return R.error().put("data","更改失败");
        }

    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据id删除客户信息")
    @ApiImplicitParam(name = "ids",value = "客户Ids",required = true)
    @DeleteMapping("/delete")
//    @RequiresPermissions("grg_account:customerinfo:delete")
    public R delete(@RequestBody String[] ids){
        customerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
