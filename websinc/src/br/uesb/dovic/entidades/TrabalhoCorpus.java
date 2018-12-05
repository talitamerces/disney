package br.uesb.dovic.entidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.uesb.dovic.enums.TipoTrabalhoCorpus;

@Entity
@Table(name="trabalhocorpus")
@NamedQueries({
	@NamedQuery(name="findByTrabalho", 
			query = "SELECT t.colaboradores FROM TrabalhoCorpus t "
					+ "WHERE t.id=:idTrabalho ")})
	public class TrabalhoCorpus implements Serializable {
		@Id
		@Column(name="idtrabalhocorpus")
		@SequenceGenerator(name="SEQ_TRABALHO", sequenceName="SEQ_TRABALHO_ID", allocationSize=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TRABALHO")
		private Integer id;
		
		@Enumerated	
		@Column(name="tipoTrabalhoCorpus",  nullable=false)
		private TipoTrabalhoCorpus tipoTrabalhoCorpus;
		
		@Column(name="dataInicioTrabalho")
		@Temporal(TemporalType.DATE)
		private Date dataInicioTrabalho;
		
		@Column(name="dataFimTrabalho")
		@Temporal(TemporalType.DATE)
		private Date dataFimTrabalho;
		
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name="colaboradortrabalho", joinColumns={@JoinColumn(name="idtrabalhocorpus")},
		inverseJoinColumns={@JoinColumn(name="idcolaborador")})
		private List<Colaborador> colaboradores = new ArrayList<Colaborador>();
		
		
		
		public TrabalhoCorpus(){
			
		}
		
		public void adicionarColaborador(Colaborador obj){
			colaboradores.add(obj);
		}

//		public List<Colaborador> getColaboradores() {
//			return colaboradores;
//		}



		public void setColaboradores(List<Colaborador> colaboradores) {
			this.colaboradores = colaboradores;
		}



		public Integer getIdTrabalhoCorpus() {
			return id;
		}

		public void setIdTrabalhoCorpus(Integer idTrabalhoCorpus) {
			this.id = idTrabalhoCorpus;
		}

		

		public TipoTrabalhoCorpus getTipoTrabalhoCorpus() {
			return tipoTrabalhoCorpus;
		}

		public void setTipoTrabalhoCorpus(TipoTrabalhoCorpus tipoTrabalhoCorpus) {
			this.tipoTrabalhoCorpus = tipoTrabalhoCorpus;
		}

		public Date getDataInicioTrabalho() {
			return dataInicioTrabalho;
		}

		public void setDataInicioTrabalho(Date dataInicioTrabalho) {
			this.dataInicioTrabalho = dataInicioTrabalho;
		}

		public Date getDataFimTrabalho() {
			return dataFimTrabalho;
		}

		public void setDataFimTrabalho(Date dataFimTrabalho) {
			this.dataFimTrabalho = dataFimTrabalho;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((dataFimTrabalho == null) ? 0 : dataFimTrabalho
							.hashCode());
			result = prime
					* result
					+ ((dataInicioTrabalho == null) ? 0 : dataInicioTrabalho
							.hashCode());
			result = prime
					* result
					+ ((id == null) ? 0 : id
							.hashCode());
			result = prime
					* result
					+ ((tipoTrabalhoCorpus == null) ? 0 : tipoTrabalhoCorpus
							.hashCode());
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
			TrabalhoCorpus other = (TrabalhoCorpus) obj;
			if (dataFimTrabalho == null) {
				if (other.dataFimTrabalho != null)
					return false;
			} else if (!dataFimTrabalho.equals(other.dataFimTrabalho))
				return false;
			if (dataInicioTrabalho == null) {
				if (other.dataInicioTrabalho != null)
					return false;
			} else if (!dataInicioTrabalho.equals(other.dataInicioTrabalho))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (tipoTrabalhoCorpus == null) {
				if (other.tipoTrabalhoCorpus != null)
					return false;
			} else if (!tipoTrabalhoCorpus.equals(other.tipoTrabalhoCorpus))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TrabalhoCorpus [id=" + id
					+ ", tipoTrabalhoCorpus=" + tipoTrabalhoCorpus
					+ ", dataInicioTrabalho=" + dataInicioTrabalho
					+ ", dataFimTrabalho=" + dataFimTrabalho + "]";
		}
		
		

}
