package com.gfviegas.ex3.heranca;

import java.util.Date;

public class BemSemovente extends BemPatriomonial {
    private Date dataNascimento;
    private Especie especie;

    public BemSemovente(String descricao, Date dataAquisicao, int id, Date dataNascimento, Especie especie) {
        super(descricao, dataAquisicao, id);
        this.dataNascimento = dataNascimento;
        this.especie = especie;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}
