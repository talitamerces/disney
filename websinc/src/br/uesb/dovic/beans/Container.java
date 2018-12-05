package br.uesb.dovic.beans;

import java.io.Serializable;
import java.util.List;

import br.uesb.dovic.enums.Operacao;

public class Container implements Serializable  {
	private List<ItemConsulta> itensContainer;
	private Operacao operacao;
	
	
	
	public Container(List<ItemConsulta> itensContainer, Operacao operacao) {
		super();
		this.itensContainer = itensContainer;
		this.operacao = operacao;
	}
	public List<ItemConsulta> getItensContainer() {
		return itensContainer;
	}
	public void setItensContainer(List<ItemConsulta> itensContainer) {
		this.itensContainer = itensContainer;
	}
	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itensContainer == null) ? 0 : itensContainer.hashCode());
		result = prime * result
				+ ((operacao == null) ? 0 : operacao.hashCode());
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
		Container other = (Container) obj;
		if (itensContainer == null) {
			if (other.itensContainer != null)
				return false;
		} else if (!itensContainer.equals(other.itensContainer))
			return false;
		if (operacao != other.operacao)
			return false;
		return true;
	}
	
	

}
