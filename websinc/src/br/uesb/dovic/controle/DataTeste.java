package br.uesb.dovic.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

@ManagedBean(name="dataBean")
@RequestScoped
public class DataTeste {
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	

}
