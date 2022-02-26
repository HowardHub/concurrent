package com.ln.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/26 11:14
 **/
public class AtomicIntegerPlay {


    private AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }
    //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
    public int getCount() {
        return count.get();
    }

}
