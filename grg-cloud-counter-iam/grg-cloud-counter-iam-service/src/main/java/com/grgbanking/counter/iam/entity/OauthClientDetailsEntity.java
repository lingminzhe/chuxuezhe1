package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统客户端资源列表
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetailsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID(主键)
     */
    @TableId
    private Long id;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔
     */
    private String resourceIds;
    /**
     * 用于指定客户端(client)的访问密匙
     */
    private String clientSecret;
    /**
     * 指定客户端申请的权限范围
     */
    private String scope;
    /**
     * 指定客户端支持的grant_type
     */
    private String authorizedGrantTypes;
    /**
     * 客户端的重定向URI
     */
    private String webServerRedirectUri;
    /**
     * 指定客户端所拥有的Spring Security的权限值
     */
    private String authorities;
    /**
     * 设定客户端的access_token的有效时间值(单位:秒),
     */
    private Integer accessTokenValidity;
    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒)
     */
    private Integer refreshTokenValidity;
    /**
     * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据
     */
    private String additionalInformation;
    /**
     * 设置用户是否自动Approval操作
     */
    private String autoapprove;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date creationDate;
    /**
     * 修改人
     */
    private String lastUpdatedBy;
    /**
     * 修改时间
     */
    private Date lastUpdateDate;
    /**
     * 操作序号
     */
    private String operationId;

}
