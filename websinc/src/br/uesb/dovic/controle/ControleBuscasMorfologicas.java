package br.uesb.dovic.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.uesb.dovic.beans.Etiqueta;
import br.uesb.dovic.beans.ItemConsulta;
import br.uesb.dovic.beans.Resultado;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.enums.Operacao;
import br.uesb.dovic.modelo.DAODocumentoMicro;
import br.uesb.dovic.servicos.EtiquetaService;
import br.uesb.dovic.servicos.SearchService;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.servicos.XMLService;

@ManagedBean(name = "controleBuscasMorfologicas")
@SessionScoped
public class ControleBuscasMorfologicas {

	private List<Etiqueta> etiquetas;
	private List<ItemConsulta> consultaList1;
	private List<ItemConsulta> consultaList2;
	private List<ItemConsulta> consultaList3;
	private List<ItemConsulta> consultaList4;
	private List<ItemConsulta> consultaList5;
	private List<ItemConsulta> consultaList6;
	private List<SelectItem> listaOperacoes;
	private List<DocumentoMicro> listaTextos;
	
	
	private Etiqueta selectedEtiqueta1;
	private Etiqueta selectedEtiqueta2;
	private Etiqueta selectedEtiqueta3;
	private Etiqueta selectedEtiqueta4;
	private Etiqueta selectedEtiqueta5;
	private Etiqueta selectedEtiqueta6;
	
	private List<DocumentoMicro> selectedTextos;
	private String tituloTexto;
	private String arquivoDeBusca;
	private DocumentoMicro selectedTexto;
	private List<Resultado> resultadoBusca;
	
	private Operacao selectedOperacao1;
	private Operacao selectedOperacao2;
	private Operacao selectedOperacao3;
	private Operacao selectedOperacao5;
	private String selectedOpcaoTextos;
	private String selectedOperacaoLogica1;
	private String selectedOperacaoLogica2;
	private String selectedOperacaoLogica3;
	private String selectedOperacaoLogica4;
	private String selectedOperacaoLogica5;
	private String selectedOperacaoLogica6;
	
	String consulta;
	private String argumentoN1;
	private String argumentoN2;
	private String argumentoN3;
	private String argumentoN5;
	private boolean mostrarLista2;
	private boolean mostrarLista3;
	private boolean mostrarLista4;
	private boolean mostrarLista5;
	private boolean mostrarLista6;
	private boolean mostrarValorN1;
	private boolean mostrarValorN2;
	private boolean mostrarValorN3;
	private boolean mostrarValorN5;
	private boolean mostrarSelecaoTextos;
	private boolean operacaoNegacao1;
	private boolean operacaoNegacao2;
	private boolean operacaoNegacao3;
	private boolean operacaoNegacao5;
	private String resultadoBuscaMorfologica;
	private Integer totalOcorrenciasBusca;
	private String json;

	private ViewService viewService;
	private EtiquetaService serviceMorfo;
	private SearchService searchService;
	private XMLService xmlService;
	private DAODocumentoMicro<DocumentoMicro> dmDAO;

	@PostConstruct
	public void init() {
		
		serviceMorfo=new EtiquetaService();
		searchService= new SearchService();
		xmlService= new XMLService();
		etiquetas = serviceMorfo.getEtiquetas();
		viewService = new ViewService();
		listaOperacoes = viewService.getSelectItensFromArray(Operacao.values(),	"toString");
		listaTextos= dmDAO.getAnotadosMorfo();
		consulta = "";
		mostrarLista2 = mostrarLista3 = mostrarLista4 = mostrarLista5 =mostrarLista6 =false;
		mostrarValorN1 = mostrarValorN2= mostrarValorN3=mostrarValorN5=false;
		mostrarSelecaoTextos = false;
		operacaoNegacao1=operacaoNegacao2=operacaoNegacao3=operacaoNegacao5=false;
		consultaList1 = new ArrayList<ItemConsulta>();
		consultaList2 = new ArrayList<ItemConsulta>();
		consultaList3 = new ArrayList<ItemConsulta>();
		consultaList4 = new ArrayList<ItemConsulta>();
		consultaList5 = new ArrayList<ItemConsulta>();
		consultaList6 = new ArrayList<ItemConsulta>();
		selectedEtiqueta1 = null;
		selectedEtiqueta2 = null;
		selectedEtiqueta3 = null;
		selectedEtiqueta4 = null;
		selectedEtiqueta5 = null;
		selectedEtiqueta6 = null;
		resultadoBuscaMorfologica="SEM RESULTADOS";
		selectedOpcaoTextos="todos";
		selectedOperacaoLogica1=selectedOperacaoLogica2=selectedOperacaoLogica3=
				selectedOperacaoLogica4=selectedOperacaoLogica5=selectedOperacaoLogica6="or";
		argumentoN1=argumentoN2=argumentoN3=argumentoN5="";
		totalOcorrenciasBusca=0;
		json="";

	}

