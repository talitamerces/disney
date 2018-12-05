package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;


import javax.xml.namespace.QName;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class IPrecedes {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		  XQConnection conn = ds.getConnection();
		  String expStr="";
		  	  
		  String arg1="NP";	
		  String arg2="D-F-P";
		  
		  
		  expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/g_008_newxml6.xml')//DOCUMENTO/"
					+ "(*[starts-with(name(),'IP-MAT')]|IP-IMP|IP-SUB|FRAG|CP-THT|CONJP)";
		  expStr+=" let $sentenca := fn:string($ipmat) ";
		
		 expStr+=" where "
			// PRIMEIRO IRMÃO SEGUINTE
//		 	+ "($ipmat//"+arg1+"/following-sibling::*[not(self::COMMA)][1][self::"+arg2+"])"
//		 	+ " or " 
//		    // PRIMEIRO DESCENDENTE DO PRIMEIRO IRMÃO SEGUINTE
//	 		+ " ($ipmat//"+arg1+"/following-sibling::*[not(self::COMMA)][1]/descendant::*[not(self::COMMA)][1][self::"+arg2+"])  " 
//	 		+ " or "
//	 		//PRIMEIRO IRMÃO SEGUINTE DE ALGUM ANCESTRAL QUE TENHA IRMÃO
//		 	+ "($ipmat//"+arg1+"[count(following-sibling::*)=0]/ancestor::*[@id>$ipmat[@id]][count(following-sibling::*)>0][1]/following-sibling::*[not(self::COMMA)][1][self::"+arg2+"])"
//		 	+ " or "
		 	//PRIMEIRO DESCENDENTE DO PRIMEIRO IRMÃO SEGUINTE DE ALGUM ANCESTRAL
//		 	+ "($ipmat//"+arg1+"[count(following-sibling::*)=0]/ancestor::*[@id>$ipmat[@id]][count(following-sibling::*)>0][1]/following-sibling::*[not(self::COMMA)][1]/descendant::*[not(self::COMMA)][1][self::"+arg2+"])"
		 	+ "($ipmat//"+arg1+"[count(following-sibling::*)=0]/ancestor::*[@id>$ipmat[@id]][count(following-sibling::*)>0][1]/following-sibling::*[not(self::COMMA)][1]/descendant::*[self::"+arg2+"]/parent::node()/child::*[1][self::D-F-P])"
	 		+ " return $sentenca";
		 		
 
	
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