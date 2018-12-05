package br.uesb.dovic.beans;

public class PaginaDocumento {
	private String nrPagina;
	private String texto;
	private String imagem;
	private String nrFolha;
	private String ladoImagem;
	
	
	public PaginaDocumento(){
		
	}
	

	
	
	public PaginaDocumento(String nr_pagina, String texto, String imagem, String nrFolha, String lado) {
		super();
		this.nrPagina = nr_pagina;
		this.texto = texto;
		this.imagem = imagem;
		this.nrFolha=nrFolha;
		this.ladoImagem=lado;
		
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result
				+ ((nrPagina == null) ? 0 : nrPagina.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaginaDocumento other = (PaginaDocumento) obj;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (nrPagina == null) {
			if (other.nrPagina != null)
				return false;
		} else if (!nrPagina.equals(other.nrPagina))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}




	public String getNrPagina() {
		return nrPagina;
	}




	public void setNrPagina(String nrPagina) {
		this.nrPagina = nrPagina;
	}




	public String getTexto() {
		return texto;
	}




	public void setTexto(String texto) {
		this.texto = texto;
	}




	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public String getNrFolha() {
		return nrFolha;
	}


	public void setNrFolha(String nrFolha) {
		this.nrFolha = nrFolha;
	}


	public String getLadoImagem() {
		return ladoImagem;
	}


	public void setLadoImagem(String ladoImagem) {
		this.ladoImagem = ladoImagem;
	}
	
	

}



	