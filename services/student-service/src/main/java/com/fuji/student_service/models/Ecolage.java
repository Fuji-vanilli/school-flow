package com.fuji.student_service.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;

@Getter @Setter
public class Ecolage {
    private Month month;
    private String year;
    private Date paymentDate;
    private BigDecimal amount;
    private boolean status;
}
