package br.uesb.dovic.controle;

import java.io.Serializable;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.uesb.dovic.entidades.TipoDocumentoMacro;
import br.uesb.dovic.modelo.DAOTipoMacro;

@SuppressWarnings("serial")
@ManagedBean(name="controleTipoMacro")
@ViewScoped
public class ControleTipoMacro implements Serializable{
	private DAOTipoMacro<TipoDocumentoMacro> dao;
	private TipoDocumentoMacro objeto;
	private boolean edicao;
	
	
	public ControleTipoMacro(){
		dao = new DAOTipoMacro<TipoDocumentoMacro>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/tipoDocumentoMacro?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new TipoDocumentoMacro();
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
	
	public String alterar(TipoDocumentoMacro obj){
		objeto = obj;
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOTipoMacro<TipoDocumentoMacro> getDao() {
		return dao;
	}
	public void setDao(DAOTipoMacro<TipoDocumentoMacro> dao) {
		this.dao = dao;
	}
	public TipoDocumentoMacro getObjeto() {
		return objeto;
	}
	public void setObjeto(TipoDocumentoMacro objeto) {
		this.objeto = objeto;
	}
	

}
