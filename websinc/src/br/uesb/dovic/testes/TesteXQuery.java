package br.uesb.dovic.testes;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;


import javax.xml.namespace.QName;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class TesteXQuery {

	public static void main(String[] args) {
		XQDataSource ds = new SaxonXQDataSource();
		
		try{

		   XQConnection conn = ds.getConnection();

		 

//		   String expStr =  "for $c in fn:doc('1.29.xml')//document/body/text/sc/s/w "
//
//		      + " where $c/m/@v='NPR'"
//
//		      + "return $c/o";
		   
		   String expStr =  "for $c in fn:doc('1.29.xml')/document/body/text/sc/p/s/w "

		      + "let $token := fn:data($c/o)"

		      + "return $token";

		  

		   XQPreparedExpression exp = conn.prepareExpression(expStr);

		      XQResultSequence resultSequence = exp.executeQuery();

		 

		   while (resultSequence.next()) {

		     System.out.print(resultSequence.getItemAsString(null)+" ");

		   }
		   conn.close();
		   resultSequence.close();

		 
		} catch(Exception e)
		{
			 System.out.println("DEU PAU");
		}
		  
		  }

		

	}

//String expStr = "declare variable $idade as xs:integer external; "
//
//      + ""
//
//      + "for $c in fn:doc('clientes.xml')/clientes/cliente "
//
//      + "let $nome := fn:data($c/nome) "
//
//      + "where xs:integer($c/idade) > $idade "
//
//      + "order by xs:double($c/rendaMensal) "
//
//      + "return $nome ";
//
// 
//
//   XQPreparedExpression exp = conn.prepareExpression(expStr);
//
//   exp.bindInt(new QName("idade"), 27, null);
//
// 
//
//   XQResultSequence resultSequence = exp.executeQuery();
//
//Leia mais em: Artigo Java Magazine 67 - XQJ – XQuery API for Java http://www.devmedia.com.br/artigo-java-magazine-67-xqj-xquery-api-for-java/12783#ixzz3D7uRaFbb
//
//
