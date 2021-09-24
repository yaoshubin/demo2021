package com.example.demo.juc;

import java.util.concurrent.locks.ReentrantLock;

class Ticket1{
    private int  number = 30;
    private final ReentrantLock lock = new ReentrantLock();

    public  void saleTicket(){
        lock.lock();
        try {
            if(number  > 0){
                System.out.println(Thread.currentThread().getName()+"卖出第:"+(number -- )+"张，还剩："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class T02ReentrantLockDemo {

    public static void main(String[] args) {
        Ticket1 ticket1 = new Ticket1();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket1.saleTicket(); } },"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket1.saleTicket(); } },"B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket1.saleTicket(); } },"C").start();

    }
}
