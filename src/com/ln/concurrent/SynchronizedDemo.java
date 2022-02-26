package com.ln.concurrent;

public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized");
        }
    }


    public synchronized void method2() {

        System.out.println("synchronized");

    }
}
