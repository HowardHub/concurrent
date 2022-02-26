package com.ln.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 只有成为锁的owner，才能调用锁的wait和notify方法
 * @Author HeZhipeng
 * @Date 2022/2/25 21:14
 **/

public class WaitNotifyPlay {

    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread( () -> {
            System.out.println("线程t1开始执行");
            synchronized (obj) {
                try {
                    System.out.println("线程t1获取到obj锁，成为锁的owner，并且进入waitSet等待");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程t1被唤醒，结束等待");
            }

        }, "t1");
        t1.start();

        System.out.println("主线程开始执行");
        Thread.sleep(1000); // 确保wait在notify之前执行。
        synchronized (obj) {
            System.out.println("主线程获得obj锁，并且调用notifyAll唤醒等待的t1");
            obj.notifyAll();
        }

        System.out.println("主线程结束执行");
        


    }

}
