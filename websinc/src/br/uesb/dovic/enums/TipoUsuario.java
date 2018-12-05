package br.uesb.dovic.enums;



import java.io.Serializable;

public enum TipoUsuario implements Serializable{
	ADMINISTRADOR("Administrador"),
	COLABORADOR("Colaborador"),
	USUARIO("Usuário");
	

	private static final long serialVersionUID = 1l;
    private String nome;

    private TipoUsuario(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
