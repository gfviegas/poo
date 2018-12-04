package com.gfviegas.ex2;

public class Onibus extends Veiculo {
    private int capacidadePassageiros;

    public Onibus(String placa, int numEixos, double peso, int capacidade) {
        super(placa, numEixos, peso);

        if (!validaCapacidade(capacidade)) {
            System.out.println("Onibus com capacidade ímpar. Será considerado o valor par anterior ao fornecido.");
            this.capacidadePassageiros = (capacidade - 1);
            return;
        }

        this.capacidadePassageiros = capacidade;
    }

    public int getCapacidade() {
        return capacidadePassageiros;
    }

    public void setCapacidade(int capacidade) {
        if (!validaCapacidade(capacidade)) return;
        this.capacidadePassageiros = capacidade;
    }

    private boolean validaCapacidade(int capacidade) {
        return (capacidade % 2) == 0;
    }

    @Override
    public String toString() {
        return "Onibus com capacidade para " + capacidadePassageiros + " ocupantes.";
    }
}
