package com.grgbanking.counter.csr.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgEmployeeServiceDao;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import com.grgbanking.counter.csr.vo.EmployeeCustomerVo;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("grgEmployeeServiceService")
public class GrgEmployeeServiceImpl extends ServiceImpl<GrgEmployeeServiceDao, GrgEmployeeServiceEntity> implements GrgEmployeeService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private GrgBusiOptService busiOptService;



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
        //1.
        QueryWrapper<GrgEmployeeServiceEntity> wrapper = new QueryWrapper<GrgEmployeeServiceEntity>().eq("employee_id", id);
        GrgEmployeeServiceEntity entity = this.baseMapper.selectOne(wrapper);
//        EmployeeInfoVo employeeInfoVo = new EmployeeInfoVo();
//        BeanUtils.copyProperties(entity,employeeInfoVo);

        //2.
        return entity;
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
    @Transactional
    @Override
    public synchronized EmployeeCustomerVo getFreeEmployee(String userId) {

        //获取所有空闲座席
        List<GrgEmployeeServiceEntity> list = getAllFreeEmployee();
        if (list!=null && list.size()>0 && list.get(0)!=null){
            //获取list中第一个空闲客服
            String employeeId = list.get(0).getEmployeeId();
            //分配客服并将status置为工作状态
            UpdateWrapper<GrgEmployeeServiceEntity> wrapper = new UpdateWrapper<GrgEmployeeServiceEntity>()
                    .eq("employee_id", employeeId)
                    .set("employee_status", workStatus);
            this.baseMapper.update(new GrgEmployeeServiceEntity(),wrapper);
            log.info("分配座席成功 座席id:"+employeeId);
            GrgEmployeeServiceEntity entity = list.get(0);

            EmployeeCustomerVo employeeCustomerVo = new EmployeeCustomerVo();
            BeanUtils.copyProperties(entity,employeeCustomerVo);
            employeeCustomerVo.setCustomerId(userId);

            //分配成功 生成流水号 并将联系写入grg_busi_opt 维护
            GrgBusiOptEntity busiOptEntity = new GrgBusiOptEntity();
            busiOptEntity.setCustomerId(userId);
            busiOptEntity.setUserId(employeeId);
            //(1、已完成  2、正在进行 3、未完成
            busiOptEntity.setBusiOptStatus("2");
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = LocalDate.now().format(fmt)+"";


            busiOptService.updateByBusiOptNo(busiOptEntity);

            return employeeCustomerVo;
        }else {
            log.info("没有空闲队列");
            return null;
        }
    }



    /**
     * 将座席设置为就绪
     * @param userId
     * @return
     */
    @Override
    public int setFreeEmployee(String userId, String employeeId) {

        int update = 0;

        if (StringUtils.isNotEmpty(employeeId)&&StringUtils.isNotEmpty(employeeId)){
            UpdateWrapper<GrgEmployeeServiceEntity> wrapper = new UpdateWrapper<GrgEmployeeServiceEntity>().eq("employee_id", employeeId).set("employee_status", freeStatus);
            update = this.baseMapper.update(new GrgEmployeeServiceEntity(), wrapper);
        }
        return update;

    }

    @Override
    public int updateByEmployeeId(GrgEmployeeServiceEntity grgEmployeeService) {
        UpdateWrapper<GrgEmployeeServiceEntity> wrapper = new UpdateWrapper<GrgEmployeeServiceEntity>().eq("employee_id", grgEmployeeService.getEmployeeId());

        return this.baseMapper.update(grgEmployeeService,wrapper);
    }


}