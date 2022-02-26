package com.ln.concurrent;

/**
 * @Description 单例--双重校验锁的实现方式--线程安全
 * @Author HeZhipeng
 * @Date 2022/2/25 23:31
 **/
public class SingletonPlay {


    /**
     * volatile：防止指令重排
     * 创建一个对象有三步
     * 1.分配内存空间
     * 2.初始化数据
     * 3.将变量指向内存空间
     * 假设：thread 1调用getInstance时执行了1、3后，thread 2执行getInstance发现singleInstance不为空
     * 但实际上singleInstance并没有被初始化。
     */
    private static volatile SingletonPlay singleInstance;



    private SingletonPlay(){

    }

    public static SingletonPlay getInstance(){
        if (singleInstance == null) {
            synchronized (SingletonPlay.class) {
                if (singleInstance == null) {
                    singleInstance = new SingletonPlay();
                }
            }
        }

        return singleInstance;

    }


}
