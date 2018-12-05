package br.uesb.dovic.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import br.uesb.dovic.beans.Etiqueta;
	
@ManagedBean(name="themeService", eager = true)
@ApplicationScoped	
public class EtiquetaService {
	     
	    private List<Etiqueta> etiquetas;
	      
	   
	    public  EtiquetaService() {
	    	
	    	
	        etiquetas = new ArrayList<Etiqueta>();
	        etiquetas.add(new Etiqueta(new Integer(1), "ADJETIVO (Feminino plural)","ADJ-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(2), "ADJETIVO (Feminino singular)","ADJ-F"));
	        etiquetas.add(new Etiqueta(new Integer(3), "ADJETIVO (Gênero duplo plural)","ADJ-G-P"));
	        etiquetas.add(new Etiqueta(new Integer(4), "ADJETIVO (Gênero duplo singular)","ADJ-G"));
	        etiquetas.add(new Etiqueta(new Integer(5), "ADJETIVO (Masculino plural)","ADJ-P"));
	        etiquetas.add(new Etiqueta(new Integer(6), "ADJETIVO (Masculino singular)","ADJ"));
	        etiquetas.add(new Etiqueta(new Integer(7), "ADJETIVO COMPARATIVO","ADJ-R-G"));
	        etiquetas.add(new Etiqueta(new Integer(8), "ADJETIVO COMPARATIVO (tanta)","ADJ-R-F"));
	        etiquetas.add(new Etiqueta(new Integer(9), "ADJETIVO COMPARATIVO (tantas)","ADJ-R-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(10), "ADJETIVO COMPARATIVO (tanto)","ADJ-R"));
	        etiquetas.add(new Etiqueta(new Integer(11), "ADJETIVO COMPARATIVO (tantos)","ADJ-R-P"));
	        etiquetas.add(new Etiqueta(new Integer(12), "ADJETIVO SUPERLATIVO  (Masculino plural)","ADJ-S-P"));
	        etiquetas.add(new Etiqueta(new Integer(13), "ADJETIVO SUPERLATIVO (Feminino plural)","ADJ-S-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(14), "ADJETIVO SUPERLATIVO (Feminino singular)","ADJ-S-F"));
	        etiquetas.add(new Etiqueta(new Integer(15), "ADJETIVO SUPERLATIVO (Masculino singular)","ADJ-S"));
	        etiquetas.add(new Etiqueta(new Integer(16), "ADVÉRBIO","ADV"));
	        etiquetas.add(new Etiqueta(new Integer(17), "ADVÉRBIO EXCLAMATIVO/COMPARATIVO","ADV-R"));
	        etiquetas.add(new Etiqueta(new Integer(18), "ADVÉRBIO NEGATIVO","ADV-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(19), "ADVÉRBIO SUPERLATIVO","ADV-S"));
	        etiquetas.add(new Etiqueta(new Integer(20), "CLÍTICO (Contração)","CL+CL"));
	        etiquetas.add(new Etiqueta(new Integer(21), "CLÍTICO (Geral)","CL"));
	        etiquetas.add(new Etiqueta(new Integer(22), "CLÍTICO \"SE\"","SE"));
	        etiquetas.add(new Etiqueta(new Integer(23), "COMPLEMENTADOR \"que\"","C"));
	        etiquetas.add(new Etiqueta(new Integer(24), "CONJUNÇÃO","CONJ"));
	        etiquetas.add(new Etiqueta(new Integer(25), "CONJUNÇÃO NEGATIVA","CONJ-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(26), "CONJUNÇÃO SUBORDINADA","CONJS"));
	        etiquetas.add(new Etiqueta(new Integer(27), "DEMONSTRATIVO","DEM"));
	        etiquetas.add(new Etiqueta(new Integer(28), "DETERMINANTE (Gênero duplo plural)","D-G-P"));
	        etiquetas.add(new Etiqueta(new Integer(29), "DETERMINANTE (Gênero duplo singular)","D-G"));
	        etiquetas.add(new Etiqueta(new Integer(30), "DETERMINANTE DEFINIDO (Feminino Plural)","D-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(31), "DETERMINANTE DEFINIDO (Masculino Plural)","D-P"));
	        etiquetas.add(new Etiqueta(new Integer(32), "DETERMINANTE DEFINIDO (Masculino Singular)","D"));
	        etiquetas.add(new Etiqueta(new Integer(33), "DETERMINANTE DEFINIDO(Feminino Singular)","D-F"));
	        etiquetas.add(new Etiqueta(new Integer(34), "DETERMINANTE EXCLAMATIVO/INTERROGATIVO","WD"));
	        etiquetas.add(new Etiqueta(new Integer(35), "DETERMINANTE EXCLAMATIVO/INTERROGATIVO (Feminino Plural)","WD-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(36), "DETERMINANTE EXCLAMATIVO/INTERROGATIVO (Feminino Singular)","WD-F"));
	        etiquetas.add(new Etiqueta(new Integer(37), "DETERMINANTE EXCLAMATIVO/INTERROGATIVO (Masculino Plural)","WD-P"));
	        etiquetas.add(new Etiqueta(new Integer(38), "DETERMINANTE INDEFINIDO  (Feminino Plural)","D-UM-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(39), "DETERMINANTE INDEFINIDO (Feminino Singular)","D-UM-F"));
	        etiquetas.add(new Etiqueta(new Integer(40), "DETERMINANTE INDEFINIDO (Masculino Plural)","D-UM-P"));
	        etiquetas.add(new Etiqueta(new Integer(41), "DETERMINANTE INDEFINIDO (Masculino Singular)","D-UM"));
	        etiquetas.add(new Etiqueta(new Integer(42), "ELEMENTO RELATIVO \"Como\"|\"Onde\"|\"Quanto\"","WADV"));
	        etiquetas.add(new Etiqueta(new Integer(43), "ELEMENTO RELATIVO \"Cuja\" (Feminino-Singular)","WPRO$-F"));
	        etiquetas.add(new Etiqueta(new Integer(44), "ELEMENTO RELATIVO \"Cujas\" (Feminino-Plural)","WPRO$-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(45), "ELEMENTO RELATIVO \"Cujo\" (Masculino-Singular)","WPRO$"));
	        etiquetas.add(new Etiqueta(new Integer(46), "ELEMENTO RELATIVO \"Cujos\" (Masculino-Plural)","WPRO$-P"));
	        etiquetas.add(new Etiqueta(new Integer(47), "ELEMENTO RELATIVO \"Quais\"|\"Quantos\" (Plural)","WPRO-P"));
	        etiquetas.add(new Etiqueta(new Integer(48), "ELEMENTO RELATIVO \"Quantas\" (Feminino-Plural)","WPRO-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(49), "ELEMENTO RELATIVO \"Que\"|\"Qual\" (Singular)","WPRO"));
	        etiquetas.add(new Etiqueta(new Integer(50), "ESTAR (Flexionado com morfema -ra)","ET-RA"));
	        etiquetas.add(new Etiqueta(new Integer(51), "ESTAR (Futuro -  condicional)","ET-R"));
	        etiquetas.add(new Etiqueta(new Integer(52), "ESTAR (Futuro do Subjuntivo)","ET-SR"));
	        etiquetas.add(new Etiqueta(new Integer(53), "ESTAR (Gerúndio)","ET-G"));
	        etiquetas.add(new Etiqueta(new Integer(54), "ESTAR (Imperativo)","ET-I"));
	        etiquetas.add(new Etiqueta(new Integer(55), "ESTAR (Infinitivo flexionado)","ET-F"));
	        etiquetas.add(new Etiqueta(new Integer(56), "ESTAR (Particípio Passado)","ET-PP"));
	        etiquetas.add(new Etiqueta(new Integer(57), "ESTAR (Passado do Subjuntivo)","ET-SP"));
	        etiquetas.add(new Etiqueta(new Integer(58), "ESTAR (Passado do Subjuntivo)","ET-SD"));
	        etiquetas.add(new Etiqueta(new Integer(59), "ESTAR (Passado)","ET-D"));
	        etiquetas.add(new Etiqueta(new Integer(60), "ESTAR (Presente)","ET-P"));
	        etiquetas.add(new Etiqueta(new Integer(61), "ESTAR(Infinitivo)","ET"));
	        etiquetas.add(new Etiqueta(new Integer(62), "ESTAR + MESÓCLISE","ET-R!CL"));
	        etiquetas.add(new Etiqueta(new Integer(63), "HAVER (Flexionado com morfema -ra)","HV-RA"));
	        etiquetas.add(new Etiqueta(new Integer(64), "HAVER (Futuro -  condicional)","HV-R"));
	        etiquetas.add(new Etiqueta(new Integer(65), "HAVER (Futuro do Subjuntivo)","HV-SR"));
	        etiquetas.add(new Etiqueta(new Integer(66), "HAVER (Gerúndio)","HV-G"));
	        etiquetas.add(new Etiqueta(new Integer(67), "HAVER (Imperativo)","HV-I"));
	        etiquetas.add(new Etiqueta(new Integer(68), "HAVER (Infinitivo flexionado)","HV-F"));
	        etiquetas.add(new Etiqueta(new Integer(69), "HAVER (Infinitivo)","HV"));
	        etiquetas.add(new Etiqueta(new Integer(70), "HAVER (Particípio Passado)","HV-PP"));
	        etiquetas.add(new Etiqueta(new Integer(71), "HAVER (Particípio Passivo)","HV-NA"));
	        etiquetas.add(new Etiqueta(new Integer(72), "HAVER (Passado do Subjuntivo)","HV-SP"));
	        etiquetas.add(new Etiqueta(new Integer(73), "HAVER (Passado do Subjuntivo)","HV-SD"));
	        etiquetas.add(new Etiqueta(new Integer(74), "HAVER (Passado)","HV-D"));
	        etiquetas.add(new Etiqueta(new Integer(75), "HAVER (Presente)","HV-P"));
	        etiquetas.add(new Etiqueta(new Integer(76), "HAVER + MESÓCLISE","HV-R!CL"));
	        etiquetas.add(new Etiqueta(new Integer(77), "INTERJEIÇÃO","INTJ"));
	        etiquetas.add(new Etiqueta(new Integer(78), "NEGAÇÃO \"Nada\"|\"Ninguém\"|\"Nenhum\"","Q-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(79), "NEGAÇÃO \"Não\"","NEG"));
	        etiquetas.add(new Etiqueta(new Integer(80), "NEGAÇÃO \"Nem\"","CONJ-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(81), "NEGAÇÃO \"Nenhuma\"","Q-NEG-F"));
	        etiquetas.add(new Etiqueta(new Integer(82), "NEGAÇÃO \"Nenhumas\"","Q-NEG-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(83), "NEGAÇÃO \"Nenhuns\"","Q-NEG-P"));
	        etiquetas.add(new Etiqueta(new Integer(84), "NEGAÇÃO \"Nunca\"","ADV-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(85), "NEGAÇÃO \"Senão\"","SENAO"));
	        etiquetas.add(new Etiqueta(new Integer(86), "NOME (Plural)","N-P"));
	        etiquetas.add(new Etiqueta(new Integer(87), "NOME (Singular)","N"));
	        etiquetas.add(new Etiqueta(new Integer(88), "NOME PRÓPRIO (Plural)","NPR-P"));
	        etiquetas.add(new Etiqueta(new Integer(89), "NOME PRÓPRIO (Singular)","NPR"));
	        etiquetas.add(new Etiqueta(new Integer(90), "NÚMERO CARDINAL","NUM"));
	        etiquetas.add(new Etiqueta(new Integer(91), "NÚMERO CARDINAL (Feminino)","NUM-F"));
	        etiquetas.add(new Etiqueta(new Integer(92), "\"Outra\"","OUTRO-F"));
	        etiquetas.add(new Etiqueta(new Integer(93), "\"Outras\"","OUTRO-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(94), "\"Outro\"","OUTRO"));
	        etiquetas.add(new Etiqueta(new Integer(95), "\"Outros\"","OUTRO-P"));
	        etiquetas.add(new Etiqueta(new Integer(96), "PALAVRA DESCONHECIDA","XX"));
	        etiquetas.add(new Etiqueta(new Integer(97), "PALAVRA ESPECÍFICA","LEAF"));
	        etiquetas.add(new Etiqueta(new Integer(98), "PALAVRA ESTRANGEIRA","FW"));
	        etiquetas.add(new Etiqueta(new Integer(99), "PALAVRA INTERROGATIVA","WQ"));
	        etiquetas.add(new Etiqueta(new Integer(100), "PARTÍCULA DE FOCO","FP"));
	        etiquetas.add(new Etiqueta(new Integer(101), "PREPOSIÇÃO","P"));
	        etiquetas.add(new Etiqueta(new Integer(102), "PREPOSIÇÃO + \"algum\"","P+Q"));
	        etiquetas.add(new Etiqueta(new Integer(103), "PREPOSIÇÃO + \"alguma\"","P+Q-F"));
	        etiquetas.add(new Etiqueta(new Integer(104), "PREPOSIÇÃO + \"algumas\"","P+Q-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(105), "PREPOSIÇÃO + \"alguns\"","P+Q-P"));
	        etiquetas.add(new Etiqueta(new Integer(106), "PREPOSIÇÃO + \"outra\"","P+OUTRO-F"));
	        etiquetas.add(new Etiqueta(new Integer(107), "PREPOSIÇÃO + \"outras\"","P+OUTRO-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(108), "PREPOSIÇÃO + \"outro\"","P+OUTRO"));
	        etiquetas.add(new Etiqueta(new Integer(109), "PREPOSIÇÃO + \"outros\"","P+OUTRO-P"));
	        etiquetas.add(new Etiqueta(new Integer(110), "PREPOSIÇÃO + ADVÉRBIO","P+ADV"));
	        etiquetas.add(new Etiqueta(new Integer(111), "PREPOSIÇÃO + CLÍTICO","P+CL"));
	        etiquetas.add(new Etiqueta(new Integer(112), "PREPOSIÇÃO + DEMONSTRATIVO","P+DEM"));
	        etiquetas.add(new Etiqueta(new Integer(113), "PREPOSIÇÃO + DETERMINANTE (Definido Feminino plural)","P+D-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(114), "PREPOSIÇÃO + DETERMINANTE (Definido Feminino singular)","P+D-F"));
	        etiquetas.add(new Etiqueta(new Integer(115), "PREPOSIÇÃO + DETERMINANTE (Definido masculino plural)","P+D-P"));
	        etiquetas.add(new Etiqueta(new Integer(116), "PREPOSIÇÃO + DETERMINANTE (Definido masculino singular)","P+D"));
	        etiquetas.add(new Etiqueta(new Integer(117), "PREPOSIÇÃO + DETERMINANTE (Indefinido feminino singular)","P+D-UM-F"));
	        etiquetas.add(new Etiqueta(new Integer(118), "PREPOSIÇÃO + DETERMINANTE (Indefinido feminino splural)","P+D-UM-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(119), "PREPOSIÇÃO + DETERMINANTE (Indefinido masculino plural)","P+D-UM-P"));
	        etiquetas.add(new Etiqueta(new Integer(120), "PREPOSIÇÃO + DETERMINANTE (Indefinido masculino singular)","P+D-UM"));
	        etiquetas.add(new Etiqueta(new Integer(121), "PREPOSIÇÃO + NOME PRÓPRIO","P+NPR"));
	        etiquetas.add(new Etiqueta(new Integer(122), "PREPOSIÇÃO + PRONOME POSSESSIVO","P+PRO"));
	        etiquetas.add(new Etiqueta(new Integer(123), "PRESPOSIÇÃO + \"que\" (\"porque\" em interrogativas)","P+WPRO"));
	        etiquetas.add(new Etiqueta(new Integer(124), "PRONOME","PRO"));
	        etiquetas.add(new Etiqueta(new Integer(125), "PRONOME (Preposição+oblíquo)","P+PRO"));
	        etiquetas.add(new Etiqueta(new Integer(126), "PRONOME POSSESSIVO (Feminino Plural)","PRO$-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(127), "PRONOME POSSESSIVO (Feminino Singular)","PROS-F"));
	        etiquetas.add(new Etiqueta(new Integer(128), "PRONOME POSSESSIVO (Masculino Plural)","PRO$-P"));
	        etiquetas.add(new Etiqueta(new Integer(129), "PRONOME POSSESSIVO (Masculino Singular)","PRO$"));
	        etiquetas.add(new Etiqueta(new Integer(130), "QUANTIFICADOR","Q"));
	        etiquetas.add(new Etiqueta(new Integer(131), "QUANTIFICADOR (Feminino plural)","Q-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(132), "QUANTIFICADOR (Feminino)","Q-F"));
	        etiquetas.add(new Etiqueta(new Integer(133), "QUANTIFICADOR (Plural)","Q-P"));
	        etiquetas.add(new Etiqueta(new Integer(134), "QUANTIFICADOR \"Cada\"|\"Qualquer\"","Q-G"));
	        etiquetas.add(new Etiqueta(new Integer(135), "QUANTIFICADOR \"Quaisquer\"","Q-G-P"));
	        etiquetas.add(new Etiqueta(new Integer(136), "QUANTIFICADOR NEGATIVO","Q-NEG"));
	        etiquetas.add(new Etiqueta(new Integer(137), "QUANTIFICADOR NEGATIVO (Feminino plural)","Q-NEG-F-P"));
	        etiquetas.add(new Etiqueta(new Integer(138), "QUANTIFICADOR NEGATIVO (Feminino)","Q-NEG-F"));
	        etiquetas.add(new Etiqueta(new Integer(139), "QUANTIFICADOR NEGATIVO (Plural)","Q-NEG-P"));
	        etiquetas.add(new Etiqueta(new Integer(140), "SER (com morfema -RA)","SR-RA"));
	        etiquetas.add(new Etiqueta(new Integer(141), "SER (Futuro - Condicional)","SR-R"));
	        etiquetas.add(new Etiqueta(new Integer(142), "SER (Futuro do Subjuntivo)","SR-SR"));
	        etiquetas.add(new Etiqueta(new Integer(143), "SER (Gerúndio)","SR-G"));
	        etiquetas.add(new Etiqueta(new Integer(144), "SER (Imperativo)","SR-I"));
	        etiquetas.add(new Etiqueta(new Integer(145), "SER (Infinitivo flexionado)","SR-F"));
	        etiquetas.add(new Etiqueta(new Integer(146), "SER (Infinitivo)","SR"));
	        etiquetas.add(new Etiqueta(new Integer(147), "SER (Particípio Passado)","SR-PP"));
	        etiquetas.add(new Etiqueta(new Integer(148), "SER (Passado do Subjuntivo)","SR-SD"));
	        etiquetas.add(new Etiqueta(new Integer(149), "SER (Presente do Subjuntivo)","SR-SP"));
	        etiquetas.add(new Etiqueta(new Integer(150), "SER (Presente)","SR-P"));
	        etiquetas.add(new Etiqueta(new Integer(151), "SER + MESÓCLISE","SR-R!CL"));
	        etiquetas.add(new Etiqueta(new Integer(152), "SER (Todas as formas)","SR*", true));
	        etiquetas.add(new Etiqueta(new Integer(153), "TER (Flexionado com morfema -ra)","TR-RA"));
	        etiquetas.add(new Etiqueta(new Integer(154), "TER (Futuro -  condicional)","TR-R"));
	        etiquetas.add(new Etiqueta(new Integer(155), "TER (Futuro do Subjuntivo)","TR-SR"));
	        etiquetas.add(new Etiqueta(new Integer(156), "TER (Gerúndio)","TR-G"));
	        etiquetas.add(new Etiqueta(new Integer(157), "TER (Imperativo)","TR-I"));
	        etiquetas.add(new Etiqueta(new Integer(158), "TER (Infinitivo flexionado)","TR-F"));
	        etiquetas.add(new Etiqueta(new Integer(159), "TER (Infinitivo)","TR"));
	        etiquetas.add(new Etiqueta(new Integer(160), "TER (Particípio Passado)","TR-PP"));
	        etiquetas.add(new Etiqueta(new Integer(161), "TER (Particípio Passivo)","TR-NA"));
	        etiquetas.add(new Etiqueta(new Integer(162), "TER (Passado do Subjuntivo)","TR-SD"));
	        etiquetas.add(new Etiqueta(new Integer(163), "TER (Passado do Subjuntivo)","TR-SD"));
	        etiquetas.add(new Etiqueta(new Integer(164), "TER (Passado)","TR-D"));
	        etiquetas.add(new Etiqueta(new Integer(165), "TER (Presente)","TR-P"));
	        etiquetas.add(new Etiqueta(new Integer(166), "TER + MESÓCLISE","TR-R!CL"));
	        etiquetas.add(new Etiqueta(new Integer(167), "TER (Todas as formas)","TR*", true));
	        etiquetas.add(new Etiqueta(new Integer(168), "VERBO (com morfema -RA)","VB-RA"));
	        etiquetas.add(new Etiqueta(new Integer(169), "VERBO (com morfema -RA) + CLÍTICO","VB-RA+CL"));
	        etiquetas.add(new Etiqueta(new Integer(170), "VERBO (Futuro - Condicional)","VB-R"));
	        etiquetas.add(new Etiqueta(new Integer(171), "VERBO (Futuro - Condicional) + CLÍTICO","VB-R+CL"));
	        etiquetas.add(new Etiqueta(new Integer(172), "VERBO (Futuro do Subjuntivo)","VB-SR"));
	        etiquetas.add(new Etiqueta(new Integer(173), "VERBO (Futuro do Subjuntivo)  + CLÍTICO","VB-SR+CL"));
	        etiquetas.add(new Etiqueta(new Integer(174), "VERBO (Gerúndio)","VB-G"));
	        etiquetas.add(new Etiqueta(new Integer(175), "VERBO (Gerúndio)  + CLÍTICO","VB-G+CL"));
	        etiquetas.add(new Etiqueta(new Integer(176), "VERBO (Imperativo)","VB-I"));
	        etiquetas.add(new Etiqueta(new Integer(177), "VERBO (Imperativo)  + CLÍTICO","VB-I+CL"));
	        etiquetas.add(new Etiqueta(new Integer(178), "VERBO (Infinitivo flexionado)","VB-F"));
	        etiquetas.add(new Etiqueta(new Integer(179), "VERBO (Infinitivo flexionado) + CLÍTICO","VB-F+CL"));
	        etiquetas.add(new Etiqueta(new Integer(180), "VERBO (Infinitivo)","VB"));
	        etiquetas.add(new Etiqueta(new Integer(181), "VERBO (Infinitivo) + CLÍTICO","VB+CL"));
	        etiquetas.add(new Etiqueta(new Integer(182), "VERBO (Infinitivo) + MESÓCLISE","VB-R!CL"));
	        etiquetas.add(new Etiqueta(new Integer(183), "VERBO (Particípio Passado)","VB-PP"));
	        etiquetas.add(new Etiqueta(new Integer(184), "VERBO (Particípio Passivo)","VB-NA"));
	        etiquetas.add(new Etiqueta(new Integer(185), "VERBO (Passado do Subjuntivo)","VB-SD"));
	        etiquetas.add(new Etiqueta(new Integer(186), "VERBO (Passado do Subjuntivo) + CLÍTICO","VB-SD+CL"));
	        etiquetas.add(new Etiqueta(new Integer(187), "VERBO (Passado)","VB-D"));
	        etiquetas.add(new Etiqueta(new Integer(188), "VERBO (Presente do Subjuntivo)","VB-SP"));
	        etiquetas.add(new Etiqueta(new Integer(189), "VERBO (Presente do Subjuntivo) + CLÍTICO","VB-SP+CL"));
	        etiquetas.add(new Etiqueta(new Integer(190), "VERBO (Presente)","VB-P"));
	        etiquetas.add(new Etiqueta(new Integer(191), "VERBO (Presente) + CLÍTICO","VB-P+CL"));
	        etiquetas.add(new Etiqueta(new Integer(192), "VERBO (Todas as formas)","VB*", true));
	        etiquetas.add(new Etiqueta(new Integer(193), "VERBO (Flexionado)","VB-*", true));






	    }
	    
	    
	    public List<SelectItem> getGrupos(){
	    	List<SelectItem> categories = new ArrayList<SelectItem>();
//	        SelectItemGroup group1 = new SelectItemGroup("Verbo SER");
//	        SelectItemGroup group2 = new SelectItemGroup("Verbo HAVER");
//	        
//	         
//	        SelectItemGroup group11 = new SelectItemGroup("Subgrupo SER");
//	        
//	         
//	      
//	         
//	        SelectItem option21 = new SelectItem(new Etiqueta(new Integer(24), "HAVER (Particípio Passivo)","HV-NA"), "HAVER (Particípio Passivo)","HV-NA");
//	        SelectItem option22 = new SelectItem(new Etiqueta(new Integer(23), "HAVER (Particípio Passado)","HV-PP"),"HAVER (Particípio Passado)");
//	         
//	       
//	         
//	       
//	        SelectItem option112 = new SelectItem(new Etiqueta(new Integer(0), "Verbo (Qualquer verbo)","VB"),"teste verbo ser");
//	        SelectItem option113 = new SelectItem(new Etiqueta(new Integer(1), "SER (Infinitivo)","SR"),"SER (Infinitivo)");
//	        group11.setSelectItems(new SelectItem[]{option112, option113});
//	         
//	        group2.setSelectItems(new SelectItem[]{option21, option22});
//	        group1.setSelectItems(new SelectItem[]{group11});
//	        
//	         
//	       
//	         
//	        categories.add(group1);
//	        categories.add(group2);
	       
	    	  SelectItemGroup group1 = new SelectItemGroup("Group 1");
	          SelectItemGroup group2 = new SelectItemGroup("Group 2");
	          SelectItemGroup group3 = new SelectItemGroup("Group 3");
	          SelectItemGroup group4 = new SelectItemGroup("Group 4");
	           
	          SelectItemGroup group11 = new SelectItemGroup("Group 1.1");
	          SelectItemGroup group12 = new SelectItemGroup("Group 1.2");
	           
	          SelectItemGroup group21 = new SelectItemGroup("Group 2.1");
	           
	          SelectItem option31 = new SelectItem("Option 3.1", "Option 3.1");
	          SelectItem option32 = new SelectItem("Option 3.2", "Option 3.2");
	          SelectItem option33 = new SelectItem("Option 3.3", "Option 3.3");
	          SelectItem option34 = new SelectItem("Option 3.4", "Option 3.4");
	           
	          SelectItem option41 = new SelectItem("Option 4.1", "Option 4.1");
	           
	          SelectItem option111 = new SelectItem("Option 1.1.1");
	          SelectItem option112 = new SelectItem("Option 1.1.2");
	          group11.setSelectItems(new SelectItem[]{option111, option112});
	           
	          SelectItem option121 = new SelectItem("Option 1.2.1", "Option 1.2.1");
	          SelectItem option122 = new SelectItem("Option 1.2.2", "Option 1.2.2");
	          SelectItem option123 = new SelectItem("Option 1.2.3", "Option 1.2.3");
	          group12.setSelectItems(new SelectItem[]{option121, option122, option123});
	           
	          SelectItem option211 = new SelectItem("Option 2.1.1", "Option 2.1.1");
	          group21.setSelectItems(new SelectItem[]{option211});
	           
	          group1.setSelectItems(new SelectItem[]{group11, group12});
	          group2.setSelectItems(new SelectItem[]{group21});
	          group3.setSelectItems(new SelectItem[]{option31, option32, option33, option34});
	          group4.setSelectItems(new SelectItem[]{option41});
	           
	          categories.add(group1);
	          categories.add(group2);
	          categories.add(group3);
	          categories.add(group4);
	        
	       return categories;
	    }
	     
	    public List<Etiqueta> getEtiquetas() {
	        return etiquetas;
	    }
	}


