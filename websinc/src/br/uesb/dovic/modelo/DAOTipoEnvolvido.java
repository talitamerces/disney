package br.uesb.dovic.modelo;


import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.TipoEnvolvido;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOTipoEnvolvido<T> extends GenericDAO<T>  implements Serializable{ 

	public DAOTipoEnvolvido(){
		super.setClasse(TipoEnvolvido.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idTipoEnvolvido"));
		super.getListaOrdem().add(new Ordem("Nome", "descricaoTipoEnvolvido"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
		
}
