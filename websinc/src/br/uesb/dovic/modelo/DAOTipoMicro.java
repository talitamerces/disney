package br.uesb.dovic.modelo;


import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.TipoDocumentoMicro;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOTipoMicro<T> extends GenericDAO<T>  implements Serializable{ 

	public DAOTipoMicro(){
		super.setClasse(TipoDocumentoMicro.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idTipoMicro"));
		super.getListaOrdem().add(new Ordem("Nome", "descricaoTipoMicro"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
		
}
