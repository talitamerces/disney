package br.uesb.dovic.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Pais;
import br.uesb.dovic.modelo.DAOPais;

@SuppressWarnings("serial")
@ManagedBean(name="controlePais")
@ViewScoped
public class ControlePais implements Serializable{
	private DAOPais<Pais> dao;
	private Pais objeto;
	private boolean edicao;
	
	
	public ControlePais(){
		dao = new DAOPais<Pais>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/pais?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Pais();
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
	
	public String alterar(Pais obj){
		objeto = obj;
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOPais<Pais> getDao() {
		return dao;
	}
	public void setDao(DAOPais<Pais> dao) {
		this.dao = dao;
	}
	public Pais getObjeto() {
		return objeto;
	}
	public void setObjeto(Pais objeto) {
		this.objeto = objeto;
	}
	

}
