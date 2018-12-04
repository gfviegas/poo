package com.gfviegas.ex1;

/**
 * Classe representando um professor que recebe por hora
 */
public class ProfessorHorista extends Professor {
    private double horasTrabalhadas;
    private double salarioHora;

    public ProfessorHorista(int matricula, String nome, String dataNascimento, double horasTrabalhadas, double salarioHora) {
        super(matricula, nome, dataNascimento);
        this.horasTrabalhadas = horasTrabalhadas;
        this.salarioHora = salarioHora;
    }
}
