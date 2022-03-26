package com.pdd;

public class AccountingSyncBad implements Runnable{
    static int i=0;
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2=new Thread(new AccountingSyncBad());
        /**
         * 虽然用synchronized修饰了increase方法，但new了两个不同的实例对象，也不意味着存在两个不同的实例对象锁，因此线程和t1 t2会进入各自的实例对象锁，而i是共享变量
         * 无法保证线程安全，需要将increase方法修改为静态同步方法 static synchronized , 这样锁就是当前类，无论创建多少实例，都共用一把锁
         */
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}