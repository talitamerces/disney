package br.uesb.dovic.enums;
import java.io.Serializable;


	
	public enum CategoriaColaborador implements Serializable{
		SENIOR("Sênior", "Doutor"),
		MASTER("Master", "Mestre"),
		JUNIORMESTRANDO("Júnior", "Mestrando"),
		JUNIORGRADUANDO("Júnior", "Graduando");
		

		private static final long serialVersionUID = 1l;
	    private String nome;
	    private String titulacao;

	    private CategoriaColaborador(String nome, String t) {
	        this.nome = nome;
	        titulacao=t;
	    }

	    @Override
	    public String toString() {
	        return nome+"-"+titulacao;
	    }

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getTitulacao() {
			return titulacao;
		}

		public void setTitulacao(String titulacao) {
			this.titulacao = titulacao;
		}
	    
	    

	}



