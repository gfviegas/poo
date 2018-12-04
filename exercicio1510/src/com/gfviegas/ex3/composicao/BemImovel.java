package com.gfviegas.ex3.composicao;

import java.util.Date;

public class BemImovel {
    private BemPatriomonial bemPatriomonial;
    private double area;
    private double ultimoValorIPTU;

    public BemImovel(String descricao, Date dataAquisicao, int id, double area, double ultimoValorIPTU) {
        bemPatriomonial = new BemPatriomonial(descricao, dataAquisicao, id);
        this.area = area;
        this.ultimoValorIPTU = ultimoValorIPTU;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getUltimoValorIPTU() {
        return ultimoValorIPTU;
    }

    public void setUltimoValorIPTU(double ultimoValorIPTU) {
        this.ultimoValorIPTU = ultimoValorIPTU;
    }
}
