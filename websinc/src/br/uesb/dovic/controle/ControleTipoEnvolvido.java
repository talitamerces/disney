package br.uesb.dovic.controle;

import java.io.Serializable;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.uesb.dovic.entidades.TipoEnvolvido;
import br.uesb.dovic.modelo.DAOTipoEnvolvido;

@SuppressWarnings("serial")
@ManagedBean(name="controleTipoEnvolvido")
@ViewScoped
public class ControleTipoEnvolvido implements Serializable{
	private DAOTipoEnvolvido<TipoEnvolvido> dao;
	private TipoEnvolvido objeto;
	private boolean edicao;
	
	
	public ControleTipoEnvolvido(){
		dao = new DAOTipoEnvolvido<TipoEnvolvido>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/tipoEnvolvido?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new TipoEnvolvido();
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
	
	public String alterar(TipoEnvolvido obj){
		objeto = obj;
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}

	public DAOTipoEnvolvido<TipoEnvolvido> getDao() {
		return dao;
	}

	public void setDao(DAOTipoEnvolvido<TipoEnvolvido> dao) {
		this.dao = dao;
	}

	public TipoEnvolvido getObjeto() {
		return objeto;
	}

	public void setObjeto(TipoEnvolvido objeto) {
		this.objeto = objeto;
	}
	
	

}
