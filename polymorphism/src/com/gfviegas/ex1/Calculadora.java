package com.gfviegas.ex1;

public abstract class Calculadora {

    double soma(Number a, Number b){
        return a.doubleValue() + b.doubleValue();
    }

    double subtracao(Number a, Number b) {
        return a.doubleValue() - b.doubleValue();
    }

    double divisao(Number a, Number b) throws IllegalArgumentException {
        if (b.doubleValue() == 0) throw new IllegalArgumentException("Argument 'divisor' is 0");
        return a.doubleValue() / b.doubleValue();
    }

    double multiplicacao(Number a, Number b) {
        return a.doubleValue() * a.doubleValue();
    }
}
