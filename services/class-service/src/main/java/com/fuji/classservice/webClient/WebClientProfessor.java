package com.fuji.classservice.webClient;

import com.fuji.classservice.models.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientProfessor {
    private final WebClient.Builder webClient;

    public Professor getProfessor(String idProfessor) {
        return null;
    }
}
