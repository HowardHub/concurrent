package com.ln.concurrent.selfsysnc;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/27 15:24
 **/
public class LnLockPlay {


    static int count = 0;
    static LnLock lock = new LnLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run () {
                try {
                    lock.lock();
                    for (int i = 0; i < 10000; i++) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }



}
