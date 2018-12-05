package br.uesb.dovic.beans;

public class Lexico {
	private String original;
	private String edicao;
	private String tipoEdicao;
	
	public Lexico(){
		
	}
	
	public Lexico(String original, String edicao, String tipoEdicao) {
		super();
		this.original = original;
		this.edicao = edicao;
		this.tipoEdicao = tipoEdicao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
		result = prime * result
				+ ((original == null) ? 0 : original.hashCode());
		result = prime * result
				+ ((tipoEdicao == null) ? 0 : tipoEdicao.hashCode());
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
		Lexico other = (Lexico) obj;
		if (edicao == null) {
			if (other.edicao != null)
				return false;
		} else if (!edicao.equals(other.edicao))
			return false;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		if (tipoEdicao == null) {
			if (other.tipoEdicao != null)
				return false;
		} else if (!tipoEdicao.equals(other.tipoEdicao))
			return false;
		return true;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getTipoEdicao() {
		return tipoEdicao;
	}
	public void setTipoEdicao(String tipoEdicao) {
		this.tipoEdicao = tipoEdicao;
	}
	
	

}
