package com.fuji.classservice.webClient;

import com.fuji.classservice.models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientStudentGetter {
    private final WebClient.Builder webClient;

    public Student getStudent(String studentId) {
        log.info("Get student {}", studentId);
        return webClient.build().get()
                .uri("http://localhost:8800/STUDENT-SERVICE/api/student/get-by-id/" + studentId)
                .retrieve()
                .bodyToMono(Student.class)
                .block();
    }

    public List<Student> getAllStudentsByIds(List<String> studentIds) {
        return webClient.build().post()
                .uri("http://localhost:8800/STUDENT-SERVICE/api/student/all-by-ids")
                .bodyValue(studentIds)
                .retrieve()
                .bodyToFlux(Student.class)
                .collectList()
                .block();

    }
}
