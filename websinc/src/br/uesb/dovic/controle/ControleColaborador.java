package br.uesb.dovic.controle;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.uesb.dovic.entidades.Colaborador;
import br.uesb.dovic.enums.CategoriaColaborador;
import br.uesb.dovic.enums.TipoData;
import br.uesb.dovic.modelo.DAOColaborador;
import br.uesb.dovic.servicos.ViewService;

@SuppressWarnings("serial")
@ManagedBean(name="controleColaborador")
@ViewScoped
public class ControleColaborador  implements Serializable{
	private DAOColaborador<Colaborador> dao;
	private Colaborador objeto;
	private boolean edicao;
	private CategoriaColaborador selectedCategoria;
	private List<SelectItem> listaCategorias;
	private ViewService viewService;
	
	
	public ControleColaborador(){
		dao = new DAOColaborador<Colaborador>();
		viewService = new ViewService();
		listaCategorias = viewService.getSelectItensFromArray(CategoriaColaborador.values(),
				"toString");
		edicao=false;
	}

	
	public String novo(){
		objeto = new Colaborador();
		this.edicao = true;
		return null;		
	}
	
	public List<Colaborador>getMaster(){
		return dao.getColaboradoresPorCategoria(CategoriaColaborador.MASTER);
		
	}
	
	public List<Colaborador>getSenior(){
		return dao.getColaboradoresPorCategoria(CategoriaColaborador.SENIOR);
		
	}
	
	public List<Colaborador>getMestrando(){
		return dao.getColaboradoresPorCategoria(CategoriaColaborador.JUNIORMESTRANDO);
		
	}
	
	public List<Colaborador>getGraduando(){
		return dao.getColaboradoresPorCategoria(CategoriaColaborador.JUNIORGRADUANDO);
		
	}
	

	public boolean getEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	

	public CategoriaColaborador getSelectedCategoria() {
		return selectedCategoria;
	}


	public void setSelectedCategoria(CategoriaColaborador selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}
	
	


	public List<SelectItem> getListaCategorias() {
		return listaCategorias;
	}


	public void setListaCategorias(List<SelectItem> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}


	public String cancelar(){
		edicao = false;
		objeto=null;
		return null;
	}
	
	public String gravar(){
		
		boolean gravou = false;
		objeto.setCategoriaColaborador(selectedCategoria);
		
		if (objeto.getId() == null){
			gravou = dao.persist(objeto);
		}else {
			gravou = dao.merge(objeto);
		}
		if (gravou){
			edicao = false;
			}
		return null;
		
		
	}
	
	public String alterar(Colaborador obj){
		objeto = obj;
		selectedCategoria=objeto.getCategoriaColaborador();
		edicao=true; 
		return null;
	}
	
	public String excluir(){
		
		dao.remove(objeto);
				
		return null;
	}
	public String listar(){
		return "/pages/cadastros/colaborador?faces-redirect=true";
	}


	public DAOColaborador<Colaborador> getDao() {
		return dao;
	}


	public void setDao(DAOColaborador<Colaborador> dao) {
		this.dao = dao;
	}


	public Colaborador getObjeto() {
		return objeto;
	}


	public void setObjeto(Colaborador objeto) {
		this.objeto = objeto;
	}
	
	

	
}
