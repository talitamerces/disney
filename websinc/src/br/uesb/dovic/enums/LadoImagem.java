package br.uesb.dovic.enums;

import java.io.Serializable;

public enum LadoImagem implements Serializable {
	FRENTE("FRENTE"),
	VERSO("VERSO");
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private LadoImagem(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}


//	FRENTE,
//	VERSO,
//	CAPA,
//	LOMBADA



