package com.example.demo.p6;

/**
 *   1 考察字符串常量池（堆）
 *   2  sun.misc.Version.init();
 *     private static final String launcher_name = "java";
 *
 */
public class StringDemo {

    public static void main(String[] args) {
        String  str = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str);
        System.out.println(str.intern());
        System.out.println(str == str.intern());  //true

        System.out.println();

        String  str1 = new StringBuilder("ja").append("va").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1==str1.intern());  //false

    }
}
