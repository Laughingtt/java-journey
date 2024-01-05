package com.laughing.microservice.common.utils.gcode;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        // 表名和接口名
        String className = "Orders";

        // 模板数据
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("class_name", className);

        // FreeMarker 配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/templates");

        // 生成 Controller
        generateFile(cfg, dataModel, "controller.ftl", "src/main/java/com/laughing/microservice/common/utils/gcode/" + "Controller.java");

//        // 生成 Dao
//        generateFile(cfg, dataModel, "dao.ftl", "src/main/java/com/example/dao/" + apiName + "Dao.java");
//
//        // 生成 EntityMapper.xml
//        generateFile(cfg, dataModel, "entityMapper.ftl", "src/main/resources/mapper/" + apiName + "EntityMapper.xml");
    }

    private static void generateFile(Configuration cfg, Map<String, Object> dataModel, String templateName, String outputFilePath)
            throws IOException, TemplateException {
        // 获取模板
        Template template = cfg.getTemplate(templateName);

        // 创建输出文件
        File outputFile = new File(outputFilePath);
        try (Writer writer = new FileWriter(outputFile)) {
            // 将模板与数据合并，并写入文件
            template.process(dataModel, writer);
        }

        System.out.println("Generated: " + outputFilePath);
    }
}
