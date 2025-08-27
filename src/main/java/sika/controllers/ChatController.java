package sika.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.BaseAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController {

    private final ChatClient chatClient;


    private ChatController(ChatClient.Builder builder, PgVectorStore store) {
        BaseAdvisor advisor = new QuestionAnswerAdvisor(store);
        this.chatClient = builder
                .defaultAdvisors(advisor)
                .build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(HttpServletRequest request) {
        log.info("[{}] New Request incoming ", request.getSession().getId());
        String response = this.chatClient.prompt().user("how did the federal reserve's recent interest rate cut impact various asset classes according to the analysis ").call().content();
        return ResponseEntity.ok(response);
    }
}



