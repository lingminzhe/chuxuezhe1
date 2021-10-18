package com.grgbanking.counter.bank.dubbo;

import com.grgbanking.counter.bank.api.dubbo.RemoteCusAccountService;
import com.grgbanking.counter.bank.api.entity.CreditCardEntity;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.service.GrgAccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteCusAccountServiceImpl implements RemoteCusAccountService {

    @Autowired
    private GrgAccountService grgAccountService;

    @Override
    public Boolean acvCard(CreditCardEntity creditCardEntity) {
        GrgAccountEntity grgAccountEntity = grgAccountService.getById(creditCardEntity.getAccountId());
        if (grgAccountEntity == null){
            return null;
        }else {
            if (grgAccountEntity.getCvvCode().equals(creditCardEntity.getCvvCode())){
                return true;
            }else {
                return false;
            }
        }

    }
}
