package com.gfviegas.ex1;

/**
 * Classe representando um professor contratado que recebe por período
 */
public class ProfessorEfetivo extends Professor {
    private JornadaTrabalho jornadaTrabalho;
    private double salario;

    public ProfessorEfetivo(int matricula, String nome, String dataNascimento, JornadaTrabalho jornadaTrabalho, double salario) {
        super(matricula, nome, dataNascimento);
        this.jornadaTrabalho = jornadaTrabalho;
        this.salario = salario;
    }
}
