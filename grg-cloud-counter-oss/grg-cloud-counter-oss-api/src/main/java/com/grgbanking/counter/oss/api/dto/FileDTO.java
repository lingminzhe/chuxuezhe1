package com.grgbanking.counter.oss.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileDTO implements Serializable {

    private static final long serialVersionUID = -3275256067334372054L;

    String original;

    String fileName;

    String md5;

    long size;

    String contentType;

    String url;

    String userId;

}
