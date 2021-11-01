package com.grgbanking.counter.bank.api;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.grgbanking.counter.common.core.encrypt.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-20
 * 短信接口平台
 */
@Slf4j
@Component
public class SmsApi {
    /**
     *	 * 输入参数：	说明
     * 	 * u	用户号码	用户手机号码，11位有效数字
     * 	 * p	认证明文	随机16位字符串
     * 	 * e	认证密文
     * 	 * t	时间戳
     * 	 * f	类型	固定传值（ycyh）
     * 	 * d	发送时间	传空值表示立即发送
     * 	 * c	发送内容
     * @param mobile
     * @throws IOException
     */
    public void getSmsApi(String mobile,String code) throws IOException {
        String url = "http://oa.grgbanking.com/interface/sms/phone_msg.php";
        String f = "ycyh";
        //生成随机16位字符串
        String randomString = RandomUtil.randomString(16);
        long millis = System.currentTimeMillis();
        String currentTimeMillis = String.valueOf(millis);
        String md5String = MD5Util.getMD5String("YcGrgBank" + currentTimeMillis + "BaRnkm" + mobile + randomString);

        StringBuilder s1 = new StringBuilder();
        String s2 = s1.append("?u=" + mobile + "&p=" + randomString
                + "&e=" + md5String) + "&t=" + currentTimeMillis + "&f=" + f
                + "&c=" + code;
        String s3 = url+s2;

        String result2 = HttpRequest.get(s3)
                .header(Header.USER_AGENT, "//头信息，多个头信息多次调用此方法即可Hutool http")
                //表单内容
//				.form(s2)
                //超时，毫秒
                .timeout(20000)
                .execute().body();
        log.info(result2);
    }
}
