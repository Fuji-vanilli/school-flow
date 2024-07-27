package com.fuji.student_service.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Note {
    private Date date;
    private Map<Course, BigDecimal> values;
    private Period period;
}
