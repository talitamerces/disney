package br.uesb.dovic.enums;

import java.io.Serializable;

public enum Completude implements Serializable{
	COMPLETO("Completo"),
	INCOMPLETO("Incompleto"),
	IMPOSSIVEL_DETERMINAR("Impossível determinar");
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private Completude(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
