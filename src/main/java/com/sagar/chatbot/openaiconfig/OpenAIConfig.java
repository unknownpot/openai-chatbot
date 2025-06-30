package com.sagar.chatbot.openaiconfig;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai")
@Data
public class OpenAIConfig {

    private String apiKey;
    private String endpoint;
    private String model;

}
