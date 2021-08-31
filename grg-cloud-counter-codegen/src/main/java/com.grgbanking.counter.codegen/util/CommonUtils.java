package com.grgbanking.counter.codegen.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.grgbanking.counter.codegen.bo.CodeGeneratorBo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author grgbanking
 * @since 2019-04-17 12:04
 */
public class CommonUtils {

    /**
     * 数据库连接信息
     * @param bo {@link CodeGeneratorBo}
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(CodeGeneratorBo bo) {
        return new DataSourceConfig()
                .setDbType(bo.getDbType())
                .setUrl(bo.getDbUrl())
                .setUsername(bo.getUsername())
                .setPassword(bo.getPassword())
                .setDriverName(bo.getDriver())
                ;
    }

    // 配置
    private static GlobalConfig globalConfig(CodeGeneratorBo bo) {
        String outDir = bo.getOutDir();
        DateType dateType = DateType.TIME_PACK;
        if (!"8".equalsIgnoreCase(bo.getJdkVersion())) {
            dateType = DateType.ONLY_DATE;
        }
        return new GlobalConfig()
                .setAuthor(bo.getAuthor())
                .setOutputDir(outDir)
                .setFileOverride(true) // 是否覆盖已有文件
                //.setOpen(true) // 是否打开输出目录
                .setDateType(dateType) // 时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
                .setActiveRecord(true)// 不需要ActiveRecord特性的请改为false
                .setEnableCache(handleBoolean(bo.getEnableCache()))// XML 二级缓存
                .setBaseResultMap(handleBoolean(bo.getBaseResultMap()))// XML ResultMap
                .setBaseColumnList(handleBoolean(bo.getBaseColumnList()))// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName(bo.getFileNamePatternEntity())
                .setMapperName(bo.getFileNamePatternMapper())
                .setXmlName(bo.getFileNamePatternMapperXml())
                .setServiceName(bo.getFileNamePatternService())
                .setServiceImplName(bo.getFileNamePatternServiceImpl())
                .setControllerName(bo.getFileNamePatternController())
                .setIdType(IdType.ASSIGN_ID) // 主键类型
                .setSwagger2(bo.getSwaggerSupport() != null && bo.getSwaggerSupport()) // model swagger2
                ;
//                if (!serviceNameStartWithI)
//                    config.setServiceName("%sService");
    }


    private static StrategyConfig strategyConfig(CodeGeneratorBo bo) {
        return new StrategyConfig()
                .setCapitalMode(true) // 全局大写命名 ORACLE 注意
                .setSkipView(false) // 是否跳过视图
                //.setDbColumnUnderline(true)
                .setTablePrefix(bo.getTablePrefixes())// 此处可以修改为您的表前缀(数组)
                .setFieldPrefix(bo.getFieldPrefixes()) // 字段前缀
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setInclude(bo.getTableNames())//修改替换成你需要的表名，多个表名传数组
                .setExclude(bo.getExcludeTableNames()) // 排除生成的表
                .setEntityLombokModel(bo.getLombokModel() != null && bo.getLombokModel()) // lombok实体
                .setChainModel(bo.getLombokChainModel() != null && bo.getLombokChainModel()) // 【实体】是否为构建者模型（默认 false）
                .setEntityColumnConstant(bo.getColumnConstant() != null && bo.getColumnConstant()) // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
                .setLogicDeleteFieldName(bo.getFieldLogicDelete()) // 逻辑删除属性名称
                .setVersionFieldName(bo.getFieldVersion()) // 乐观锁字段名
                .setEntityTableFieldAnnotationEnable(bo.getFieldAnnotation() != null && bo.getFieldAnnotation()) // 开启实体字段注解
                ;
    }

    // 包信息配置
    private static PackageConfig packageConfig(CodeGeneratorBo bo) {
        return new PackageConfig()
                .setParent(bo.getPackageName())
                .setController(bo.getPackageController())
                .setEntity(bo.getPackageEntity())
                .setMapper(bo.getPackageMapper())
                .setXml(bo.getPackageMapperXml())
                .setService(bo.getPackageService())
                .setServiceImpl(bo.getPackageServiceImpl())
                ;
    }

    /**
     * 获取模板引擎
     * @return 模板引擎 {@link AbstractTemplateEngine}
     */
    private static AbstractTemplateEngine getTemplateEngine(CodeGeneratorBo bo) {
        String templateEngine = bo.getTemplateEngine();
        switch (templateEngine) {
            case "velocity":
                return new VelocityTemplateEngine();
            case "freemarker":
                return new FreemarkerTemplateEngine();
            case "beetl":
                return new BeetlTemplateEngine();
        }
        return new VelocityTemplateEngine();
    }

    public static void execute(CodeGeneratorBo bo) {
        GlobalConfig globalConfig = globalConfig(bo);
        //DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);
        DataSourceConfig dataSourceConfig = dataSourceConfig(bo);
        StrategyConfig strategyConfig = strategyConfig(bo);
        PackageConfig packageConfig = packageConfig(bo);
//        InjectionConfig injectionConfig = injectionConfig(packageConfig);
        AbstractTemplateEngine templateEngine = getTemplateEngine(bo);
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(templateEngine)
                //.setCfg(injectionConfig)
                .execute();
    }

    private static boolean handleBoolean(Boolean b) {
        return b != null && b;
    }

}
