package com.gfviegas.library.user;

import com.gfviegas.library.Address;

public class User {
    private int id;
    private String name;
    private String password;
    private Address address;

    public User(int id, String name, String password, String street, String city, int number, int zipCode) {
        this.id = id;
        this.name = name;
        setPassword(password);

        this.address = new Address(street, number, zipCode, city);
    }

    //    GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
