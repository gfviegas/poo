package com.gfviegas.ex1;

public class CalculadoraCientifica extends Calculadora {
    final static String name = "Calculadora Cientifica HP 10S+";

    double potencia(Number a, Number b) {
        return Math.pow(a.doubleValue(), b.doubleValue());
    }

    double raizQuadrada(Number a) throws IllegalArgumentException {
        if (a.doubleValue() < 0) throw new IllegalArgumentException("Argumento de raiz quadrada é negativo");
        return Math.sqrt(a.doubleValue());
    }
}
