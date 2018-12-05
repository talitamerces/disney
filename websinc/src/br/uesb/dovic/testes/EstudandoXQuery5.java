package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;







import net.sf.saxon.xqj.SaxonXQDataSource;

public class EstudandoXQuery5 {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		  XQConnection conn = ds.getConnection();
		  String expStr;
		  String arquivoXML="WebContent/resources/arquivosxml/1.29.xml";
		  
//		  String expStr = "for $s in fn:doc('"
//					+ arquivoXML
//					+ "')/document/body/text/sc/p/s/w "
//
//					+ " return if($s/e) then <span class=\"mod\" title=\"{data($s/o)}\">{data($s/e)}</span> else data($s/o)";
	
					
		 	
		  expStr = "for $c in fn:doc('" + arquivoXML
					+ "')/document/body/text/sc/p/s/w "
			+ "return if ($c/o/bk/sce) then <page>{data($c/o)}</page> else "
					+ "if ($c/o/bk[@t='l']) then <linha>{data($c/o)}</linha> else data($c/o)";
		  
		  XQPreparedExpression exp = conn.prepareExpression(expStr);

		  XQResultSequence resultSequence = exp.executeQuery();

		 
		  int count=0;
			 
		  String temp, teste="";
		  String page="";
		  while (resultSequence.next()) {
			  count++;
			  temp=resultSequence.getItemAsString(null);
			 
				page +=temp+"\n******************";
	  
	   }
		   conn.close();
		   resultSequence.close();
		 
	   
		   System.out.println("TOTAL: "+count+"\n\n"+page);
			 
		} catch(Exception e)
		{
			e.printStackTrace(); 
			System.out.println(e.toString());
		}
		  
	 }

	}



