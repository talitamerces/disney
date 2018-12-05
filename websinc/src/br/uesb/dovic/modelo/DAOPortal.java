package br.uesb.dovic.modelo;

import java.io.Serializable;

import br.uesb.dovic.entidades.Portal;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOPortal <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOPortal(){
			super.setClasse(Portal.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			
		}
			
	}