package br.uesb.dovic.modelo;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.enums.CategoriaColaborador;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOColaborador  <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOColaborador(){
			super.setClasse(Colaborador.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			super.getListaOrdem().add(new Ordem("Código", "idColaborador"
					+ ""));
			super.getListaOrdem().add(new Ordem("Nome", "nome"));
			super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
			super.setFiltro("");
			super.setMaximoObjetos(3);
			super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
		}
	
	
	public List<Colaborador> getColaboradoresPorCategoria(CategoriaColaborador categoria)

	{
		super.iniciarTransacao();
		EntityManager entityManager = getEm();
		entityManager.flush();
		Query query = entityManager.createNamedQuery("findByCategoria");
		query.setParameter("categoria", categoria);
		List<Colaborador> colaboradores = (List<Colaborador>) query.getResultList();
		entityManager.clear();
		super.commitTransacao();
		return colaboradores;

	}
			
	}


