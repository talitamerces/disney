package br.uesb.dovic.testes;

public class TesteRegex {
	public static void main (String args[])
	{
		String teste="aline";
//		teste=teste.replaceAll("\\*(T|ICH|exp|arb|pro)\\*(-\\d)?", "=OK=");
		teste=teste.substring(0,teste.length()-1);
		System.out.println(teste);
		
	}
}
