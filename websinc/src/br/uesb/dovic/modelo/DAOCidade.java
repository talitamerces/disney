package br.uesb.dovic.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Cidade;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOCidade  <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOCidade(){
			super.setClasse(Cidade.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("Código", "idCidade"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nomeCidade"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
	
	
	 public List<Cidade> getByEstado(Integer idEstado)

     

	  {
	 super.iniciarTransacao();

	 EntityManager entityManager = getEm();   
	 entityManager.flush();

	    Query query = entityManager.createNamedQuery("findByEstado");

	    query.setParameter("idEstado", idEstado);

	    List<Cidade> cidades = (List<Cidade>) query.getResultList();

	    entityManager.clear();
	   super.commitTransacao(); 

	    return cidades;

	  }
			
	}