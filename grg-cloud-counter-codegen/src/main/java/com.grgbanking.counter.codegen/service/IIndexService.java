package com.grgbanking.counter.codegen.service;

import com.fengwenyi.api.result.ResultTemplate;
import com.grgbanking.counter.codegen.vo.CodeGeneratorRequestVo;

/**
 * @author grgbanking
 * @since 2021-07-12
 */
public interface IIndexService {

    /**
     * 生成代码
     * @param requestVo
     * @return
     */
    ResultTemplate<Void> codeGenerator(CodeGeneratorRequestVo requestVo);

}
