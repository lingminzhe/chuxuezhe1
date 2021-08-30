package com.grgbanking;

import com.grgbanking.counter.iam.constans.AppCoreConstants;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

    @Test
    public void encode() {
//        System.out.println("Bearer b49b81aa-8281-4931-ab77-5d04a79e6005".substring(7));
        System.out.println(new BCryptPasswordEncoder().encode(AppCoreConstants.DEFAULT_PWD));
    }
}
