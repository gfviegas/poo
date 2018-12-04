package com.gfviegas.ex2;

public abstract class Veiculo {
    float velocidade;

    public abstract int getNumeroRodas();

    public abstract void acelerar(float velocidade);

    public abstract void parar();
}
