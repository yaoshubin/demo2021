package com.example.demo.juc;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap  底层变化
 *   优化HashMap ：
 *   1、设置大的初始容量，避免重复扩容造成的时间消耗
 *
 *
 */
public class T09HashMap {

    public static void main(String[] args) {
       Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }

}
