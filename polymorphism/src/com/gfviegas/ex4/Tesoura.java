package com.gfviegas.ex4;

public class Tesoura implements Objeto {
    public boolean verificaVitoria(Objeto adversario) {
        return (adversario instanceof Papel);
    }
    @Override
    public String toString() {
        return "Tesoura";
    }
}
