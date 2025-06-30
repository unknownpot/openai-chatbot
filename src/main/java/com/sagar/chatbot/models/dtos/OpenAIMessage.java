package com.sagar.chatbot.models.dtos;

import lombok.Data;

@Data
public class OpenAIMessage {

    private String role;
    private String content;


    public OpenAIMessage(String user, String userMessage) {
        this.role = user;
        this.content = userMessage;
    }
}
