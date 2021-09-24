package com.example.demo.juc;

class Ticket{
    private int  number = 30;

    public  synchronized void saleTicket(){
        if(number  > 0){
            System.out.println(Thread.currentThread().getName()+"卖出第:"+(number -- )+"张，还剩："+number);
        }
    }
}


/**
 * 企业级多线程套路：高内聚低耦合   =  线程操纵资源类
 */
public class T01SaleTicket {

    public static void main(String[] args) {


        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }

            }
        },"A").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.saleTicket();
                }
            }
        },"C").start();

    }


}
