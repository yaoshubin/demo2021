package com.example.demo.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 集齐七颗龙珠就能召唤神龙
 *
 *
 * CyclicBarrier  做加法  ，够数了唤醒线程
 */
public class T13CycleBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Thread(() -> {
            System.out.println("main:  \t" + "*******召唤神龙!");
        }));

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "收集到一颗龙珠****");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }

}
