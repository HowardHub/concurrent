package com.ln.concurrent;


import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * @Description
 *

 *
 * @Author HeZhipeng
 * @Date 2022/2/21 22:40
 **/
public class Base {


    static int r1 = 0;
    static int r2 = 0;
    public static void main(String[] args) throws InterruptedException {
        //test2();

//        hasTimeWait();

//        testInterrupt();


//        interruptNormalThread();


//        interruptPark();

        testDaemonThread();
    }


    /*
     * 分析如下
     * 第一个 join：等待 t1 时, t2 并没有停止, 而在运行
     * 第二个 join：1s 后, 执行到此, t2 也运行了 1s, 因此也只需再等待 1s
     * 如果颠倒两个 join 呢？
     * 最终都是输出 2s
     */
    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;

        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();


        t1.join(); // 等待t1结束
        t2.join(); // 等待t2结束
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(String.format("r1: %s r2: %s cost: %s", r1, r2, end - start));

    }




    public static void hasTimeWait() throws InterruptedException {
        System.out.println("main开始");
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 开始");
                sleep(2000); // join1.5s时，t1睡1s，main可以等待，睡2s，main等不到。
                System.out.println("t1 结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        t1.join(1500);

        System.out.println("main 结束");

    }


    /**
     * 主线程打断t1线程的demo
     * 打断sleep，wait，join 的线程
     * 这几个方法都会让线程进入阻塞状态
     * 打断 sleep 的线程, 会清空打断状态
     */
    public static void testInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 begins sleep");
                sleep(5000);
                System.out.println("t1 sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace(); // 当睡不够5s就被唤醒时，会抛出异常
            }
        }, "t1");

        t1.start();
        sleep(1000); // 主线程睡1s，好让t1进入睡眠

        System.out.println("main has interrupt t1");
        t1.interrupt(); // 打断阻塞线程t1
        System.out.println(t1.isInterrupted()); // sleep,join,wait方法被打断后，打断标记被清空


    }


    /**
     * 打断正在运行的线程
     * 打断标记为true
     * 使用“打断标记”可以优雅地停止线程
     * @throws InterruptedException
     */
    public static void interruptNormalThread() throws InterruptedException {

        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) { // 被打断后，停止运行
                    System.out.println(" 打断状态:" + interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();
        sleep(500);
        t2.interrupt();
    }


    /**
     * 打断park
     * @throws InterruptedException
     */
    public static void interruptPark() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("park");
            LockSupport.park();
            System.out.println("unpark");
            System.out.println("打断状态：" + Thread.currentThread().isInterrupted());
            System.out.println("打断状态：" + Thread.interrupted());

            LockSupport.park(); // 打断状态为真时，会让park失效；只需要调用Thread.interrupted()，改变打断状态后又可以使用进入park
            System.out.println("再次unpark..");
        }, "t1");

        t1.start();
        sleep(1000);
        t1.interrupt();


    }


    /**
     * 守护线程：其他线程结束后，守护线程会被强制结束
     * 应用：垃圾回收器
     */
    public static void testDaemonThread() throws InterruptedException {

        Thread daemon = new Thread(() -> {
            System.out.println("daemon start");
            try {
                sleep(5000);
                System.out.println("daemon end"); // 由于main线程执行时间为1s，1 < 5，所以这条语句不会被执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "daemon");
        System.out.println("main start");
        daemon.setDaemon(true);
        daemon.start();

        sleep(1000);
        System.out.println("main end");


    }



}
