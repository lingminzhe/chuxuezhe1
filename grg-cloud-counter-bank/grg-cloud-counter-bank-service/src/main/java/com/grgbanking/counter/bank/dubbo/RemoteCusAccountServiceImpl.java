package com.grgbanking.counter.bank.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusAccountService;
import com.grgbanking.counter.bank.api.entity.CreditCardEntity;
import com.grgbanking.counter.bank.api.vo.BankCardVo;
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
        GrgAccountEntity grgAccountEntity = grgAccountService.getOne(new QueryWrapper<GrgAccountEntity>().
                eq("card_no", creditCardEntity.getAccountId()));
        if (grgAccountEntity == null) {
            return null;
        } else {
            if (grgAccountEntity.getCvvCode().equals(creditCardEntity.getCvvCode())) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public Boolean verifyCardPwd(BankCardVo bankCardVo) {
        GrgAccountEntity grgAccountEntity = grgAccountService.getOne(new QueryWrapper<GrgAccountEntity>().eq("card_no", bankCardVo.getCardNo())
                .eq("card_pwd", bankCardVo.getCardPwd()));
        if (grgAccountEntity == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateCardStatus(BankCardVo bankCardVo) {
        GrgAccountEntity account = new GrgAccountEntity();
        account.setCardNo(bankCardVo.getCardNo());
        account.setAccountStatus(bankCardVo.getAccountStatus());
        boolean b = grgAccountService.updateByCardNo(account);
        return b;
    }
}
