package com.grgbanking.counter.bank.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusAccountEntity;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import com.grgbanking.counter.bank.service.GrgAccountService;
import com.grgbanking.counter.bank.service.GrgCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
    public GrgCusInfoEntity findCusInfo(String user_id) {
        GrgCustomerEntity grgCustomerEntity = grgCustomerService.getById(user_id);
        GrgCusInfoEntity grgCusInfoEntiry = new GrgCusInfoEntity();
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
    public GrgCusInfoEntity getByCardNoOrIdNo(String no) {
        QueryWrapper<GrgCustomerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("identifynumber", no).or().eq("phone", no);
        GrgCustomerEntity grgCustomerEntity = grgCustomerService.getOne(wrapper);
        GrgCusInfoEntity grgCusInfoEntity = new GrgCusInfoEntity();
        if (grgCustomerEntity != null){
            BeanUtils.copyProperties(grgCustomerEntity, grgCusInfoEntity);
        }
        return grgCusInfoEntity;
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
    public boolean bindBankCard(GrgCusAccountEntity grgCusAccountEntity) {
        UpdateWrapper<GrgAccountEntity> wrapper = new UpdateWrapper<>();
        GrgAccountEntity grgAccountEntity = new GrgAccountEntity();
        grgAccountEntity.setBind("1");
        wrapper.eq("customer_id", grgCusAccountEntity.getCustomerId());
        wrapper.eq("card_no", grgCusAccountEntity.getCardNo());
        return grgAccountService.update(grgAccountEntity, wrapper);
    }
}
