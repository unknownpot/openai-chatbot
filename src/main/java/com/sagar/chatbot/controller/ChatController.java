package com.sagar.chatbot.controller;


import com.sagar.chatbot.models.dtos.ChatRequest;
import com.sagar.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(chatService.getChatResponse(request.getMessage()));
    }
}

