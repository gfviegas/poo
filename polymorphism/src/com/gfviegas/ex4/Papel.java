package com.gfviegas.ex4;

public class Papel implements Objeto {
    public boolean verificaVitoria(Objeto adversario) {
        return (adversario instanceof Pedra);
    }

    @Override
    public String toString() {
        return "Papel";
    }
}
