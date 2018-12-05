package br.uesb.dovic.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Cor;
import br.uesb.dovic.modelo.DAOCor;

@SuppressWarnings("serial")
@ManagedBean(name="controleCor")
@ViewScoped
public class ControleCor implements Serializable{
	private DAOCor<Cor> dao;
	private Cor objeto;
	private boolean edicao;
	
	
	public ControleCor(){
		dao = new DAOCor<Cor>();
		edicao=false;
	}
	
	public String listar(){
		return "/pages/cadastros/cor?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Cor();
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
	
	public String alterar(Cor obj){
		objeto = obj;
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOCor<Cor> getDao() {
		return dao;
	}
	public void setDao(DAOCor<Cor> dao) {
		this.dao = dao;
	}
	public Cor getObjeto() {
		return objeto;
	}
	public void setObjeto(Cor objeto) {
		this.objeto = objeto;
	}
	

}
