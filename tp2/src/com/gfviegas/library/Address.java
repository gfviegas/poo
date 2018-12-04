package com.gfviegas.library;

public class Address {
    private String street;
    private int number;
    private int zipCode;
    private String city;

    public Address(String street, int number, int zipCode, String city) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }
}
