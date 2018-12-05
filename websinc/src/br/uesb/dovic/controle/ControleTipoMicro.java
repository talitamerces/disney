package br.uesb.dovic.controle;

import java.io.Serializable;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.uesb.dovic.entidades.TipoDocumentoMicro;
import br.uesb.dovic.modelo.DAOTipoMicro;

@SuppressWarnings("serial")
@ManagedBean(name="controleTipoMicro")
@ViewScoped
public class ControleTipoMicro implements Serializable{
	private DAOTipoMicro<TipoDocumentoMicro> dao;
	private TipoDocumentoMicro objeto;
	private boolean edicao;
	
	
	public ControleTipoMicro(){
		dao = new DAOTipoMicro<TipoDocumentoMicro>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/tipoDocumentoMicro?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new TipoDocumentoMicro();
		this.edicao = true;
		return null;		
	}
	

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String cancelar(){
		edicao = false;
		objeto=null;
		return null;
	}
	
	public String gravar(){
		
		boolean gravou = false;
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;}
		return null;
		
		
	}
	
	public String alterar(TipoDocumentoMicro obj){
		objeto = obj;
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOTipoMicro<TipoDocumentoMicro> getDao() {
		return dao;
	}
	public void setDao(DAOTipoMicro<TipoDocumentoMicro> dao) {
		this.dao = dao;
	}
	public TipoDocumentoMicro getObjeto() {
		return objeto;
	}
	public void setObjeto(TipoDocumentoMicro objeto) {
		this.objeto = objeto;
	}
	

}
