package com.grgbanking.counter.csr.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@Data
public class GetFileDto {
    private String employeeId;
    private List<String> fileBusiType;
}
