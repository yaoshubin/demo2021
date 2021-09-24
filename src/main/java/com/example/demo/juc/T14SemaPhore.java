package com.example.demo.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *   
 *   场景：模拟6车占3个位置
 *   
 *   实现：占位和释放
 */
public class T14SemaPhore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +"\t"+"占了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() +"\t"+"离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
