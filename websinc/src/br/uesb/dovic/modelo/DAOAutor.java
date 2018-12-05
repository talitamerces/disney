package br.uesb.dovic.modelo;


import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Autor;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOAutor <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOAutor(){
			super.setClasse(Autor.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("Código", "idAutor"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nomeAutor"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
			
	}
