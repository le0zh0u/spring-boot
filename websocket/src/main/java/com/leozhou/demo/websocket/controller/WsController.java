package com.leozhou.demo.websocket.controller;

import com.leozhou.demo.websocket.dto.RequestMessageDto;
import com.leozhou.demo.websocket.dto.ResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by zhouchunjie on 16/7/27.
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")//当浏览器想服务端发哦少年宫请求时, 通过MessageMapping映射/welcome
    @SendTo("/topic/getResponse")//当服务端有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public ResponseDto say(RequestMessageDto message) throws Exception {
        Thread.sleep(3000);
        return new ResponseDto("Welcome " + message.getName() + "!");
    }
}
