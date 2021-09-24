package com.example.demo.juc;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 场景：线程8锁
 * 实现：
 * （1）synchronized        约束 对象
 * （2）static synchronized  约束 类
 * （3） 不患寡 患不均
 * <p>
 * 锁现象：
 * （1）标准访问：两个普通同步方法  synchronized，同一个对象
 * （2）sendEmail暂停3秒钟 ，两个普通同步方法  synchronized，同一个对象
 * （3）sendEmail暂停3秒钟 ，两个普通同步方法  synchronized，两个对象
 * （4）sendEmail暂停3秒钟 ，一个普通同步方法  synchronized，一个普通方法 ，一个对象
 * （5）sendEmail暂停3秒钟 ，一个普通同步方法  synchronized，一个普通方法 ，两个对象
 * （6）sendEmail暂停3秒钟 ，两个静态普通同步方法  synchronized，两个对象
 * （7）sendEmail暂停3秒钟 ，一个普通同步方法  synchronized，一个静态普通同步方法 ，一个对象
 * （8）sendEmail暂停3秒钟 ，一个普通同步方法  synchronized，一个静态普通同步方法 ，两个对象
 *
 */

class Phone {

    @SneakyThrows
    public  static synchronized void sendEmail() {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("-----------sendEmail");
    }

    public  synchronized void sendMsg() {
        System.out.println("-----------sendMsg");
    }

    public void hello(){
        System.out.println("-----------hello");
    }
}


public class T06Lock8 {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }


        new Thread(() -> {
            try {
                phone.sendMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }


}
