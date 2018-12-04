package com.gfviegas.ex3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Forma> formas = new ArrayList<Forma>();

        formas.add(new Retangulo(23.92f, 24.23f));
        formas.add(new Circulo(12.3f));
        formas.add(new Quadrado(29.2f));
        formas.add(new Retangulo(1.99f, 90));
        formas.add(new Quadrado(4.0f));

        for (Forma f: formas) {
            System.out.format("Forma %s: \n", f.getTipo());
            System.out.format("\t %-12s: %.2f \n", "Perímetro", f.cacularPerimetro());
            System.out.format("\t %-12s: %.2f \n", "Área", f.calcularArea());
        }
    }
}
