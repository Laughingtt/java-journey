package map;

import java.util.HashMap;
import java.util.Map;

public class MapInitConfig {
    // 静态字典用来存储配置信息
    private static final Map<String, String> configDictionary = new HashMap<>();

    static {
        // 在静态块中加载配置信息
        // 这里可以从配置文件、数据库或其他来源加载配置数据
        configDictionary.put("key1", "value1");
        configDictionary.put("key2", "value2");
        configDictionary.put("key3", "value3");
        // 可以添加更多配置项

        // 可以在这里执行其他静态初始化操作
    }

    // 获取配置项的方法
    public static String getConfigValue(String key) {
        return configDictionary.get(key);
    }

    public static void main(String[] args) {
        // 使用配置字典中的值
        System.out.println(getConfigValue("key1"));
        System.out.println(getConfigValue("key2"));

        System.out.println(StaticFlowExample.staticVariable);
    }
}


