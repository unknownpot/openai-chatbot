package com.sagar.chatbot.service;


import com.sagar.chatbot.models.dtos.OpenAIMessage;
import com.sagar.chatbot.models.dtos.OpenAIRequest;
import com.sagar.chatbot.models.dtos.OpenAIResponse;
import com.sagar.chatbot.openaiconfig.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    @Autowired
    private OpenAIConfig config;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String userMessage) {
        OpenAIMessage user = new OpenAIMessage("user", userMessage);
        OpenAIRequest request = new OpenAIRequest();
        request.setModel(config.getModel());
        request.setMessages(List.of(user));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(config.getApiKey());

        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<OpenAIResponse> response = restTemplate.postForEntity(
                config.getEndpoint(), entity, OpenAIResponse.class);

        return Objects.requireNonNull(response.getBody()).getChoices().get(0).getMessage().getContent();
    }
}