	public String converteJSON(String xml) throws Exception{
		
		 json=xmlService.converteJSONMorfo(xml);
		 return "/pages/buscas/sentenca?faces-redirect=true";
	}
	
	
	public void novaBuscaMorfologica(){
		consultaList1.clear();
		consultaList2.clear();
		consultaList3.clear();
		consultaList4.clear();
		consultaList5.clear();
		consultaList6.clear();
		selectedEtiqueta1=null;
		selectedEtiqueta2=null;
		selectedEtiqueta3=null;
		selectedEtiqueta4=null;
		selectedEtiqueta5=null;
		selectedEtiqueta6=null;
		consulta="";
		selectedOperacao1=selectedOperacao2=selectedOperacao3=selectedOperacao5=null;
		operacaoNegacao1=operacaoNegacao2=operacaoNegacao3=operacaoNegacao5=false;
		argumentoN1=argumentoN2=argumentoN3=argumentoN5="";
		

	}
	

	
	public ControleBuscasMorfologicas() {
		dmDAO = new DAODocumentoMicro<DocumentoMicro>();
		
	}
	
	
	public String cancelarSelecao(){
		
		selectedTexto=null;
		mostrarSelecaoTextos = false;
		return "";
		
	}
	
	public String selecionarTexto(){
			
		mostrarSelecaoTextos = false;
		for (DocumentoMicro dm: listaTextos){
			if (dm.getTitulo().equals(tituloTexto))
				arquivoDeBusca=dm.getArquivoAnotacaoXML();
		}
	
		return "";
		
	}
	
	
	public String atualizaPanelTextos() {
		
		mostrarSelecaoTextos = true;
		return null;
		
	}
	
	
	public void adicionarContainer(){
		
		mostrarLista5=true;
		
	}

	public boolean especificarValor(ItemConsulta item)
	{
		if (item.getEtiqueta().isPermiteValor())
			return false;
		return true;
	}


	
		
	public String listar() {
		return "/pages/buscas/buscaMorfologica?faces-redirect=true";
	}
	
	
	public String montaResultado(List<Resultado> resultado){
		String result="";
		for (Resultado item: resultado)
		{
			result+=item.getSentenca()+"\n\n";
			result+="TOTAL DE OCORRÊNCIAS NA SENTENÇA: "+item.getOcorrencias()+"\n\n";
			result+="<a href=\"#\">VISUALIZAR ÁRVORE DA SENTENÇA</a>\n\n";
			result+=" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n\n";
			
			
			
		}
		
		return result;
		
	}
	
	
	public List<Etiqueta> completeEtiquetas(String query) {
        
        List<Etiqueta> filteredTags = new ArrayList<Etiqueta>();
         
        for (int i = 0; i < etiquetas.size(); i++) {
            Etiqueta e = etiquetas.get(i);
            if(e.getDisplayName().startsWith(query)) {
            	filteredTags.add(e);
            }
        }
         
        return filteredTags;
    }
	
	
	
	
	public void processarConsultaMorfologica(){
		
	
		
		
		resultadoBusca=searchService.buscaMorfologica(consultaList1, consultaList2, consultaList3,
				consultaList4,consultaList5,consultaList6,
				selectedOperacaoLogica1,  selectedOperacaoLogica2,  selectedOperacaoLogica3,
				selectedOperacaoLogica4,  selectedOperacaoLogica5,  selectedOperacaoLogica6,
				selectedOperacao1, selectedOperacao2,selectedOperacao3, selectedOperacao5,
				operacaoNegacao1, operacaoNegacao2,operacaoNegacao3, operacaoNegacao5,
				argumentoN1,argumentoN2,argumentoN3,argumentoN5, arquivoDeBusca);
		
		
		
		
				
		
		totalOcorrenciasBusca= searchService.getTotalOcorrencias();
	}
	
	
	
