package com.grgbanking.counter.common.security.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GrgUser extends User implements Serializable{

    private static final long serialVersionUID = -4874895958582058938L;

    /**
     * 用户ID
     */
    @Getter
    @Setter
    private Long userId;
    /**
     * 用户名称
     */
    @Getter
    @Setter
    private String name;
    /**
     * 启用情况  0：未启用   1：启用
     */
    @Getter
    @Setter
    private String isEnabled;
    /**
     * 锁定情况  0：未锁定   1：锁定
     */
    @Getter
    @Setter
    private String isLocked;

    /**
     * 所属机构id
     */
    @Getter
    @Setter
    private Long joinOrgId;
    /**
     * 所属机构code
     */
    @Getter
    @Setter
    private String joinOrgCode;
    /**
     * 所属机构名称
     */
    @Getter
    @Setter
    private String joinOrgName;

    /**
     * 角色id列表
     */
    @Getter
    @Setter
    private List<Long> roleIdList;
    /**
     * 手机号
     */
    @Getter
    @Setter
    private String phone;
    /**
     * 邮箱
     */
    @Getter
    @Setter
    private String email;
    /**
     * 头像文件id
     */
    @Getter
    @Setter
    private String avatarId;

    public GrgUser(Long userId,String username, String password,String phone, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.name = username;
        this.phone = phone;
    }

}
