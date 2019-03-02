package br.uesb.dovic.modelo;

import java.io.Serializable;

import br.uesb.dovic.conversores.ConverterOrdem;
import br.uesb.dovic.entidades.DocumentoMacro;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAODocumentoMacro<T> extends GenericDAO<T> implements Serializable {

	public DAODocumentoMacro() {
		super.setClasse(DocumentoMacro.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "idDocumentoMacro" + ""));
		super.getListaOrdem().add(new Ordem("Título", "titulo"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(3);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}

}
