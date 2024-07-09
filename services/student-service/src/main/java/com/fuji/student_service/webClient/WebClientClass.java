package com.fuji.student_service.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientClass {
    private final WebClient.Builder webClient;

    public void deleteStudentFromClass(Map<String, String> params) {
        webClient.build().patch()
                .uri("http://localhost:8800/CLASS-SERVICE/api/class/delete-student")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
