package com.vostroi.autosocketclient;

import org.springframework.web.bind.annotation.*;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2020/12/10 15:30
 * @projectName auto-socket-client
 * @title: TriggerControoler
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/triger")
public class TriggerControoler {
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    public static Map<String, Session> getSessionMap(){
        return  sessionMap;
    }

    @GetMapping(value = "/conn/{key}")
    public String connectServer(@PathVariable("key") String key) {
        try {
            WebSocketClientAuto client = new WebSocketClientAuto(new URI("ws://127.0.0.1:10086/socket/auto/"+key));
            client.connect();
            WebSocketClientAuto.putClient(key , client);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return key;
    }

    @GetMapping(value = "/send/{key}/{content}")
    public String sendMessage(@PathVariable("key") String key, @PathVariable("content") String content) {
        WebSocketClientAuto client = WebSocketClientAuto.getClient(key);
        client.send(content);
        return "success";
    }

    @GetMapping(value = "/conn/auto/{key}")
    public String connectAutoServer(@PathVariable("key") String key) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI r = URI.create("wss://127.0.0.1:8781/websocket/automat/order/"+key);
            Session session = container.connectToServer(WebSocketClientAutoAnotation.class, r);
            sessionMap.put(key , session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping(value = "/send/auto/{key}")
    public String sendAutoMessage(@PathVariable("key") String key,  String content) {
        Session session = sessionMap.get(key);
        try {
            session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping(value = "/tst/{msg}")
    public String testController(@PathVariable("msg")  String msg){
        return "msg ---> " + new Date();
    }

}
