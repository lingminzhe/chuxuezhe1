package com.grgbanking.counter.app.tencent.entity;

import com.tencentcloudapi.faceid.v20180301.models.ImageRecognitionRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ImageRecognitionv2Request extends ImageRecognitionRequest implements Serializable {

    @Getter
    @Setter
    public String customerId;

}
