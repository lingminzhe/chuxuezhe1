package com.grgbanking.counter.iam.constans;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: grgms-sys
 * @description: 返回值国际化
 * @author: chainos
 * @create: 2021-02-04 13:02
 */
@Getter
@AllArgsConstructor
public enum RespI18nConstants {

    COM1001("exception.com1001", "空指针异常"),
    COM1002("exception.com1002", "类型转换异常"),
    COM1003("exception.com1003", "数组越界异常"),
    COM1004("exception.com1004", "未查询到数据"),
    COM1005("exception.com1005", "参数不能为空"),
    COM1006("exception.com1006", "操作失败"),
    COM1007("exception.com1007", "用户不存在"),
    COM2001("exception.com2001", "ID列表不能为空"),
    COM2002("exception.com2002", "ID不能为空"),
    COM2003("exception.com2003", "启用状态不能为空"),
    COM2004("exception.com2004", "应用类型不能为空"),

    LOGIN1001("exception.login1001", "密码错误，累计错误次数达到%s次，账户被锁定%s分钟"),
    LOGIN1002("exception.login1002", "累计错误次数达到%s次，账户被锁定%s分钟"),

    AREA1001("exception.area1001", "无法删除已被用户使用的区域"),
    AREA1002("exception.area1002", "启用失败"),
    AREA1003("exception.area1003", "停用失败"),
    AREA1004("exception.area1004", "父区域未启用，不可操作"),
    AREA1005("exception.area1005", "子区域未禁用，不可操作"),
    AREA1006("exception.area1006", "区域名称已存在"),
    AREA1007("exception.area1007", "区域编码已存在"),
    AREA1008("exception.area1008", "国际化编码已存在"),
    AREA1009("exception.area1009", "区域结构path已存在"),
    AREA1010("exception.area1010", "删除失败"),
    AREA1011("exception.area1011", "操作失败"),
    AREA1012("exception.area1012", "不能停用已被机构使用的区域"),

    ORG1001("exception.org1001", "无法删除已被用户使用的机构"),
    ORG1002("exception.org1002", "请先启用父机构"),
    ORG1003("exception.org1003", "机构未找到"),
    ORG1004("exception.org1004", "无法删除存在子节点的机构"),
    ORG1005("exception.org1005", "请先停用子机构"),
    ORG1006("exception.org1006", "请先停用关联用户"),
    ORG1007("exception.org1007", "机构编码不能为空"),
    ORG1008("exception.org1008", "启用状态不能为空"),
    ORG1009("exception.org1009", "国际化编码不能为空"),
    ORG1010("exception.org1010", "区域不能为空"),
    ORG1011("exception.org1011", "机构名称不能为空"),
    ORG1012("exception.org1012", "机构编码不允许为0"),
    ORG1013("exception.org1013", "机构编码已存在"),
    ORG1014("exception.org1014", "机构名称已存在"),
    ORG1015("exception.org1015", "国际化编码已存在"),
    ORG1016("exception.org1016", "机构全称已存在"),

    USER1001("exception.user1001", "不能删除所属机构相同的用户"),
    USER1002("exception.user1002", "用户账号不能为空"),
    USER1003("exception.user1003", "用户不存在"),
    USER1004("exception.user1004", "超级管理员不能停用"),
    USER1005("exception.user1005", "原始密码错误"),
    USER1006("exception.user1006", "新旧密码相同"),
    USER1007("exception.user1007", "无法删除超管用户"),
    USER1008("exception.user1008", "邮箱格式不正确"),
    USER1009("exception.user1009", "电话格式不正确"),

    USER1011("exception.user1011", "账号格式不正确"),
    USER1012("exception.user1012", "姓名不能为空"),
    USER1013("exception.user1013", "移动电话不能为空"),
    USER1014("exception.user1014", "账号启用状态不能为空"),
    USER1015("exception.user1015", "角色不能为空"),
    USER1016("exception.user1016", "管理区域不能为空"),
    USER1017("exception.user1017", "所属机构不能为空"),
    USER1018("exception.user1018", "管理机构不能为空"),
    USER1019("exception.user1019", "密码不能为空"),
    USER1020("exception.user1020", "账号已存在"),
    USER1021("exception.user1021", "所选管理区域不在范围内"),
    USER1022("exception.user1022", "所属机构需要在所选区域内"),
    USER1023("exception.user1023", "管理机构要在所属机构内"),
    USER1024("exception.user1024", "用户已被禁用"),

