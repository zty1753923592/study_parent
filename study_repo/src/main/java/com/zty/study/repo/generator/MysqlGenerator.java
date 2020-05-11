package com.zty.study.repo.generator;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.zty.study.repo.generator.convert.MyConvert;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MysqlGenerator {

    /**
     * 配置文件路径  看好了
     */
    private static String configPath = "/study_repo/src/main/resources/config/generator.yaml";

    //===================================  全局配置 ===================================
    /**
     * 当前项目路径
     */
    private static String projectPath = System.getProperty("user.dir");

    //=================================== 数据源配置 ==================================
    /**
     * 数据库类型
     */
    private static DbType dataSourceDbType = DbType.MYSQL;
    /**
     * 数据库driverName
     */
    private static String dataSourceDriverName = "com.mysql.cj.jdbc.Driver";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    private static JSONObject parseYaml(Path cf) {
        Yaml yaml = new Yaml();
        try {
            Map configMap = yaml.load(new FileInputStream(cf.toFile()));
            JSONObject object = new JSONObject(configMap);
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //读取配置文件获得JSONObject
        JSONObject configObject = parseYaml(Paths.get(projectPath + configPath));

        if (null == configObject) {
            System.err.println("获取到配置文件中的配置项为空!");
            return;
        }
        //==================================== 全局配置 =======================================
        JSONObject globalJsonObject = configObject.getJSONObject("global");
        if (null != globalJsonObject) {
            GlobalConfig gc = new GlobalConfig();
            boolean isNull = null == globalJsonObject.getString("output-dic");
            if (isNull) {
                System.err.println("请设置生成文件位于项目里的相对路径！");
            } else {
                gc.setOutputDir(projectPath + globalJsonObject.getString("output-dic"));
            }
            boolean isFileOverride = "true".equals(globalJsonObject.getString("file-override"));
            if (isFileOverride) {
                gc.setFileOverride(true);
            }
            boolean isSwagger = "true".equals(globalJsonObject.getString("swagger"));
            if (isSwagger) {
                gc.setSwagger2(true);
            }
            gc.setAuthor(globalJsonObject.getString("author"))
                    .setOpen(false)
                    .setEnableCache(false)
                    .setBaseResultMap(true)
                    .setBaseColumnList(true);
            gc.setActiveRecord(true);
            gc.setControllerName("%sApi");
            mpg.setGlobalConfig(gc);
        }

        //=================================== 数据源配置 =======================================
        JSONObject datasourceJsonObject = configObject.getJSONObject("datasource");
        MyConvert myConvert = new MyConvert();
        if (null != datasourceJsonObject) {
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(dataSourceDbType)
                    .setDriverName(dataSourceDriverName)
                    .setUrl(datasourceJsonObject.getString("url"))
                    .setUsername(datasourceJsonObject.getString("username"))
                    .setPassword(datasourceJsonObject.getString("password"))
                    .setTypeConvert(myConvert)
            ;
            mpg.setDataSource(dsc);
        }

        //====================================== 包配置 ==========================================
        final JSONObject packageJsonObject = configObject.getJSONObject("package");
        if (null != packageJsonObject) {
            PackageConfig pc = new PackageConfig();
            //TODO 暂时注解掉模块名设置，统一放在repo模块下
            /*        pc.setModuleName(scanner("模块名"));*/
            pc.setParent(packageJsonObject.getString("parent"))
                    .setEntity(packageJsonObject.getString("entity"))
                    //这里不进行xml包配置是因为要通过自定义配置把xml优先生成在资源文件夹下
                    .setXml(packageJsonObject.getString(null))
                    .setMapper(packageJsonObject.getString("mapper"))
                    .setController(packageJsonObject.getString("controller"))
                    .setService(packageJsonObject.getString("service"))
                    .setServiceImpl(packageJsonObject.getString("service-impl"));
            mpg.setPackageInfo(pc);
        }

        //==================================  模板配置 =============================================
        final JSONObject templateJsonObject = configObject.getJSONObject("template");
        if (null != templateJsonObject) {
            TemplateConfig templateConfig = new TemplateConfig();
            // 配置自定义输出模板
            //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            templateConfig.setEntity(templateJsonObject.getString("entity-vm"))
                    .setMapper(templateJsonObject.getString("mapper-vm"))
                    //这里不进行xml模板配置是因为要通过自定义配置把xml优先生成在资源文件夹下
                    .setXml(null)
                    .setController(templateJsonObject.getString("controller-vm"))
                    .setService(templateJsonObject.getString("service-vm"))
                    .setServiceImpl(templateJsonObject.getString("service-impl-vm"));
            mpg.setTemplate(templateConfig);
        }

        //=================================== 策略配置  =============================================
        JSONObject strategyJsonObject = configObject.getJSONObject("strategy");
        if (null != strategyJsonObject) {
            StrategyConfig strategy = new StrategyConfig();
            //表名下划线命名->类的驼峰形式命名
            boolean isNamingUnderlineToCamel = "true".equals(strategyJsonObject.getString("naming"));
            if (isNamingUnderlineToCamel) {
                strategy.setNaming(NamingStrategy.underline_to_camel);
            }
            boolean isColumnNamingUnderlineToCamel = "true".equals(strategyJsonObject.getString("column-naming"));
            if (isColumnNamingUnderlineToCamel) {
                strategy.setNaming(NamingStrategy.underline_to_camel);
            }
            boolean isEntityTableFieldAnnotationEnable = "true".equals(strategyJsonObject.getString("entity-table-field-annotation-enable"));
            if (isEntityTableFieldAnnotationEnable) {
                strategy.setEntityTableFieldAnnotationEnable(true);
            }
            boolean isEntityLombokModel = "true".equals(strategyJsonObject.getString("entity-lombok-model"));
            if (isEntityLombokModel) {
                strategy.setEntityLombokModel(true);
            }
            boolean isEntityBuilderModel = "true".equals(strategyJsonObject.getString("entity-builder-model"));
            if (isEntityBuilderModel) {
                strategy.setEntityBuilderModel(true);
            }
            boolean isRestControllerStyle = "true".equals(strategyJsonObject.getString("rest-controller-style"));
            if (isRestControllerStyle) {
                strategy.setRestControllerStyle(true);
            }
            strategy.setSuperMapperClass(strategyJsonObject.getString("super-mapper-class"))
                    .setSuperEntityClass(strategyJsonObject.getString("super-entity-class"))
                    .setSuperControllerClass(strategyJsonObject.getString("super-controller-class"))
                    .setSuperServiceClass(strategyJsonObject.getString("super-service-class"))
                    .setSuperServiceImplClass(strategyJsonObject.getString("super-service-impl-class"))
                    .setVersionFieldName("version")
                    .setLogicDeleteFieldName("status")
                    .setInclude("yb_sys_mesg_primary")
//                    .setExclude("spatial_ref_sys")
                    .setInclude(scanner("表名，有多个表英文逗号分割").split(","))
                    //全局大写命名
                     .setCapitalMode(false);
            mpg.setStrategy(strategy);
        }

        //===================================== 自定义配置 ======================================
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        //自定义的模板路径，没有自动识别模板类型，模板文件名要指明.vm
        String templatePath = new StringBuilder(templateJsonObject.getString("xml-vm")).append(".vm").toString();
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                //TODO 暂时不设置模块名，全部放在一起
                return projectPath + templateJsonObject.getString("mapping-dic") + packageJsonObject.getString("xml") + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

/*        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
