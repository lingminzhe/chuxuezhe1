package com.grgbanking.counter.common.core.util;


/**
 * @Author:mcyang
 * @Date:2020/12/3 8:41 上午
 */
public class BankIdentifierGenerator {

    private final BankSequence sequence;

    public BankIdentifierGenerator() {
        this.sequence = new BankSequence();
    }

    public BankIdentifierGenerator(long workerId, long dataCenterId) {
        this.sequence = new BankSequence(workerId, dataCenterId);
    }

    public BankIdentifierGenerator(BankSequence sequence) {
        this.sequence = sequence;
    }

    public Long nextId(Object entity) {
        return this.sequence.nextId();
    }

}
