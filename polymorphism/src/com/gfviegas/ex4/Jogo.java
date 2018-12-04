package com.gfviegas.ex4;

import java.util.ArrayList;
import java.util.Random;

public class Jogo {
    private final int MAX_GAMES = 20;

    private void disputa(Objeto adv1, Objeto adv2) {
        String nomeAdv1 = adv1.toString();
        String nomeAdv2 = adv2.toString();

        System.out.printf("Disputa entre %s e %s \n", nomeAdv1, nomeAdv2);

        if (nomeAdv1.equals(nomeAdv2)) {
            System.out.print("\tEmpate!\n\n");
            return;
        }

        String vencedor = (adv1.verificaVitoria(adv2)) ? nomeAdv1 : nomeAdv2;
        System.out.printf("\tO vencedor é %s !\n\n", vencedor);
    }


    public void joga() {
        Random random = new Random();

        Objeto pedra = new Pedra();
        Objeto papel = new Papel();
        Objeto tesoura = new Tesoura();

        ArrayList<Objeto> objetos = new ArrayList<Objeto>();
        objetos.add(pedra);
        objetos.add(papel);
        objetos.add(tesoura);


        for (int i = 0; i < this.MAX_GAMES; i++) {
            int sorteado1 = random.nextInt(3);
            int sorteado2 = random.nextInt(3);

            this.disputa(objetos.get(sorteado1), objetos.get(sorteado2));
        }
    }
}
