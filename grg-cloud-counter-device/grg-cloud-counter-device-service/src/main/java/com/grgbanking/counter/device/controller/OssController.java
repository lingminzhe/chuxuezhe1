package com.grgbanking.counter.device.controller;

import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * aws 对外提供服务端点
 *
 */
@RestController
@RequestMapping("/app")
public class OssController {

	@DubboReference
	private RemoteOssService remoteOssService;

	/**
     * 上传文件
	 * @param file
     * @return
     */
	@SneakyThrows
	@PostMapping("/file/upload")
	public Resp upload(@RequestBody MultipartFile file) {
		byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
		String md5 = FileUtil.getFileMd5(file);
		String original = file.getOriginalFilename();
		long size = file.getSize();
		String contentType = file.getContentType();
		return Resp.success(remoteOssService.upload(fileByte, md5, original, size, contentType));
	}

}