	public String saidaBuscaMorfologica() {
		processarConsultaMorfologica();
		return "/pages/buscas/resultadoBuscaMorfologica?faces-redirect=true";
	}
	
	public void addItem1() {
			
		consultaList1.add(new ItemConsulta(selectedEtiqueta1, null));
		selectedEtiqueta1=null;
		montaConsulta();

	}
	
	public void addItem2() {

		consultaList2.add(new ItemConsulta(selectedEtiqueta2, null));
		selectedEtiqueta2=null;
		montaConsulta();

	}
	

	public void addItem3() {

		consultaList3.add(new ItemConsulta(selectedEtiqueta3, null));
		selectedEtiqueta3=null;
		montaConsulta();

	}
	
	public void addItem4() {

		consultaList4.add(new ItemConsulta(selectedEtiqueta4, null));
		selectedEtiqueta4=null;
		montaConsulta();

	}
	
	public void addItem5() {

		consultaList5.add(new ItemConsulta(selectedEtiqueta5, null));
		selectedEtiqueta5=null;
		montaConsulta();

	}
	
	public void addItem6() {

		consultaList6.add(new ItemConsulta(selectedEtiqueta6, null));
		selectedEtiqueta6=null;
		montaConsulta();

	}
	
	public void deleteItem1(ItemConsulta e) {

		consultaList1.remove(e);
		montaConsulta();
	}

	public void deleteItem2(ItemConsulta e) {

		consultaList2.remove(e);
		montaConsulta();

	}
	
	
	
	public void deleteItem3(ItemConsulta e) {

		consultaList3.remove(e);
		montaConsulta();

	}
	
	
	public void deleteItem4(ItemConsulta e) {

		consultaList4.remove(e);
		montaConsulta();

	}
	
	public void deleteItem5(ItemConsulta e) {

		consultaList5.remove(e);
		montaConsulta();

	}
	public void deleteItem6(ItemConsulta e) {

		consultaList6.remove(e);
		montaConsulta();

	}
	

	public void montaConsulta() {
		consulta = "";
		String operacao;int tamanho;
		if (selectedOperacaoLogica1.equals("or"))
			operacao="OU";
		else
			operacao="E";
		
		
		tamanho = consultaList1.size() - 1;
		for (int i = 0; i < tamanho; i++) {
			consulta += consultaList1.get(i).getEtiqueta().getDisplayName()
					+ "<span class=\"palavrachave\"> "+operacao+" </span>";
		}
		if (tamanho >= 0)
			consulta += consultaList1.get(tamanho).getEtiqueta().getDisplayName()+ " ";
		
		if (selectedOperacao1 != null)
			if (operacaoNegacao1==true)
				consulta += "<span class=\"operacao\">NÃO "
						+ selectedOperacao1.toString() + "</span> ";
			else
				consulta += "<span class=\"operacao\">"
					+ selectedOperacao1.toString() + "</span> ";

		
		if (selectedOperacaoLogica2.equals("or"))
			operacao="OU";
		else
			operacao="E";
		
		
		tamanho = consultaList2.size() - 1;
		for (int i = 0; i < tamanho; i++) {
			consulta += consultaList2.get(i).getEtiqueta().getDisplayName()
					+ "<span class=\"palavrachave\"> "+operacao+" </span>";
		}
		if (tamanho >= 0)
			consulta += consultaList2.get(tamanho).getEtiqueta()
					.getDisplayName()
					+ " ";
	
		
	}

