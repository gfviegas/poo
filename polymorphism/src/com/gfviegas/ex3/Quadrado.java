package com.gfviegas.ex3;

public class Quadrado extends Retangulo {

    public Quadrado(float lado) {
        super(lado, lado);
    }

    @Override
    String getTipo() {
        return "Quadrado";
    }
}