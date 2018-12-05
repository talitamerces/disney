package br.uesb.dovic.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.uesb.dovic.entidades.Portal;
import br.uesb.dovic.modelo.DAOPortal;

@SuppressWarnings("serial")
@ManagedBean(name="controlePortal")
@SessionScoped
public class ControlePortal implements Serializable{
	private DAOPortal<Portal> dao;
	private Portal objeto;
	private String apresentacao;
	private String agradecimentos;
	private String lapelinc;
	private String corpusDovic;
	private String projetos;
	private boolean edicao;
	
	
	public ControlePortal(){
		dao = new DAOPortal<Portal>();
		objeto=dao.getEm().find(Portal.class, 1);
		agradecimentos=	objeto.getAgradecimentos();
		apresentacao=objeto.getApresentacao();
		corpusDovic=objeto.getCorpusDovic();
		lapelinc=objeto.getLapelinc();
		projetos=objeto.getProjetos();
		edicao=false;
	}
	
	public String listar(){
		alterar();
		return "/pages/cadastros/admPortal?faces-redirect=true";
	}
	
	public String novo(){
		objeto = new Portal();
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
		objeto.setAgradecimentos(agradecimentos);
		objeto.setApresentacao(apresentacao);
		objeto.setCorpusDovic(corpusDovic);
		objeto.setLapelinc(lapelinc);
		objeto.setProjetos(projetos);
		gravou = dao.merge(objeto);
		if (gravou)
			edicao = false;
		return null;
	
	}
	
	public String alterar(){
		
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOPortal<Portal> getDao() {
		return dao;
	}
	public void setDao(DAOPortal<Portal> dao) {
		this.dao = dao;
	}
	public Portal getObjeto() {
		return objeto;
	}
	public void setObjeto(Portal objeto) {
		this.objeto = objeto;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public String getAgradecimentos() {
		return agradecimentos;
	}

	public void setAgradecimentos(String agradecimentos) {
		this.agradecimentos = agradecimentos;
	}

	public String getLapelinc() {
		return lapelinc;
	}

	public void setLapelinc(String lapelinc) {
		this.lapelinc = lapelinc;
	}

	public String getCorpusDovic() {
		return corpusDovic;
	}

	public void setCorpusDovic(String corpusDovic) {
		this.corpusDovic = corpusDovic;
	}

	public String getProjetos() {
		return projetos;
	}

	public void setProjetos(String projetos) {
		this.projetos = projetos;
	}
	
 
}
