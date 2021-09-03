package com.grgbanking.counter.iam.controller;


import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.iam.api.entity.SysDeptEntity;
import com.grgbanking.counter.iam.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @SysLog("查询部门列表")
    @GetMapping("/list")
    public List<SysDeptEntity> list(Long deptId,String userId){
        if (sysDeptService != null) {
            throw new CheckedException("报错"+deptId);
        }
        return sysDeptService.list();
    }

}

