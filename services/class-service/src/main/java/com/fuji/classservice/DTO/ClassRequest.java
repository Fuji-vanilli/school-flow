package com.fuji.classservice.DTO;

import com.fuji.classservice.entities.Section;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClassRequest(
        String id,
        @NotNull(message = "level required:")
        String level,
        @NotNull(message = "section required!")
        Section section,
        @NotNull(message = "maximum capacity required!")
        BigDecimal maximumCapacity,
        @NotNull(message = "ecolage amount required!")
        BigDecimal ecolage
) {

}
