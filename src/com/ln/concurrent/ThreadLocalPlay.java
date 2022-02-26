package com.ln.concurrent;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/26 10:02
 **/
public class ThreadLocalPlay {

    private static ThreadLocal<Integer> i = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            i.set(1);
            System.out.println("t1下 i = " + i.get());

            i.set(i.get() + 1);
            System.out.println("t1下++ i = " + i.get());
            i.remove();
        }).start();

        i.set(1);
        System.out.println("main下 i = " + i.get());

        i.set(i.get() + 1);
        System.out.println("main下++ i = " + i.get());
        i.remove();

    }


}
