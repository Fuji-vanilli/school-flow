package com.fuji.student_service.models;

public enum Month {
    JANUARY ("Janvier"),
    FEBRUARY("Fevrier"),
    MARCH("Mars"),
    APRIL("Avril"),
    MAY("Mai"),
    JUNE("Juin"),
    JULY("Juillet"),
    AUGUST("Aout"),
    SEPTEMBER("Septembre"),
    OCTOBER("Octobre"),
    NOVEMBER("Novembre"),
    DECEMBER("Decembre");

    private final String value;
    Month(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
