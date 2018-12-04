package com.gfviegas.ex2;

public class Veiculo {
    private String placa;
    private int numEixos;
    private double peso;

    public Veiculo(String placa, int numEixos, double peso) {
        this.placa = placa;
        this.numEixos = numEixos;
        this.peso = peso;
    }

    public Veiculo(String placa, int numEixos) {
        this.placa = placa;
        this.numEixos = numEixos;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumEixos() {
        return numEixos;
    }

    public void setNumEixos(int numEixos) {
        this.numEixos = numEixos;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Veiculo de placa " + placa + '\'';
    }
}
