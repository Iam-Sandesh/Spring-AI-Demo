package com.learn.SpringAICode.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaAIController {

    // By using Chat Model

//    private OllamaChatModel chatModel;
//
//    public OllamaAIController(OllamaChatModel chatModel) {
//        this.chatModel = chatModel;
//    }

    // By using chat client (it provides more control over response).
    // If we have only one LLM then we can also use ChatClient.Builder

    private ChatClient chatClient;

    public OllamaAIController(OllamaChatModel chatModel, ChatMemory chatMemory) {
//        this.chatClient = ChatClient.create(chatModel);

        // adding memory advisor
        this.chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }


    @GetMapping("/askOllama/{message}")
    public ResponseEntity<String> getAiResponse(@PathVariable String message) {

        String response = "";
        String model = "";
        try {
            // getting response with chatModel
//            response = chatModel.call(message);

            // ChatClient with Chat Response
            ChatResponse chatResponse = chatClient.prompt(message).call().chatResponse();

            response = chatResponse.getResult().getOutput().getText();

            model = chatResponse.getMetadata().getModel();

            
        } catch (Exception ex) {
            response = "Exception Occurred.";
        }

        return ResponseEntity.ok(response);
    }
}