	public void selectOperacao1(){
		if(selectedOperacao1.equals(Operacao.EXISTS) || selectedOperacao1.equals(Operacao.INICIAL) ||
				selectedOperacao1.equals(Operacao.FINAL) || selectedOperacao1.equals(Operacao.POSICAO))
			mostrarLista2 = false;
		else
			mostrarLista2 = true;
		if (selectedOperacao1.equals(Operacao.VIZINHANCADIREITA) || 
				selectedOperacao1.equals(Operacao.VIZINHANCAESQUERDA) ||
				selectedOperacao1.equals(Operacao.VIZINHANCA) ||
				selectedOperacao1.equals(Operacao.POSICAO) )
			mostrarValorN1 = true;
		else
			mostrarValorN1  = false;
		montaConsulta();
	}
	
	public void selectOperacao2(){
		if(selectedOperacao2.equals(Operacao.EXISTS) || selectedOperacao2.equals(Operacao.INICIAL) ||
				selectedOperacao2.equals(Operacao.FINAL) || selectedOperacao2.equals(Operacao.POSICAO))
			mostrarLista3 = false;
		else
			mostrarLista3 = true;
		if (selectedOperacao2.equals(Operacao.VIZINHANCADIREITA) || 
				selectedOperacao2.equals(Operacao.VIZINHANCAESQUERDA) ||
				selectedOperacao2.equals(Operacao.VIZINHANCA) ||
				selectedOperacao2.equals(Operacao.POSICAO) )
			mostrarValorN2 = true;
		else
			mostrarValorN2  = false;
		montaConsulta();
	}
	
	public void selectOperacao3(){
		if(selectedOperacao3.equals(Operacao.EXISTS) || selectedOperacao3.equals(Operacao.INICIAL) ||
				selectedOperacao3.equals(Operacao.FINAL) || selectedOperacao3.equals(Operacao.POSICAO))
			mostrarLista4 = false;
		else
			mostrarLista4 = true;
		if (selectedOperacao3.equals(Operacao.VIZINHANCADIREITA) || 
				selectedOperacao3.equals(Operacao.VIZINHANCAESQUERDA) ||
				selectedOperacao3.equals(Operacao.VIZINHANCA) ||
				selectedOperacao3.equals(Operacao.POSICAO) )
			mostrarValorN3 = true;
		else
			mostrarValorN3  = false;
		montaConsulta();
	}
	
	public void selectOperacao5(){
		if(selectedOperacao5.equals(Operacao.EXISTS) || selectedOperacao5.equals(Operacao.INICIAL) ||
				selectedOperacao5.equals(Operacao.FINAL) || selectedOperacao5.equals(Operacao.POSICAO))
			mostrarLista6 = false;
		else
			mostrarLista6 = true;
		if (selectedOperacao5.equals(Operacao.VIZINHANCADIREITA) || 
				selectedOperacao5.equals(Operacao.VIZINHANCAESQUERDA) ||
				selectedOperacao5.equals(Operacao.VIZINHANCA) ||
				selectedOperacao5.equals(Operacao.POSICAO) )
			mostrarValorN5 = true;
		else
			mostrarValorN5  = false;
		montaConsulta();
	}
	
	

	public String getOperacaoTotal2(){
		if ((operacaoNegacao1==true) && (selectedOperacao1!=null))
				return "NÃO " +selectedOperacao1.toString();
		if ((operacaoNegacao1==false) && (selectedOperacao1!=null))
			return selectedOperacao1.toString();
		return "";
		
	}
	
	public String getOperacaoTotal3(){
		if ((operacaoNegacao2==true) && (selectedOperacao2!=null))
				return "NÃO " +selectedOperacao2.toString();
		if ((operacaoNegacao2==false) && (selectedOperacao2!=null))
			return selectedOperacao2.toString();
		return "";
		
	}
	
	
	public String getOperacaoTotal4(){
		if ((operacaoNegacao3==true) && (selectedOperacao3!=null))
				return "NÃO " +selectedOperacao3.toString();
		if ((operacaoNegacao3==false) && (selectedOperacao3!=null))
			return selectedOperacao3.toString();
		return "";
		
	}
	
