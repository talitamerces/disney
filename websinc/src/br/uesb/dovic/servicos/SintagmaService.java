package br.uesb.dovic.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.uesb.dovic.beans.Etiqueta;
	
@ManagedBean(name="etiquetaservice", eager = true)
@ApplicationScoped	
public class SintagmaService {
	     
	    private List<Etiqueta> etiquetas;
	   
 
	    public  SintagmaService() {
	    	
	        EtiquetaService service= new EtiquetaService();
	        etiquetas= new ArrayList<Etiqueta>();
	        etiquetas.addAll(service.getEtiquetas());
	        etiquetas.add(new Etiqueta(new Integer(194), "Sintagma Nominal","NP"));
	        etiquetas.add(new Etiqueta(new Integer(195), "Sintagma Nominal - Objeto Indireto","NP-DAT"));
	        etiquetas.add(new Etiqueta(new Integer(196), "Sintagma Nominal - Objeto Direto","NP-ACC"));
	        etiquetas.add(new Etiqueta(new Integer(197), "Sintagma Nominal-Vocativo","NP-VOC"));
	        etiquetas.add(new Etiqueta(new Integer(198), "Sintagma Nominal-Genitivo","NP-GEN"));
	        etiquetas.add(new Etiqueta(new Integer(199), "Sintagma Nominal - Sujeito","NP-SBJ"));
	        etiquetas.add(new Etiqueta(new Integer(200), "Sintagma Nominal - Deslocado � Esquerda","NP-LFD"));
	        etiquetas.add(new Etiqueta(new Integer(201), "Sintagma Nominal -Adverbial","NP-ADV"));
	        etiquetas.add(new Etiqueta(new Integer(202), "Sintagma Nominal -Apositivo ou parent�tico","NP-PRN"));
	        etiquetas.add(new Etiqueta(new Integer(203), "Sintagma Nominal -Cl�ticos SE n�o argumental","NP-SE"));
	        etiquetas.add(new Etiqueta(new Integer(204), "Sintagma Preposicional","PP"));
	        etiquetas.add(new Etiqueta(new Integer(205), "Sintagma Preposicional - Objeto","PP-ACC"));
	        etiquetas.add(new Etiqueta(new Integer(206), "Sintagma Preposicional - Objeto Indireto","PP-DAT"));
	        etiquetas.add(new Etiqueta(new Integer(207), "Sintagma Preposicional - Deslocado � Esquerda","PP-LFD"));
	        etiquetas.add(new Etiqueta(new Integer(208), "Sintagma Preposicional - Parent�tico","PP-PRN"));
	        etiquetas.add(new Etiqueta(new Integer(209), "Sintagma Preposicional - Sujeito","PP-SBJ"));
	        etiquetas.add(new Etiqueta(new Integer(210), "Cl�usula adverbial","CP-ADV"));
	        etiquetas.add(new Etiqueta(new Integer(211), "Small Clause","IP-SMC"));
	        etiquetas.add(new Etiqueta(new Integer(212), "Senten�a matriz","IP-MAT"));
	        etiquetas.add(new Etiqueta(new Integer(213), "Senten�a encaixada - com QUE","CP-THT"));
	        etiquetas.add(new Etiqueta(new Integer(214), "Senten�a encaixada - com SE",""));
	        etiquetas.add(new Etiqueta(new Integer(215), "Senten�a encaixada - com QUE","CP-QUE"));
	        etiquetas.add(new Etiqueta(new Integer(216), "Yes/no question?","CP-QUE-SPE"));
	        etiquetas.add(new Etiqueta(new Integer(217), "Senten�a subordinada","IP-SUB"));
	        etiquetas.add(new Etiqueta(new Integer(218), "Senten�a infinitiva","IP-INF"));
	        etiquetas.add(new Etiqueta(new Integer(219), "Senten�a relativa","CP-REL"));
	        etiquetas.add(new Etiqueta(new Integer(220), "Senten�a relativa livre","CP-FRL"));
	        etiquetas.add(new Etiqueta(new Integer(221), "Senten�a no ger�ndio","IP-GER"));
	        etiquetas.add(new Etiqueta(new Integer(222), "Senten�a relativa adjungida","CP-CAR"));
	        etiquetas.add(new Etiqueta(new Integer(223), "Senten�a comparativa","CP-CMP"));
	        etiquetas.add(new Etiqueta(new Integer(224), "Senten�a com partic�pio","IP-PPL"));
	        etiquetas.add(new Etiqueta(new Integer(225), "PP-LOC","PP-LOC"));
	        etiquetas.add(new Etiqueta(new Integer(226), "Proje��o Adjetival","ADJP"));
	        etiquetas.add(new Etiqueta(new Integer(227), "Sintagma adverbial","ADVP"));
	        etiquetas.add(new Etiqueta(new Integer(228), "CP-EXL","CP-EXL"));
	        etiquetas.add(new Etiqueta(new Integer(229), "WNP","WNP"));
	        etiquetas.add(new Etiqueta(new Integer(230), "Sujeito nulo *pro*","*pro*"));
	        etiquetas.add(new Etiqueta(new Integer(231), "Sujeito nulo *exp*","*exp*"));
	        etiquetas.add(new Etiqueta(new Integer(232), "Cl�usula relativa reduzida","RRC"));
	        etiquetas.add(new Etiqueta(new Integer(233), "Degree Clauses","CP-DEG"));
	        etiquetas.add(new Etiqueta(new Integer(234), "IP-SUB-SPE","IP-SUB-SPE"));
	        etiquetas.add(new Etiqueta(new Integer(235), "Resposta curta","FRAG-ANS"));
	        etiquetas.add(new Etiqueta(new Integer(236), "CONJP","CONJP"));
	        etiquetas.add(new Etiqueta(new Integer(237), "Senten�a pseudo-clivada","CP-CLF"));
	        etiquetas.add(new Etiqueta(new Integer(238), "Sintagma Adjetival","AP"));
	        etiquetas.add(new Etiqueta(new Integer(239), "Senten�a encaixada","CP*", true));
	       



}
	    
	    public List<Etiqueta> getEtiquetas() {
	        return etiquetas;
	    }
	    
}
