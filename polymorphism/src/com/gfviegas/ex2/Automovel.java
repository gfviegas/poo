package com.gfviegas.ex2;

import java.util.Date;

public class Automovel extends Veiculo {
    private Date ultimaTrocaOleo;

    public Automovel() {
    }

    public Automovel(Date ultimaTrocaOleo) {
        this.ultimaTrocaOleo = ultimaTrocaOleo;
    }

    public int getNumeroRodas() {
        return 4;
    }

    public void acelerar(float velocidade) {
        this.velocidade = velocidade;
    }

    public void parar() {
        this.velocidade = 0;
    }

    public void trocarOleo() {
        this.ultimaTrocaOleo = new Date();
    }

}
