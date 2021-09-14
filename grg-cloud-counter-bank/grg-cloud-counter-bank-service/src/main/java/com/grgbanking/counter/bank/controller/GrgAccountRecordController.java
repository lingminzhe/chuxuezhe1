package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import com.grgbanking.counter.bank.service.GrgAccountRecordService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 银行卡业务操作记录表
 *
 * @author GRGBanking
 * @date 2021-09-03
 */
@Api(tags = "银行卡账户交易信息")
@RestController
@RequestMapping("bank/accountrecord")
public class GrgAccountRecordController {
    @Autowired
    private GrgAccountRecordService grgAccountRecordService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询所有银行卡账户交易信息")
    @GetMapping("/list")
   //@grgAccountService("bank:grgaccountrecord:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgAccountRecordService.queryPage(params);

        return Resp.success(page,"page");
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据id查询账户流水信息")
    @GetMapping("/info/{id}")
   //@grgAccountService("bank:grgaccountrecord:info")
    public Resp info(@PathVariable("id") Integer id){
		GrgAccountRecordEntity grgAccountRecord = grgAccountRecordService.getById(id);

        return Resp.success(grgAccountRecord,"grgAccountRecord");
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据account_id查询账户信息")
    @ApiImplicitParam(name = "customer_id",value = "客户Id",required = true)
    @GetMapping("/getAccountRecordById/{id}")
    //@grgAccountService("bank:grgaccount:info")
    public Resp getAccountById(@PathVariable("id") Integer id){
        List<GrgAccountRecordEntity> entities = grgAccountRecordService.getByCustomerId(id);
//        GrgAccountRecordEntity entity = grgAccountRecordService.getByCustomerId(id);

        return Resp.success(entities, "entities");
    }



    /**
     * 保存
     */
    @Transactional
    @ApiOperation(value = "新增账户交易流水信息")
    @PostMapping("/save")
   //@grgAccountService("bank:grgaccountrecord:save")
    public Resp save(@RequestBody GrgAccountRecordEntity grgAccountRecord){
        grgAccountRecordService.save(grgAccountRecord);

        return Resp.success().setCode(200);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "根据accountId选择修改流水记录")
    @PostMapping("/update")
   //@grgAccountService("bank:grgaccountrecord:update")
    public Resp update(@RequestBody GrgAccountRecordEntity grgAccountRecord){
		grgAccountRecordService.updateById(grgAccountRecord);

        return Resp.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据Id选择删除流水记录")
    @DeleteMapping("/delete")
   //@grgAccountService("bank:grgaccountrecord:delete")
    public Resp delete(@RequestBody Integer[] ids){
		grgAccountRecordService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
