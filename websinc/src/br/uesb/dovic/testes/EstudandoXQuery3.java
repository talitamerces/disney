package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;


import javax.xml.namespace.QName;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class EstudandoXQuery3 {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		  XQConnection conn = ds.getConnection();
		  String expStr="";
		   
//		  expStr+="for $s in fn:doc('WebContent/resources/arquivosxml/morfologico.xml')//document/body/text/sc/p/s";
//		  expStr+=" let $sentenca:= data($s/w/o)" ;
//		  expStr+=" let $ocorrencias:=count($s/w/m[@v=\"NPR\"])";
//		  expStr+=" where ($s/w/m[@v=\"NPR\"])" ;
//		  expStr += "  return <li>{$sentenca}<enter/> {$ocorrencias}</li>";
		  
//		 expStr+="for $s in fn:doc('WebContent/resources/arquivosxml/morfologico.xml')//document/body/text/sc/p/s "
//		 		+ "let $sentenca:= data($s/w/o) "
//				 + "let $arvore:=$s/(* except e)"
//				+" let $ocorrencias:=count(($s/w/(m[@v='N']|m[@v='NPR'])/parent::w/following-sibling::w[1]/m[@v='Q'])) "
//		 		+ "where ($s/w/(m[@v='N'])/parent::w/following-sibling::w[1]/(m[@v='Q']))"
//		 		+ "return <li>{$sentenca}<span> {$arvore}</span></li>";
		  
		  
		  
//		  expStr+="for $s in fn:doc('WebContent/resources/arquivosxml/1.29.xml')//document/body/text/sc/p[not(@t)]/s "
//				  +"let $sentenca:= data($s/w/o) "
//				  + "where ($s/w/m/@v[starts-with(.,'CL')]) "
//				  + "return <li>{$sentenca}<enter/></li>";
		  
		  expStr+="for $s in fn:doc('WebContent/resources/arquivosxml/1.29.xml')//document/body/text/sc/p[not(@t)]/s"
		  		+ " let $sentenca:= data($s/w/o) "
		  		+ "let $ocorrencias:=count(($s/w/m[@v='NUM']/parent::w/following-sibling::w/m[@v='P']) "
		  		+ "and ($s/w/m[@v='P']/parent::w/following-sibling::w/m[@v='NPR']))"
		  		+ " where ($s/w/m[@v='NUM']/parent::w/following-sibling::w/m[@v='P']) "
		  		+ "and ($s/w/m[@v='P']/parent::w/following-sibling::w/m[@v='NPR'])  "
		  		+ "return <li>{$sentenca}<enter/> {$ocorrencias}</li>";
		  
			 
//		expStr+=  "for $sce in fn:doc('WebContent/resources/arquivosxml/morfologico.xml')/document/body/text/sc/p/s/w "
//	    
//	      + " return if($s/e) then <mod>{data($s/e)}</mod> else data($s/o)";
		
		 
		 //vizinhança:
		 // "where ($s/w/m[@v='NPR']/parent::w/following-sibling::w[1]/m[@v='P+D-F'])"
		 
		// following-sibling::*[1][self::"+arg2+
		  XQPreparedExpression exp = conn.prepareExpression(expStr);

		  XQResultSequence resultSequence = exp.executeQuery();

		 
		  String temp, teste="";
		  while (resultSequence.next()) {
 
			  teste +=resultSequence.getItemAsString(null)+"\n";
		
		
	   }
//		   teste=teste.replaceAll("\\s+", " ");
//		   teste=teste.replaceAll("(<li>|</li>)", "");
//		   teste=teste.replaceAll("(<enter/>)", "\n");
		   conn.close();
		   resultSequence.close();
		 
	   
		   System.out.println(teste);
			 
		} catch(Exception e)
		{
			e.printStackTrace(); 
			System.out.println(e.toString());
		}
		  
	 }

	}


