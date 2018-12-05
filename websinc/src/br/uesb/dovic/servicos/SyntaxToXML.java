package br.uesb.dovic.servicos;



import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 *
 * @author Aline Silva Costa
 */
public class SyntaxToXML {

    private boolean eofFound ;
    private BufferedReader in;
    private BufferedWriter out;
   
    Stack<String> myStack = new Stack<String>();
   
    private int id;

    public SyntaxToXML() {
        id=0;
        eofFound=false;
    }
    
    

    public boolean eofFound() {
        return this.eofFound;
    }

 

  

    public String nextLine() {
        String lineFile = "";
        if (!this.eofFound) {
       
                try {
                    lineFile = this.in.readLine();
                    if (lineFile != null) {
                        return lineFile;
                    } else {
                        this.eofFound = true;
                        return "";
                    }
                } catch (IOException e) {
                   e.printStackTrace();
                    return "";
                }
            
        } else {
            return "eof";
        }
    }

  
    
    public void xmlTransform(BufferedReader in, String nomeArquivoSintatico) {
        
        eofFound=false;
    	String mine = new String();
        
          try {
                        	
        	
        	  this.in = in;  
        	          	
                        	
                FileWriter fw = new FileWriter(nomeArquivoSintatico);
                out = new BufferedWriter(fw);
                out.write("<DOCUMENTO id=\""+id+"\">");
                while (!mine.equals("eof")) {

                    mine = this.nextLine();
                    if (!mine.equals("eof")) {
                        out.write(this.getXML(mine));
                    }

                    
                }
                out.write("</DOCUMENTO>");
                out.flush();
                out.close();
            } catch (IOException e) {
               
            	System.out.println("ERRO AO GRAVAR ARQUIVO XML SINTÁTICO");
            	e.printStackTrace();
            }
          
          try {
              this.in.close();
          } catch (IOException e) {
        	  System.out.println("ERRO AO FECHAR ARQUIVO PSD");
          	e.printStackTrace();
          }
        
    }

    public String getXML(String line) {
        StringTokenizer st = new StringTokenizer(line, " ()", true);
        String token;
        String outXML = "";


        while (st.hasMoreElements()) {

            token = st.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            } else if (token.equals("(")) {
                myStack.push(token);
            } else if (token.equals(")")) {
                if (myStack.peek().equals("(")) {
                    myStack.pop();
                } else {
                    outXML += "</" + myStack.pop() + ">\n";
                }
            } else if (myStack.peek().equals("(")) {
                myStack.pop();
                id++;
                outXML += "<" + token + " id=\""+id +"\">\n";
                myStack.push(token);
            } else {
                if (token.endsWith(">") && (token.startsWith("<"))) {
                    token = token.substring(1, token.length() - 1);
                    
                   }
                id++;
                if (token.matches("(\\*(T|ICH|exp|arb|pro)\\*(-\\d+)?)|(\\*-\\d+)|,|\\.|:|;"))
                    outXML += "<LEAF id=\""+id+"\" W=\"no\" v=\""+token+"\">"+token+"</LEAF>";
                else
                  outXML += "<LEAF id=\""+id+"\" W=\"yes\" v=\""+token+"\">"+token+"</LEAF>";  
            }
        }
        
        
        
        
        outXML=outXML.replaceAll("<\\. id", "<POINT id");
        outXML=outXML.replaceAll("</\\.>", "</POINT>");
        outXML=outXML.replaceAll("<, id", "<COMMA id");
        outXML=outXML.replaceAll("</,>", "</COMMA>");
        outXML=outXML.replaceAll("<<paren> id", "<PAREN id");
        outXML=outXML.replaceAll("</<paren>>", "</PAREN>");
        outXML=outXML.replaceAll("SSparen", ")");
        outXML=outXML.replaceAll("<PAREN>\nparen", "<PAREN>\n(");
        outXML=outXML.replaceAll("\\$", "S");
        return outXML;
    }
}

