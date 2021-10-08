package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.service.GrgAccountRecordService;
import com.grgbanking.counter.bank.service.GrgAccountService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 银行卡详细表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03
 */
@Api(tags = "银行卡账户信息")
@Slf4j
@RestController
@RequestMapping("/account")
public class GrgAccountController {
    @Autowired
    private GrgAccountService grgAccountService;

    @Autowired
    private GrgAccountRecordService accountRecordService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询所有银行卡账户信息")
    @GetMapping("/list")
    //@grgAccountService("bank:grgaccount:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgAccountService.queryPage(params);

        return Resp.success(page, "page");
    }

    /**
     * 列表
     */
    @ApiOperation(value = "查询用户名下账户信息customerId为必输入，输入0表示查询全部  可选择根据主键id或卡号精确查询")
    @GetMapping("/listAll/{customer_id}")
    //@grgAccountService("bank:grgaccount:list")
    public Resp listAll(@RequestParam Map<String, Object> params,
                        @PathVariable("customer_id") Integer customerId){
        PageUtils page = grgAccountService.queryPage(params,customerId);

        return Resp.success(page, "page");
    }

    /**
     * 信息
     */
    @ApiOperation(value = "根据customer_id查询账户信息")
    @ApiImplicitParam(name = "customer_id",value = "客户Id",required = true)
    @GetMapping("/info/{id}")
    //@grgAccountService("bank:grgaccount:info")
    public Resp info(@PathVariable("id") Integer id){
        List<GrgAccountEntity> entities = grgAccountService.getByCustomerId(id);

        return Resp.success(entities, "grgAccount");
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增客户信息")
    @ApiImplicitParam(name = "AccountEntity",value = "客户信息",required = true)
    @PostMapping("/save")
    //@grgAccountService("bank:grgaccount:save")
    public Resp save(@Valid @RequestBody GrgAccountEntity grgAccount){
        grgAccountService.save(grgAccount);

        return Resp.success().setMsg("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "根据card_no选择账户挂失")
    @ApiImplicitParam(name = "AccountEntity",value = "卡号",required = true)
    @PostMapping("/update")
    //@grgAccountService("bank:grgaccount:update")
    public Resp update(@RequestBody GrgAccountEntity grgAccount){
        boolean b = grgAccountService.updateByCardNo(grgAccount);
        if (!b){
            return Resp.failed().setMsg("更改失败");
        }

        return Resp.success().setMsg("更改成功");
    }

    /**
     * 删除
     */
    //TODO 删除状态变化
    @ApiOperation(value = "根据id删除客户信息")
    @ApiImplicitParam(name = "ids",value = "客户Ids",required = true)
    @DeleteMapping("/delete")
    //@grgAccountService("bank:grgaccount:delete")
    public Resp delete(@RequestBody Integer[] ids){
        boolean b = grgAccountService.removeByIds(Arrays.asList(ids));
        if (!b){
            return Resp.failed().setMsg("删除失败");
        }

        return Resp.success().setMsg("删除成功");
    }

}
