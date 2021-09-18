package com.grgbanking.counter.csr.vo;

/**
 * 业务办理数量 分为1个月和当日
 * @author: Ye Kaitao
 * @create: 2021-09-17
 */

import lombok.Data;

@Data
public class BusiOptNumVo {

    /**
     * 今日已完成业务量
     */
    private String todayBusiOptNum;

    /**
     * 今日呼入量
     */
    private String todayCallOptNum;

    /**
     * 当月已完成业务量
     */
    private String monthBusiOptNum;;

    /**
     * 当月呼入量
     */
    private String monthCallOptNum;

    /**
     * 当前排队数量
     */
    private String queueNum;


}
