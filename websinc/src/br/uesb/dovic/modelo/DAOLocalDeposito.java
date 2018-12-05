package br.uesb.dovic.modelo;

import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.LocalDeposito;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOLocalDeposito  <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOLocalDeposito(){
			super.setClasse(LocalDeposito.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("Código", "idLocal"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nomeLocal"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
			
	}