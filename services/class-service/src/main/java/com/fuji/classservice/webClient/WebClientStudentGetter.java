package com.fuji.classservice.webClient;

import com.fuji.classservice.models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientStudentGetter {
    private final WebClient.Builder webClient;

    public Student getStudent(String studentId) {
        return webClient.build().get()
                .uri("http://localhost:8800/STUDENT-SERVICE/api/student/get/" + studentId)
                .retrieve()
                .bodyToMono(Student.class)
                .block();
    }
}
