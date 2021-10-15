package com.grgbanking.counter.app.tencent.entity;

import com.tencentcloudapi.faceid.v20180301.models.IdCardOCRVerificationResponse;
import lombok.Data;

import java.util.Date;

@Data
public class IdCardOCRResponse extends IdCardOCRVerificationResponse {

    private Date validDate;

}
