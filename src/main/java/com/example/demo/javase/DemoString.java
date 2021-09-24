package com.example.demo.javase;

import com.example.demo.juc.User;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 主要String基础面试题
 * <p>
 * 1、Object 和String  equals 区别
 *
 *
 * 2、hashCode和equals的关系：https://blog.csdn.net/qq_33709582/article/details/113337405
 * 数组长度-1、^运算、>>>16，这三个操作都是为了让key在hashmap的桶中尽可能分散
 * 用&而不用%是为了提高计算性能
 * (n - 1) & hash   ==》 (h = key.hashCode()) ^ (h >>> 16)
 *
 */
@Slf4j
public class DemoString extends Object {

    public static void main(String[] args) {


       /* String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = str2; // 引用传递
        System.out.println(str1 == str2); //false
        System.out.println(str1 == str3); //false
        System.out.println(str2 == str2); //true
        System.out.println(str1.equals(str2)); //true
        System.out.println(str1.equals(str3)); //true
        System.out.println(str2.equals(str3)); //true*/


        //如果两个对象的hashCode()相等，那么他们的equals()不一定相等。
        //如果两个对象的equals()相等，那么他们的hashCode()必定相等。
        User u1 = new User();
        User u2 = new User();
        //System.out.println(u1.equals(u2));
        //System.out.println(u1.hashCode());
        //System.out.println(u2.hashCode());
        //System.out.println(u1==u2);


        //length-1 & hashCode()>>>16
        log.info("数组长度不-1：{}", 16 & "郭德纲".hashCode());
        log.info("数组长度不-1：{}", 16 & "彭于晏".hashCode());
        log.info("数组长度不-1：{}", 16 & "李小龙".hashCode());
        log.info("数组长度不-1：{}", 16 & "蔡徐鸡".hashCode());
        log.info("数组长度不-1：{}", 16 & "唱跳rap篮球鸡叫".hashCode());

        log.info("数组长度-1但是不进行异或和>>>16运算：{}", 15 & "郭德纲".hashCode());
        log.info("数组长度-1但是不进行异或和>>>16运算：{}", 15 & "彭于晏".hashCode());
        log.info("数组长度-1但是不进行异或和>>>16运算：{}", 15 & "李小龙".hashCode());
        log.info("数组长度-1但是不进行异或和>>>16运算：{}", 15 & "蔡徐鸡".hashCode());
        log.info("数组长度-1但是不进行异或和>>>16运算：{}", 15 & "唱跳rap篮球鸡叫".hashCode());

        log.info("数组长度-1并且进行异或和>>>16运算：{}", 15 & ("郭德纲".hashCode()^("郭德纲".hashCode()>>>16)));
        log.info("数组长度-1并且进行异或和>>>16运算：{}", 15 & ("彭于晏".hashCode()^("彭于晏".hashCode()>>>16)));
        log.info("数组长度-1并且进行异或和>>>16运算：{}", 15 & ("李小龙".hashCode()^("李小龙".hashCode()>>>16)));
        log.info("数组长度-1并且进行异或和>>>16运算：{}", 15 & ("蔡徐鸡".hashCode()^("蔡徐鸡".hashCode()>>>16)));
        log.info("数组长度-1并且进行异或和>>>16运算：{}", 15 & ("唱跳rap篮球鸡叫".hashCode()^("唱跳rap篮球鸡叫".hashCode()>>>16)));


    }
}
