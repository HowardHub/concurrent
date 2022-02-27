package com.ln.concurrent;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 阿里巴巴推荐的使用 ThreadPoolExecutor 构造函数自定义参数的方式来创建线程池
 * @Author HeZhipeng
 * @Date 2022/2/26 10:49
 **/
public class ThreadPoolExecutorPlay {


    private static final int COOL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                COOL_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy() // main Start. Time = Sat Feb 26 10:56:29 CST 2022，调用者线程自己执行任务
        );

        for (int i = 0; i < 1000; i++) {
            executor.execute(new MyRunnable("" + i));
        }


        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("finished all threads");


    }

}


/**
 * 任务
 */
class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }

}
