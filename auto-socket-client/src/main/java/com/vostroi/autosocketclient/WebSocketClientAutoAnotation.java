package com.vostroi.autosocketclient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
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
@ClientEndpoint
public class WebSocketClientAutoAnotation {
    private static Logger logger = LoggerFactory.getLogger(WebSocketClientAutoAnotation.class);

    @OnOpen
    public void onOpen(Session session) {
        logger.info("websocket connected...");
    }

    @OnMessage
    public void onMessage(Session session, String s) {
        logger.info("message from server s={}" , s);
    }

    @OnClose
    public void onClose() {
        logger.info("websocket closed " );
    }


}
