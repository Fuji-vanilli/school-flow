package com.fuji.classservice.webClient;

import com.fuji.classservice.models.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientCourse {
    private final WebClient.Builder webClient;

    public Course getCourse(String id) {
        log.info("course with the id {} getted successfully!", id);
        return webClient.build().get()
                .uri("http://localhost:8800/COURSE-SERVICE/api/course/get/"+id)
                .retrieve()
                .bodyToMono(Course.class)
                .block();
    }

    public List<Course> getAllCourseByIDs(List<String> ids) {
        return List.of();
    }
}
