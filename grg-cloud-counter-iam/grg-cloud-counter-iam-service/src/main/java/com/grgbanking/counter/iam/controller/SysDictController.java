package com.grgbanking.counter.iam.controller;


import cn.hutool.core.util.StrUtil;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.iam.api.entity.SysDictEntity;
import com.grgbanking.counter.iam.api.entity.SysDictItemEntity;
import com.grgbanking.counter.iam.service.SysDictItemService;
import com.grgbanking.counter.iam.service.SysDictService;
import com.grgbanking.counter.iam.vo.DictWithItemVo;
import com.grgbanking.counter.iam.vo.ItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "数据字典表")
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysDictItemService sysDictItemService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询数据字典表")
    @GetMapping("/list")
    public Resp list(@RequestParam Map<String, Object> params){
        //分页查询所以
        PageUtils page = sysDictService.queryPage(params);
        //直接查询所有
        List<SysDictEntity> list = sysDictService.list();
        return Resp.success(list," page");
    }

    @Deprecated
    @ApiOperation(value = "查询数据字典字段")
    @GetMapping("/listAll")
    public Resp listAll(){
        //查询所有
        List<Map<String, Map<String, String>>> list = sysDictService.listDictWithItem();
        return Resp.success(list," 数据字典");
    }

    /**
     * 根据字典type 找到对应的字典列表
     * @param dict
     * @return
     */

    @Deprecated
    @PostMapping("/根据字典type 找到对应的字典列表")
    public Resp getDictItemByType(@RequestBody DictWithItemVo dict){
        String type = dict.getType();
        if (StrUtil.isNotEmpty(type)) {
            List<SysDictItemEntity> entities = sysDictItemService.getDictItemByType(type);
            return Resp.success(entities);
        }
        return Resp.failed("查不到当前字段,请联系管理员添加");
    }

    /**
     * 查找某个数据字典
     */
    @Deprecated
    @ApiOperation(value = "查找数据字典")
    @PostMapping("/getDictByType")
    public Resp getDictByType(@RequestBody SysDictEntity sysDict){
        List<DictWithItemVo> dict = sysDictService.getDictByType(sysDict);
        if (dict.size()==0){
            return Resp.failed(dict,"查不到当前字段,请联系管理员添加");
        }else {
            return Resp.success(dict, "type");
        }
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增数据字典")
    @PostMapping("/save")
    public Resp save(@RequestBody SysDictEntity sysDict){
        sysDictService.saveDictAndItem(sysDict);

        return Resp.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改数据字典")
    @PostMapping("/update")
    public Resp update(@RequestBody SysDictEntity sysDict){
        int i = sysDictService.updateDictById(sysDict);

        return Resp.success(i);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除数据字典")
    @PostMapping("/delete")
    public Resp delete(@RequestBody Long[] dictIds){
        sysDictService.removeDictById(Arrays.asList(dictIds));

        return Resp.success();
    }

    /**
     * 获取所有字典字段
     * @return
     */
    @ApiOperation(value = "获取所有字典字段")
    @GetMapping("/getAllDict")
    public Resp getAllDict(){
        List<ItemVo> itemVos = sysDictService.listDictWithItem1();

        return Resp.success(itemVos,"数据字典");
    }

    /**
     * 根据类型获取字典字段
     * @param type
     * @return
     */
    @ApiOperation(value = "根据类型获取字典字段")
    @GetMapping("/getByType")
    public Resp getByType(@RequestParam String type){
        List<ItemVo> itemVos = sysDictService.getByType(type);

        return Resp.success(itemVos,"数据字典");
    }




}

