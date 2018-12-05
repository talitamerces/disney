package br.uesb.dovic.enums;

import java.io.Serializable;

public enum TipoTrabalhoCorpus implements Serializable{
	CAPTURA("Captura"),
	CATALOGACAO("Cataloga��o"),
	EDICAO_fOTOGRAFICA("Edi��o fotogr�fica"),
	ANALISE_DESCRITIVA("An�lise descritiva"),
	CADASTRO_WEBSINC("Cadastro no WebSinc"),
	TRANSCRICAO("Transcri��o"),
	EDICAO("Edi��o"),
	REVISAO_SINTAXE("Revis�o da anota��o sint�tica"),
	REVISAO_MORFOLOGIA("Revis�o da anota��o morfol�gica");

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