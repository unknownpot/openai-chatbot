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
        try {
            OpenAIMessage user = new OpenAIMessage("user", userMessage);
            OpenAIRequest request = new OpenAIRequest();
            request.setModel(config.getModel());
            request.setMessages(List.of(user));

            HttpHeaders headers = getHttpHeaders();

            HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<OpenAIResponse> response = restTemplate.postForEntity(
                    config.getEndpoint(), entity, OpenAIResponse.class);

            if (response.getBody() != null && response.getBody().getChoices() != null
                    && !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            } else {
                throw new IllegalAccessException("Invalid response from OpenAI API");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while communicating with OpenAI API: " + e.getMessage(), e);
        }

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(config.getApiKey());
        return headers;
    }
}

