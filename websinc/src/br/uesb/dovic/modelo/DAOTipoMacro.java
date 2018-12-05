package br.uesb.dovic.modelo;


import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.TipoDocumentoMacro;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOTipoMacro<T> extends GenericDAO<T>  implements Serializable{ 

	public DAOTipoMacro(){
		super.setClasse(TipoDocumentoMacro.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idTipoMacro"));
		super.getListaOrdem().add(new Ordem("Nome", "descricaoTipoMacro"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
		
}
