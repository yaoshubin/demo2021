package com.example.demo.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 线程 第三种实现方式    implements  Callable
 *
 * 点题：
 * 1、比较Callable  和Runable的区别 ：返回值和方法名
 * 2、使用futureTask 实现和主线程百川如海
 *
 */

class MyThread  implements Callable<String>{

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(4);  //模拟另起线程等待返回
        return "hello";
    }
}



public class T10CallableThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask, "A").start();
       // new Thread(futureTask, "B").start();  //思考如果这个放开  是否会出现执行Callable两次
        System.out.println(futureTask.get());      //获得返回值
        System.out.println("mainThread执行完成*****************");  //直到有返回值才执行
    }

}
