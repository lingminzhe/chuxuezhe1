package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import com.grgbanking.counter.csr.vo.EmployeeCustomerVo;
import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;




/**
 * 坐席客服表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13
 */
@Api(tags = "座席端服务信息")
@RestController
@RequestMapping("/empservice")
public class GrgEmployeeController {

    @Autowired
    private GrgEmployeeService grgEmployeeService;

    @DubboReference
    RemoteUserService remoteUserService;

    /**
     * 列表
     */
    @ApiOperation(value = "查看所有座席的状态")
    @GetMapping("/list")
    //@RequiresPermissions("csr:grgemployeeservice:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgEmployeeService.queryPage(params);

        return Resp.success(page, "page");
    }
    /**
     * 列表
     */
    @ApiOperation(value = "查看座席个人信息")
    @GetMapping("/employeeInfo")
    //@RequiresPermissions("csr:grgemployeeservice:list")
    public Resp employeeInfo(@RequestParam String name){
        UserInfo info = remoteUserService.info(name);

        return Resp.success(info, "page");
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据employee_id查询座席状态信息")
    @GetMapping("/info/{id}")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp info(@PathVariable("id") String id){
		GrgEmployeeServiceEntity grgEmployeeService = this.grgEmployeeService.getByEmployeeId(id);

        return Resp.success(grgEmployeeService, "grgEmployeeService");
    }

    /**
     * 获取所有空闲座席
     */
    //TODO 分配座席问题
    @Deprecated
    @ApiOperation(value = "获取所有空闲座席")
    @GetMapping("/getAllFreeEmployee")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp getAllFreeEmployee(){
         List<GrgEmployeeServiceEntity> list = grgEmployeeService.getAllFreeEmployee();

        return Resp.success(list, "空闲座席数量为："+list.size());
    }

    /**
     * 获取所有空闲座席
     */
    //TODO 分配座席问题
    @ApiOperation(value = "分配空闲座席")
    @GetMapping("/getFreeEmployee")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp getFreeEmployee(@RequestParam String id){

        EmployeeCustomerVo entity = grgEmployeeService.getFreeEmployee(id);

        if (entity == null){
            return Resp.failed("目前没有空闲座席");
        }
        return Resp.success(entity, "空闲座席");
    }



    /** setFreeEmployee
     * 保存
     */
    @Transactional
    @ApiOperation(value = "新增座席")
    @PostMapping("/save")
    //@RequiresPermissions("csr:grgemployeeservice:save")
    public Resp save(@RequestBody GrgEmployeeServiceEntity grgEmployeeService){
		this.grgEmployeeService.save(grgEmployeeService);

        return Resp.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "更改座席状态")
    @Transactional
    @PostMapping("/update")
    //@RequiresPermissions("csr:grgemployeeservice:update")
    public Resp update(@RequestBody GrgEmployeeServiceEntity grgEmployeeService, HttpServletRequest request){
        SysUserEntity sysUser = remoteUserService.currentUser(request.getHeader("Authorization"));
        grgEmployeeService.setEmployeeId(String.valueOf(sysUser.getUserId()));
        int i = this.grgEmployeeService.updateByEmployeeId(grgEmployeeService);
        if (i==0){
            return Resp.failed(i,"更改失败");
        }
        return Resp.success(i,"更改成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除座席")
    @DeleteMapping("/delete")
    //@RequiresPermissions("csr:grgemployeeservice:delete")
    public Resp delete(@RequestBody String[] ids){
        boolean b = grgEmployeeService.removeByIds(Arrays.asList(ids));
        if (!b){
            return Resp.failed();
        }

        return Resp.success(b,"删除成功");
    }

}
