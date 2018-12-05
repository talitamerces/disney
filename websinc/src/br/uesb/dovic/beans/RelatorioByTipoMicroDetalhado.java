package br.uesb.dovic.beans;

import java.util.List;

public class RelatorioByTipoMicroDetalhado {
	private Integer idMacro;
	private String nomeMacro;
	private List<RelatorioByTipoMicro> detalhes;
	
	
	

	public RelatorioByTipoMicroDetalhado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RelatorioByTipoMicroDetalhado(Integer idMacro, String nomeMacro,
			List<RelatorioByTipoMicro> detalhes) {
		super();
		this.idMacro = idMacro;
		this.nomeMacro = nomeMacro;
		this.detalhes = detalhes;
	}
	public Integer getIdMacro() {
		return idMacro;
	}
	public void setIdMacro(Integer idMacro) {
		this.idMacro = idMacro;
	}
	public String getNomeMacro() {
		return nomeMacro;
	}
	public void setNomeMacro(String nomeMacro) {
		this.nomeMacro = nomeMacro;
	}
	public List<RelatorioByTipoMicro> getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(List<RelatorioByTipoMicro> detalhes) {
		this.detalhes = detalhes;
	}
	
	
	

}
