package com.gfviegas.ex4;

public class Pedra implements Objeto {
    public boolean verificaVitoria(Objeto adversario) {
        return (adversario instanceof Tesoura);
    }

    @Override
    public String toString() {
        return "Pedra";
    }
}
