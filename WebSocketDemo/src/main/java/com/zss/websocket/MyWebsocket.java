package com.zss.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author ZSS
 * @date 2019/12/6 9:27
 * @description 我的 websocket demo
 */
@ServerEndpoint("/websocket/{uid}")
public class MyWebsocket {


    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        System.out.println("websocket已经连接.... " + session);
        System.out.println("websocket Uid : " + uid);

        // 客户响应，欢迎登录（连接）系统
        try {
            session.getBasicRemote().sendText("欢迎登录本系统");
        } catch (IOException e) {
            System.out.println("反馈消息异常：" + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("websocket已经关闭.... " + session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自客户端的消息 ：" + message);

        try {
            Thread.sleep(2000);
            System.out.println("睡眠 2000 ms");
        } catch (InterruptedException e) {
            System.out.println("睡眠失败：" + e.getMessage());
        }

        // 给客户端一个反馈
        try {
            session.getBasicRemote().sendText("消息已收到");
        } catch (IOException e) {
            System.out.println("反馈消息异常：" + e.getMessage());
        }
    }
}
