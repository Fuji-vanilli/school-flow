package com.fuji.course_service.webClient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebClientCourse {
    private final WebClient.Builder webClient;

    public void addCourseToClass(String courseID, String classID) {
        Map<String, String> params= Map.of(
                "courseID", courseID,
                "classID", classID
        );

        webClient.build().patch()
                .uri("http://localhost:8800/CLASS-SERVICE/api/course/add-course")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
