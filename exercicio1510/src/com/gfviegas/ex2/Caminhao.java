package com.gfviegas.ex2;

public class Caminhao extends Veiculo {
    private double capacidadeCarga;

    public Caminhao(String placa, int numEixos, double peso, double capacidadeCarga) {
        super(placa, numEixos);

        if (!validaPeso(peso) || !validaPeso(capacidadeCarga)) {
            System.out.println("Caminhao invalido!");
            return;
        }

        setPeso(peso);
        this.capacidadeCarga = capacidadeCarga;
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(double capacidadeCarga) {
        if (!validaPeso(capacidadeCarga)) return;
        this.capacidadeCarga = capacidadeCarga;
    }

    private boolean validaPeso(double peso) {
        return (peso <= 45);
    }

    @Override
    public String toString() {
        return "Caminhao de carga - capacidade " + capacidadeCarga + " toneladas. Veiculo de placa " + this.getPlaca();
    }
}
