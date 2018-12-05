package br.uesb.dovic.testes;

import java.io.File;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;


import javax.xml.namespace.QName;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class EstudandoXQuery4 {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		  XQConnection conn = ds.getConnection();
		  String expStr="";
		  
//		  expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/iprecedes.xml')//DOCUMENTO";
//		  expStr+=" let $sentenca := fn:string($ipmat)";
//		  expStr+=" where exists($ipmat//((D-F/following-sibling::NP)|(NP/following-sibling::D-F)))";
//		  expStr += " return $sentenca";
//		 
		  expStr="declare namespace functx = \"http://www.functx.com\";"
				  +"declare function functx:iprecedes"
				  +"($precedente as element()) as xs:boolean"
				  +"{if ($precedente/count(following-sibling::* except COMMA)>0) then"
				  +" if (($precedente/following-sibling::*[1][self::D-F-P]) or ($precedente/following-sibling::*[1]/descendant::*[1][self::D-F-P])"
				  + "or ($precedente/following-sibling::*[1][self::COMMA]/following-sibling::*[1]/descendant::*[1][self::D-F-P]) or"
				  + "($precedente/following-sibling::*[1][self::COMMA]/following-sibling::*[1][self::D-F-P]))"
				  +"    then  true()"
				  + "   else false()"
				  + "  else  if ($precedente/..) then functx:iprecedes($precedente/..)"
				  + "    else false()" 
			
				  +"}; ";
		  
			 final String SEPARATOR  = File.separator;
			  final String HOME_FOLDER = System.getProperty("user.home");
			 String UPLOAD_FOLDER = HOME_FOLDER + SEPARATOR + "websinc" + SEPARATOR + "arquivosxml" + SEPARATOR;
			 String uri=convertToFileURL(UPLOAD_FOLDER+"g_008_newxml5.xml");

	
		 // expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/g_008_newxml5.xml')//DOCUMENTO/"
		  //expStr+="for $ipmat in fn:doc('WebContent/resources/arquivosxml/exemploprecedes.xml')//DOCUMENTO/IP-MAT";
			 
			 expStr+="for $ipmat in fn:doc('"+ uri+"')//DOCUMENTO/"
		  		+ "(*[starts-with(name(),'IP-MAT')]|IP-IMP|IP-SUB|FRAG|CP-THT|CONJP)";
		  expStr+=" let $sentenca := fn:string($ipmat) "
				+"for $no in ($ipmat//NP)"
				+" return if (functx:iprecedes($no)) then $sentenca "
				+" else ()";

//				+ "(NPR/(ancestor::* except IP-MAT)/following-sibling::*/descendant::ADV)|"
//				+ "(NPR/ancestor::*/following-sibling::ADV)))"
			//+" where ($ipmat//NP-SBJ[count(* except (POINT|COMMA))=6])" 
			  	//+ "where ($ipmat//(NP-SBJ/NPR/parent::NP-SBJ[count(*)=1]))"
			  

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
	
	
	private static String convertToFileURL ( String filename )
	{
	    // On JDK 1.2 and later, simplify this to:
	    // "path = file.toURL().toString()".
	    String path = new File ( filename ).getAbsolutePath ();
	    if ( File.separatorChar != '/' )
	    {
	        path = path.replace ( File.separatorChar, '/' );
	    }
	    if ( !path.startsWith ( "/" ) )
	    {
	        path = "/" + path;
	    }
	    String retVal =  "file:" + path;

	    return retVal;
	}

	}

//   XQPreparedExpression exp = conn.prepareExpression(expStr);
//
//   exp.bindInt(new QName("idade"), 27, null);

//   XQResultSequence resultSequence = exp.executeQuery();

