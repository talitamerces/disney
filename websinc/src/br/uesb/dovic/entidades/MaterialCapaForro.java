package br.uesb.dovic.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name="materialcapaforro")
public class MaterialCapaForro implements Serializable {
	@Id
	@Column(name="idMaterialCapa")
	@SequenceGenerator(name="SEQ_MATERIAL", sequenceName="SEQ_MATERIAL_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MATERIAL")
	private Integer id;
	
	@NotEmpty(message="O nome do material deve ser informado")
	@Length(max=100, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="nomeMaterialCapa", length=100, nullable=false)
	private String nomeMaterialCapa;
	
	@ManyToMany(mappedBy="materiaisForro")
	private List<DocumentoMacro> documentos= new ArrayList<DocumentoMacro>();
	
	@Transient
	private boolean selecionado;
	
	public MaterialCapaForro(){
		
	}
	
	
	

	public MaterialCapaForro(Integer id, String nomeMaterialCapa) {
		super();
		this.id = id;
		this.nomeMaterialCapa = nomeMaterialCapa;
	}



//	public List<DocumentoMacro> getDocumentos() {
//		return documentos;
//	}



	public void setDocumentos(List<DocumentoMacro> documentos) {
		this.documentos = documentos;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer idMaterialCapa) {
		this.id = idMaterialCapa;
	}

	public String getNomeMaterialCapa() {
		return nomeMaterialCapa;
	}

	public void setNomeMaterialCapa(String nomeMaterialCapa) {
		this.nomeMaterialCapa = nomeMaterialCapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((nomeMaterialCapa == null) ? 0 : nomeMaterialCapa.hashCode());
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
		MaterialCapaForro other = (MaterialCapaForro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeMaterialCapa == null) {
			if (other.nomeMaterialCapa != null)
				return false;
		} else if (!nomeMaterialCapa.equals(other.nomeMaterialCapa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " [idMaterialCapa=" + id
				+ ", nomeMaterialCapa=" + nomeMaterialCapa + "]";
	}



	
	public boolean isSelecionado() {
		return selecionado;
	}




	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	

}
