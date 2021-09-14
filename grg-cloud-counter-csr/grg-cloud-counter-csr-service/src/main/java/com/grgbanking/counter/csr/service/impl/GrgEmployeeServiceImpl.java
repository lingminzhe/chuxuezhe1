package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgEmployeeServiceDao;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("grgEmployeeServiceService")
public class GrgEmployeeServiceImpl extends ServiceImpl<GrgEmployeeServiceDao, GrgEmployeeServiceEntity> implements GrgEmployeeService {

    //空闲状态码
    private static int freeStatus = 2;
    private static int workStatus = 3;


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
    public synchronized List<GrgEmployeeServiceEntity> getAllFreeEmployee() {

        //SELECT id,employee_id,employee_status,busi_no,create_time,update_time FROM grg_employee_service WHERE (employee_status = ?)
        QueryWrapper<GrgEmployeeServiceEntity> wrapper = new QueryWrapper<GrgEmployeeServiceEntity>().eq("employee_status", freeStatus);
        List<GrgEmployeeServiceEntity> entities = this.baseMapper.selectList(wrapper);

        return entities;
    }

    /**
     * 分配
     * @return
     */
    @Override
    public synchronized GrgEmployeeServiceEntity getFreeEmployee(String userId) {



        List<GrgEmployeeServiceEntity> list = getAllFreeEmployee();
        if (list!=null && list.size()>0 && list.get(0)!=null){
            //获取list中第一个空闲客服
            String employeeId = list.get(0).getEmployeeId();
            UpdateWrapper<GrgEmployeeServiceEntity> wrapper = new UpdateWrapper<GrgEmployeeServiceEntity>().eq("employee_id", employeeId).set("employee_status", workStatus);
            this.baseMapper.update(new GrgEmployeeServiceEntity(),wrapper);
            log.info("分配座席成功 座席id:"+employeeId);
            return list.get(0);
        }else {
            log.info("没有空闲队列");
            return null;
        }

    }

}