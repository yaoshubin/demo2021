package com.example.demo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 辅助工具类：
 * 场景：6个同学同时走完，班长才能锁门
 * 解决方案：
 * CountDownLatch  类似于计数器，执行到0 的时候回唤醒阻塞线程
 */
public class T12CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        //错误程序演示
       /* for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t离开教室");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }*/


       //正确程序演示
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t离开教室");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();  //阻塞直到计数为0

        System.out.println(Thread.currentThread().getName() + "\t班长离开教室");
    }

}
