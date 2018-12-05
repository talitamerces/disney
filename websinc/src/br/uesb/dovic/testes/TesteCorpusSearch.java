package br.uesb.dovic.testes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TesteCorpusSearch {
	
public static void main(String[] args){
	

String arquivoPSD="C:\\workspace\\DovicWeb\\src\\br\\uesb\\dovic\\testes\\a_001_psd.txt";

    String arquivoBusca="C:\\workspace\\DovicWeb\\src\\br\\uesb\\dovic\\testes\\query1.q";
    
    final String[] COMMAND_LINE = {"java","-classpath", "C:\\CorpusSearch\\csearch\\jar\\CS.jar","csearch/CorpusSearch", arquivoBusca, arquivoPSD};
   // final String[] COMMAND_LINE = {"java","-classpath", "C:\\CorpusSearch\\csearch\\jar\\CS.jar","csearch/CorpusSearch"};
    //final String[] COMMAND_LINE = {"java"};
    String gedit="C:\\Program Files (x86)\\gedit\\bin\\gedit.exe";
    try{
  	Process Processo = Runtime.getRuntime().exec(COMMAND_LINE);

		InputStream is=Processo.getErrorStream();

		DataInputStream dis=new DataInputStream(is);

		BufferedReader d = new BufferedReader(new InputStreamReader(dis));

	 	StringBuffer buf=new StringBuffer();

		String text;

		while((text=d.readLine())!=null)

		buf.append(text+"\n");
		System.out.println(buf.toString());
	          
		}	catch (Exception ioe)

		{

		ioe.printStackTrace();

	}
		
		
}


}



