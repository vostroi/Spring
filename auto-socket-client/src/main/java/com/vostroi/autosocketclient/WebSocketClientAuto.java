package com.vostroi.autosocketclient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2020/12/10 15:39
 * @projectName auto-socket-client
 * @title: WebSocketClientAuto
 * @description: TODO
 */
public class WebSocketClientAuto extends WebSocketClient {

    private static Map<String, WebSocketClientAuto> clientAutoMap = new ConcurrentHashMap<>();

    public static WebSocketClientAuto getClient(String key) {
        WebSocketClientAuto client = clientAutoMap.get(key);
        if(client == null){
            try {
                client = new WebSocketClientAuto(new URI("ws://127.0.0.1:10086/socket/auto/"+key));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            client.connect();
            WebSocketClientAuto.putClient(key , client);
        }
        return client;
    }

    public static void putClient(String key , WebSocketClientAuto clientAuto){
        clientAutoMap.put(key , clientAuto);
    }

    public WebSocketClientAuto(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("websocket connected...");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("message from server s="+s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("websocket closed i="+i+"s="+s+"b="+b);
    }

    @Override
    public void onError(Exception e) {
        System.out.println("websocket error");
        e.printStackTrace();
    }
}
