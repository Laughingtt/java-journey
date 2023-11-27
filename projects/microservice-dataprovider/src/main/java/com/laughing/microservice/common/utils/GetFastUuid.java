package com.laughing.microservice.common.utils;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class GetFastUuid {

    public static String getTransId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString().replace("-","");
    }

    public static void main(String[] args) {
        // 使用JUG库生成UUID
        UUID uuid = Generators.timeBasedGenerator().generate();

        System.out.println("Generated UUID: " + uuid);
    }
}
