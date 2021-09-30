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
import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.csr.api.dubbo.RemoteFileMgrService;
import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.iam.api.dubbo.RemoteSysFileService;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.config.OssProperties;
import com.grgbanking.counter.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.util.Calendar;

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @DubboReference
    RemoteFileMgrService remoteFileMgrService;

    @DubboReference
    RemoteSysFileService remoteSysFileService;

    @Autowired
    private OssProperties ossProperties;

    private AmazonS3 amazonS3;

    // TODO 同时保存相关信息到数据库
    @Override
    public FileDTO upload(byte[] fileByte, String md5, String original, long size, String contentType, GrgFileMgrEntity grgFileMgrEntity, String createUser) {
        FileDTO fileDTO = new FileDTO();
        String fileName = FileUtil.randomFileName();
        fileDTO.setOriginalName(original);
        fileDTO.setFileMd5(md5);
        fileDTO.setFileName(fileName);
        fileDTO.setFileSize(size);
        fileDTO.setFileType(contentType);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileByte);
        amazonS3.putObject(ossProperties.getBucketName(), fileName, byteArrayInputStream, objectMetadata);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // url有效期最多7天
        String url = amazonS3.generatePresignedUrl(ossProperties.getBucketName(), fileName, calendar.getTime()).toString();
        fileDTO.setUrl(url);
        fileDTO.setEnable("1");
        fileDTO.setBucketName(ossProperties.getBucketName());
        fileDTO.setCreateUser(createUser);
        remoteFileMgrService.save(grgFileMgrEntity);
        remoteSysFileService.save(fileDTO);
        return fileDTO;
    }

    // TODO 从数据库查询
    @Override
    public FileDTO list(String fileType, String userId) {
        return null;
    }

    // TODO 从数据库查询
    @Override
    public FileDTO queryFileInfo(String fileName) {
        FileDTO result = new FileDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // url有效期最多7天
        String url = amazonS3.generatePresignedUrl(ossProperties.getBucketName(), fileName, calendar.getTime()).toString();
        result.setUrl(url);
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
