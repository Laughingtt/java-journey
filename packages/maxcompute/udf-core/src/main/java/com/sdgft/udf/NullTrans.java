package com.sdgft.udf;

import com.aliyun.odps.udf.UDF;

public class NullTrans extends UDF {
    // TODO define parameters and return type, e.g:  public String evaluate(String a, String b)
    public String evaluate(String s) {
        return "hello world:" + s;
    }

    public void main(String[] args) {
        System.out.println(evaluate("111"));
    }
}