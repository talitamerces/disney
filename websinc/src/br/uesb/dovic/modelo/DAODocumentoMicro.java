package br.uesb.dovic.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uesb.dovic.beans.RelatorioByTipoMicro;
import br.uesb.dovic.beans.RelatorioByTipoMicroDetalhado;
import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.enums.TipoUsuario;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAODocumentoMicro<T> extends GenericDAO<T> implements Serializable {

	public DAODocumentoMicro() {
		super.setClasse(DocumentoMicro.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idDocumentoMicro" + ""));
		super.getListaOrdem().add(new Ordem("Título", "titulo"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}

	public List<DocumentoMicro> getByDocumentoMacro(Integer idMacro) {
		//super.iniciarTransacao();
		EntityManager entityManager = getEm();
//		entityManager.flush();
		Query query = entityManager.createNamedQuery("findByMacro");
		query.setParameter("idMacro", idMacro);
		List<DocumentoMicro> documentos = (List<DocumentoMicro>) query.getResultList();
		//super.commitTransacao();
		//entityManager.clear();
		return documentos;
	}
	
	public List<RelatorioByTipoMicro> groupByTipoMicro() {
		super.iniciarTransacao();
		EntityManager entityManager = getEm();
		entityManager.flush();
		Query query = entityManager.createNamedQuery("groupByTipoMicro");
		//List<RelatorioByTipoMicro> resultado=query.getResultList();
		List<RelatorioByTipoMicro> resultado=new ArrayList<RelatorioByTipoMicro>();
		List<Object> dados = query.getResultList();
		Iterator iterator = dados.iterator();
		while( iterator.hasNext() ) {
		     Object[] columns = (Object[])iterator.next();
		     RelatorioByTipoMicro objectEntity = new RelatorioByTipoMicro();
		     String nomeTipo= (String) columns[0];
		     Long total= (Long) columns[1];
		     objectEntity.setNomeTipoMicro(nomeTipo);
		     objectEntity.setTotal(total);
		     resultado.add(objectEntity);
		}

		
		super.commitTransacao();
		entityManager.clear();
		return resultado;
}
	
	public List<RelatorioByTipoMicroDetalhado> groupByTipoMicroDetalhado() {
		super.iniciarTransacao();
		EntityManager entityManager = getEm();
		entityManager.flush();
		Query query = entityManager.createNamedQuery("groupByTipoMicroDetalhado");
		List<Integer> inseridos= new ArrayList<Integer>();
		List<RelatorioByTipoMicroDetalhado> resultado=new ArrayList<RelatorioByTipoMicroDetalhado>();
		
		List<RelatorioByTipoMicro> listaDetalhes;
		List<Object> dados = query.getResultList();
		Iterator iterator = dados.iterator();
		
		while( iterator.hasNext() ) {
			
		     Object[] columns = (Object[])iterator.next();
		     Integer idMacro=(Integer) columns[0];
		     String titulo= (String) columns[1];
		     String nomeTipo= (String) columns[2];
		     Long total= (Long) columns[3];
		     
		     if (!inseridos.contains(idMacro))
		     {
		    	 inseridos.add(idMacro);
		    	 RelatorioByTipoMicroDetalhado objectEntity2 = new RelatorioByTipoMicroDetalhado();
	    		 objectEntity2.setIdMacro(idMacro);
	    		 objectEntity2.setNomeMacro(titulo);
	    		 listaDetalhes=new ArrayList<RelatorioByTipoMicro>();
		    	 RelatorioByTipoMicro objectEntity1 = new RelatorioByTipoMicro();
	    		 objectEntity1.setNomeTipoMicro(nomeTipo);
	    		 objectEntity1.setTotal(total);
	    		 listaDetalhes.add(objectEntity1);
	    		 objectEntity2.setDetalhes(listaDetalhes);
	    		 resultado.add(objectEntity2);
	    		    	 
		     }
		     
		     else
		     {
		    	 int size= resultado.size();  
		    	 RelatorioByTipoMicroDetalhado objectEntity2 = resultado.get(size-1);
			     listaDetalhes=objectEntity2.getDetalhes();
				 RelatorioByTipoMicro objectEntity1 = new RelatorioByTipoMicro();
			     objectEntity1.setNomeTipoMicro(nomeTipo);
			     objectEntity1.setTotal(total);
			     listaDetalhes.add(objectEntity1);
			     objectEntity2.setDetalhes(listaDetalhes);
			    		    	 
				     
		     }
		    	 
		}

		entityManager.clear();
		super.commitTransacao();
		return resultado;
}

	public List<Imagem> getImagensByDocumentoMicro(Integer idMicro)

	{
		

		EntityManager entityManager = getEm();
		Query query = entityManager.createNamedQuery("findByMicro");
		query.setParameter("idMicro", idMicro);
		List<Imagem> imagens = (List<Imagem>) query.getResultList();
		return imagens;

	}

	public List<DocumentoMicro> listarAvulsos()

	{
	

		EntityManager entityManager = getEm();
		Query query = entityManager.createNamedQuery("findAvulsos");

		List<DocumentoMicro> documentos = (List<DocumentoMicro>) query
				.getResultList();

		return documentos;

	}

	public List<DocumentoMicro> getAnotadosMorfo()

	{
		

		EntityManager entityManager = getEm();
		
        Query query = entityManager.createNamedQuery("findAnotadosMorfo");

		List<DocumentoMicro> documentos = (List<DocumentoMicro>) query
				.getResultList();

		return documentos;

	}

	public List<DocumentoMicro> getAnotadosSintax()

	{
	

		EntityManager entityManager = getEm();
		

		Query query = entityManager.createNamedQuery("findAnotadosSintax");

		List<DocumentoMicro> documentos = (List<DocumentoMicro>) query
				.getResultList();


		return documentos;

	}

	public List<Imagem> getImagensSelecao(Integer idMacro, Integer idMicro)

	{
		
		EntityManager entityManager = getEm();
		
		Query query = entityManager.createNamedQuery("findImagensSelecao");
		query.setParameter("idMacro", idMacro);
		query.setParameter("idMicro", idMicro);
		List<Imagem> imagens = (List<Imagem>) query.getResultList();
//		
		return imagens;

	}

	public List<Colaborador> getColaboradores()

	{
//		super.iniciarTransacao();
		EntityManager entityManager = getEm();
//		entityManager.flush();
		Query query = entityManager.createNamedQuery("findColaboradores");
		List<Colaborador> colaboradores = (List<Colaborador>) query.getResultList();
//		entityManager.clear();
//		super.commitTransacao();
		return colaboradores;

	}

	public List<Imagem> getImagensMacro(Integer idMacro)

	{
	   EntityManager entityManager = getEm();
	   Query query = entityManager.createNamedQuery("findImagensMacro");
	   query.setParameter("idMacro", idMacro);
	    List<Imagem> imagens = (List<Imagem>) query.getResultList();

		return imagens;

	}
	
	
	public List<Imagem> findImagensMicro(Integer idMicro)

	{
	   EntityManager entityManager = getEm();
	   Query query = entityManager.createNamedQuery("findImagensMicro");
	   query.setParameter("idMicro", idMicro);
	    List<Imagem> imagens = (List<Imagem>) query.getResultList();

		return imagens;

	}

}
