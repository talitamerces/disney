package br.uesb.dovic.controle;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.Autor;
import br.uesb.dovic.modelo.DAOAutor;

@SuppressWarnings("serial")
@ManagedBean(name="controleAutor")
@ViewScoped
public class ControleAutor implements Serializable{
	private DAOAutor<Autor> dao;
	private Autor objeto;
	private boolean edicao;
	private Integer anoNascimento;
	private Integer anoMorte;
	
	
	public ControleAutor(){
		dao = new DAOAutor<Autor>();
		edicao=false;
	}
	
	
	public void ajaxAnoNascimento(){
		 Calendar calendar = Calendar.getInstance();  
	        calendar.setTime( objeto.getDataNascimentoCompleta());  
	      
		anoNascimento = calendar.get(Calendar.YEAR);
				
	}
	
	public void ajaxAnoMorte(){
		 Calendar calendar = Calendar.getInstance();  
	        calendar.setTime( objeto.getDataMorteCompleta());  
	      
		anoMorte = calendar.get(Calendar.YEAR);
				
	}
	public String listar(){
		return "/pages/cadastros/autor?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Autor();
		this.edicao = true;
		anoNascimento=null;
		anoMorte=null;
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
		
		if (this.anoMorte!=0)
			objeto.setAnoMorte(anoMorte);
		if (anoNascimento!=0)
			objeto.setAnoNascimento(anoNascimento);
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;}
		return null;
		
		
	}
	
	public String alterar(Autor obj){
		objeto = obj;
		edicao=true; 
		anoNascimento= objeto.getAnoNascimento();
		anoMorte = objeto.getAnoMorte();
		
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOAutor<Autor> getDao() {
		return dao;
	}
	public void setDao(DAOAutor<Autor> dao) {
		this.dao = dao;
	}
	public Autor getObjeto() {
		return objeto;
	}
	public void setObjeto(Autor objeto) {
		this.objeto = objeto;
	}

	public Integer getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public Integer getAnoMorte() {
		return anoMorte;
	}

	public void setAnoMorte(Integer anoMorte) {
		this.anoMorte = anoMorte;
	}
	
	

}

