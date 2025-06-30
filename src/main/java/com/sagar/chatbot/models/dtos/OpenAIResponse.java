package com.sagar.chatbot.models.dtos;


import lombok.Data;
import java.util.List;

@Data
public class OpenAIResponse {

    private List<Choice> choices;

    @Data
    public static class Choice {
        private OpenAIMessage message;
    }
}
