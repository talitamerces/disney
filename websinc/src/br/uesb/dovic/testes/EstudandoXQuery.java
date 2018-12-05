package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class EstudandoXQuery {
	public static void main(String[] args) {
	XQDataSource ds = new SaxonXQDataSource();
	
	try{

	  XQConnection conn = ds.getConnection();
	  String expStr="";
	  
	  
	  expStr="declare namespace functx = \"http://www.functx.com\";"
			  +"declare function functx:iprecedes"
			  +"($precedente as element(), $sucessor as element(), $folha as element()) as xs:boolean"
			  +"{"
			  +" if (($precedente[@id]<$sucessor[@id]) and ($sucessor[@id]<$folha[@id]) and "
			  + "not($precedente/descendant::node is $folha))"
			  +"    then  true()"
			  + "   else false()"
		
			  +"}; ";

	  expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/g_008_newxml6.xml')//DOCUMENTO/"
	 	  		+ "(*[starts-with(name(),'IP-MAT')]|IP-IMP|IP-SUB|FRAG|CP-THT|CONJP)";
	  expStr+=" let $sentenca := fn:string($ipmat) "
			
			 
			  +" where (not($ipmat//ADJ-F/following-sibling::*[1][self::PP])) and"
			  + "($ipmat//NP-GEN) and ($ipmat//ADJ-F) "
//			  + "or ($no/following-sibling::*[1]/descendant::*[1][self::D-F-P])"
//			  + " or ($no/ancestor::*/descendant::*[@id<$folha[@id]])"
	  		
	  		+ " return ($sentenca) ";

			
//			+ "(NPR/(ancestor::* except IP-MAT)/following-sibling::*/descendant::ADV)|"
//			+ "(NPR/ancestor::*/following-sibling::ADV)))"
		//+" where ($ipmat//NP-SBJ[count(* except (POINT|COMMA))=6])" 
		  	//+ "where ($ipmat//(NP-SBJ/NPR/parent::NP-SBJ[count(*)=1]))"
		  
		
	  	
	 
	
	  
	

   
//	  You could also select the first two
//	  children of each product, with any name, using:
//	  doc("catalog.xml")/catalog/product/*[position( ) < 3]
			  
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