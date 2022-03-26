package com.vostroi.autosocket.queue.event.handler;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lmax.disruptor.EventHandler;
import com.vostroi.autosocket.queue.event.HeartBeatEvent;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2020/12/14 16:36
 * @projectName auto-socket
 * @title: HeartBeatEventHandler
 * @description: TODO
 */
@Slf4j
public class HeartBeatEventHandler implements EventHandler<HeartBeatEvent> {

    @Override
    public void onEvent(HeartBeatEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("onEvent sequence={},  endOfBatch={}, event={}", sequence,  endOfBatch, event);
    }
}
