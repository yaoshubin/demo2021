package com.example.demo.juc;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 场景： A -> B -> C  ,A 打印5 次，B 打印 10 次 ，C 打印 15 接着继续  ，来10 轮
 *  订单-库存-支付
 * 实现:
 * Condition  await()  signal（）  精准唤醒
 * 唤醒时  修改标志位   flag
 *
 */


class ShareResource {

    private int flag = 1;
    final Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    @SneakyThrows
    public void print5() {

        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("A : \t" + i);
            }
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @SneakyThrows
    public void print10() {

        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("B : \t" + i);
            }
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @SneakyThrows
    public void print15() {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println("C : \t" + i);
            }
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}


public class T05ABCTransDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareResource.print5();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int j = 0; j < 10; j++) {
                    shareResource.print10();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int j = 0; j < 10; j++) {
                    shareResource.print15();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
