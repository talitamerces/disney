package br.uesb.dovic.beans;

public class Resultado {
	private Integer id;
	private String sentenca;
	private String arvore;
	private Integer ocorrencias;
	
	
	public Resultado() {
		
	}





	public Resultado(Integer id, String sentenca, String arvore,
			Integer ocorrencias) {
		super();
		this.id = id;
		this.sentenca = sentenca;
		this.arvore = arvore;
		this.ocorrencias = ocorrencias;
	}





	public String getSentenca() {
		return sentenca;
	}


	public void setSentenca(String sentenca) {
		this.sentenca = sentenca;
	}


	public String getArvore() {
		return arvore;
	}


	public void setArvore(String arvore) {
		this.arvore = arvore;
	}


	public Integer getOcorrencias() {
		return ocorrencias;
	}


	public void setOcorrencias(Integer ocorrencias) {
		this.ocorrencias = ocorrencias;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arvore == null) ? 0 : arvore.hashCode());
		result = prime * result
				+ ((ocorrencias == null) ? 0 : ocorrencias.hashCode());
		result = prime * result
				+ ((sentenca == null) ? 0 : sentenca.hashCode());
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
		Resultado other = (Resultado) obj;
		if (arvore == null) {
			if (other.arvore != null)
				return false;
		} else if (!arvore.equals(other.arvore))
			return false;
		if (ocorrencias == null) {
			if (other.ocorrencias != null)
				return false;
		} else if (!ocorrencias.equals(other.ocorrencias))
			return false;
		if (sentenca == null) {
			if (other.sentenca != null)
				return false;
		} else if (!sentenca.equals(other.sentenca))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
