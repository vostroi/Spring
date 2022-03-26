package com.pdd;

public class TestLock {
    public static void main(String[] args) {
        Ticket td = new Ticket();
        new Thread(td, "窗口1").start();
        new Thread(td, "窗口2").start();
        new Thread(td, "窗口3").start();
    }
}

/**
 * 多个线程同时操作共享数据td.ticket，会出现线程安全问题，同一张票卖出去好几次或者余票为负
 */
class Ticket implements Runnable {
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + (--ticket));
            }
        }
    }
}

/**
 * 以前用同步代码块/同步方法解决，现在用同步锁解决
 */
//class Ticket implements Runnable {
//    private Lock lock = new ReentrantLock();
//
//    private int ticket = 100;
//    @Override
//    public void run() {
//        while (true) {
//            //  上锁
//            lock.lock();
//            try {
//                if (ticket > 0) {
//                    try {
//                        Thread.sleep(200);
//                    } catch (Exception e) {
//                    }
//                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + (--ticket));
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//            } finally {
//              lock.unlock();
//            }
//        }
//    }
//}