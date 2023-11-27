package com.sdgft.udf;

public class Main {
    String name = "java main";


    public String getName() {
        return name;
    }

    public static String executor() {
        return "main executor";
    }

    public static void main(String[] args) {
        Sha256Crypto sha256Crypto = new Sha256Crypto();
        System.out.println(sha256Crypto.evaluate("hello"));

        System.out.println(executor());
    }
}
