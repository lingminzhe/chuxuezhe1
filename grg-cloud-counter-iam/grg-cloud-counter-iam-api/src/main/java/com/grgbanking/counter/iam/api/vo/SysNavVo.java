package com.grgbanking.counter.iam.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lggui1
 * @Date 2021/1/25
 * @Description
 **/
@Data
public class SysNavVo implements Serializable {

    private static final long serialVersionUID = 7588975040965825678L;

    private List<SysMenuTreeVo> menuList = null;

    private List<String> authorityList = null;

}
