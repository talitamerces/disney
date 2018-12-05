package br.uesb.dovic.testes;

public class ExpressaoRegular {

	public static void main(String[] args) {
		String token="*exp*";
		if (token.matches("(\\*(T|ICH|exp|arb|pro)\\*(-\\d+)?)|(\\*-\\d+)|,|\\."))
				System.out.println("OK");
		else
			System.out.println("NÃO CASA");

	}

}
