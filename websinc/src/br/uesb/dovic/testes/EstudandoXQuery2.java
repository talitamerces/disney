package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;


import javax.xml.namespace.QName;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class EstudandoXQuery2 {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		  XQConnection conn = ds.getConnection();
		  String expStr="";
		  	  
		  String arg1="NP";		
		  String arg2="D-F-P";
		  
		  expStr="declare namespace functx = \"http://www.functx.com\";"
				  +"declare function functx:descendente($no as element()) as xs:boolean"
				  +"{ if ($no[count(child::*)>0]) then"//se tem filho
				  + " 	if ($no/child::*[1][self::"+arg2+"]) then " //se o primeiro filho é o nó procurado
				  +" 		true() "
				  +" 	else "
				  + "		functx:descendente($no/child::*[1]) " 
				  + " else	"
				  + " 	false()"
				  +"}; ";
		  
		  expStr+= "declare function functx:iprecedes($no as element()) as xs:boolean"
				  +"{ if ($no/count(following-sibling::*[not(self::COMMA)])=0) then " // se não tem irmãos
				  +" 	functx:iprecedes($no/parent::node()) "
				  +"else " // se tem irmão
				  + " 	if ($no/following-sibling::*[not(self::COMMA)][1][self::"+arg2+"]) then "
				  + "		true()"
				  + "  	else "
				  + " 		functx:descendente($no/following-sibling::*[not(self::COMMA)][1]) "
				  +"}; ";

		 
		  
		  
		  expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/g_008_newxml6.xml')//DOCUMENTO/"
					+ "(*[starts-with(name(),'IP-MAT')]|IP-IMP|IP-SUB|FRAG|CP-THT|CONJP)";
		  expStr+=" let $sentenca := fn:string($ipmat) "
				  +" for $no in ($ipmat//"+arg1+")";
		  
		  expStr+=" return "
		  		+ " if (functx:iprecedes($no)) then"
				+ " 	$sentenca "
				+ "else ()";
		 		
		  	
		 	
		 		
 
	
		  XQPreparedExpression exp = conn.prepareExpression(expStr);

		  XQResultSequence resultSequence = exp.executeQuery();
		  int count=0;
		 
		  String temp, teste="";
		  while (resultSequence.next()) {
		count++;
	     temp=resultSequence.getItemAsString(null);
	     temp=temp.replaceAll("(\n)+", " ");
	     teste+= temp+ "\n******************\n";
	   }
		   conn.close();
		   resultSequence.close();
		 
	   
		   System.out.println("TOTAL: "+count+"\n\n"+teste);
			 
		} catch(Exception e)
		{
			e.printStackTrace(); 
			System.out.println(e.toString());
		}
		  
	 }

	}

//expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/g_008_XML.xml')//DOCUMENTO/(IP-MAT|IP-IMP)";
//expStr+=" let $sentenca := fn:string($ipmat)";
//expStr+=" where "
//		+ "exists($ipmat//((NP-SBJ/following-sibling::NP-ACC)|(NP-ACC/following-sibling::NP-SBJ))) ";
//expStr += "return $sentenca";

//expStr="declare namespace functx = \"http://www.functx.com\";"
//		  +"declare function functx:iprecedes"
//		  +"($precedente as element(), $sucessor as xs:string) as xs:boolean"
//		  +"{if ($precedente/count(following-sibling::*)>0) then"
//		  + "  if (($precedente/following-sibling::*[1][self::D-F-P]) or ($precedente/following-sibling::*[1]/descendant::*[1][self::D-F-P]))"
//		  +"      then return true"
//		  + "  else return false"
//		  + " else return functx:iprecedes($precedente/parent::*,$sucessor)"
//		  +"}; ";

//   XQPreparedExpression exp = conn.prepareExpression(expStr);
//
//   exp.bindInt(new QName("idade"), 27, null);

//   XQResultSequence resultSequence = exp.executeQuery();


//expStr+=" let $sentenca := fn:string($ipmat) "
//	+ " let $no:=$ipmat//ADJ-F-P"
//	+ " let $idfolha:=$no/following::LEAF[@W='yes'][1][@id]"
//	+ " let $suc:=$ipmat//CONJ"
//	+" return if ("
//	+"($suc[@id<$idfolha]) and ($suc[@id>$no[@id]])"
//	+ "and not($suc/ancestor::ADJ-F-P[@id]=$no[@id])"
//	+ ")"
//	+ " then $sentenca "
//+" else ()";