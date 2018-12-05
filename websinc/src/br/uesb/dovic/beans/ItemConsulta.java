package br.uesb.dovic.beans;

import java.io.Serializable;

import br.uesb.dovic.beans.Etiqueta;
import br.uesb.dovic.enums.Operacao;

public class ItemConsulta implements Serializable {
	
		 
	    private Etiqueta etiqueta;   
	    private String valor;
	   	    
	    
	    
	    
		public ItemConsulta() {
			
		}
		
		public ItemConsulta(Etiqueta etiqueta, String valor) {
			
			this.etiqueta = etiqueta;
			this.valor = valor;
			
		}
		
public ItemConsulta(Etiqueta etiqueta) {
			
			this.etiqueta = etiqueta;
			
		}
		public Etiqueta getEtiqueta() {
			return etiqueta;
		}
		public void setEtiqueta(Etiqueta etiqueta) {
			this.etiqueta = etiqueta;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((etiqueta == null) ? 0 : etiqueta.hashCode());
			result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
			ItemConsulta other = (ItemConsulta) obj;
			if (etiqueta == null) {
				if (other.etiqueta != null)
					return false;
			} else if (!etiqueta.equals(other.etiqueta))
				return false;
			if (valor == null) {
				if (other.valor != null)
					return false;
			} else if (!valor.equals(other.valor))
				return false;
			return true;
		}
	
	   
	  
	    


}
