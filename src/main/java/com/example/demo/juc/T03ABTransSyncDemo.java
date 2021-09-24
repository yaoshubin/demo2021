package com.example.demo.juc;

/**
 * 场景：实现A、b线程交叉进行输出
 *
 * 问题：
 * 1 异常  IllegalMonitorStateException     --  synchronized
 * 2  if虚假唤醒    --  while
 *
 */

class AirConder {

    private int number = 0;

    public  synchronized void increament() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println("increament:\t" + number);

        this.notifyAll();
    }

    public  synchronized void decreament() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println("decreament:\t" + number);
        this.notifyAll();
    }
}

public class T03ABTransSyncDemo {

    public static void main(String[] args) {
        AirConder airConder = new AirConder();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    airConder.increament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "A").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    airConder.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "B").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    airConder.increament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "C").start();
        }



        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    airConder.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "D").start();
        }

    }
}
