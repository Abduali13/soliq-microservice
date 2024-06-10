package com.company.userservice.client.enums;

public enum CardType {
    HUMO("Humo"),
    UZCARD("UzCard"),
    VISA("Visa"),
    MASTERCARD("MasterCard");

    private String name;

    CardType(String name){
        this.name = name;
    }

}
