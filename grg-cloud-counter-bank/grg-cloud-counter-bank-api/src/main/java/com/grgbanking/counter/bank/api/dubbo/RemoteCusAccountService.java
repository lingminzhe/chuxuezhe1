package com.grgbanking.counter.bank.api.dubbo;


import com.grgbanking.counter.bank.api.entity.CreditCardEntity;

public interface RemoteCusAccountService {

    Boolean acvCard(CreditCardEntity creditCardEntity);

}
