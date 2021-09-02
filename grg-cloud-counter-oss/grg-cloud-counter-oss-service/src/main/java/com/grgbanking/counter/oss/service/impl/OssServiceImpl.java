package com.grgbanking.counter.oss.service.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.oss.config.OssProperties;
import com.grgbanking.counter.oss.service.OssService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssProperties ossProperties;

    private AmazonS3 amazonS3;

    // TODO 同时保存相关信息到数据库
    @SneakyThrows
    @Override
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        String original = file.getOriginalFilename();
        String md5 = FileUtil.getFileMd5(file);
        String fileName = FileUtil.randomFileName();
        String fileSize = FileUtil.getPrintSize(file.getSize());

        result.put("original", original);
        result.put("md5", md5);
        result.put("fileName", fileName);
        result.put("size", fileSize);
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        amazonS3.putObject(ossProperties.getBucketName(), fileName, byteArrayInputStream, objectMetadata);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // url有效期最多7天
        String url = amazonS3.generatePresignedUrl(ossProperties.getBucketName(), fileName, calendar.getTime()).toString();
        result.put("url", url);
        return result;
    }

    // TODO 从数据库查询
    @Override
    public Map<String, Object> list(String fileType, String userId) {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    // TODO 从数据库查询
    @Override
    public Map<String, Object> queryFileInfo(String fileName) {
        Map<String, Object> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // url有效期最多7天
        String url = amazonS3.generatePresignedUrl(ossProperties.getBucketName(), fileName, calendar.getTime()).toString();
        result.put("url", url);
        return result;
    }

    // TODO 同时删除数据库和S3
    @Override
    public boolean deleteFile(String fileName) {
        amazonS3.deleteObject(ossProperties.getBucketName(), fileName);
        return true;
    }


    @PostConstruct
	public void init() {
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setMaxConnections(ossProperties.getMaxConnections());
		AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
				ossProperties.getEndpoint(), ossProperties.getRegion());
		AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
				ossProperties.getSecretKey());
		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		this.amazonS3 = AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)
				.withClientConfiguration(clientConfiguration).withCredentials(awsCredentialsProvider)
				.disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
	}


}
