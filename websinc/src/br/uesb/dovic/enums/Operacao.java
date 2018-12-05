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
	VIZINHANCADIREITA("TEM COMO N-�SIMO VIZINHO � DIREITA"),
	VIZINHANCAESQUERDA("TEM COMO N-�SIMO VIZINHO � ESQUERDA"),
	VIZINHANCA("TEM COMO N-�SIMO VIZINHO (� DIREITA OU � ESQUERDA)"),
	INICIAL("PALAVRA INICIAL NA SENTEN�A"),
	FINAL("PALAVRA FINAL NA SENTEN�A"),
	POSICAO("PALAVRA NA N-�SIMA POSI��O NA SENTEN�A");
	
	
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


