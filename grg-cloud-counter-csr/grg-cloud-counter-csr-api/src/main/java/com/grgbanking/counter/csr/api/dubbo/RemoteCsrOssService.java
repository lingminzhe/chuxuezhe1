package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.dto.UploadFileDTO;

import java.util.List;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
public interface RemoteCsrOssService {

    List<UploadFileDTO> getFileByCustomerId(String customerId, List<String> busiType);
}
