package com.example.demo.juc;

import java.util.concurrent.*;

/**
 * 线程池 : 避免创建连接 造成的开削
 * Executors  辅助获得线程池
 *   线程池原理：
 *   1、线程池7大参数及工作流程
 *   2、阻塞队列和复用原理
 *   3、为什么自定义线程池？ 因为 Integer.MAX_VALUE  会出现OOM
 *   4、线程池用哪个？生产如何设置参数--  自定义线程池
 *   5、4大拒绝策略
 *
 */
public class T16ThreadPool {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);  //固定窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();  //只有一个窗口
        //ExecutorService threadPool = Executors.newCachedThreadPool();  //可伸缩窗口，自动扩容
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(2
                , 5
                , 2l
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(3)
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            myThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "办理业务");
            });
        }
    }

}
