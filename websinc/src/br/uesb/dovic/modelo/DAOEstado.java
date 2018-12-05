package br.uesb.dovic.modelo;

import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Estado;
import br.uesb.dovic.jpa.EntityManagerUtil;

import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
public class DAOEstado <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOEstado(){
			super.setClasse(Estado.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("Código", "idEstado"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nomeEstado"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
	
	 public List<Estado> getByPais(Integer idPais)
 		  {
		 super.iniciarTransacao();

		 EntityManager entityManager = getEm();   
		 entityManager.flush();

		    Query query = entityManager.createNamedQuery("findByPais");

		    query.setParameter("idPais", idPais);

		    List<Estado> estados = (List<Estado>) query.getResultList();

		    entityManager.clear();
		   super.commitTransacao(); 

		    return estados;

		  }

		

			
	}