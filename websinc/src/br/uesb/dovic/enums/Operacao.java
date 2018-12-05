package br.uesb.dovic.enums;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public enum Operacao implements Serializable {
	
	EXISTS("EXISTE"),
	PRECEDES("PRECEDE"),
	IPRECEDES("PRECEDE IMEDIATAMENTE"),
	VIZINHANCADIREITA("TEM COMO N-ÉSIMO VIZINHO À DIREITA"),
	VIZINHANCAESQUERDA("TEM COMO N-ÉSIMO VIZINHO À ESQUERDA"),
	VIZINHANCA("TEM COMO N-ÉSIMO VIZINHO (À DIREITA OU À ESQUERDA)"),
	INICIAL("PALAVRA INICIAL NA SENTENÇA"),
	FINAL("PALAVRA FINAL NA SENTENÇA"),
	POSICAO("PALAVRA NA N-ÉSIMA POSIÇÃO NA SENTENÇA");
	
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private Operacao(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}


