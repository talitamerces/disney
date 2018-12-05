package br.uesb.dovic.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.enums.TipoImagem;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOImagem<T> extends GenericDAO<T> implements Serializable {

	public DAOImagem() {
		super.setClasse(Imagem.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idImagem" + ""));
		super.getListaOrdem().add(new Ordem("Caminho", "enderecoImagem"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}

	public List<Imagem> getByDocumentoMacro(Integer idMacro)

	{
		
		EntityManager entityManager = getEm();
		Query query = entityManager.createNamedQuery("findImagemByMacro");
		query.setParameter("idMacro", idMacro);
		List<Imagem> imagens = (List<Imagem>) query.getResultList();
		return imagens;

	}
	
	public List<Imagem> getImagensCatalogo(Integer idMacro)

	{
		super.iniciarTransacao();
		EntityManager entityManager = getEm();
		entityManager.flush();
		Query query = entityManager.createNamedQuery("getImagensCatalogo");
		query.setParameter("idMacro", idMacro);
	    query.setParameter("tipos", Arrays.asList(TipoImagem.CAPA, TipoImagem.ABERTURA, 
	    		 TipoImagem.FECHAMENTO,TipoImagem.LOMBADA));
		List<Imagem> imagens = (List<Imagem>) query.getResultList();
		entityManager.clear();
		super.commitTransacao();
		return imagens;

	}
	


}
