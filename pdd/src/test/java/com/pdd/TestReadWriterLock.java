package com.pdd;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriterLock {
    public static void main(String[] args){
       ReadWriterLockDemo rw = new ReadWriterLockDemo();

       new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0;i<10000;i++){//100个线程读
                   Runnable runnable = () -> rw.get();
                   Thread thread = new Thread(runnable);
                   thread.start();
               }
           }
       }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {//一个线程写
                        @Override
                        public void run() {
                            int num = (int) (Math.random()*101);
                            rw.set(num);
                        }
                    },"write:"+i+":").start();
                }
            }
        }).start();
    }
}

class ReadWriterLockDemo{
    private int number = 0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读(可以多个线程同时操作)
    public void get(){
//        readWriteLock.readLock().lock();//上锁
        try {
            System.out.println(Thread.currentThread().getName()+":"+number);
        }finally {
//            readWriteLock.readLock().unlock();//释放锁
        }
    }
    //写(一次只能有一个线程操作)
    public void set(int number){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "number:" + number);
            this.number = number;
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}