package com.vostroi.autosocket.queue.disruptor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2020/12/14 16:39
 * @projectName auto-socket
 * @title: HeartBeatDisruptor
 * @description: TODO
 */
@Slf4j
public class HeartBeatDisruptor {

    private static class HeartBeatDisruptorInit{
        private static final HeartBeatDisruptor instance = new HeartBeatDisruptor();
    }

    public HeartBeatDisruptor getInstance() {
        return HeartBeatDisruptorInit.instance;
    }


    /**
     * 初始化队列
     */
    public void init(String params){

    }



}
