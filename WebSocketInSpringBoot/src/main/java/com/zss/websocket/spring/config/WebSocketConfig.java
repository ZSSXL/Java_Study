package com.zss.websocket.spring.config;

import com.zss.websocket.spring.WebSocketHandler;
import com.zss.websocket.spring.interceptor.MyHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author ZSS
 * @date 2019/12/6 11:11
 * @description websocket 配置类
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;
    private final MyHandShakeInterceptor myHandShakeInterceptor;

    @Autowired
    public WebSocketConfig(WebSocketHandler webSocketHandler, MyHandShakeInterceptor myHandShakeInterceptor) {
        this.webSocketHandler = webSocketHandler;
        this.myHandShakeInterceptor = myHandShakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.webSocketHandler, "/ws")
                .setAllowedOrigins("*")
                .addInterceptors(this.myHandShakeInterceptor);
    }
}
