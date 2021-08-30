package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.DateUtil;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.log.bo.SysLogBo;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.dao.SysLogDao;
import com.grgbanking.counter.iam.entity.SysLogEntity;
import com.grgbanking.counter.iam.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public IPage<SysLogEntity> queryList(Page page, String username,String ip,String startDate,String endDate) {
        if(StringUtils.isNotBlank(startDate)){
            Date strDate = DateUtil.stringToDate(startDate,"yyyy-MM-dd");
            startDate = DateUtil.format(strDate,"yyyy-MM-dd");
        }
        if(StringUtils.isNotBlank(endDate)){
            Date edDate =   DateUtil.stringToDate(endDate,"yyyy-MM-dd");
            endDate =DateUtil.format(edDate,"yyyy-MM-dd");
        }
        Long count = this.baseMapper.getPageCount(username, ip, startDate, endDate);
        page.setPages(count % page.getSize() == 0 ? count / page.getSize() : count / page.getSize() + 1);
        page.setTotal(count);
        //条件分页模糊查询（自定义返回数据）
        Long startIndex = (page.getCurrent() - 1) * page.getSize();
        Long endIndex = page.getCurrent() * page.getSize();

        List<SysLogEntity> logList = this.baseMapper.getPageList(username,ip,startDate,endDate,startIndex,endIndex,page.getSize());
        page.setRecords(logList);

        return page;
    }

    @Override
    public Boolean saveSysLog(SysLogBo sysLogBo) {
        SysLogEntity sysLog = new SysLogEntity();
        BeanUtils.copyProperties(sysLogBo, sysLog);
        sysLog.setId(UUIDUtils.idNumber());
        if(StringUtils.isEmpty(sysLogBo.getCreatedBy())){
            sysLog.setCreatedBy(SecurityContextUtil.getUsername());
        }
        sysLog.setCreationDate(new DateTime());
        int row = this.baseMapper.insert(sysLog);
        if(row > 0){
            return true;
        }else{
            return false;
        }
    }

}
