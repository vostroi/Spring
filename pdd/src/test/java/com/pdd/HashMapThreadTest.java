package com.pdd;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 HashMap 多线程下非线程安全
 */
public class HashMapThreadTest extends Thread {

    private static HashMap<Integer , Integer> map = new HashMap<Integer , Integer>();
    private static AtomicInteger integer = new AtomicInteger();
    private static final Integer LOOP = 1000000;


    @Override
    public void run() {
        while (integer.get() <= LOOP){
            map.put(integer.get() , integer.get());
            integer.incrementAndGet();
        }
    }


    public static void main(String[] args) {
//        for (int i = 0 ; i < 4; i++){
//            HashMapThreadTest threadTest = new HashMapThreadTest();
//            threadTest.start();;
//        }
//
//        LinkedHashMap<Integer, String> link = new LinkedHashMap<Integer, String>();

        System.out.println(Integer.parseInt("0001111", 2) & 15);
        System.out.println(Integer.parseInt("0011111", 2) & 15);
        System.out.println(Integer.parseInt("0111111", 2) & 15);
        System.out.println(Integer.parseInt("1111111", 2) & 15);

    }
}
