package be.helmo.api.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();// Different options can be set here by "builder."
    }


    @PostMapping("/financewise/chat")
    public String chat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
