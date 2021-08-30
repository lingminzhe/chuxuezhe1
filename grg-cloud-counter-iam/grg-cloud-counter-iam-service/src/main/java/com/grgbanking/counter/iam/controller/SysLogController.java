package com.grgbanking.counter.iam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysLogEntity;
import com.grgbanking.counter.iam.service.SysLogService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: grgms-sys
 * @description: 日志管理接口
 * @author: chainos
 * @create: 2021-02-04 15:02
 */
@Api(value = "日志管理接口", tags = "日志管理接口")
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询操作日志
     */
    @SysLog("查询操作日志")
    @GetMapping("/query/list")
    @ApiOperation(value = "查询操作日志", notes = "查询操作日志", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "IP地址", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false, dataType = "String", paramType = "query")
    })
    public Resp<IPage<SysLogEntity>> queryList(Page page, String username, String ip, String startDate, String endDate) {
        IPage<SysLogEntity> sysLogEntityIPage = sysLogService.queryList(page, username,ip,startDate,endDate);
        return Resp.ok(sysLogEntityIPage);
    }

}
