package br.uesb.dovic.modelo;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.entidades.TrabalhoCorpus;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOTrabalhoCorpus<T> extends GenericDAO<T>  implements Serializable{ 

	public DAOTrabalhoCorpus(){
		super.setClasse(TrabalhoCorpus.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idtrabalhocorpus"+ ""));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(0));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}
		
	
	public List<Colaborador> loadColaboradores(Integer idTrabalho){
		super.iniciarTransacao();
		EntityManager entityManager = getEm();
		entityManager.flush();
		Query query = entityManager.createNamedQuery("findByTrabalho");
		query.setParameter("idTrabalho", idTrabalho);
		List<Colaborador> colaboradores = (List<Colaborador>) query.getResultList();
		entityManager.clear();
		super.commitTransacao();
		return colaboradores;
		
		
}
}

