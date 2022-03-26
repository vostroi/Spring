package com.vostroi.autosocketclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @date 2020/12/14 14:44
 * @projectName auto-socket-client
 * @title: SocketHeartBeats
 * @description: TODO
 */
@Component
public class SocketHeartBeats {
    private static  final Logger logger = LoggerFactory.getLogger(SocketHeartBeats.class);

    @Scheduled(cron="0/5 * * * * ? ")
    public void heartBeat(){
        Map<String, Session> sessionMap = TriggerControoler.getSessionMap();
        if(!sessionMap.isEmpty()){
            Set<Map.Entry<String, Session>> entries = sessionMap.entrySet();
            for (Map.Entry<String, Session> entry : entries) {
                try {
                    entry.getValue().getBasicRemote().sendText("ping");
                    logger.info("发送心跳 {}" , entry.getKey());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
