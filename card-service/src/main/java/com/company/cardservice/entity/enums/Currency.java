package com.company.cardservice.entity.enums;

public enum Currency {
    SUM("UZB - Sum"),
    DOLLAR("USA - Dollar"),
    EURO("Europa - Euro");

    private String name;
    Currency(String name){
        this.name=name;
    }
}
