package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="portal")
@SequenceGenerator(name="SEQ_PORTAL", sequenceName="SEQ_PORTAL_ID", allocationSize=1)
	public class Portal  implements Serializable {
		@Id
		@Column(name="idportal")
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PORTAL")
		private Integer id;
		
		
		@Column(name="apresentacao")
		private String apresentacao;
		
		@Column(name="agradecimentos")
		private String agradecimentos;
		
		@Column(name="lapelinc")
		private String lapelinc;
		
		@Column(name="corpusdovic")
		private String corpusDovic;
		
		@Column(name="projetos")
		private String projetos;
		
		


		public Portal() {
			
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((apresentacao == null) ? 0 : apresentacao.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			Portal other = (Portal) obj;
			if (apresentacao == null) {
				if (other.apresentacao != null)
					return false;
			} else if (!apresentacao.equals(other.apresentacao))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
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