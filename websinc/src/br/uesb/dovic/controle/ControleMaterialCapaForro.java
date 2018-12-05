	package br.uesb.dovic.controle;

	import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.uesb.dovic.entidades.MaterialCapaForro;
import br.uesb.dovic.modelo.DAOMaterialCapaForro;


	@SuppressWarnings("serial")
	@ManagedBean(name="controleMaterialCapaForro")
	@ViewScoped
	public class ControleMaterialCapaForro implements Serializable{
		private DAOMaterialCapaForro<MaterialCapaForro> dao;
		private MaterialCapaForro objeto;
		private boolean edicao;
		
		
		public ControleMaterialCapaForro(){
			dao = new DAOMaterialCapaForro<MaterialCapaForro>();
			edicao=false;
		}
		
		public String listar(){
			return "/pages/cadastros/materialCapaForro?faces-redirect=true";
		}
		
		public String novo(){
			objeto = new MaterialCapaForro();
			this.edicao = true;
			return null;		
		}
		

		public boolean getEdicao() {
			return edicao;
		}

		public void setEdicao(boolean edicao) {
			this.edicao = edicao;
		}

		public String cancelar(){
			edicao = false;
			objeto=null;
			return null;
		}
		
		public String gravar(){
			
			boolean gravou = false;
			if (objeto.getId() == null){
				gravou = dao.persist(objeto);
			}else {
				gravou = dao.merge(objeto);
			}
			if (gravou){
				edicao = false;}
			return null;
			
			
		}
		
		public String alterar(MaterialCapaForro obj){
			objeto = obj;
			edicao=true; 
			return null;
		}
		
		public String excluir(){
			
			dao.remove(objeto);
					
			return null;
		}
		
		public DAOMaterialCapaForro<MaterialCapaForro> getDao() {
			return dao;
		}
		public void setDao(DAOMaterialCapaForro<MaterialCapaForro> dao) {
			this.dao = dao;
		}
		public MaterialCapaForro getObjeto() {
			return objeto;
		}
		public void setObjeto(MaterialCapaForro objeto) {
			this.objeto = objeto;
		}
		

	}
