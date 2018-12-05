package br.uesb.dovic.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;

import br.uesb.dovic.beans.Etiqueta;
import br.uesb.dovic.beans.ItemConsulta;
import br.uesb.dovic.beans.Resultado;
import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.enums.FuncaoSintatica;
import br.uesb.dovic.modelo.DAODocumentoMicro;
import br.uesb.dovic.servicos.SearchService;
import br.uesb.dovic.servicos.SintagmaService;
import br.uesb.dovic.servicos.ViewService;
import br.uesb.dovic.servicos.XMLService;

@ManagedBean(name = "controleBuscasSintaticas")
@SessionScoped
public class ControleBuscasSintaticas {

	
	private List<Etiqueta> etiquetas;
	private List<ItemConsulta> consultaListaSintatica1;
	private List<ItemConsulta> consultaListaSintatica2;
	private List<ItemConsulta> consultaListaSintatica3;
	private List<ItemConsulta> consultaListaSintatica4;
	private List<ItemConsulta> consultaListaSintatica5;
	private List<ItemConsulta> consultaListaSintatica6;
	private List<SelectItem> listaFuncoes;
	private List<DocumentoMicro> listaTextos;
	private DocumentoMicro selectedTexto;
	private String tituloTexto;
	private String arquivoDeBusca;
	
	
	private Etiqueta selectedEtiqueta1;
	private Etiqueta selectedEtiqueta2;
	private Etiqueta selectedEtiqueta3;
	private Etiqueta selectedEtiqueta4;
	private Etiqueta selectedEtiqueta5;
	private Etiqueta selectedEtiqueta6;
	
	
	private List<DocumentoMicro> selectedTextos;
	private List<Resultado> resultadoBusca;
	private FuncaoSintatica selectedFuncao1;
	private FuncaoSintatica selectedFuncao2;
	private FuncaoSintatica selectedFuncao3;
	private FuncaoSintatica selectedFuncao5;
	
	
	private String selectedOpcaoTextos;
	private String selectedOperacaoLogica1;
	private String selectedOperacaoLogica2;
	private String selectedOperacaoLogica3;
	private String selectedOperacaoLogica4;
	private String selectedOperacaoLogica5;
	private String selectedOperacaoLogica6;
	
	
	private String consulta;
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
	private String resultadoBuscaSintatica;
	
	private Integer totalOcorrenciasBusca;
	private String json;

	private ViewService viewService;
	private SintagmaService serviceSin;
	private SearchService searchService;
	private XMLService xmlService;
	private DAODocumentoMicro<DocumentoMicro> dmDAO;

	@PostConstruct
	public void init() {
		serviceSin = new SintagmaService();
		
		searchService= new SearchService();
		xmlService= new XMLService();
		etiquetas = serviceSin.getEtiquetas();
		viewService = new ViewService();
		listaFuncoes = viewService.getSelectItensFromArray(FuncaoSintatica.values(),"toString");
		listaTextos= dmDAO.getAnotadosSintax();
		consulta = "";
		mostrarLista2 = mostrarLista3 = mostrarLista4 = false;
		mostrarLista5 = mostrarLista6 = false;
		
		mostrarValorN1 = mostrarValorN2 = mostrarValorN3 = mostrarValorN5 =false;
		mostrarSelecaoTextos = false;
		operacaoNegacao1= operacaoNegacao2 = operacaoNegacao3 = operacaoNegacao5 =false;
		consultaListaSintatica1=new ArrayList<ItemConsulta>();
		consultaListaSintatica2=new ArrayList<ItemConsulta>();	
		consultaListaSintatica3=new ArrayList<ItemConsulta>();
		consultaListaSintatica4=new ArrayList<ItemConsulta>();
		consultaListaSintatica5=new ArrayList<ItemConsulta>();
		consultaListaSintatica6=new ArrayList<ItemConsulta>();
		selectedEtiqueta1 = null;
		selectedEtiqueta2 = null;
		selectedEtiqueta3 = null;
		selectedEtiqueta4 = null;
		selectedEtiqueta5 = null;
		selectedEtiqueta6 = null;
		resultadoBuscaSintatica="SEM RESULTADOS";
		selectedOpcaoTextos="todos";
		selectedOperacaoLogica1=selectedOperacaoLogica2=
				selectedOperacaoLogica3=selectedOperacaoLogica4=
				selectedOperacaoLogica5=selectedOperacaoLogica6="or";
		argumentoN1= argumentoN2= argumentoN3=argumentoN5="";
		totalOcorrenciasBusca=0;
		json="";

	}


	
	public void novaBuscaSintatica(){
		consultaListaSintatica1.clear();
		consultaListaSintatica2.clear();
		consultaListaSintatica3.clear();
		consultaListaSintatica4.clear();
		consultaListaSintatica5.clear();
		consultaListaSintatica6.clear();
		selectedEtiqueta1=null;
		selectedEtiqueta2=null;
		selectedEtiqueta3=null;
		selectedEtiqueta4=null;
		selectedEtiqueta5=null;
		selectedEtiqueta6=null;
		
		consulta="";
		selectedFuncao1=null;
		selectedFuncao2=null;
		selectedFuncao3=null;
		selectedFuncao5=null;
		
		operacaoNegacao1=false;
		operacaoNegacao2=false;
		operacaoNegacao3=false;
		operacaoNegacao5=false;
		
		argumentoN1="";
		argumentoN2="";
		argumentoN3="";
		argumentoN5="";

	}
	
