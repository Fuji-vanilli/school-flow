package com.fuji.professor_service.models;

import java.math.BigDecimal;

public record Course(
        String id,
        String code,
        String title,
        String description,
        BigDecimal hoursPerWeek,
        BigDecimal pricePerHour
) {
}
