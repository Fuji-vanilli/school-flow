package com.fuji.student_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Course {
    private String id;
    private String name;
    private BigDecimal coefficient;
    private BigDecimal duration;
}
