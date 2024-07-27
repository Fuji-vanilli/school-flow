package com.fuji.professor_service.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Class(
        String id,
        String level,
        Section section,
        BigDecimal ecolage
) {
}
