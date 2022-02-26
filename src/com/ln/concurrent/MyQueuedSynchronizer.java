package com.ln.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description 除了下面的5个钩子方法之外，AQS 类中的其他方法都是 final ，所以无法被其他类重写
 * @Author HeZhipeng
 * @Date 2022/2/26 11:22
 **/
public class MyQueuedSynchronizer extends AbstractQueuedSynchronizer {


    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }


    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }


    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }


}
