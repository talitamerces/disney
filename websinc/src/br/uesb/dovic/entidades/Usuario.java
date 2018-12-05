package br.uesb.dovic.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.uesb.dovic.enums.TipoUsuario;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	@Id
	@Column(name="idusuario")
	@SequenceGenerator(name="SEQ_USUARIO", sequenceName="SEQ_USUARIO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USUARIO")
	private Integer id;
	
	
	@Column(name="nomeusuario")
	@NotEmpty(message="O nome do usuário deve ser informado")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	private String nomeUsuario;
	
	
	
	@Column(name="emailusuario")
	@Email(message="Informe um email válido")
	private String emailUsuario;
	
	
	
	@Column(name="instituicaousuario")
	private String instituicaoUsuario;
	
	
	@Column(name="loginusuario")
	//@Length(max=15, message="O login não pode ultrapassar {max} caracteres")
	//@NotEmpty(message="O login deve ser informado")
	private String loginUsuario;
	
	
	@NotEmpty(message="A senha deve ser informada")
	@Column(name="senhausuario")
	private String senhaUsuario;
	
	
	@Column(name="interesseusuario")
	private String interesseUsuario;
	
	@Enumerated
	@Column(name="tipousuario")
	private TipoUsuario tipoUsuario;
	
	
	@Column(name="ativousuario")
	private Boolean ativoUsuario;
	
	
	

    //Tipo de usuário:
	//Pesquisador, Estudante, Colaborador
	//Registro de Log de usuário
	//Campo ativo/inativo

	
	public Usuario(){
		
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer idUsuario) {
		this.id = idUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getEmailUsuario() {
		return emailUsuario;
	}


	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}


	public String getInstituicaoUsuario() {
		return instituicaoUsuario;
	}


	public void setInstituicaoUsuario(String instituicaoUsuario) {
		this.instituicaoUsuario = instituicaoUsuario;
	}


	public String getLoginUsuario() {
		return loginUsuario;
	}


	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}


	public String getSenhaUsuario() {
		return senhaUsuario;
	}


	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}


	public String getInteresseUsuario() {
		return interesseUsuario;
	}


	public void setInteresseUsuario(String interesseUsuario) {
		this.interesseUsuario = interesseUsuario;
	}


	

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + id + ", nomeUsuario="
				+ nomeUsuario + ", emailUsuario=" + emailUsuario
				+ ", instituicaoUsuario=" + instituicaoUsuario
				+ ", loginUsuario=" + loginUsuario + ", senhaUsuario="
				+ senhaUsuario + ", interesseUsuario=" + interesseUsuario + "]";
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Boolean getAtivoUsuario() {
		return ativoUsuario;
	}

	public void setAtivoUsuario(Boolean ativoUsuario) {
		this.ativoUsuario = ativoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ativoUsuario == null) ? 0 : ativoUsuario.hashCode());
		result = prime * result
				+ ((emailUsuario == null) ? 0 : emailUsuario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((instituicaoUsuario == null) ? 0 : instituicaoUsuario
						.hashCode());
		result = prime
				* result
				+ ((interesseUsuario == null) ? 0 : interesseUsuario.hashCode());
		result = prime * result
				+ ((loginUsuario == null) ? 0 : loginUsuario.hashCode());
		result = prime * result
				+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result
				+ ((senhaUsuario == null) ? 0 : senhaUsuario.hashCode());
		result = prime * result
				+ ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativoUsuario == null) {
			if (other.ativoUsuario != null)
				return false;
		} else if (!ativoUsuario.equals(other.ativoUsuario))
			return false;
		if (emailUsuario == null) {
			if (other.emailUsuario != null)
				return false;
		} else if (!emailUsuario.equals(other.emailUsuario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instituicaoUsuario == null) {
			if (other.instituicaoUsuario != null)
				return false;
		} else if (!instituicaoUsuario.equals(other.instituicaoUsuario))
			return false;
		if (interesseUsuario == null) {
			if (other.interesseUsuario != null)
				return false;
		} else if (!interesseUsuario.equals(other.interesseUsuario))
			return false;
		if (loginUsuario == null) {
			if (other.loginUsuario != null)
				return false;
		} else if (!loginUsuario.equals(other.loginUsuario))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (senhaUsuario == null) {
			if (other.senhaUsuario != null)
				return false;
		} else if (!senhaUsuario.equals(other.senhaUsuario))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		return true;
	}

	


}
