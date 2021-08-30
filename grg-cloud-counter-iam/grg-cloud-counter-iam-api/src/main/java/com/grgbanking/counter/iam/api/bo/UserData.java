package com.grgbanking.counter.iam.api.bo;

import lombok.Data;

import java.util.List;

@Data
public class UserData {

    private SysUserBo userBo;

    private List<String> authorities;

}