    ROLE1001("exception.role1001", "只可以编辑自己创建的角色"),
    ROLE1002("exception.role1002", "角色名称不能为空"),
    ROLE1003("exception.role1003", "角色类型不能为空"),
    ROLE1004("exception.role1004", "启用状态不能为空"),
    ROLE1005("exception.role1005", "可使用菜单不能为空"),
    ROLE1006("exception.role1006", "启用状态不正确"),
    ROLE1007("exception.role1007", "角色名称已存在"),
    ROLE1008("exception.role1008", "只可以停/启用自己创建的角色"),
    ROLE1009("exception.role1009", "有用户使用的角色不可停用"),
    ROLE1010("exception.role1010", "无法停用超管角色"),
    ROLE1011("exception.role1011", "无法删除超管角色"),
    ROLE1012("exception.role1012", "只可以删除自己创建的角色"),
    ROLE1013("exception.role1013", "当前角色有用户使用无法删除"),

    BIZ_CONF1001("exception.bizConf1001", "该业务配置信息不存在"),
    BIZ_CONF1002("exception.bizConf1002", "业务编号不能为空"),
    BIZ_CONF1003("exception.bizConf1003", "业务名称不能为空"),
    BIZ_CONF1004("exception.bizConf1004", "业务编号应唯一"),

    MENU1001("exception.menu1001", "无法删除有角色关联的菜单"),
    MENU1002("exception.menu1002", "无法删除有启用子菜单的菜单"),
    MENU1003("exception.menu1003", "有启用状态的子级菜单不可停用"),
    MENU1004("exception.menu1004", "有停用状态的父级菜单不可启用"),
    MENU1005("exception.menu1005", "菜单不存在"),
    MENU1006("exception.menu1006", "菜单编码应唯一"),
    MENU1007("exception.menu1007", "父编码不能为空"),
    MENU1008("exception.menu1008", "编码不能为空"),
    MENU1009("exception.menu1009", "资源类型不能为空"),
    MENU1010("exception.menu1010", "名称不能为空"),
    MENU1011("exception.menu1011", "所选父菜单不存在"),
    MENU1012("exception.menu1012", "系统下只能为目录类型"),
    MENU1013("exception.menu1013", "目录名称重复"),
    MENU1014("exception.menu1014", "目录下不能直接添加按钮类型"),
    MENU1015("exception.menu1015", "名称重复"),
    MENU1016("exception.menu1016", "菜单下只能为按钮类型"),
    MENU1017("exception.menu1017", "按钮名称重复"),
    MENU1018("exception.menu1018", "按钮下不能添加其他类型"),
    MENU1019("exception.menu1019", "当前菜单下有子菜单，不能更改父节点或菜单类型"),
    MENU1020("exception.menu1020", "系统下不能为系统类型"),
    MENU1021("exception.menu1021", "同一菜单下，按钮名称唯一"),
    MENU1022("exception.menu1022", "同一应用类型下，系统、目录、菜单名称唯一"),

    AUTHORITY1001("exception.authority1001", "同一个所属服务下，权限标识不能相同"),
    AUTHORITY1002("exception.authority1002", "无法删除授权给菜单的标识!"),
    AUTHORITY1003("exception.authority1003", "所属服务名不能为空"),
    AUTHORITY1004("exception.authority1004", "所属模块名不能为空"),
    AUTHORITY1005("exception.authority1005", "权限标识不能为空"),
    AUTHORITY1006("exception.authority1006", "所属类名不能为空"),
    AUTHORITY1007("exception.authority1007", "所属方法名不能为空"),

    DICT1001("exception.dict1001", "应用类别、代码类型、代码值三者组合值不能重复"),
    DICT1002("exception.dict1002", "字典不存在"),
    DICT1003("exception.dict1003", "该字典的国际化编码为空"),

    I18N1001("exception.i18n1001", "应用类别、数据类型、国际化编码、语言类型组合值不能重复"),
    I18N1002("exception.i18n1002", "国际化编码不能为空"),
    I18N1003("exception.i18n1003", "应用类别不能为空"),
    I18N1004("exception.i18n1004", "数据类型不能为空"),
    I18N1005("exception.i18n1005", "国际化语言类型不能为空"),
    I18N1006("exception.i18n1006", "国际化值不能为空"),
    I18N1007("exception.i18n1007", "该国际化值对应数据不存在"),
    I18N1008("exception.i18n1008","数据类型应与国际化编码前缀一致"),

