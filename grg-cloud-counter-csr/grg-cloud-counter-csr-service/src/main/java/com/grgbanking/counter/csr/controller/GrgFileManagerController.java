package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 附件管理表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@RestController
@RequestMapping("csr/grgfilemanager")
public class GrgFileManagerController {
    @Autowired
    private GrgFileManagerService grgFileManagerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("csr:grgfilemanager:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgFileManagerService.queryPage(params);

        return Resp.success(page, "page");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("csr:grgfilemanager:info")
    public Resp info(@PathVariable("id") String id){
		GrgFileManagerEntity grgFileManager = grgFileManagerService.getById(id);

        return Resp.success(grgFileManager, "grgFileManager");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("csr:grgfilemanager:save")
    public Resp save(@RequestBody GrgFileManagerEntity grgFileManager){
		grgFileManagerService.save(grgFileManager);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("csr:grgfilemanager:update")
    public Resp update(@RequestBody GrgFileManagerEntity grgFileManager){
		grgFileManagerService.updateById(grgFileManager);

        return Resp.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("csr:grgfilemanager:delete")
    public Resp delete(@RequestBody String[] ids){
		grgFileManagerService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
