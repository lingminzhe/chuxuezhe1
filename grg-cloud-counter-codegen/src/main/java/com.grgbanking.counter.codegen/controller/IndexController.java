package com.grgbanking.counter.codegen.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.grgbanking.counter.codegen.service.IIndexService;
import com.grgbanking.counter.codegen.vo.CodeGeneratorRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author grgbanking
 * @since 2021-07-12
 */
@Controller
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/code-generator")
    @ResponseBody
    public ResultTemplate<Void> codeGenerator(@RequestBody @Validated CodeGeneratorRequestVo requestVo) {
        return indexService.codeGenerator(requestVo);
    }

}
