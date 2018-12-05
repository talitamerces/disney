package br.uesb.dovic.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.uesb.dovic.entidades.Autor;
import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.enums.TipoUsuario;
import br.uesb.dovic.modelo.DAOUsuario;
import br.uesb.dovic.servicos.Criptografia;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.util.UtilMensagens;

@SuppressWarnings("serial")
@ManagedBean(name="controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable{
	private DAOUsuario<Usuario> dao;
	private Usuario objeto;
	private boolean edicao;
	private boolean novoUsuario;
	private TipoUsuario selectedTipoUsuario;
	private Boolean ativoUsuario;
	private List<SelectItem> listaTipoUsuario;
	private ViewService viewService;
	private String confirmacaoSenha;
	private String senhaUsuario;
	private String novaSenhaUsuario;
	
	
	public ControleUsuario(){
		dao = new DAOUsuario<Usuario>();
		edicao=false;
		novoUsuario=false;
		ativoUsuario=true;
		viewService = new ViewService();
		listaTipoUsuario = viewService.getSelectItensFromArray(TipoUsuario.values(), "toString");
		confirmacaoSenha="";
		senhaUsuario="";
		
	}
	
	public String listar(){
		return "/pages/corpus/usuario2?faces-redirect=true";
	}
	
	
	public String novo(){
		objeto = new Usuario();
		objeto.setAtivoUsuario(true);
		this.edicao = true;
		this.novoUsuario=true;
		return null;
	}
	
	public String alterar(Usuario obj){
		objeto = obj;
		confirmacaoSenha=obj.getSenhaUsuario();
		selectedTipoUsuario=obj.getTipoUsuario();
		edicao=true; 
		novoUsuario=false;
			
		return null;
	}
	
	public String alterarSenha(){
		boolean gravou = false;
		
		String senhaCriptografada=Criptografia.criptografar(novaSenhaUsuario);
		objeto.setSenhaUsuario(senhaCriptografada);
		gravou = dao.merge(objeto);
		
		if (gravou)
			edicao = false;
			
		return "";
	}
	
	

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String cancelar(){
		edicao = false;
		novoUsuario=false;
		confirmacaoSenha="";
		objeto=null;
		return "/pages/corpus/usuario2?faces-redirect=true";
	}
	
	
	public String gravar(){
	
		boolean gravou = false;
		objeto.setTipoUsuario(selectedTipoUsuario);
		String senhaCriptografada=Criptografia.criptografar(senhaUsuario);
		objeto.setSenhaUsuario(senhaCriptografada);
		
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}
		else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;
			}
		return "/pages/corpus/usuario2?faces-redirect=true";
		
		
	}
	

	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	
	public DAOUsuario<Usuario> getDao() {
		return dao;
	}
	public void setDao(DAOUsuario<Usuario> dao) {
		this.dao = dao;
	}
	public Usuario getObjeto() {
		return objeto;
	}
	public void setObjeto(Usuario objeto) {
		this.objeto = objeto;
	}

	public TipoUsuario getSelectedTipoUsuario() {
		return selectedTipoUsuario;
	}

	public void setSelectedTipoUsuario(TipoUsuario selectedTipoUsuario) {
		this.selectedTipoUsuario = selectedTipoUsuario;
	}

	public Boolean getAtivoUsuario() {
		return ativoUsuario;
	}

	public void setAtivousuario(Boolean ativousuario) {
		this.ativoUsuario = ativousuario;
	}

	public List<SelectItem> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<SelectItem> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public ViewService getViewService() {
		return viewService;
	}

	public void setViewService(ViewService viewService) {
		this.viewService = viewService;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getSenha() {
		return senhaUsuario;
	}

	public void setSenha(String senha) {
		this.senhaUsuario = senha;
	}

	public void setAtivoUsuario(Boolean ativoUsuario) {
		this.ativoUsuario = ativoUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public boolean isNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(boolean novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public String getNovaSenhaUsuario() {
		return novaSenhaUsuario;
	}

	public void setNovaSenhaUsuario(String novaSenhaUsuario) {
		this.novaSenhaUsuario = novaSenhaUsuario;
	}

	
}
