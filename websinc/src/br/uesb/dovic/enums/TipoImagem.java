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

public enum TipoImagem implements Serializable {
	FOLHA("FOLHA"),
	CAPA("CAPA"),
	CAPAINTERNA("CAPA INTERNA"),
	CONTRACAPA("CONTRA CAPA"),
	LOMBADA("LOMBADA"),
	ABERTURA("TERMO DE ABERTURA"),
	FECHAMENTO("TERMO DE FECHAMENTO");
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private TipoImagem(String nome) {
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


