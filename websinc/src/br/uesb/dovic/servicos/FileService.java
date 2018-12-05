package br.uesb.dovic.servicos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.uesb.dovic.util.UtilErros;
import br.uesb.dovic.util.UtilMensagens;

public class FileService {
	
	private static final String SEPARATOR = File.separator;
	private static final String HOME_FOLDER = System.getProperty("user.home");
	private static String UPLOAD_FOLDER = HOME_FOLDER + SEPARATOR + "websinc"
			+ SEPARATOR + "uploads" + SEPARATOR;
	private static String IMAGENS_FOLDER = HOME_FOLDER + SEPARATOR + "websinc"
			+ SEPARATOR + "imagens" + SEPARATOR;
	private static boolean gravouArquivo=true;
	

	public static String getExtensao(String caminho) {

		int pos = caminho.lastIndexOf('.');
		if (pos > -1) {
			return caminho.substring(pos + 1);
		}
		return "";
	}
	
	
	public static String convertToFileURL ( String filename )
	{
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
	
	public static void copyFile(String fileName, InputStream in) {

		String pasta;
		if (fileName.contains("jpg") || fileName.contains("png") ||
				fileName.contains("JPG") || fileName.contains("PNG"))
			pasta=IMAGENS_FOLDER;
		else
			pasta=UPLOAD_FOLDER;
		try {

			OutputStream out = new FileOutputStream(new File(pasta
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			
			}

			in.close();
			out.flush();
			out.close();
			gravouArquivo=true;

		} catch (IOException e) {
			e.printStackTrace();
			gravouArquivo=false;
			UtilMensagens.mensagemErro("Erro fazer upload do arquivo "+
					UtilErros.getMensagemErro(e));
		}
	}
	
	
	public static void deleteFile(String fileName) {

		File arquivo = new File(IMAGENS_FOLDER	+ fileName);
		arquivo.delete();
	}


	public static boolean isGravouArquivo() {
		return gravouArquivo;
	}


	public static void setGravouArquivo(boolean gravouArquivo) {
		FileService.gravouArquivo = gravouArquivo;
	}

  

}
