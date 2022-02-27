package com.ln.concurrent.unsugesst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/26 16:18
 **/
public class SingleThreadExecutorPlay {

    public static void main(String[] args){


        /**
         * 队列无界，会出现OOM
         * # There is insufficient memory for the Java Runtime Environment to continue.
         * # Native memory allocation (malloc) failed to allocate 32744 bytes for ChunkPool::allocate
         * # An error report file with more information is saved as:
         */
        //ExecutorService executorService = Executors.newFixedThreadPool(100000);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 线程数量无解，也会出现OOM
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newScheduledThreadPool(100000);



        for (int i = 0; i < 1000000; i++) {
            executorService.execute(() -> {
                System.out.println("test:"+ Thread.currentThread().getId());
            });
        }

        executorService.shutdown();




    }

}
