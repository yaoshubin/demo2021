package com.example.demo.juc;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 故障：
 * 多线程环境下，ArrayList 不再正确
 * java.util.ConcurrentModificationException
 *
 * 原因：
 * 多线程同时读写，会出现数据不一致的情况
 *
 * 解决方案：
 * 1、Vector  较重，性能慢
 * 2、Collections.synchronizedList(new ArrayList<>());
 * 3、new CopyOnWriteArrayList<>();   // Arrays.copyOf(elements, len + 1); 读写分离
 *
 *
 */
public class T07NoSafeList {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
