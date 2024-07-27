package com.fuji.student_service.entities;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Address {
    private String address1;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}
