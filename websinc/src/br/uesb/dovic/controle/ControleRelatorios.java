package br.uesb.dovic.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.uesb.dovic.beans.RelatorioByTipoMicro;
import br.uesb.dovic.beans.RelatorioByTipoMicroDetalhado;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.TipoDocumentoMicro;
import br.uesb.dovic.modelo.DAODocumentoMicro;
import br.uesb.dovic.modelo.DAOTipoMicro;

	@SuppressWarnings("serial")
	@ManagedBean(name = "controleRelatorios")
	@SessionScoped
	public class ControleRelatorios implements Serializable {
		private DAODocumentoMicro<DocumentoMicro> daoMicro;
		private DAOTipoMicro<TipoDocumentoMicro> daoTipoMicro;
		
		@PostConstruct
		public void init() {
			daoMicro = new DAODocumentoMicro<DocumentoMicro>();
			daoTipoMicro = new DAOTipoMicro<TipoDocumentoMicro>();
		}
		
		public List<RelatorioByTipoMicro> groupByTipoMicro() {
			List<RelatorioByTipoMicro> lista = daoMicro.groupByTipoMicro();
			return lista;
		}
		
		public List<RelatorioByTipoMicroDetalhado> groupByTipoMicroDetalhado() {
			List<RelatorioByTipoMicroDetalhado> lista = daoMicro.groupByTipoMicroDetalhado();
			return lista;
		}

		public DAODocumentoMicro<DocumentoMicro> getDaoMicro() {
			return daoMicro;
		}

		public void setDaoMicro(DAODocumentoMicro<DocumentoMicro> daoMicro) {
			this.daoMicro = daoMicro;
		}

		public DAOTipoMicro<TipoDocumentoMicro> getDaoTipoMicro() {
			return daoTipoMicro;
		}

		public void setDaoTipoMicro(DAOTipoMicro<TipoDocumentoMicro> daoTipoMicro) {
			this.daoTipoMicro = daoTipoMicro;
		}	
		
		public String listar(){
			return "/pages/relatorios/relatorioByTipoMicro?faces-redirect=true";
		}

}
