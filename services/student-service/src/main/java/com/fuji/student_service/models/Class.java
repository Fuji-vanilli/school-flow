package com.fuji.student_service.models;

import java.math.BigDecimal;

public record Class(
        String id,
        String level,
        String section,
        BigDecimal ecolage
) {
}
