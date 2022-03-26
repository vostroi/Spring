package com.vostroi.autosocket.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2020/12/10 14:35
 * @projectName auto-socket
 * @title: AutoSocketServer
 * @description: TODO
 */
@RestController
@ServerEndpoint("/socket/auto/{key}")
public class AutoSocketServer {
    /**
     * 客户端数量
     */
    private static AtomicInteger clientNum = new AtomicInteger(0);

    /**
     * 客户端集合onMessage
     */
    public static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        System.out.println("socket init ...");
    }

    @OnOpen
    public void onOpen(@PathParam("key") String key , Session session){
        System.out.println("建立链接 key=" + key);
        if (!(sessionMap.containsKey(key))) {
            sessionMap.put(key, session);
            clientNum.incrementAndGet();
        }
        sendMessage(session , "welcome "+ key +" --> " + new Date());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("message from cient message = " + message);
        sendMessage(session , "got ur msg --> " + new Date());
    }

    /**
     * 发送文本消息
     * @param session
     * @param content
     */
    public static void sendMessage(Session session, String content) {
        try {
            session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
