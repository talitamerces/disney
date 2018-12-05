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

public enum FuncaoSintatica implements Serializable {
	
	EXISTS("EXISTE"),
	PRECEDES("PRECEDE"),
	IPRECEDES("PRECEDE IMEDIATAMENTE"),
	DOMINATES("DOMINA"),
	IDOMINATES("DOMINA IMEDIATAMENTE"),
	EIRMAO("É IRMÃO DE"),
	CCOMMANDS("C-COMANDA"),
	DOMSWORDS("DOMINA N PALAVRAS"),
	DOMSWORDSMENOR("DOMINA MENOS DE N PALAVRAS"),
	DOMSWORDSMAIOR("DOMINA MAIS DE N PALAVRAS"),
	IDOMSFIRST("DOMINA IMEDIATAMENTE COMO PRIMEIRO FILHO"),
	IDOMSLAST("DOMINA IMEDIATAMENTE COMO ÚLTIMO FILHO"),
	IDOMSNUMBER("DOMINA IMEDIATAMENTE COMO N-ÉSIMO FILHO"),
	IDOMSONLY("DOMINA IMEDIATAMENTE COMO ÚNICO FILHO"),
	IDOMSTOTAL("DOMINA IMEDIATAMENTE N FILHOS"),
	IDOMSTOTALMENOR("DOMINA IMEDIATAMENTE MENOS DE N FILHOS"),
	IDOMSTOTALMAIOR("DOMINA IMEDIATAMENTE MAIS DE N FILHOS");
	
	
	private static final long serialVersionUID = 1l;
    private String nome;

    private FuncaoSintatica(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}


