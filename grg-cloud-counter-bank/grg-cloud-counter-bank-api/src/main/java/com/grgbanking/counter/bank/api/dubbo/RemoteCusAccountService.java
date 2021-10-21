package com.grgbanking.counter.bank.api.dubbo;


import com.grgbanking.counter.bank.api.entity.CreditCardEntity;
import com.grgbanking.counter.bank.api.vo.BankCardVo;

public interface RemoteCusAccountService {

    Boolean acvCard(CreditCardEntity creditCardEntity);

    Boolean verifyCardPwd(BankCardVo bankCardVo);

    Boolean updateCardStatus(BankCardVo bankCardVo);

}
