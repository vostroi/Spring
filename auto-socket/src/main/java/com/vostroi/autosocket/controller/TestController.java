package com.vostroi.autosocket.controller;

import com.vostroi.autosocket.service.AutoSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2020/12/10 14:54
 * @projectName auto-socket
 * @title: TestController
 * @description: TODO
 */
@RestController
@RequestMapping(value="/tst")
public class TestController {

    @GetMapping(value="/now/{msg}")
    public String now(@PathVariable("msg") String msg) {
        return msg + "-->" + new Date();
    }

    @GetMapping(value = "/send/{key}/{content}")
    public String sendMsg(@PathVariable("key") String key , @PathVariable("content") String content){
        Session session = AutoSocketServer.sessionMap.get(key);
        AutoSocketServer.sendMessage(session , content + " --> " + UUID.randomUUID().toString());
        return "SUCCESS";
    }
}