	public String getOperacaoTotal6(){
		if ((operacaoNegacao5==true) && (selectedOperacao5!=null))
				return "NÃO " +selectedOperacao5.toString();
		if ((operacaoNegacao5==false) && (selectedOperacao5!=null))
			return selectedOperacao5.toString();
		return "";
		
	}


	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}


	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}


	public List<ItemConsulta> getConsultaList1() {
		return consultaList1;
	}


	public void setConsultaList1(List<ItemConsulta> consultaList1) {
		this.consultaList1 = consultaList1;
	}


	public List<ItemConsulta> getConsultaList2() {
		return consultaList2;
	}


	public void setConsultaList2(List<ItemConsulta> consultaList2) {
		this.consultaList2 = consultaList2;
	}


	public List<ItemConsulta> getConsultaList3() {
		return consultaList3;
	}


	public void setConsultaList3(List<ItemConsulta> consultaList3) {
		this.consultaList3 = consultaList3;
	}


	public List<ItemConsulta> getConsultaList4() {
		return consultaList4;
	}


	public void setConsultaList4(List<ItemConsulta> consultaList4) {
		this.consultaList4 = consultaList4;
	}


	public List<SelectItem> getListaOperacoes() {
		return listaOperacoes;
	}


	public void setListaOperacoes(List<SelectItem> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}


	public List<DocumentoMicro> getListaTextos() {
		return listaTextos;
	}


	public void setListaTextos(List<DocumentoMicro> listaTextos) {
		this.listaTextos = listaTextos;
	}


	public Etiqueta getSelectedEtiqueta1() {
		return selectedEtiqueta1;
	}


	public void setSelectedEtiqueta1(Etiqueta selectedEtiqueta1) {
		this.selectedEtiqueta1 = selectedEtiqueta1;
	}


	public Etiqueta getSelectedEtiqueta2() {
		return selectedEtiqueta2;
	}


	public void setSelectedEtiqueta2(Etiqueta selectedEtiqueta2) {
		this.selectedEtiqueta2 = selectedEtiqueta2;
	}


	public Etiqueta getSelectedEtiqueta3() {
		return selectedEtiqueta3;
	}


	public void setSelectedEtiqueta3(Etiqueta selectedEtiqueta3) {
		this.selectedEtiqueta3 = selectedEtiqueta3;
	}


	public Etiqueta getSelectedEtiqueta4() {
		return selectedEtiqueta4;
	}


	public void setSelectedEtiqueta4(Etiqueta selectedEtiqueta4) {
		this.selectedEtiqueta4 = selectedEtiqueta4;
	}


	public List<DocumentoMicro> getSelectedTextos() {
		return selectedTextos;
	}


	public void setSelectedTextos(List<DocumentoMicro> selectedTextos) {
		this.selectedTextos = selectedTextos;
	}


	public List<Resultado> getResultadoBusca() {
		return resultadoBusca;
	}


	public void setResultadoBusca(List<Resultado> resultadoBusca) {
		this.resultadoBusca = resultadoBusca;
	}


	public Operacao getSelectedOperacao1() {
		return selectedOperacao1;
	}


	public void setSelectedOperacao1(Operacao selectedOperacao1) {
		this.selectedOperacao1 = selectedOperacao1;
	}


	public Operacao getSelectedOperacao2() {
		return selectedOperacao2;
	}


	public void setSelectedOperacao2(Operacao selectedOperacao2) {
		this.selectedOperacao2 = selectedOperacao2;
	}


	public Operacao getSelectedOperacao3() {
		return selectedOperacao3;
	}


	public void setSelectedOperacao3(Operacao selectedOperacao3) {
		this.selectedOperacao3 = selectedOperacao3;
	}


	public String getSelectedOpcaoTextos() {
		return selectedOpcaoTextos;
	}


	public void setSelectedOpcaoTextos(String selectedOpcaoTextos) {
		this.selectedOpcaoTextos = selectedOpcaoTextos;
	}


	public String getSelectedOperacaoLogica1() {
		return selectedOperacaoLogica1;
	}


	public void setSelectedOperacaoLogica1(String selectedOperacaoLogica1) {
		this.selectedOperacaoLogica1 = selectedOperacaoLogica1;
	}


	public String getSelectedOperacaoLogica2() {
		return selectedOperacaoLogica2;
	}


	public void setSelectedOperacaoLogica2(String selectedOperacaoLogica2) {
		this.selectedOperacaoLogica2 = selectedOperacaoLogica2;
	}


	public String getSelectedOperacaoLogica3() {
		return selectedOperacaoLogica3;
	}


	public void setSelectedOperacaoLogica3(String selectedOperacaoLogica3) {
		this.selectedOperacaoLogica3 = selectedOperacaoLogica3;
	}


	public String getSelectedOperacaoLogica4() {
		return selectedOperacaoLogica4;
	}


	public void setSelectedOperacaoLogica4(String selectedOperacaoLogica4) {
		this.selectedOperacaoLogica4 = selectedOperacaoLogica4;
	}


	public String getConsulta() {
		return consulta;
	}


	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}


	public String getArgumentoN1() {
		return argumentoN1;
	}


	public void setArgumentoN1(String argumentoN1) {
		this.argumentoN1 = argumentoN1;
	}


	public String getArgumentoN2() {
		return argumentoN2;
	}


	public void setArgumentoN2(String argumentoN2) {
		this.argumentoN2 = argumentoN2;
	}


	public String getArgumentoN3() {
		return argumentoN3;
	}


	public void setArgumentoN3(String argumentoN3) {
		this.argumentoN3 = argumentoN3;
	}


	public boolean isMostrarLista2() {
		return mostrarLista2;
	}


	public void setMostrarLista2(boolean mostrarLista2) {
		this.mostrarLista2 = mostrarLista2;
	}


	public boolean isMostrarLista3() {
		return mostrarLista3;
	}


	public void setMostrarLista3(boolean mostrarLista3) {
		this.mostrarLista3 = mostrarLista3;
	}


	public boolean isMostrarLista4() {
		return mostrarLista4;
	}


	public void setMostrarLista4(boolean mostrarLista4) {
		this.mostrarLista4 = mostrarLista4;
	}


	public boolean isMostrarValorN1() {
		return mostrarValorN1;
	}


	public void setMostrarValorN1(boolean mostrarValorN1) {
		this.mostrarValorN1 = mostrarValorN1;
	}


	public boolean isMostrarValorN2() {
		return mostrarValorN2;
	}


	public void setMostrarValorN2(boolean mostrarValorN2) {
		this.mostrarValorN2 = mostrarValorN2;
	}


	public boolean isMostrarValorN3() {
		return mostrarValorN3;
	}


	public void setMostrarValorN3(boolean mostrarValorN3) {
		this.mostrarValorN3 = mostrarValorN3;
	}


	public boolean isMostrarSelecaoTextos() {
		return mostrarSelecaoTextos;
	}


	public void setMostrarSelecaoTextos(boolean mostrarSelecaoTextos) {
		this.mostrarSelecaoTextos = mostrarSelecaoTextos;
	}


	public boolean isOperacaoNegacao1() {
		return operacaoNegacao1;
	}


	public void setOperacaoNegacao1(boolean operacaoNegacao1) {
		this.operacaoNegacao1 = operacaoNegacao1;
	}


	public boolean isOperacaoNegacao2() {
		return operacaoNegacao2;
	}


	public void setOperacaoNegacao2(boolean operacaoNegacao2) {
		this.operacaoNegacao2 = operacaoNegacao2;
	}


	public boolean isOperacaoNegacao3() {
		return operacaoNegacao3;
	}


	public void setOperacaoNegacao3(boolean operacaoNegacao3) {
		this.operacaoNegacao3 = operacaoNegacao3;
	}


	public String getResultadoBuscaMorfologica() {
		return resultadoBuscaMorfologica;
	}


	public void setResultadoBuscaMorfologica(String resultadoBuscaMorfologica) {
		this.resultadoBuscaMorfologica = resultadoBuscaMorfologica;
	}


	public Integer getTotalOcorrenciasBusca() {
		return totalOcorrenciasBusca;
	}


	public void setTotalOcorrenciasBusca(Integer totalOcorrenciasBusca) {
		this.totalOcorrenciasBusca = totalOcorrenciasBusca;
	}


	public String getJson() {
		return json;
	}


	public void setJson(String json) {
		this.json = json;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consultaList1 == null) ? 0 : consultaList1.hashCode());
		result = prime * result
				+ ((consultaList2 == null) ? 0 : consultaList2.hashCode());
		result = prime * result
				+ ((consultaList3 == null) ? 0 : consultaList3.hashCode());
		result = prime * result
				+ ((consultaList4 == null) ? 0 : consultaList4.hashCode());
		result = prime * result
				+ ((etiquetas == null) ? 0 : etiquetas.hashCode());
		result = prime
				* result
				+ ((selectedEtiqueta1 == null) ? 0 : selectedEtiqueta1
						.hashCode());
		result = prime
				* result
				+ ((selectedEtiqueta2 == null) ? 0 : selectedEtiqueta2
						.hashCode());
		result = prime
				* result
				+ ((selectedEtiqueta3 == null) ? 0 : selectedEtiqueta3
						.hashCode());
		result = prime
				* result
				+ ((selectedEtiqueta4 == null) ? 0 : selectedEtiqueta4
						.hashCode());
		result = prime
				* result
				+ ((selectedOperacao1 == null) ? 0 : selectedOperacao1
						.hashCode());
		result = prime
				* result
				+ ((selectedOperacao2 == null) ? 0 : selectedOperacao2
						.hashCode());
		result = prime
				* result
				+ ((selectedOperacao3 == null) ? 0 : selectedOperacao3
						.hashCode());
		result = prime
				* result
				+ ((selectedOperacaoLogica1 == null) ? 0
						: selectedOperacaoLogica1.hashCode());
		result = prime
				* result
				+ ((selectedOperacaoLogica2 == null) ? 0
						: selectedOperacaoLogica2.hashCode());
		result = prime
				* result
				+ ((selectedOperacaoLogica3 == null) ? 0
						: selectedOperacaoLogica3.hashCode());
		result = prime
				* result
				+ ((selectedOperacaoLogica4 == null) ? 0
						: selectedOperacaoLogica4.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControleBuscasMorfologicas other = (ControleBuscasMorfologicas) obj;
		if (consultaList1 == null) {
			if (other.consultaList1 != null)
				return false;
		} else if (!consultaList1.equals(other.consultaList1))
			return false;
		if (consultaList2 == null) {
			if (other.consultaList2 != null)
				return false;
		} else if (!consultaList2.equals(other.consultaList2))
			return false;
		if (consultaList3 == null) {
			if (other.consultaList3 != null)
				return false;
		} else if (!consultaList3.equals(other.consultaList3))
			return false;
		if (consultaList4 == null) {
			if (other.consultaList4 != null)
				return false;
		} else if (!consultaList4.equals(other.consultaList4))
			return false;
		if (etiquetas == null) {
			if (other.etiquetas != null)
				return false;
		} else if (!etiquetas.equals(other.etiquetas))
			return false;
		if (selectedEtiqueta1 == null) {
			if (other.selectedEtiqueta1 != null)
				return false;
		} else if (!selectedEtiqueta1.equals(other.selectedEtiqueta1))
			return false;
		if (selectedEtiqueta2 == null) {
			if (other.selectedEtiqueta2 != null)
				return false;
		} else if (!selectedEtiqueta2.equals(other.selectedEtiqueta2))
			return false;
		if (selectedEtiqueta3 == null) {
			if (other.selectedEtiqueta3 != null)
				return false;
		} else if (!selectedEtiqueta3.equals(other.selectedEtiqueta3))
			return false;
		if (selectedEtiqueta4 == null) {
			if (other.selectedEtiqueta4 != null)
				return false;
		} else if (!selectedEtiqueta4.equals(other.selectedEtiqueta4))
			return false;
		if (selectedOperacao1 != other.selectedOperacao1)
			return false;
		if (selectedOperacao2 != other.selectedOperacao2)
			return false;
		if (selectedOperacao3 != other.selectedOperacao3)
			return false;
		if (selectedOperacaoLogica1 == null) {
			if (other.selectedOperacaoLogica1 != null)
				return false;
		} else if (!selectedOperacaoLogica1
				.equals(other.selectedOperacaoLogica1))
			return false;
		if (selectedOperacaoLogica2 == null) {
			if (other.selectedOperacaoLogica2 != null)
				return false;
		} else if (!selectedOperacaoLogica2
				.equals(other.selectedOperacaoLogica2))
			return false;
		if (selectedOperacaoLogica3 == null) {
			if (other.selectedOperacaoLogica3 != null)
				return false;
		} else if (!selectedOperacaoLogica3
				.equals(other.selectedOperacaoLogica3))
			return false;
		if (selectedOperacaoLogica4 == null) {
			if (other.selectedOperacaoLogica4 != null)
				return false;
		} else if (!selectedOperacaoLogica4
				.equals(other.selectedOperacaoLogica4))
			return false;
		return true;
	}


	public List<ItemConsulta> getConsultaList5() {
		return consultaList5;
	}


	public void setConsultaList5(List<ItemConsulta> consultaList5) {
		this.consultaList5 = consultaList5;
	}


	public List<ItemConsulta> getConsultaList6() {
		return consultaList6;
	}


	public void setConsultaList6(List<ItemConsulta> consultaList6) {
		this.consultaList6 = consultaList6;
	}


	public Etiqueta getSelectedEtiqueta5() {
		return selectedEtiqueta5;
	}


	public void setSelectedEtiqueta5(Etiqueta selectedEtiqueta5) {
		this.selectedEtiqueta5 = selectedEtiqueta5;
	}


	public Etiqueta getSelectedEtiqueta6() {
		return selectedEtiqueta6;
	}


	public void setSelectedEtiqueta6(Etiqueta selectedEtiqueta6) {
		this.selectedEtiqueta6 = selectedEtiqueta6;
	}


	public Operacao getSelectedOperacao5() {
		return selectedOperacao5;
	}


	public void setSelectedOperacao5(Operacao selectedOperacao5) {
		this.selectedOperacao5 = selectedOperacao5;
	}


	public String getSelectedOperacaoLogica5() {
		return selectedOperacaoLogica5;
	}


	public void setSelectedOperacaoLogica5(String selectedOperacaoLogica5) {
		this.selectedOperacaoLogica5 = selectedOperacaoLogica5;
	}


	public String getSelectedOperacaoLogica6() {
		return selectedOperacaoLogica6;
	}


	public void setSelectedOperacaoLogica6(String selectedOperacaoLogica6) {
		this.selectedOperacaoLogica6 = selectedOperacaoLogica6;
	}


	public String getArgumentoN5() {
		return argumentoN5;
	}


	public void setArgumentoN5(String argumentoN5) {
		this.argumentoN5 = argumentoN5;
	}


	public boolean isMostrarLista5() {
		return mostrarLista5;
	}


	public void setMostrarLista5(boolean mostrarLista5) {
		this.mostrarLista5 = mostrarLista5;
	}


	public boolean isMostrarLista6() {
		return mostrarLista6;
	}


	public void setMostrarLista6(boolean mostrarLista6) {
		this.mostrarLista6 = mostrarLista6;
	}


	public boolean isMostrarValorN5() {
		return mostrarValorN5;
	}


	public void setMostrarValorN5(boolean mostrarValorN5) {
		this.mostrarValorN5 = mostrarValorN5;
	}


	public boolean isOperacaoNegacao5() {
		return operacaoNegacao5;
	}


	public void setOperacaoNegacao5(boolean operacaoNegacao5) {
		this.operacaoNegacao5 = operacaoNegacao5;
	}

	public DocumentoMicro getSelectedTexto() {
		return selectedTexto;
	}

	public void setSelectedTexto(DocumentoMicro selectedTexto) {
		this.selectedTexto = selectedTexto;
		
		
	}

	public String getTituloTexto() {
		return tituloTexto;
	}

	public void setTituloTexto(String tituloTexto) {
		this.tituloTexto = tituloTexto;
	}

	public String getArquivoDeBusca() {
		return arquivoDeBusca;
	}

	public void setArquivoDeBusca(String arquivoDeBusca) {
		this.arquivoDeBusca = arquivoDeBusca;
	}


		

}
