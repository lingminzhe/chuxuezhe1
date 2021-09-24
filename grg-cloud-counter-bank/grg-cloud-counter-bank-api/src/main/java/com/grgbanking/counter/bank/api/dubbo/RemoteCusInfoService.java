package com.grgbanking.counter.bank.api.dubbo;

import com.grgbanking.counter.bank.api.entity.GrgCusAccountEntity;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntiry;

import java.util.List;

public interface RemoteCusInfoService {

    GrgCusInfoEntiry findCusInfo(String userId);

    List<GrgCusAccountEntity> findCusAccountList(String customerId);

    GrgCusAccountEntity findCusAccount(String id);

    boolean saveBankCard(GrgCusAccountEntity account);

    void deleteBankCard();

    GrgCusAccountEntity searchCusAccount(GrgCusAccountEntity grgCusAccountEntity);
}
