package com.grgbanking.counter.iam.controller;


import com.grgbanking.counter.iam.service.SysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典项 前端控制器
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/sys/dict/item")
public class SysDictItemController {

    @Autowired
    private SysDictItemService sysDictItemService;



}

