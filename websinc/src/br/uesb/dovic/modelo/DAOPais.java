package br.uesb.dovic.modelo;

import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Pais;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOPais <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOPais(){
			super.setClasse(Pais.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("C�digo", "idPais"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nomePais"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
			
	}