package com.grgbanking.counter.bank.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusAccountEntity;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntiry;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import com.grgbanking.counter.bank.service.GrgAccountService;
import com.grgbanking.counter.bank.service.GrgCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@DubboService
@Slf4j
public class RemoteCusInfoServiceImpl implements RemoteCusInfoService {

    @Autowired
    GrgCustomerService grgCustomerService;

    @Autowired
    GrgAccountService grgAccountService;

    @Override
    public GrgCusInfoEntiry findCusInfo(String user_id) {
        GrgCustomerEntity grgCustomerEntity = grgCustomerService.getById(user_id);
        GrgCusInfoEntiry grgCusInfoEntiry = new GrgCusInfoEntiry();
        BeanUtils.copyProperties(grgCustomerEntity, grgCusInfoEntiry);
        return grgCusInfoEntiry;
    }

    @Override
    public List<GrgCusAccountEntity> findCusAccountList(String customerId) {
        QueryWrapper<GrgAccountEntity> wrapper = new QueryWrapper();
        wrapper.eq("customer_id", customerId);
        List<GrgAccountEntity> accountList = grgAccountService.list(wrapper);
        List<GrgCusAccountEntity> accountEntityList = new ArrayList<>();
        accountList.forEach(account -> {
            GrgCusAccountEntity accountEntity = new GrgCusAccountEntity();
            BeanUtils.copyProperties(account, accountEntity);
            accountEntityList.add(accountEntity);
        });
        return accountEntityList;
    }

    @Override
    public GrgCusAccountEntity findCusAccount(String id) {
        GrgAccountEntity accountDto = grgAccountService.getById(id);
        GrgCusAccountEntity accountVo = new GrgCusAccountEntity();
        BeanUtils.copyProperties(accountDto, accountVo);
        return accountVo;
    }

    @Override
    public boolean saveBankCard(GrgCusAccountEntity account) {
        GrgAccountEntity grgAccountEntity = new GrgAccountEntity();
        BeanUtils.copyProperties(account, grgAccountEntity);
        return grgAccountService.save(grgAccountEntity);
    }

    @Override
    public void deleteBankCard() {

    }

    @Override
    public GrgCusAccountEntity searchCusAccount(GrgCusAccountEntity grgCusAccountEntity) {
        QueryWrapper<GrgAccountEntity> wrapper = new QueryWrapper();
        wrapper.eq("customer_id", grgCusAccountEntity.getCustomerId());
        wrapper.eq("card_no", grgCusAccountEntity.getCardNo());
        GrgAccountEntity one = grgAccountService.getOne(wrapper, true);
        GrgCusAccountEntity grgCusAccount = new GrgCusAccountEntity();
        BeanUtils.copyProperties(one, grgCusAccount);
        return grgCusAccount;
    }
}
