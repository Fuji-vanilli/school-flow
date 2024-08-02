package com.fuji.classservice.models;


import java.math.BigDecimal;

public record Course(
        String id,
        String code,
        String title,
        String description,
        String professorID,
        String classID,
        BigDecimal credit,
        BigDecimal hoursPerWeek,
        BigDecimal pricePerHour
) {
}
