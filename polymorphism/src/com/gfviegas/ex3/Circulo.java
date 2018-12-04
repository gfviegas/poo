package com.gfviegas.ex3;

public class Circulo extends Forma {
    float raio;

    public Circulo(float raio) {
        this.raio = raio;
    }

    float calcularArea() {
        return (float) (Math.PI * Math.pow(raio, 2));
    }

    float cacularPerimetro() {
        return (float) (2 * Math.PI * raio);
    }

    String getTipo() {
        return "Circulo";
    }
}
