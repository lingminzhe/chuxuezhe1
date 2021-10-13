package com.grgbanking.counter.device.tencent.controller;

import com.grgbanking.counter.device.tencent.entity.TencentEidToken;
import com.grgbanking.counter.device.tencent.entity.TencentUserInfo;
import com.grgbanking.counter.device.tencent.service.TencentService;
import com.grgbanking.counter.common.core.util.Resp;
import com.tencentcloudapi.faceid.v20180301.models.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "腾讯相关接口")
@RestController
@RequestMapping("/tencent")
public class TencentController {

    @Autowired
    TencentService tencentService;

    /**
     * 活体人脸核身
     * @param req
     * @return
     */
    @ApiOperation("活体人脸核身")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idCard", value = "身份证号", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "videoBase64", value = "用于活体检测的视频，视频的BASE64值", required = true),
            @ApiImplicitParam(name = "livenessType", value = "LIP为数字模式，ACTION为动作模式，SILENT为静默模式", required = true)
    })
    @PostMapping("/live/faceid")
    public Resp<LivenessRecognitionResponse> liveFaceid(@RequestBody LivenessRecognitionRequest req) {
        return Resp.success(tencentService.liveFaceid(req));
    }

    /**
     * 活体人脸比对
     * @param req
     * @return
     */
    @ApiOperation("活体人脸比对")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageBase64", value = "人脸比对的照片", required = true),
            @ApiImplicitParam(name = "videoBase64", value = "用于活体检测的视频，视频的BASE64值", required = true),
            @ApiImplicitParam(name = "livenessType", value = "LIP为数字模式，ACTION为动作模式，SILENT为静默模式", required = true)
    })
    @PostMapping("/live/face/comparison")
    public Resp<LivenessCompareResponse> liveFaceComparison(@RequestBody LivenessCompareRequest req) {
        return Resp.success(tencentService.liveFaceComparison(req));
    }

    /**
     * 活体检测
     * @param req
     * @return
     */
    @ApiOperation("活体检测")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoBase64", value = "用于活体检测的视频，视频的BASE64值", required = true),
            @ApiImplicitParam(name = "livenessType", value = "LIP为数字模式，ACTION为动作模式，SILENT为静默模式", required = true)
    })
    @ApiResponses({@ApiResponse(code = 200, message = "ok")})
    @PostMapping("/live/check")
    public Resp<LivenessResponse> liveCheck(@RequestBody LivenessRequest req) {
        return Resp.success(tencentService.liveCheck(req));
    }

    /**
     * 照片人脸核身
     * @param req
     * @return
     */
    @ApiOperation("照片人脸核身")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idCard", value = "身份证号", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "imageBase64", value = "人脸比对的照片", required = true)
    })
    @PostMapping("/image/recognition")
    public Resp<ImageRecognitionResponse> imageRecognition(@RequestBody ImageRecognitionRequest req) {
        return Resp.success(tencentService.imageRecognition(req));
    }

    /**
     * 获取动作顺序
     * @param req
     * @return
     */
    @ApiOperation("获取动作顺序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idCard", value = "身份证号", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "imageBase64", value = "人脸比对的照片", required = true)
    })
    @PostMapping("/action/sequence")
    public Resp<GetActionSequenceResponse> actionSequence(@RequestBody GetActionSequenceRequest req) {
        return Resp.success(tencentService.actionSequence(req));
    }

    /**
     * 获取视频通讯userSig
     * @param userId
     * @return
     */
    @CrossOrigin
    @ApiOperation("获取视频通讯userSig")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true)
    @PostMapping("/getUserSig")
    public Resp<String> getUserSig(String userId) {
        System.out.println(userId);
        return Resp.success(tencentService.getUserSig(userId));
    }

    /**
     * 获取e证通token和认证链接
     * @param userInfo
     * @return
     */
    @ApiOperation("获取e证通token和认证链接")
    @PostMapping("/eid/token")
    public Resp<TencentEidToken> eidToken(@RequestBody TencentUserInfo userInfo) {
        return Resp.success(tencentService.eidRequest(userInfo));
    }

    /**
     * 获取e证通认证结果
     * @param eidToken
     * @return
     */
    @ApiOperation("获取e证通认证结果")
    @PostMapping("/eid/result")
    public Resp<GetEidResultResponse> eidResult(@RequestBody TencentEidToken eidToken) {
        return Resp.success(tencentService.eidResPonse(eidToken));
    }

    /**
     * 身份信息及有效期核验
     * @param req
     * @return
     */
    @ApiOperation("身份信息及有效期核验")
    @PostMapping("/check/id/name")
    public Resp<CheckIdNameDateResponse> checkIdNameDate(@RequestBody CheckIdNameDateRequest req) {
        return Resp.success(tencentService.checkIdNameDate(req));
    }

    /**
     * 银行卡基础信息查询
     * @param req
     * @return
     */
    @ApiOperation("银行卡基础信息查询")
    @PostMapping("/check/bank/card")
    public Resp<CheckBankCardInformationResponse> checkBankCardInfo(@RequestBody CheckBankCardInformationRequest req) {
        return Resp.success(tencentService.checkBankCardInfo(req));
    }

    /**
     * 身份证人像照片验真
     * @param req
     * @return
     */
    @ApiOperation("身份证人像照片验真")
    @PostMapping("/check/id/card")
    public Resp<CheckIdCardInformationResponse> checkIdCardInfo(@RequestBody CheckIdCardInformationRequest req) {
        return Resp.success(tencentService.checkIdCardInfo(req));
    }

    /**
     * 身份证识别及信息核验
     * @param req
     * @return
     */
    @ApiOperation("身份证识别及信息核验")
    @PostMapping("/id/card/ocr/veri")
    public Resp<IdCardOCRVerificationResponse> idCardOCRVeri(@RequestBody IdCardOCRVerificationRequest req) {
        return Resp.success(tencentService.idCardOCRVeri(req));
    }

}
