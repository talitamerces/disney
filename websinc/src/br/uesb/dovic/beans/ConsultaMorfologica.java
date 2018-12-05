package br.uesb.dovic.beans;

import java.util.List;

public class ConsultaMorfologica {
	
	private List<Container> consulta;

	
	public ConsultaMorfologica(List<Container> consulta) {
		super();
		this.consulta = consulta;
	}

	public List<Container> getConsulta() {
		return consulta;
	}

	public void setConsulta(List<Container> consulta) {
		this.consulta = consulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consulta == null) ? 0 : consulta.hashCode());
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
		ConsultaMorfologica other = (ConsultaMorfologica) obj;
		if (consulta == null) {
			if (other.consulta != null)
				return false;
		} else if (!consulta.equals(other.consulta))
			return false;
		return true;
	}
	
	

}
