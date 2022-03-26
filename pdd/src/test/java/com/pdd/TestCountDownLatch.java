package com.pdd;

import java.util.concurrent.CountDownLatch;

/**
 * 测试闭锁
 */

/**
 * 没使用闭锁
 * 这里主线程要统计 100个子线程总共耗时，但是是并发执行，并不能统计出真实耗时；需要 CountDownLatch
 */
public class TestCountDownLatch {
    public static void main(String[] args){
        LatchDemo ld = new LatchDemo();
        long start = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            new Thread(ld).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+(end - start)/1000+"秒");
    }
}

class LatchDemo implements Runnable{
    public LatchDemo(){
    }
    @Override
    public void run() {
        for (int i = 0;i<5000;i++){
            if (i % 2 == 0){//50000以内的偶数
                System.out.println(i);
            }
        }
    }
}

/**
public class TestCountDownLatch {
    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(100);    // 有多少个线程，参数就是几
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        System.out.println("start="+start);
        for (int i = 0;i<100;i++){
            new Thread(ld).start();
        }

        try {
            latch.await();  // 在这10个线程执行完之前先等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end="+end);
        System.out.println("耗费时间为："+(end - start)/1000+"秒");
    }
}

class LatchDemo implements Runnable{
    private CountDownLatch latch;
    public LatchDemo(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i = 0;i<5000;i++){
                    if (i % 2 == 0){//50000以内的偶数
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();  // 第执行完一个线程  就递减
            }
        }
    }
}

*/
