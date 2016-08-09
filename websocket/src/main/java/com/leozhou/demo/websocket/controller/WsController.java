package com.leozhou.demo.websocket.controller;

import com.leozhou.demo.websocket.dto.RequestMessageDto;
import com.leozhou.demo.websocket.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by zhouchunjie on 16/7/27.
 */
@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;//通过SimpMessagingTemplate向浏览器发送消息

    @MessageMapping("/welcome")//当浏览器想服务端发哦少年宫请求时, 通过MessageMapping映射/welcome
    @SendTo("/topic/getResponse")//当服务端有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public ResponseDto say(RequestMessageDto message) throws Exception {
        Thread.sleep(3000);
        return new ResponseDto("Welcome " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        //假设只能abc发给abcd,或abcd发给abc
        if (principal.getName().equals("abc")) {
            //通过messagingTemplate.convertAndSendToUser向用户发送消息,第一个参数是接受消息的用户,第二个是浏览器订阅的地址,第三个是消息本身
            messagingTemplate.convertAndSendToUser("abcd", "/queue/notifications", principal.getName() + "-send:" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("abc", "/queue/notifications", principal.getName() + "-send:" + msg);
        }
    }
}
