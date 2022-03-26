package com.vostroi.autosocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Administrator
 * @date 2020/12/10 14:51
 * @projectName auto-socket
 * @title: SocketConfiguration
 * @description: TODO
 */
@Configuration
public class SocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        System.out.println("SocketConfiguration...");
        return new ServerEndpointExporter();
    }
}
