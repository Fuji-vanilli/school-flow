package com.fuji.classservice.DTO;

import com.fuji.classservice.entities.Level;
import com.fuji.classservice.entities.Section;
import com.fuji.classservice.models.Professor.Professor;
import com.fuji.classservice.models.Student.Student;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClassRequest(
        @NotNull(message = "level required:")
        Level level,
        @NotNull(message = "section required!")
        Section section,
        @NotNull(message = "maximum capacity required!")
        BigDecimal maximumCapacity,
        @NotNull(message = "ecolage amount required!")
        BigDecimal ecolage
) {

}
