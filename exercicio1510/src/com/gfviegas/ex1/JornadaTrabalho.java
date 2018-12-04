package com.gfviegas.ex1;

/**
 * Jornadas de Trabalho disponíveis para um professor efetivo
 */
public enum JornadaTrabalho {
    MEIO_HORARIO(0, 20),
    HORARIO_INTEGRAL(1, 40),
    DEDICACAO_EXCLUSIVA(2, 0);

    private int id;
    private int horas;

    JornadaTrabalho(int id, int horas) {
        this.id = id;
        this.horas = horas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
