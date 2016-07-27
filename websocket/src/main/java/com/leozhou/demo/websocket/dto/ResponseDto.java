package com.leozhou.demo.websocket.dto;

/**
 * Created by zhouchunjie on 16/7/27.
 */
public class ResponseDto {

    private String responseMessage;

    public ResponseDto(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
