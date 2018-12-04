package com.gfviegas.ex1;

public class Main {

    public static void main(String[] args) {
        CalculadoraDeBolso calculadoraRuim = new CalculadoraDeBolso();
        CalculadoraCientifica calculadoraOtima = new CalculadoraCientifica();

        System.out.println("***Utilizando a calculadora ruim!***");
        System.out.format("%.2f + %.2f = %.2f\n", 23.5, 12.12, calculadoraRuim.soma(23.5, 12.12));
        System.out.format("%.2f - %.2f = %.2f\n", 23.5, 12.12, calculadoraRuim.subtracao(23.5, 12.12));
        System.out.format("%.2f * %.2f = %.2f\n", 23.5, 12.12, calculadoraRuim.multiplicacao(23.5, 12.12));
        System.out.format("%.2f / %.2f = %.2f\n", 23.5, 12.12, calculadoraRuim.divisao(23.5, 12.12));
        System.out.println("*** Fim calculadora ruim!***\n");

        System.out.println("***Utilizando a calculadora boa!***");
        System.out.format("%.2f + %.2f = %.2f\n", 5771.5, 123.78, calculadoraOtima.soma(5771.5, 123.78));
        System.out.format("%.2f - %.2f = %.2f\n", 5771.5, 123.78, calculadoraOtima.subtracao(5771.5, 123.78));
        System.out.format("%.2f * %.2f = %.2f\n", 5771.5, 123.78, calculadoraOtima.multiplicacao(5771.5, 123.78));
        System.out.format("%.2f / %.2f = %.2f\n", 5771.5, 123.78, calculadoraOtima.divisao(5771.5, 123.78));
        System.out.format("%.2f ^ %.2f = %.2f\n", 233.1, 3.0, calculadoraOtima.potencia(233.1, 3.0));
        System.out.format("Raiz de %.1f = %.2f\n", 5771.5, calculadoraOtima.raizQuadrada(5771.5));
        System.out.println("*** Fim calculadora ruim!***");
    }
}
