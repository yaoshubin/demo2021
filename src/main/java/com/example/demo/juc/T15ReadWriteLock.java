package com.example.demo.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读操作共享，写操作独占
 * <p>
 * 场景：多线程下资源共享
 * 实现方案：
 * 使用ReadWriteLock
 *
 * readWriteLock.writeLock().lock();  写操作保证数据一致性原则
 * readWriteLock.readLock().lock();   读操作无所谓
 *
 *
 */


class MyCache {

    private volatile Map<String, String> map = new HashMap<>();

    public void put(String key, String value) {
        System.out.println(Thread.currentThread().getName() + "开始写操作。。。。。");
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写操作结束。。。。。");
    }

    public String get(String key) {
        System.out.println(Thread.currentThread().getName() + "-----读操作开始");
        String result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "-----读操作结束");
        return result;
    }
}

public class T15ReadWriteLock {


    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        try {
            //写
            for (int i = 0; i < 3; i++) {
                MyCache myCache = new MyCache();
                int finalI = i;
                new Thread(() -> {
                    try {
                        myCache.put(Thread.currentThread().getName(), String.valueOf(finalI));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, String.valueOf(i)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

        readWriteLock.readLock().lock();
        try {
            //读
            for (int i = 0; i < 3; i++) {
                MyCache myCache = new MyCache();
                int finalI = i;
                new Thread(() -> {
                    try {
                        myCache.get(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, String.valueOf(i)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
