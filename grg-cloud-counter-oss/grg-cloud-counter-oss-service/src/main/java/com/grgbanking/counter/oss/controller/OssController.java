package com.grgbanking.counter.oss.controller;

import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.annotation.MultiRequestBody;
import com.grgbanking.counter.oss.config.OssProperties;
import com.grgbanking.counter.oss.service.OssService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * aws 对外提供服务端点
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/oss")
public class OssController {

	private final OssService ossService;

	private final OssProperties ossProperties;

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	// TODO 同时保存相关信息到数据库
	@SneakyThrows
	@PostMapping("/upload")
	public Resp upload(@RequestBody MultipartFile file) {
		String original = file.getOriginalFilename();
		String md5 = FileUtil.getFileMd5(file);
		String fileName = FileUtil.randomFileName();
//		template.putObject(ossProperties.getBucketName(), fileName, file.getInputStream(), file.getSize(), file.getContentType());
//		return Resp.success(template.getObjectURL(ossProperties.getBucketName(), fileName, 7));
		return null;
	}

	/**
	 * 查询列表
	 * @param fileType
	 * @param userId
	 * @return
	 */
	// TODO 从数据库查询
	@SneakyThrows
	@GetMapping("/list")
	public Resp<List> list(@MultiRequestBody String fileType,@MultiRequestBody String userId) {
		return Resp.success();

	}

	/**
	 * 查询单个文件
	 * @param fileName
	 * @return
	 */
	// TODO 从数据库查询
	@SneakyThrows
	@GetMapping("/query/{fileName}")
	public Resp queryFile(@PathVariable String fileName) {
		Map<String, Object> responseBody = new HashMap<>(8);
		// Put Object info
		responseBody.put("fileName", fileName);
//		responseBody.put("url", template.getObjectURL(ossProperties.getBucketName(), fileName, 7));
		responseBody.put("expires", 7);
		return Resp.success(responseBody);
	}

	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	// TODO 同时删除数据库和S3
	@SneakyThrows
	@GetMapping("/delete/{fileName}")
	public Resp deleteObject(@PathVariable String fileName) {
//		template.removeObject(ossProperties.getBucketName(), fileName);
		return Resp.success();
	}

}
