package com.fuji.professor_service.webClient;

import com.fuji.professor_service.models.Class;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientClass {
    private final WebClient.Builder webClient;

    public List<Class> getClassByID(List<String> ids) {
        return webClient.build().post()
                .uri("http://localhost:8800/CLASS-SERVICE/api/class/get-all-by-ids")
                .bodyValue(ids)
                .retrieve()
                .bodyToFlux(Class.class)
                .collectList()
                .block();
    }
    public void deleteStudentFromClass(Map<String, String> params) {
        webClient.build().patch()
                .uri("http://localhost:8800/CLASS-SERVICE/api/class/delete-student")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
