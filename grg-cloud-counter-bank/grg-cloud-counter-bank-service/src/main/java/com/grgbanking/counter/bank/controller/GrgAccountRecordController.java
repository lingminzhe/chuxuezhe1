package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import com.grgbanking.counter.bank.service.GrgAccountRecordService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.util.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 银行卡业务操作记录表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@RestController
@RequestMapping("bank/accountrecord")
public class GrgAccountRecordController {
    @Autowired
    private GrgAccountRecordService grgAccountRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   //@grgAccountService("bank:grgaccountrecord:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgAccountRecordService.queryPage(params);

        return Resp.success(page,"page");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
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
    //TODO
    @ApiOperation(value = "根据customer_id查询账户信息")
    @ApiImplicitParam(name = "customer_id",value = "客户Id",required = true)
    @RequestMapping("/getAccountRecordById/{id}")
    //@grgAccountService("bank:grgaccount:info")
    public Resp getAccountById(@PathVariable("id") Integer id){
        List<GrgAccountRecordEntity> entities = grgAccountRecordService.getByCustomerId(id);
//        GrgAccountRecordEntity entity = grgAccountRecordService.getByCustomerId(id);

        return Resp.success(entities, "entity");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   //@grgAccountService("bank:grgaccountrecord:save")
    public Resp save(@RequestBody GrgAccountRecordEntity grgAccountRecord){
		grgAccountRecordService.save(grgAccountRecord);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   //@grgAccountService("bank:grgaccountrecord:update")
    public Resp update(@RequestBody GrgAccountRecordEntity grgAccountRecord){
		grgAccountRecordService.updateById(grgAccountRecord);

        return Resp.success();    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   //@grgAccountService("bank:grgaccountrecord:delete")
    public Resp delete(@RequestBody Integer[] ids){
		grgAccountRecordService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
