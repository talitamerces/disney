package br.uesb.dovic.enums;

import java.io.Serializable;

public enum TipoData implements Serializable{
	INTERVALO("Intervalo"),
	NAO_INTERVALO("Não intervalo");
	
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private TipoData(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}