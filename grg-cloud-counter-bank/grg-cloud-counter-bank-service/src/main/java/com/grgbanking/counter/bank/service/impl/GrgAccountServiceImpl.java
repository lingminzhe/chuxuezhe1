package com.grgbanking.counter.bank.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.bank.dao.GrgAccountDao;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.service.GrgAccountService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("grgAccountService")
public class GrgAccountServiceImpl extends ServiceImpl<GrgAccountDao, GrgAccountEntity> implements GrgAccountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgAccountEntity> page = this.page(
                new Query<GrgAccountEntity>().getPage(params),
                new QueryWrapper<GrgAccountEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据客户id查出名下账户信息
     * @param id
     * @return
     */
    @Override
    public List<GrgAccountEntity> getByCustomerId(Integer id) {

     /*   List<GrgAccountEntity> status = this.baseMapper.selectList(Wrappers.<GrgAccountEntity>lambdaQuery().eq(GrgAccountEntity::getAccountStatus, "status"));

        //查出所有信息
        List<GrgAccountEntity> entities = this.baseMapper.selectList(null);
        //
        List<GrgAccountEntity> list = entities.stream().filter(grgAccountEntity ->
                grgAccountEntity.getCustomerId().equals(id)
        ).collect(Collectors.toList());*/

        List<GrgAccountEntity> list1 = this.baseMapper.selectList(Wrappers.<GrgAccountEntity>lambdaQuery()
                .eq(GrgAccountEntity::getCustomerId, id));


        return list1;
    }

    /**
     *
     * @param params
     * @param customerId
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer customerId) {
        //若传入的customerId为0 则
        if (customerId == 0) {
            IPage<GrgAccountEntity> page = this.page(
                    new Query<GrgAccountEntity>().getPage(params),
                    new QueryWrapper<GrgAccountEntity>()
            );
            return new PageUtils(page);
        }else {
            String key = (String) params.get("key");
            QueryWrapper<GrgAccountEntity> wrapper = new QueryWrapper<GrgAccountEntity>().eq("customer_id",customerId);
            if (!StringUtils.isEmpty(key)){
                wrapper.and(obj->{
                   obj.eq("id",key).or().like("card_no",key);
                });
            }
            IPage<GrgAccountEntity> page = this.page(new Query<GrgAccountEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }


    }

    @Override
    public boolean updateByCardNo(GrgAccountEntity grgAccount) {
        UpdateWrapper<GrgAccountEntity> wrapper = new UpdateWrapper<GrgAccountEntity>().eq("card_no", grgAccount.getCardNo()).set("account_status",grgAccount.getAccountStatus());
        int delete = this.baseMapper.update(null,wrapper);
        if( delete == 1){
            return true;
        }else {
            return false;
        }
    }

}