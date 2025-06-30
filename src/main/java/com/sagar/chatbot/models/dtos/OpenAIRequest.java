package com.sagar.chatbot.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OpenAIRequest {

    private String model;
    private List<OpenAIMessage> messages;
}
