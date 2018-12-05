package br.uesb.dovic.beans;

import java.io.Serializable;

public class Etiqueta implements Serializable {
	
		 
	    private Integer id;   
	    private String displayName;
	    private String name;
	    private String descricao;
	    private boolean permiteValor;
	    private boolean grupo; // Se é um grupo de etiquetas com uso de expressão regular
	     
	    public Etiqueta() {}
	 
	    public Etiqueta(Integer id, String displayName, String name) {
	        this.id = id;
	        this.displayName = displayName;
	        this.name = name;
	        permiteValor = true;
	        grupo=false;
	               
	    }
	    
	    	    
	    public Etiqueta(Integer id, String displayName, String name, boolean grupo) {
	        this.id = id;
	        this.displayName = displayName;
	        this.name = name;
	        this.grupo=grupo;
	        
	    }
	 
	    public Integer getId() {
	        return id;
	    }
	 
	    public void setId(Integer id) {
	        this.id = id;
	    }
	 
	    public String getDisplayName() {
	        return displayName;
	    }
	 
	    public void setDisplayName(String displayName) {
	        this.displayName = displayName;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	     
	    @Override
	    public String toString() {
	        return displayName;
	    }

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		
		public boolean isPermiteValor() {
			return permiteValor;
		}

		public void setPermiteValor(boolean permiteValor) {
			this.permiteValor = permiteValor;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((descricao == null) ? 0 : descricao.hashCode());
			result = prime * result
					+ ((displayName == null) ? 0 : displayName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Etiqueta other = (Etiqueta) obj;
			if (descricao == null) {
				if (other.descricao != null)
					return false;
			} else if (!descricao.equals(other.descricao))
				return false;
			if (displayName == null) {
				if (other.displayName != null)
					return false;
			} else if (!displayName.equals(other.displayName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		public boolean isGrupo() {
			return grupo;
		}

		public void setGrupo(boolean grupo) {
			this.grupo = grupo;
		}
	
	    

}
