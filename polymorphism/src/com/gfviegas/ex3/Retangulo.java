package com.gfviegas.ex3;

public class Retangulo extends Forma {
    float lado;
    float altura;

    public Retangulo(float lado, float altura) {
        this.lado = lado;
        this.altura = altura;
    }

    float calcularArea() {
        return lado * altura;
    }

    float cacularPerimetro() {
        return 2 * (lado + altura);
    }

    String getTipo() {
        return "Retângulo";
    }
}
