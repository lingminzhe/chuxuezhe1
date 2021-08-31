package com.grgbanking.counter.iam.service.impl;

import com.grgbanking.counter.iam.api.entity.SysLogEntity;
import com.grgbanking.counter.iam.dao.SysLogDao;
import com.grgbanking.counter.iam.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

}
