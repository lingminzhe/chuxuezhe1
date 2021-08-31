package com.grgbanking.counter.common.security.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long userId;

    /**
     * 部门ID
     */
    @Getter
    private Long deptId;

    /**
     * 手机号
     */
    @Getter
    private String phone;

    /**
     * 头像
     */
    @Getter
    private String avatar;

    @JsonCreator
    public GrgUser(@JsonProperty("userId") Long userId, @JsonProperty("deptId") Long deptId,
                    @JsonProperty("phone") String phone, @JsonProperty("avatar") String avatar,
                    @JsonProperty("username") String userName, @JsonProperty("password") String password,
                    @JsonProperty("enabled") boolean enabled, @JsonProperty("accountNonExpired") boolean accountNonExpired,
                    @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired, @JsonProperty("accountNonLocked") boolean accountNonLocked,
                    @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.deptId = deptId;
        this.phone = phone;
        this.avatar = avatar;
    }
}
