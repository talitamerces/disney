package br.uesb.dovic.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOUsuario <T> extends GenericDAO<T>  implements Serializable{ 


	public DAOUsuario(){
			super.setClasse(Usuario.class);
			super.setEm(EntityManagerUtil.getEntityManager());
			
		}
	
	public boolean login (String usuario, String senha){
		Query query= getEm().createQuery("from Usuario where loginUsuario=:usuario and senhaUsuario=:senha");
		
		query.setParameter("usuario",usuario);
		query.setParameter("senha",senha);
		
		if (!query.getResultList().isEmpty())
			return true;
		return false;
		
	}
	
	public Usuario localizarPorLogin(String usuario){
		return (Usuario)getEm().createQuery("from Usuario where loginUsuario=:usuario").setParameter("usuario", usuario).getSingleResult();
	}
	
	public List<Usuario> listarPorLogin(String usuario){
		List<Usuario> lista=new ArrayList<Usuario>();
		lista=getEm().createQuery("from Usuario where loginUsuario=:usuario").setParameter("usuario", usuario).getResultList();
		return lista;
	}
			
	}