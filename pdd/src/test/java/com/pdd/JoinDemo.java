package com.pdd;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class JoinDemo extends Thread{
    int i;
    Thread previousThread; //上一个线程
    public JoinDemo( Thread previousThread , int i ){
        this.previousThread = previousThread;
        this.i = i;
    }

    @Override
    public void run() {






        try {
            // 调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
             previousThread.join();
//            TimeUnit.SECONDS.sleep(2);
            if(this.interrupted()){
                this.wait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("num i = " + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for(int i = 0 ; i < 20 ; i++){
            JoinDemo joinDemo = new JoinDemo( previousThread , i );
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}
