package com.grgbanking.counter.iam.controller;


import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.iam.api.entity.SysDictEntity;
import com.grgbanking.counter.iam.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    //@REQUIREsPermissions("iam:sysdict:list")
    public Resp list(@RequestParam Map<String, Object> params){
        //分页查询所以
        PageUtils page = sysDictService.queryPage(params);
        //直接查询所有
        List<SysDictEntity> list = sysDictService.list();
        return Resp.success(list," page");
    }

    @GetMapping("/listAll")
//    //@REQUIREsPermissions("iam:sysdict:list")
    public Resp listAll(){

        //查询所有
        List<Map<String, Map<String, String>>> list = sysDictService.listDictWithItem();



        return Resp.success(list," list");
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{dictId}")
    //@REQUIREsPermissions("iam:sysdict:info")
    public Resp info(@PathVariable("dictId") Long dictId){
       SysDictEntity sysDict = sysDictService.getById(dictId);

        return Resp.success(sysDict, "sysDict");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@REQUIREsPermissions("iam:sysdict:save")
    public Resp save(@RequestBody SysDictEntity sysDict){
        sysDictService.save(sysDict);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@REQUIREsPermissions("iam:sysdict:update")
    public Resp update(@RequestBody SysDictEntity sysDict){
        sysDictService.updateById(sysDict);

        return Resp.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@REQUIREsPermissions("iam:sysdict:delete")
    public Resp delete(@RequestBody Long[] dictIds){
        sysDictService.removeByIds(Arrays.asList(dictIds));

        return Resp.success();
    }


}

