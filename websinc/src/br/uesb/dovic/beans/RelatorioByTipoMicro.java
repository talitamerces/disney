package br.uesb.dovic.beans;

import br.uesb.dovic.entidades.TipoDocumentoMicro;

public class RelatorioByTipoMicro {
	
	private String nomeTipoMicro;
	private Long total;
		
	public RelatorioByTipoMicro() {
	
	}
	
	

	public RelatorioByTipoMicro( String nomeTipoMicro,
			Long total) {
			
		this.nomeTipoMicro = nomeTipoMicro;
		this.total = total;
	}



	
	public String getNomeTipoMicro() {
		return nomeTipoMicro;
	}

	public void setNomeTipoMicro(String nomeTipoMicro) {
		this.nomeTipoMicro = nomeTipoMicro;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	


}
