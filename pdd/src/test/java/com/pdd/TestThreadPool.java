package com.pdd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolDemo tp1 = new ThreadPoolDemo();
        ThreadPoolDemo tp2 = new ThreadPoolDemo();
        ThreadPoolDemo tp3 = new ThreadPoolDemo();
        ThreadPoolDemo tp4 = new ThreadPoolDemo();
        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //2.为线程池中的线程分配任务
        pool.submit(tp1);
        pool.submit(tp2);
        pool.submit(tp3);
        pool.submit(tp4);
        //3.关闭线程池
        pool.shutdown();
    }
}

class ThreadPoolDemo implements Runnable {
    private int i = 0;
    @Override
    public void run() {
        while (i < 100) {
            System.out.println(Thread.currentThread().getName() + ":" + (i++));
        }
    }
}