package com.fuji.student_service.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientClass {
    private final WebClient.Builder webClient;

    public String deleteStudentFromClass(String classID) {
        return webClient.build().delete()
                .uri("http://localhost:8800/CLASS-SERVICE/api/class/delete/"+classID)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
