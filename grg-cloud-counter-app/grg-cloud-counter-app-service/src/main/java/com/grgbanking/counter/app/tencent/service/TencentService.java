package com.grgbanking.counter.app.tencent.service;

import com.alibaba.fastjson.JSON;
import com.grgbanking.counter.app.tencent.entity.TencentEidToken;
import com.grgbanking.counter.app.tencent.entity.TencentUserInfo;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;
import com.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class TencentService {

    @Value("${tencent.video.sdkAppId}")
    private Integer sdkAppId;

    @Value("${tencent.video.secretKey}")
    private String secretKey;

    @Value("${tencent.e-certification.secretId}")
    private String secretId;

    @Value("${tencent.e-certification.secretKey}")
    private String apiSecretKey;

    @Value("${tencent.e-certification.merchantId}")
    private String merchantId;

    private final Credential cred = new Credential();

    private final ClientProfile clientProfile = new ClientProfile();

    @PostConstruct
    public void init(){
        cred.setSecretId(secretId);
        cred.setSecretKey(apiSecretKey);
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("faceid.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        clientProfile.setHttpProfile(httpProfile);
    }

    public LivenessRecognitionResponse liveFaceid(LivenessRecognitionRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个LivenessRecognitionResponse的实例，与请求对象对应
            LivenessRecognitionResponse resp = client.LivenessRecognition(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LivenessCompareResponse liveFaceComparison(LivenessCompareRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个LivenessCompareResponse的实例，与请求对象对应
            LivenessCompareResponse resp = client.LivenessCompare(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LivenessResponse liveCheck(LivenessRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个LivenessResponse的实例，与请求对象对应
            LivenessResponse resp = client.Liveness(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageRecognitionResponse imageRecognition(ImageRecognitionRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个ImageRecognitionResponse的实例，与请求对象对应
            ImageRecognitionResponse resp = client.ImageRecognition(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GetActionSequenceResponse actionSequence(GetActionSequenceRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个GetActionSequenceResponse的实例，与请求对象对应
            GetActionSequenceResponse resp = client.GetActionSequence(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserSig(String userId){
        TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppId, secretKey);
        String userSig = api.genUserSig(userId, 180 * 86400);
        return userSig;
    }

    /**
     *
     */
    public TencentEidToken eidRequest(TencentUserInfo userInfo){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            GetEidTokenRequest req = new GetEidTokenRequest();
            req.setMerchantId(merchantId);
            req.setIdCard(userInfo.getIdCard());
            req.setName(userInfo.getName());
            req.setIdCard(userInfo.getName());
            GetEidTokenConfig getEidTokenConfig1 = new GetEidTokenConfig();
            getEidTokenConfig1.setInputType(userInfo.getType());
            // 返回的resp是一个GetEidTokenResponse的实例，与请求对象对应
            GetEidTokenResponse resp = client.GetEidToken(req);
            TencentEidToken tencentEidToken = new TencentEidToken();
            tencentEidToken.setEidToken(resp.getEidToken());
            tencentEidToken.setUrl(resp.getUrl());
            return tencentEidToken;
        } catch (TencentCloudSDKException e) {
            log.error("腾讯api调用失败:{}", e);
        }
        return null;
    }

    public GetEidResultResponse eidResPonse(TencentEidToken eidToken){
        try {
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            GetEidResultRequest req = new GetEidResultRequest();
            req.setEidToken(eidToken.getEidToken());
            // 返回的resp是一个GetEidResultResponse的实例，与请求对象对应
            GetEidResultResponse resp = client.GetEidResult(req);
            log.info(JSON.toJSONString(resp));
            DetectInfoText text = resp.getText();
            if (text == null || text.getErrCode() != 0){
                return null;
            }
            return resp;
        } catch (TencentCloudSDKException e) {
            log.error("腾讯api调用失败:{}", e);
        }
        return null;
    }

    public CheckIdNameDateResponse checkIdNameDate(CheckIdNameDateRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 返回的resp是一个CheckIdNameDateResponse的实例，与请求对象对应
            CheckIdNameDateResponse resp = client.CheckIdNameDate(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CheckBankCardInformationResponse checkBankCardInfo(CheckBankCardInformationRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            CheckBankCardInformationResponse resp = client.CheckBankCardInformation(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CheckIdCardInformationResponse checkIdCardInfo(CheckIdCardInformationRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            CheckIdCardInformationResponse resp = client.CheckIdCardInformation(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IdCardOCRVerificationResponse idCardOCRVeri(IdCardOCRVerificationRequest req){
        try {
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            IdCardOCRVerificationResponse resp = client.IdCardOCRVerification(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

}
