package com.pdd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者，消费者 模拟线程等待唤醒机制
 */
public class TestProducerAndConsumer {
    public static void main(String[] args){
           Clerk clerk = new Clerk();
           Producer producer = new Producer(clerk);
           Consumer consumer = new Consumer(clerk);
            for (int i = 0; i < 2 ; i++) {
                new Thread(producer,"生产者"+i).start();
            }
            for (int i = 0; i < 2; i++) {
                new Thread(consumer,"消费者"+i).start();
            }
    }
}
// 店员
//class Clerk{
//    private int product = 0;// 共享数据
//    public synchronized void get(){ // 进货
//        while (product >= 1){
//            System.out.println(Thread.currentThread().getName()+"：产品已满");
//            try {
//                System.out.println(Thread.currentThread().getName()+"：wait");
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName()+"：进货："+ (++product));
//        notifyAll();
//    }
//    public synchronized void sell(){// 卖货
//        while (product <= 0){
//            System.out.println(Thread.currentThread().getName()+"：缺货");
//            try {
//                System.out.println(Thread.currentThread().getName()+"：wait");
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName()+"：卖货："+ (--product));
//        notifyAll();
//    }
//}

// Lock上锁
class Clerk{
    private int product = 0;// 共享数据
    private Lock lock = new ReentrantLock();    //创建锁对象
    private Condition condition = lock.newCondition();  //

    public void get(){ // 进货
        lock.lock();    // 上锁
        try{
            while (product >= 1){
                System.out.println(Thread.currentThread().getName()+"：产品已满");
                try {
                    System.out.println(Thread.currentThread().getName()+"：wait");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"：进货："+ (++product));
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public synchronized void sell(){// 卖货
        lock.lock();
        try {
            while (product <= 0){
                System.out.println(Thread.currentThread().getName()+"：缺货");
                try {
                    System.out.println(Thread.currentThread().getName()+"：wait");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"：卖货："+ (--product));
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

// 生产者
class Producer implements Runnable{
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            clerk.get();
        }
    }
}
// 消费者
class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10 ; i++) {
            clerk.sell();
        }
    }
}