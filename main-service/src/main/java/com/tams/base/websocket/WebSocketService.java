package com.tams.base.websocket;

import com.alibaba.fastjson.JSON;
import com.tams.base.websocket.model.UserWsModel;
import com.tams.model.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author swiChen
 * @data 2022-1-11
 *
 */

@Slf4j
@Component
@ServerEndpoint("/ws/{role}/{userId}")
public class WebSocketService {


    // 记录当前在线连接数
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<Session, UserWsModel> users = new ConcurrentHashMap<>();

    // 连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("role") String role , @PathParam("userId") String uid) throws EncodeException, IOException {
        onlineCount.incrementAndGet(); // 在线数加1
        log.info("ws 连接加入: role [{}] , id [{}] , 当前人数 [{}] ",role , uid , onlineCount.get());
        UserWsModel userModel = new UserWsModel();
        userModel.setRole(role);
        userModel.setId(uid);
        users.put(session , userModel);


    }

    // 连接关闭调用的方法
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数加1
        log.info("关闭ws连接 : {} , 当前在线人数为: {}", session.getId() , onlineCount.get());
        users.remove(session);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message , Session session) {

    }

    @OnError
    public void onError(Session session , Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public static void sendMessage(UserWsModel userModel , ResultModel resultModel){
        users.forEach((session , user)->{
            if (user.getRole().equals(userModel.getRole()) && user.getId().equals(userModel.getId()) && session.isOpen()){
                sendMessage(resultModel , session);
            }
        });
    }

    // 服务端发送消息给客户端

    private static void sendMessage(ResultModel resultModel, Session toSession) {
        try {

            log.info("服务端给客户端[{}]  发送消息{}", toSession.getId(), resultModel);
            toSession.getBasicRemote().sendText(JSON.toJSONString(resultModel));
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{}", e);
        }
    }

}