	public ControleBuscasSintaticas() {
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
				arquivoDeBusca=dm.getArquivoSintaxeXML();
		}
	
		return "";
		
	}
	

	
	public String converteJSON(String xml) throws Exception{
		
		    json= xmlService.converteJSON(xml);	
		    return "/pages/buscas/arvore?faces-redirect=true";
		 
		}
	
	public void atualizaPanelTextos() {
		mostrarSelecaoTextos = true;
		
		
	}
	

	public boolean especificarValor(ItemConsulta item)
	{
		if (item.getEtiqueta().isPermiteValor())
			return false;
		return true;
	}

	
	public void adicionarContainer(){
		
		mostrarLista5=true;
		
		
	}

	public String listar() {
		return "/pages/buscas/buscaSintatica?faces-redirect=true";
	}
	

	public void processarConsulta(){
		//resultadoBuscaSintatica=searchService.compilar("xxx", "a_001_psd.txt", "query1.q");
		resultadoBusca=searchService.buscaSintatica(consultaListaSintatica1, consultaListaSintatica2,
				consultaListaSintatica3, consultaListaSintatica4,
				consultaListaSintatica5, consultaListaSintatica6,
				selectedOperacaoLogica1,selectedOperacaoLogica2,selectedOperacaoLogica3, 
				selectedOperacaoLogica4,selectedOperacaoLogica5,selectedOperacaoLogica6,
				selectedFuncao1, selectedFuncao2,selectedFuncao3,selectedFuncao5,
				operacaoNegacao1,operacaoNegacao2,operacaoNegacao3,operacaoNegacao5,
				argumentoN1, argumentoN2,argumentoN3,argumentoN5, arquivoDeBusca);
		
		resultadoBuscaSintatica=montaResultado(resultadoBusca);
		totalOcorrenciasBusca= searchService.getTotalOcorrencias();
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

	public List<Etiqueta> completeEtiquetasSintaticas(String query) {
        
        List<Etiqueta> filteredTags = new ArrayList<Etiqueta>();
         
             
            for (int i = 0; i < etiquetas.size(); i++) {
                Etiqueta e = etiquetas.get(i);
                if(e.getDisplayName().startsWith(query)) 
                	filteredTags.add(e);
                }
        
         
        return filteredTags;
    }
	
	

	
	public String saidaBuscaSintatica() {
		processarConsulta();
		return "/pages/buscas/resultadoBuscaSintatica?faces-redirect=true";
	}
	
	
	public void addItemSin1() {
		
		
		consultaListaSintatica1.add(new ItemConsulta(selectedEtiqueta1, null));
		selectedEtiqueta1=null;
		montaConsulta();

	}
	

	public void addItemSin2() {
	
		consultaListaSintatica2.add(new ItemConsulta(selectedEtiqueta2, null));
		selectedEtiqueta2=null;
		montaConsulta();

	}

	public void addItemSin3() {
	
		consultaListaSintatica3.add(new ItemConsulta(selectedEtiqueta3, null));
		selectedEtiqueta3=null;
		montaConsulta();

	}

	public void addItemSin4() {
	
		
		consultaListaSintatica4.add(new ItemConsulta(selectedEtiqueta4, null));
		selectedEtiqueta4=null;
		montaConsulta();

	}
public void addItemSin5() {
	
	
	consultaListaSintatica5.add(new ItemConsulta(selectedEtiqueta5, null));
	selectedEtiqueta5=null;
	montaConsulta();

	}
	public void addItemSin6() {
	
	
		consultaListaSintatica6.add(new ItemConsulta(selectedEtiqueta6, null));
		selectedEtiqueta6=null;
		montaConsulta();

	}

	
	public void deleteItemSin1(ItemConsulta e) {

		consultaListaSintatica1.remove(e);
		montaConsulta();
	}

	public void deleteItemSin2(ItemConsulta e) {

		consultaListaSintatica2.remove(e);
		montaConsulta();

	}
	


	public void deleteItemSin3(ItemConsulta e) {

		consultaListaSintatica3.remove(e);
		montaConsulta();

	}

	public void deleteItemSin4(ItemConsulta e) {

		consultaListaSintatica4.remove(e);
		montaConsulta();

	}	
	
	public void deleteItemSin5(ItemConsulta e) {

		consultaListaSintatica5.remove(e);
		montaConsulta();

	}
	
	public void deleteItemSin6(ItemConsulta e) {

		consultaListaSintatica6.remove(e);
		montaConsulta();

	}
	
	public boolean ehFolha(String etiqueta){
		if (etiqueta.equals("LEAF"))
		 	return true;
		return false;
		
	}
	
	public String montaConsulta(List<ItemConsulta> consultaLista, String operacaoLogica){
		String result="", operacao;
		int tamanho;
		
		
		if (operacaoLogica.equals("or"))
			operacao="OU";
		else
			operacao="E";
		
		tamanho = consultaLista.size() - 1;
			for (int i = 0; i < tamanho; i++) {
				result += consultaLista.get(i).getEtiqueta().getDisplayName()
						+ "<span class=\"palavrachave\"> "+operacao+" </span>";
			}
			if (tamanho >= 0)
				result += consultaLista.get(tamanho).getEtiqueta().getDisplayName()+ " ";
		return result;
	}
	
	public String getFuncao(FuncaoSintatica funcao, String n){
		
		if (funcao.equals(FuncaoSintatica.IDOMSNUMBER))
			return funcao.toString().replace("N-ÉSIMO", n_esimo(n));
		else if (funcao.equals(FuncaoSintatica.DOMSWORDS)|| funcao.equals(FuncaoSintatica.DOMSWORDSMAIOR)||
				funcao.equals(FuncaoSintatica.IDOMSTOTAL) || funcao.equals(FuncaoSintatica.DOMSWORDSMENOR)||
				funcao.equals(FuncaoSintatica.IDOMSTOTALMAIOR) || funcao.equals(FuncaoSintatica.IDOMSTOTALMENOR))
			return funcao.toString().replace(" N ", " "+n+" ");
		else
			return funcao.toString();
	}
	
	public String n_esimo(String n){
		if (n.equals("1"))
			return "PRIMEIRO";
		else if (n.equals("2"))
			return "SEGUNDO"; 
		else if (n.equals("3"))
			return "TERCEIRO"; 
		else if (n.equals("4"))
			return "QUARTO"; 
		else if (n.equals("5"))
			return "QUINTO"; 
		else if (n.equals("6"))
			return "SEXTO"; 
		else if (n.equals("7"))
			return "SÉTIMO"; 
		else if (n.equals("8"))
			return "OITAVO"; 
		else if (n.equals("9"))
			return "NONO"; 
		else if (n.equals("10"))
			return "DÉCIMO"; 
		else
			return "N-ÉSIMO";
	}

	public String montaConsulta(List<ItemConsulta> consultaLista1, List<ItemConsulta> consultaLista2,
			String operacaoLogica1, String operacaoLogica2,
		FuncaoSintatica funcao, boolean negacao, String n){
		
		String result= "";
		result=montaConsulta(consultaLista1,operacaoLogica1);
		
		if (funcao != null)
		{
				if (negacao==true)
					result += "<span class=\"operacao\">NÃO "
							+ getFuncao(funcao,n) + "</span> ";
				else
					result += "<span class=\"operacao\">"
						+ getFuncao(funcao,n) + "</span> ";
		
		if (funcao.equals(FuncaoSintatica.DOMINATES)||funcao.equals(FuncaoSintatica.IDOMINATES)||
				funcao.equals(FuncaoSintatica.EIRMAO)|| funcao.equals(FuncaoSintatica.IDOMSFIRST)||
				funcao.equals(FuncaoSintatica.IDOMSLAST)|| funcao.equals(FuncaoSintatica.IDOMSNUMBER)||
				funcao.equals(FuncaoSintatica.CCOMMANDS)||
				funcao.equals(FuncaoSintatica.IPRECEDES)||funcao.equals(FuncaoSintatica.PRECEDES))
			
			result+=montaConsulta(consultaLista2,operacaoLogica2);
		}
		return result;
	}
	
	public void montaConsulta() {
				
		consulta = montaConsulta(consultaListaSintatica1,consultaListaSintatica2,
				selectedOperacaoLogica1, selectedOperacaoLogica2,selectedFuncao1,operacaoNegacao1, argumentoN1);
		if (selectedFuncao2!=null)
			consulta += " E "+ montaConsulta(consultaListaSintatica2,consultaListaSintatica3,
					selectedOperacaoLogica2, selectedOperacaoLogica3,selectedFuncao2,operacaoNegacao2, argumentoN2);
		if (selectedFuncao3!=null)
			consulta += " E "+ montaConsulta(consultaListaSintatica3,consultaListaSintatica4,
					selectedOperacaoLogica3, selectedOperacaoLogica4,selectedFuncao3,operacaoNegacao3, argumentoN3);
		if (selectedFuncao5!=null)
			consulta += " E "+ montaConsulta(consultaListaSintatica5,consultaListaSintatica6,
					selectedOperacaoLogica5, selectedOperacaoLogica6,selectedFuncao5,operacaoNegacao5, argumentoN5);
		
	
	}

	
	
	public void selectFuncao1() {
		if (selectedFuncao1.equals(FuncaoSintatica.EXISTS) || selectedFuncao1.equals(FuncaoSintatica.DOMSWORDS) ||
				selectedFuncao1.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
				selectedFuncao1.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
				selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTAL) ||
				selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
				selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarLista2 = false;

		else
			mostrarLista2 = true;

		if (selectedFuncao1.equals(FuncaoSintatica.DOMSWORDS) || 
			selectedFuncao1.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
			selectedFuncao1.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
			selectedFuncao1.equals(FuncaoSintatica.IDOMSNUMBER) ||
			selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTAL) ||
			selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
			selectedFuncao1.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarValorN1 = true;

		else
			mostrarValorN1  = false;
		montaConsulta();
		
	}
	
	
	public void selectFuncao2() {
		if (selectedFuncao2.equals(FuncaoSintatica.EXISTS) || selectedFuncao2.equals(FuncaoSintatica.DOMSWORDS) ||
				selectedFuncao2.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
				selectedFuncao2.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
				selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTAL) ||
				selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
				selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarLista3 = false;

		else
			mostrarLista3 = true;

		if (selectedFuncao2.equals(FuncaoSintatica.DOMSWORDS) || 
			selectedFuncao2.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
			selectedFuncao2.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
			selectedFuncao2.equals(FuncaoSintatica.IDOMSNUMBER) ||
			selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTAL) ||
			selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
			selectedFuncao2.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarValorN2 = true;

		else
			mostrarValorN2  = false;
		montaConsulta();
		
	}

	
	public void selectFuncao3() {
		if (selectedFuncao3.equals(FuncaoSintatica.EXISTS) || selectedFuncao3.equals(FuncaoSintatica.DOMSWORDS) ||
				selectedFuncao3.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
				selectedFuncao3.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
				selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTAL) ||
				selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
				selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarLista4 = false;

		else
			mostrarLista4 = true;

		if (selectedFuncao3.equals(FuncaoSintatica.DOMSWORDS) || 
			selectedFuncao3.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
			selectedFuncao3.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
			selectedFuncao3.equals(FuncaoSintatica.IDOMSNUMBER) ||
			selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTAL) ||
			selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
			selectedFuncao3.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarValorN3 = true;

		else
			mostrarValorN3  = false;
		montaConsulta();
		
	}
	
	public void selectFuncao5() {
		if (selectedFuncao5.equals(FuncaoSintatica.EXISTS) || selectedFuncao5.equals(FuncaoSintatica.DOMSWORDS) ||
				selectedFuncao5.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
				selectedFuncao5.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
				selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTAL) ||
				selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
				selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarLista6 = false;

		else
			mostrarLista6 = true;

		if (selectedFuncao5.equals(FuncaoSintatica.DOMSWORDS) || 
			selectedFuncao5.equals(FuncaoSintatica.DOMSWORDSMENOR) ||
			selectedFuncao5.equals(FuncaoSintatica.DOMSWORDSMAIOR) ||
			selectedFuncao5.equals(FuncaoSintatica.IDOMSNUMBER) ||
			selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTAL) ||
			selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTALMENOR) ||
			selectedFuncao5.equals(FuncaoSintatica.IDOMSTOTALMAIOR) 	)
			mostrarValorN5 = true;

		else
			mostrarValorN5  = false;
		montaConsulta();
		
	}
	
	public String getFuncaoTotal2(){
		if ((operacaoNegacao1==true) && (selectedFuncao1!=null))
				return "NÃO " +selectedFuncao1.toString();
		if ((operacaoNegacao1==false) && (selectedFuncao1!=null))
			return selectedFuncao1.toString();
		return "";
		
	}
	
	public String getFuncaoTotal3(){
		if ((operacaoNegacao2==true) && (selectedFuncao2!=null))
				return "NÃO " +selectedFuncao2.toString();
		if ((operacaoNegacao2==false) && (selectedFuncao2!=null))
			return selectedFuncao2.toString();
		return "";
		
	}
	
	public String getFuncaoTotal4(){
		if ((operacaoNegacao3==true) && (selectedFuncao3!=null))
				return "NÃO " +selectedFuncao3.toString();
		if ((operacaoNegacao3==false) && (selectedFuncao3!=null))
			return selectedFuncao3.toString();
		return "";
		
	}
	
	
	public String getFuncaoTotal6(){
		if ((operacaoNegacao5==true) && (selectedFuncao5!=null))
				return "NÃO " +selectedFuncao5.toString();
		if ((operacaoNegacao5==false) && (selectedFuncao5!=null))
			return selectedFuncao5.toString();
		return "";
		
	}



	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}



	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}



	public List<ItemConsulta> getConsultaListaSintatica1() {
		return consultaListaSintatica1;
	}



	public void setConsultaListaSintatica1(
			List<ItemConsulta> consultaListaSintatica1) {
		this.consultaListaSintatica1 = consultaListaSintatica1;
	}



	public List<ItemConsulta> getConsultaListaSintatica2() {
		return consultaListaSintatica2;
	}



	public void setConsultaListaSintatica2(
			List<ItemConsulta> consultaListaSintatica2) {
		this.consultaListaSintatica2 = consultaListaSintatica2;
	}



	public List<ItemConsulta> getConsultaListaSintatica3() {
		return consultaListaSintatica3;
	}



	public void setConsultaListaSintatica3(
			List<ItemConsulta> consultaListaSintatica3) {
		this.consultaListaSintatica3 = consultaListaSintatica3;
	}



	public List<ItemConsulta> getConsultaListaSintatica4() {
		return consultaListaSintatica4;
	}



	public void setConsultaListaSintatica4(
			List<ItemConsulta> consultaListaSintatica4) {
		this.consultaListaSintatica4 = consultaListaSintatica4;
	}



	public List<ItemConsulta> getConsultaListaSintatica5() {
		return consultaListaSintatica5;
	}



	public void setConsultaListaSintatica5(
			List<ItemConsulta> consultaListaSintatica5) {
		this.consultaListaSintatica5 = consultaListaSintatica5;
	}



	public List<ItemConsulta> getConsultaListaSintatica6() {
		return consultaListaSintatica6;
	}



	public void setConsultaListaSintatica6(
			List<ItemConsulta> consultaListaSintatica6) {
		this.consultaListaSintatica6 = consultaListaSintatica6;
	}



	public List<SelectItem> getListaFuncoes() {
		return listaFuncoes;
	}



	public void setListaFuncoes(List<SelectItem> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
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



	public FuncaoSintatica getSelectedFuncao1() {
		return selectedFuncao1;
	}



	public void setSelectedFuncao1(FuncaoSintatica selectedFuncao1) {
		this.selectedFuncao1 = selectedFuncao1;
	}



	public FuncaoSintatica getSelectedFuncao2() {
		return selectedFuncao2;
	}



	public void setSelectedFuncao2(FuncaoSintatica selectedFuncao2) {
		this.selectedFuncao2 = selectedFuncao2;
	}



	public FuncaoSintatica getSelectedFuncao3() {
		return selectedFuncao3;
	}



	public void setSelectedFuncao3(FuncaoSintatica selectedFuncao3) {
		this.selectedFuncao3 = selectedFuncao3;
	}



	public FuncaoSintatica getSelectedFuncao5() {
		return selectedFuncao5;
	}



	public void setSelectedFuncao5(FuncaoSintatica selectedFuncao5) {
		this.selectedFuncao5 = selectedFuncao5;
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



	public String getArgumentoN5() {
		return argumentoN5;
	}



	public void setArgumentoN5(String argumentoN5) {
		this.argumentoN5 = argumentoN5;
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



	public boolean isMostrarValorN5() {
		return mostrarValorN5;
	}



	public void setMostrarValorN5(boolean mostrarValorN5) {
		this.mostrarValorN5 = mostrarValorN5;
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



	public boolean isOperacaoNegacao5() {
		return operacaoNegacao5;
	}



	public void setOperacaoNegacao5(boolean operacaoNegacao5) {
		this.operacaoNegacao5 = operacaoNegacao5;
	}



	public String getResultadoBuscaSintatica() {
		return resultadoBuscaSintatica;
	}



	public void setResultadoBuscaSintatica(String resultadoBuscaSintatica) {
		this.resultadoBuscaSintatica = resultadoBuscaSintatica;
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
		result = prime
				* result
				+ ((consultaListaSintatica1 == null) ? 0
						: consultaListaSintatica1.hashCode());
		result = prime
				* result
				+ ((consultaListaSintatica2 == null) ? 0
						: consultaListaSintatica2.hashCode());
		result = prime
				* result
				+ ((consultaListaSintatica3 == null) ? 0
						: consultaListaSintatica3.hashCode());
		result = prime
				* result
				+ ((consultaListaSintatica4 == null) ? 0
						: consultaListaSintatica4.hashCode());
		result = prime
				* result
				+ ((consultaListaSintatica5 == null) ? 0
						: consultaListaSintatica5.hashCode());
		result = prime
				* result
				+ ((consultaListaSintatica6 == null) ? 0
						: consultaListaSintatica6.hashCode());
		result = prime * result
				+ ((selectedFuncao1 == null) ? 0 : selectedFuncao1.hashCode());
		result = prime * result
				+ ((selectedFuncao2 == null) ? 0 : selectedFuncao2.hashCode());
		result = prime * result
				+ ((selectedFuncao3 == null) ? 0 : selectedFuncao3.hashCode());
		result = prime * result
				+ ((selectedFuncao5 == null) ? 0 : selectedFuncao5.hashCode());
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
		ControleBuscasSintaticas other = (ControleBuscasSintaticas) obj;
		if (consultaListaSintatica1 == null) {
			if (other.consultaListaSintatica1 != null)
				return false;
		} else if (!consultaListaSintatica1
				.equals(other.consultaListaSintatica1))
			return false;
		if (consultaListaSintatica2 == null) {
			if (other.consultaListaSintatica2 != null)
				return false;
		} else if (!consultaListaSintatica2
				.equals(other.consultaListaSintatica2))
			return false;
		if (consultaListaSintatica3 == null) {
			if (other.consultaListaSintatica3 != null)
				return false;
		} else if (!consultaListaSintatica3
				.equals(other.consultaListaSintatica3))
			return false;
		if (consultaListaSintatica4 == null) {
			if (other.consultaListaSintatica4 != null)
				return false;
		} else if (!consultaListaSintatica4
				.equals(other.consultaListaSintatica4))
			return false;
		if (consultaListaSintatica5 == null) {
			if (other.consultaListaSintatica5 != null)
				return false;
		} else if (!consultaListaSintatica5
				.equals(other.consultaListaSintatica5))
			return false;
		if (consultaListaSintatica6 == null) {
			if (other.consultaListaSintatica6 != null)
				return false;
		} else if (!consultaListaSintatica6
				.equals(other.consultaListaSintatica6))
			return false;
		if (selectedFuncao1 != other.selectedFuncao1)
			return false;
		if (selectedFuncao2 != other.selectedFuncao2)
			return false;
		if (selectedFuncao3 != other.selectedFuncao3)
			return false;
		if (selectedFuncao5 != other.selectedFuncao5)
			return false;
		return true;
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
