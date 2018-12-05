package br.uesb.dovic.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.enums.TipoUsuario;
import br.uesb.dovic.modelo.DAOUsuario;
import br.uesb.dovic.servicos.Criptografia;
import br.uesb.dovic.util.UtilMensagens;



@SuppressWarnings("serial")
@ManagedBean(name="controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
	private String usuario;
	private String senha;
	private Usuario usuarioLogado;
	private DAOUsuario<Usuario> dao;
	
	
	
	public ControleLogin(){
		dao=new DAOUsuario<Usuario>();
		usuarioLogado=null;
		
		
	}
		
	public String paginaLogin(){
		return "/login";
	}
	
	public String efetuarLogout(){
		usuarioLogado=null;
		HttpServletRequest req=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().invalidate();

		return "/login";
	}
		
	
	
	public String autenticar(){
		
		String senhaCriptografada= Criptografia.criptografar(senha);
		if (dao.login(usuario, senhaCriptografada)){
			usuarioLogado=dao.localizarPorLogin(usuario);
			UtilMensagens.mensagemInformacao("Login efetuado com sucesso");
				return "/index?faces-redirect=true";
		}
		else
		{
			UtilMensagens.mensagemErro("Usuário ou senha inválidos");
			return "/login";
		}
		
	}
	
	public boolean ehAdministrador(){
	if (usuarioLogado !=null)
		if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR))
			return true;
	
	return false;
	}
	
	public boolean ehColaborador(){
		if (usuarioLogado !=null)
			if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.COLABORADOR))
				return true;
		
		return false;
		}
	
	public boolean nivelColaborador(){
		if (usuarioLogado !=null)
			if ( (usuarioLogado.getTipoUsuario().equals(TipoUsuario.COLABORADOR))||(usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)))
				return true;
		
		return false;
	}

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControleLogin other = (ControleLogin) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public DAOUsuario<Usuario> getDao() {
		return dao;
	}

	public void setDao(DAOUsuario<Usuario> dao) {
		this.dao = dao;
	}
	
	
	

	
	
}
	
	
	

	