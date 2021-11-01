package com.grgbanking.counter.iam.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-29
 */
@Data
public class ItemVo {
    private Long id;
    private String type;
    private Map<String, String> value;
}
