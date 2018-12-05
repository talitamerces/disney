package br.uesb.dovic.modelo;

import java.io.Serializable;


import br.uesb.dovic.entidades.Cor;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOCor <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOCor(){
			super.setClasse(Cor.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			
		}
			
	}