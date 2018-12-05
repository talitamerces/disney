package br.uesb.dovic.testes;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConverteJSON {
	
	 public static void main (String[] args) throws Exception {


		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		    DocumentBuilder xml = factory.newDocumentBuilder();

		    Document doc = xml.parse("WebContent/resources/arquivosxml/sentenca.xml");
		    
		    Node raiz = doc.getFirstChild();
		    
		    String saida= makeNo(raiz, raiz.getChildNodes(), null);
		    

		    System.out.println(saida);		      

			     }
		     
	     
		     public static String makeNo(Node node, NodeList child, Node parent){
		    	 boolean temFilho=false;
		    	 String result="";
		    	 for (int i=0;i<child.getLength();i++)
		    	 {
			       	 if (child.item(i).getNodeType()==Node.ELEMENT_NODE)
			       	 {
			       		 temFilho=true;
			       	 	 break;
			       	 }
		    	 } 		 
			      
		    	 result+= "{\n";
		    	 if (node.getNodeName().equals("LEAF"))
		    		 result+= "\"name\":\""+node.getFirstChild().getNodeValue()+"\",\n";
		    	 else
		    		 result+= "\"name\":\""+node.getNodeName()+"\",\n";
		    	 if (parent==null)
		    		 result+= "\"parent\":\"null\",\n";
		    	 else
		    		 result+= "\"parent\":\""+parent.getNodeName()+"\",\n";
		 
		    	 if (temFilho)
			     {
		    	 result+= "\"children\":[";
		  	 
			     for (int i=0;i<child.getLength();i++)
			    	 if (child.item(i).getNodeType()==Node.ELEMENT_NODE)
			    		 result+=makeNo(child.item(i),child.item(i).getChildNodes(),node)+",";
			    
			     result=result.substring(0,result.length()-1);
			     result+="]";
			     }
		    	 else 
		    		 result=result.substring(0,result.length()-2)+"\n"; 
			     result+="}\n";
		    	 
		    	 return result;
		     }
}
		     

		  



		


