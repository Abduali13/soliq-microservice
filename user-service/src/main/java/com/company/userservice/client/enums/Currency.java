package com.company.userservice.client.enums;

public enum Currency {
    SUM("UZB - Sum"),
    DOLLAR("USA - Dollar"),
    EURO("Europa - Euro");

    private String name;
    Currency(String name){
        this.name=name;
    }
}
