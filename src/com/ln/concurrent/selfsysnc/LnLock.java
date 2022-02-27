package com.ln.concurrent.selfsysnc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description 自定义同步工具
 * @Author HeZhipeng
 * @Date 2022/2/27 15:21
 **/
public class LnLock {

    private static class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(1,0);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

    }

    private Sync sync = new Sync();

    public void lock(){
        sync.tryAcquire(1);
    }

    public void unlock(){
        sync.tryRelease(1);
    }





}
