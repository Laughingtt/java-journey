package com.laughing.microservice.common.utils.gcode;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class DataModelExample {

    public static void main(String[] args) {
        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", "John");
        dataModel.put("age", 25);

        // 将数据模型传递给模板引擎进行处理
        processTemplate(dataModel);
    }

    private static void processTemplate(Map<String, Object> dataModel) {
        try {
            // 获取 FreeMarker 配置
            Configuration cfg = FreeMarkerConfig.getConfig();

            // 获取模板
            Template template = cfg.getTemplate("example.ftl");

            Writer writer = new OutputStreamWriter(System.out);

            template.process(dataModel, writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
