package com.grgbanking.counter.iam.vo;

import lombok.Data;

/**
 * 字典表及字典表详情vo
 * @author: Ye Kaitao
 * @create: 2021-09-27
 */
@Data
public class DictWithItemVo {

    private Long dictId;
    private String type;
    private String description;

    private String value;
    private String label;
//    private Map<String,String> dictItem;


}
