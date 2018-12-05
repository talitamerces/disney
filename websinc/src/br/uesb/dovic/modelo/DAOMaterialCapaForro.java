package br.uesb.dovic.modelo;


import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.MaterialCapaForro;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOMaterialCapaForro<T> extends GenericDAO<T>  implements Serializable{ 

	public DAOMaterialCapaForro(){
		super.setClasse(MaterialCapaForro.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idMaterialCapa"));
		super.getListaOrdem().add(new Ordem("Nome", "nomeMaterialCapa"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
		
}