    IMPORT_BATCH1001("exception.importBatch1001","批次编号不能为空"),
    IMPORT_BATCH1002("exception.importBatch1002","业务入库数量不能为空"),
    IMPORT_BATCH1003("exception.importBatch1003","上传导入模板文件名格式有误"),
    IMPORT_BATCH1004("exception.importBatch1004","文件名不能为空"),
    IMPORT_BATCH1005("exception.importBatch1005","导入模板必须是模板配置下载的模板"),
    IMPORT_BATCH1006("exception.importBatch1006","导入模板列数与模板配置列数不符"),
    IMPORT_BATCH1007("exception.importBatch1007", "没有上传模板文件"),
    IMPORT_BATCH1008("exception.importBatch1008", "模板文件没有添加数据"),
    IMPORT_BATCH1009("exception.importBatch1009", "模板文件数据错误"),
    IMPORT_BATCH1010("exception.importBatch1010", "模板文件数据类型错误"),
    IMPORT_BATCH1011("exception.importBatch1011", "导入数据的字典项不存在"),

    IMPORT_BIND1001("exception.importBind1001","属性列值不能超过30"),
    IMPORT_BIND1002("exception.importBind1002","该业务模板字段属性列已存在"),
    IMPORT_BIND1003("exception.importBind1003","该业务模板不存在"),
    IMPORT_BIND1004("exception.importBind1004","应用类型不能为空"),
    IMPORT_BIND1005("exception.importBind1005","业务编号不能为空"),
    IMPORT_BIND1006("exception.importBind1006","列注释不能为空"),
    IMPORT_BIND1007("exception.importBind1007","是否必录不能为空"),
    IMPORT_BIND1008("exception.importBind1008","数据类型不能为空"),
    IMPORT_BIND1009("exception.importBind1009","数据长度不能为空"),
    IMPORT_BIND1010("exception.importBind1010","国际化编码对应的列注释不匹配"),
    IMPORT_BIND1011("exception.importBind1011","国际化编码格式不正确，应为xxx.xxx格式"),
    IMPORT_BIND1012("exception.importBind1012","列字段不能为空"),

    IMPORT_TEMPLATE1001("exception.importTemplate1001","业务类型不能为空"),
    IMPORT_TEMPLATE1002("exception.importTemplate1002","应用类型应与业务编号组合唯一"),
    IMPORT_TEMPLATE1003("exception.importTemplate1003","无此模板信息"),

    OPS_ORG1001("exception.opsOrg1001","名称不能为空"),
    OPS_ORG1002("exception.opsOrg1002","国际化编码不能为空"),
    OPS_ORG1003("exception.opsOrg1003","联系人不能为空"),
    OPS_ORG1004("exception.opsOrg1004","联系电话不能为空"),
    OPS_ORG1005("exception.opsOrg1005","客服投诉电话不能为空"),
    OPS_ORG1006("exception.opsOrg1006","地址不能为空"),
    OPS_ORG1007("exception.opsOrg1007","维护商类型不能为空"),
    OPS_ORG1008("exception.opsOrg1008","启用状态不能为空"),
    OPS_ORG1009("exception.opsOrg1009","维护商名称或国际化编码不能重复"),
    OPS_ORG1010("exception.opsOrg1010","该维护商有关联用户,不能删除"),
    OPS_ORG1011("exception.opsOrg1011","该维护商不存在"),
    OPS_ORG1012("exception.opsOrg1012","无法停用有用户关联的维护商"),

    OPS_ORG_USER1001("exception.opsOrgUser1001","该维护商关联用户表信息不存在"),
    OPS_ORG_USER1002("exception.opsOrgUser1002","所属机构编号不能为空"),
    OPS_ORG_USER1003("exception.opsOrgUser1003","所属维护商编号不能为空"),
    OPS_ORG_USER1004("exception.opsOrgUser1004","角色编号不能为空"),
    OPS_ORG_USER1005("exception.opsOrgUser1005","性别不能为空"),
    OPS_ORG_USER1006("exception.opsOrgUser1006","用户账号不能更改"),
    OPS_ORG_USER1007("exception.opsOrgUser1007","机构用户关系表数据不存在"),
    OPS_ORG_USER1008("exception.opsOrgUser1008","角色用户关系表数据不存在");

    private String code;

    private String value;


}
