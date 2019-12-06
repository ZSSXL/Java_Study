package com.zss.websocket.spring;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author ZSS
 * @date 2019/12/6 11:02
 * @description websocket handler
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final String MESSAGE = "10";
    private static final int TIMES = 10;
    private static int onlineCount = 0;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("获取消息 >> " + message.getPayload());
        // 向客户端发送消息
        session.sendMessage(new TextMessage("消息已收到"));

        if (MESSAGE.equals(message.getPayload())) {
            for (int i = 0; i < TIMES; i++) {
                session.sendMessage(new TextMessage("消息 >> " + i));
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("暂停发生异常 ： " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String str = (String) session.getAttributes().get("uid");
        System.out.println("session 中的数据 ： " + str);
        session.sendMessage(new TextMessage("欢迎连接到WS服务"));
        addOnlineCount();
        System.out.println("上线成功：当前在线人数 ： " + WebSocketHandler.onlineCount);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("已经断开连接");
        subOnlineCount();
        System.out.println("下线成功：当前在线人数 ： " + WebSocketHandler.onlineCount);
    }

    /**
     * 获取当前在线人数
     *
     * @return int
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 当前人数加一
     */
    public static synchronized void addOnlineCount() {
        WebSocketHandler.onlineCount++;
    }

    /**
     * 当前人数减一
     */
    public static synchronized void subOnlineCount() {
        WebSocketHandler.onlineCount--;
    }
}
