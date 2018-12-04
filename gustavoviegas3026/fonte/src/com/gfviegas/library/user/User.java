package com.gfviegas.library.user;

import com.gfviegas.library.Address;
import com.gfviegas.library.loan.Loan;

import java.util.ArrayList;

/**
 * Entidade representando um Usuário do sistema
 */
public class User {
    private int id;
    private String name;
    private String password;
    private Address address;
    private ArrayList<Loan> bookLoans;


    /**
     * @param id - Matrícula do Usuário
     * @param name - Nome Completo do Usuário
     * @param password - Senha (não-criptografada) do Usuário
     * @param street - Rua do Endereço do Usuário
     * @param city - Cidade do Endereço do Usuário
     * @param number - Número da rua do Endereço do Usuário
     * @param zipCode - CEP do Endereço do Usuário
     */
    User(int id, String name, String password, String street, String city, int number, int zipCode) {
        this.id = id;
        this.name = name;
        this.password = password;

        this.address = new Address(street, number, zipCode, city);
        this.bookLoans = new ArrayList<Loan>();
    }

    //    GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<Loan> getBookLoans() {
        return bookLoans;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBookLoans(ArrayList<Loan> bookLoans) {
        this.bookLoans = bookLoans;
    }

    @Override
    public String toString() {
        return "ID: " + id + ". Nome: " + name + ". Senha: " + password;
    }
}
