package com.fuji.student_service.models;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Report {
    private Month month;
    private Date date;
    private String motif;
}
