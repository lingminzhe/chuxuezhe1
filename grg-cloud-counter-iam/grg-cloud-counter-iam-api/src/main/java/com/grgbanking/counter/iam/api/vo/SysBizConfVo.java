package com.grgbanking.counter.iam.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务配置出参
 *
 * @author MARK xx@grgbanking.com
 */
@Data
public class SysBizConfVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 业务键
     */
    private String bizCode;
    /**
     * 业务值
     */
    private String bizInfo;
    /**
     * 启用状态
     * */
   private String isEnabled;

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
