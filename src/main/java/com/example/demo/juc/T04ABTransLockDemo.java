package com.example.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 场景：实现A、b线程交叉进行输出
 * 新版：Lock版 condition :  await() / notifyAll()
 */

class AirConder1 {

    final Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private int number = 0;

    public void increament() throws InterruptedException {
        lock.lock();
        try {

            //判断
            while (number != 0) {
                condition.await();    //this.wait();
            }

            //干活
            number++;
            System.out.println("increament:\t" + number);

            //通知
            condition.signalAll();   //this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decreament() throws InterruptedException {
        lock.lock();
        try {
            while ( number == 0 ) {
                condition.await();    //this.wait();
            }
            number--;
            System.out.println("decreament:\t" + number);
            condition.signalAll();   //this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class T04ABTransLockDemo {

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
