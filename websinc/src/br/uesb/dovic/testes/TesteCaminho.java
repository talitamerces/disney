package br.uesb.dovic.testes;

import java.io.File;

public class TesteCaminho {

	public static void main(String[] args) {
		 final String SEPARATOR  = File.separator;
		 final String HOME_FOLDER = System.getProperty("user.home");
		 String UPLOAD_FOLDER = HOME_FOLDER + SEPARATOR + "websinc" + SEPARATOR + "uploads" + SEPARATOR;
		
		System.out.println(UPLOAD_FOLDER);

	}

}
