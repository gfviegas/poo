package com.gfviegas.ex3.composicao;

import java.util.Date;

public class BemPatriomonial {
    private String descricao;
    private Date dataAquisicao;
    private int id;
    private Situacao situacao;

    public BemPatriomonial(String descricao, Date dataAquisicao, int id) {
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.id = id;
        this.situacao = Situacao.INICIAL;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void colocarEmInventario() {
        this.situacao = Situacao.EM_INVENTARIO;
    }

    public void tombar() {
        this.situacao = Situacao.TOMBADO;
    }

    public void baixar() {
        this.situacao = Situacao.TOMBADO;
    }
}
