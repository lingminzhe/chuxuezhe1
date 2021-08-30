package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import com.grgbanking.counter.iam.api.vo.SysOrgVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lggui1
 * @date 2021年1月6日
 */
@Service
public interface SysOrgInnerService extends IService<SysOrgEntity> {

    /***
     * @Description 根据机构编码列表查询机构数据
     * @param orgCodeList
     * @return
     **/
    List<SysOrgVo> List(List<String> orgCodeList);

}

