package br.uesb.dovic.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImagemServlet
 */
//@WebServlet("/ImagemServlet")
public class ImagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SEPARATOR  = File.separator;
	private static final String HOME_FOLDER = System.getProperty("user.home");
	private static final String UPLOAD_FOLDER = HOME_FOLDER + SEPARATOR + "websinc" + SEPARATOR + "imagens" + SEPARATOR;
	
	public ImagemServlet() {
        // TODO Auto-generated constructor stub
    }

	public static String getURL(String fileName){
		return "/ImagemServlet/"+fileName;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
		
			String fileName = getFileName(request);
					
			String extensao = getExtensao(fileName);
			response.setContentType(getContentType(extensao));
	//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
			
			// Envia o arquivo.
			BufferedOutputStream saida = new BufferedOutputStream(response.getOutputStream());
			BufferedImage bImg = ImageIO.read(getFile(fileName));
			if (extensao.equalsIgnoreCase("png")) {
				ImageIO.write(bImg, "png", saida);
			}
			else if (extensao.equalsIgnoreCase("jpg")) {
				ImageIO.write(bImg, "jpg", saida);
			}
			else if (extensao.equalsIgnoreCase("gif")) {
				ImageIO.write(bImg, "gif", saida);
			}
			else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			
			saida.flush();
		}
		
	}

	private String getContentType(String extensao) {
		if (extensao.equalsIgnoreCase("png")) {
			return "image/png";
		}
		else if (extensao.equalsIgnoreCase("jpg")) {
			return "image/jpg" ;
		}
		else if (extensao.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		return null;
	}

	private String getExtensao(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private String getFileName(HttpServletRequest request){
		 return request.getRequestURI().replace("/websinc/ImagemServlet/","");
	}
	
	protected File getFile(String fileName) {
		java.io.File file = new java.io.File(UPLOAD_FOLDER+fileName);
		return file;
	}
	
	
	

}
