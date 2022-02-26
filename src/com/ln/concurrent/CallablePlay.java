package com.ln.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/2/26 10:20
 **/
public class CallablePlay {


    public static void main(String[] args) throws Exception {
        Runnable run = () -> {
            int a = 1;
            System.out.println(a);
        };

        Callable<Object> callable = Executors.callable(run);
        callable.call();

        Callable callable1  = () -> {

            return 1;
        };




    }

}
