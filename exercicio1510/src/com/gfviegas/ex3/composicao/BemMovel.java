package com.gfviegas.ex3.composicao;

import java.util.Date;

public class BemMovel {
    private BemPatriomonial bemPatriomonial;
    private long notaFiscal;
    private String nomeFornecedor;

    public BemMovel(String descricao, Date dataAquisicao, int id, long notaFiscal, String nomeFornecedor) {
        bemPatriomonial = new BemPatriomonial(descricao, dataAquisicao, id);
        this.notaFiscal = notaFiscal;
        this.nomeFornecedor = nomeFornecedor;
    }

    public long getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(long notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}
