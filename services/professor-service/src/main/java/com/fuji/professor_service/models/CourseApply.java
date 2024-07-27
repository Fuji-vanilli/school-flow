package com.fuji.professor_service.models;

import com.fuji.professor_service.entities.Month;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CourseApply {
        private Course course;
        private Class aClass;
        private BigDecimal totalHour;
        private Month month;
}
