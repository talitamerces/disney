package br.uesb.dovic.enums;

import java.io.Serializable;

public enum TipoTrabalhoCorpus implements Serializable{
	CAPTURA("Captura"),
	CATALOGACAO("Catalogação"),
	EDICAO_fOTOGRAFICA("Edição fotográfica"),
	ANALISE_DESCRITIVA("Análise descritiva"),
	CADASTRO_WEBSINC("Cadastro no WebSinc"),
	TRANSCRICAO("Transcrição"),
	EDICAO("Edição"),
	REVISAO_SINTAXE("Revisão da anotação sintática"),
	REVISAO_MORFOLOGIA("Revisão da anotação morfológica");

	private static final long serialVersionUID = 1l;
    private String nome;
    

    private TipoTrabalhoCorpus(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}