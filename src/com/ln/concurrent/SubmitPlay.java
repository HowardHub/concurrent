package com.ln.concurrent;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/26 16:06
 **/
public class SubmitPlay {


    private static final int COOL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                COOL_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy() // main Start. Time = Sat Feb 26 10:56:29 CST 2022，调用者线程自己执行任务
        );

        Future<String> submit = executor.submit(() -> {
            return "taa";
        });
        String s = submit.get();
        System.out.println(s);

        executor.shutdown();

    }


}
