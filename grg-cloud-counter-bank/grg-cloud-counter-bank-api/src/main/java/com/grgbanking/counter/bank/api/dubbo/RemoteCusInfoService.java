package com.grgbanking.counter.bank.api.dubbo;

import com.grgbanking.counter.bank.api.entity.GrgCusAccountEntity;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;

import java.util.List;

public interface RemoteCusInfoService {

    GrgCusInfoEntity findCusInfo(String userId);

    List<GrgCusAccountEntity> findCusAccountList(String customerId);

    GrgCusAccountEntity findCusAccount(String id);

    GrgCusInfoEntity getByCardNoOrIdNo(String no);

    boolean updateCusInfo(GrgCusInfoEntity cusInfoEntity);

    boolean saveBankCard(GrgCusAccountEntity account);

    void deleteBankCard();

    boolean bindBankCard(GrgCusAccountEntity grgCusAccountEntity);
}
