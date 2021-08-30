package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.log.bo.SysLogBo;
import com.grgbanking.counter.iam.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author MARK xx@grgbanking.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    IPage<SysLogEntity> queryList(Page page, String username, String ip, String startDate, String endDate);

    Boolean saveSysLog(SysLogBo sysLogBo);

}
