package com.study.springboot_websocket.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
/**
 * 服务器端点类
 */

@Component
@ServerEndpoint("/test")//设置端点的映射路径,将该类标识为WebSocket端点类
public class TestWebSocketEndpoint {


    @OnOpen
    public void onOpen(Session session)throws IOException {
        System.out.println(session.getId()+"客户端已连接");
    }

    /**
     * 当连接关闭时,在控制台打印关闭状态码
     */
    @OnClose
    public void onClose(Session session, CloseReason reason){
        System.out.println(session.getId()+"客户端已关闭,关闭吗:"+reason.getCloseCode().getCode());
    }

    /**
     * 当服务端收到客户端发来的消息后,延迟500毫秒回复
     */
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("客户端发来消息:"+message);
        try {
            Thread.sleep(500);//休眠500毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getAsyncRemote:使用异步发送消息接口的对象向客户端发送消息
        session.getAsyncRemote().sendText("服务端收到客户端发来的消息:"+message);
    }

    /**
     * 打印异常
     */
    @OnError
    public void onError(Session session,Throwable e){
        e.printStackTrace();//打印异常
    }
}
