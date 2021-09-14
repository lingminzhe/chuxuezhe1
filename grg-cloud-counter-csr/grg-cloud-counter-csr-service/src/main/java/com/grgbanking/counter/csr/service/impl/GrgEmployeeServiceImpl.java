package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgEmployeeServiceDao;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("grgEmployeeServiceService")
public class GrgEmployeeServiceImpl extends ServiceImpl<GrgEmployeeServiceDao, GrgEmployeeServiceEntity> implements GrgEmployeeService {

    //空闲状态码
    private static int freeStatus = 2;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgEmployeeServiceEntity> page = this.page(
                new Query<GrgEmployeeServiceEntity>().getPage(params),
                new QueryWrapper<GrgEmployeeServiceEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据employee_id查找对应信息
     * @param id
     * @return
     */
    @Override
    public GrgEmployeeServiceEntity getByEmployeeId(String id) {
        QueryWrapper<GrgEmployeeServiceEntity> wrapper = new QueryWrapper<GrgEmployeeServiceEntity>().eq("employee_id", id);

        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 获取空闲座席
     * @return
     */
    @Override
    public List<GrgEmployeeServiceEntity> getFreeEmployee() {

        //SELECT id,employee_id,employee_status,busi_no,create_time,update_time FROM grg_employee_service WHERE (employee_status = ?)
        QueryWrapper<GrgEmployeeServiceEntity> wrapper = new QueryWrapper<GrgEmployeeServiceEntity>().eq("employee_status", freeStatus);

        //SELECT employee_status,busi_no FROM grg_employee_service WHERE (employee_status = ?)
//        QueryWrapper<GrgEmployeeServiceEntity> wrapper1 = new QueryWrapper<GrgEmployeeServiceEntity>().select("employee_status","busi_no").eq("employee_status",2);
//        List<GrgEmployeeServiceEntity> entities = baseMapper.selectList(wrapper1);
//        System.out.println(entities);

        return this.baseMapper.selectList(wrapper);
    }

}