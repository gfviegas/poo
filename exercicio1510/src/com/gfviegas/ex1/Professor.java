package com.gfviegas.ex1;

/**
 * Classe que representa um professor
 */
public class Professor {
    /**
     * Matrícula
     */
    private int matricula;

    /**
     * Nome do Professor
     */
    private String nome;

    /**
     * Data de Nascimento
     */
    private String dataNascimento;


    public Professor(int matricula, String nome, String dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
