package br.uesb.dovic.servicos;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.uesb.dovic.beans.Lexico;
import br.uesb.dovic.beans.PaginaDocumento;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.util.ImagemServlet;

public class XMLService implements Serializable {
	private static final long serialVersionUID = 1L;

	public XMLService() {

	}

	public String buscaMorfologica(String arquivo, String etiqueta) {

		String arquivoXML = FileService.convertToFileURL(arquivo);
		String expStr = "for $c in fn:doc('" + arquivoXML
				+ "')/document/body/text/sc/p/s/w "

				+ "let $token := fn:data($c/o)"

				+ "return $token";
		return geraConsulta(expStr);
	}

	public List<PaginaDocumento> geraTranscricao(String arquivo,List<Imagem> imagens) {
		if (!arquivo.isEmpty()) {
			String arquivoXML = FileService.convertToFileURL(arquivo);
//			String expStr = "for $c in fn:doc('"
//					+ arquivoXML
//					+ "')/document/body/text/sc/p/s/w "
//					+ "return if ($c/o/bk/sce) then <page>{data($c/o)}</page> else data($c/o)";
			
			String expStr = "for $c in fn:doc('"
					+ arquivoXML
					+ "')/document/body/text/sc/p/s/w "
					+ "return if ($c/o/bk/sce) then <page>{data($c/o)}</page> else "
					+ "if ($c/o/bk[@t='l']) then <linha>{data($c/o)}</linha> else data($c/o)";

			return geraPaginas(expStr, imagens);
		} else
			return geraPaginasVazias(imagens);

	}
	
	public List<PaginaDocumento> geraPaginasVazias(List<Imagem> imagens) {
		List<PaginaDocumento> paginas = new ArrayList<PaginaDocumento>();
		String  imagem="";
		int count = 1, quantidadeImagens = imagens.size();
		while (count <= quantidadeImagens){
			imagem = getEnderecoImagem(imagens.get(count - 1));
			paginas.add(new PaginaDocumento(Integer.toString(count),"", imagem,
					Integer.toString(imagens.get(count - 1).getNumeroFolha()), 
					imagens.get(count - 1).getLadoImagem().toString()));
			count++;
		}
		return paginas;
	
	}

	public List<PaginaDocumento> geraModernizado(String arquivo,List<Imagem> imagens) {
		if (!arquivo.isEmpty()) {
		String arquivoXML = FileService.convertToFileURL(arquivo);

		String expStr = "for $c in fn:doc('"
				+ arquivoXML
				+ "')/document/body/text/sc/p/s/w "
				+ "return if ($c/o/bk/sce) then <page>{data($c/o)}</page> else "
				+ "if (($c/e[@t='mod']) and ($c/o/bk[@t='l'])) then <linha><span class=\"mod\" title=\"{data($c/o)}\">{data($c/e[@t='mod'])}</span></linha> else "
				+ "if ($c/o/bk[@t='l']) then <linha>{data($c/o)}</linha> else "
				+ "if ($c/e[@t='mod']) then <span class=\"mod\" title=\"{data($c/o)}\">{data($c/e[@t='mod'])}</span> "
				+ "else data($c/o)";

		
//		String expStr = "for $c in fn:doc('"
//				+ arquivoXML
//				+ "')/document/body/text/sc/p/s/w "
//				+ "return if ($c/o/bk/sce) then <page>{data($c/o)}</page> else "
//				+ "if ($c/e[@t='mod']) then <span class=\"mod\" title=\"{data($c/o)}\">{data($c/e[@t='mod'])}</span> else data($c/o)";

			return geraPaginas(expStr, imagens);
		}
		else
			return geraPaginasVazias(imagens);

	}

	public List<PaginaDocumento> geraPaginas(String expStr, List<Imagem> imagens) {
		List<PaginaDocumento> paginas = new ArrayList<PaginaDocumento>();

		String texto = "", token = "", imagem = "";
		int count = 1, quantidadeImagens = imagens.size();

		try {

			XQDataSource ds = new SaxonXQDataSource();
			XQConnection conn = ds.getConnection();
			XQPreparedExpression exp = conn.prepareExpression(expStr);

			XQResultSequence resultSequence = exp.executeQuery();

			while (resultSequence.next()) {

				token = resultSequence.getItemAsString(null);
				if (token.contains("<linha>"))
					texto+=token +"<br>";
				else if (!token.contains("<page>"))
					texto += token + " ";
				else {
					if (count <= quantidadeImagens)
						imagem = getEnderecoImagem(imagens.get(count - 1));
					paginas.add(new PaginaDocumento(Integer.toString(count),
							texto, imagem, Integer.toString(imagens.get(count - 1).getNumeroFolha()), 
							imagens.get(count - 1).getLadoImagem().toString()));
					count++;
					texto = "";
				}

			}

			if (!texto.equals("")) {

				if (count <= quantidadeImagens)
					imagem = getEnderecoImagem(imagens.get(count - 1));
				paginas.add(new PaginaDocumento(Integer.toString(count), texto,
						imagem,Integer.toString(imagens.get(count - 1).getNumeroFolha()), 
						imagens.get(count - 1).getLadoImagem().toString()));

			}

		} catch (Exception e) {
			e.printStackTrace();
			return paginas;
		}

		return paginas;

	}

	public String getEnderecoImagem(Imagem img) {
		if ((img.getEnderecoImagem() == null)
				|| (img.getEnderecoImagem().equals("")))
			return "";
		return ImagemServlet.getURL(img.getEnderecoImagem());
	}

	public String geraCorrigido(String arquivo) {
		String arquivoXML = FileService.convertToFileURL(arquivo);
		String expStr = "for $s in fn:doc('"
				+ arquivoXML
				+ "')/document/body/text/sc/p/s/w "

				+ " return if($s/e[@t='cor']) then <span class=\"mod\" title=\"{data($s/o)}\">{data($s/e[@t='cor'])}</span> else data($s/o)";
		return geraConsulta(expStr);
	}

	public String geraEditado(String arquivo) {
		String arquivoXML = FileService.convertToFileURL(arquivo);
		String expStr = "for $s in fn:doc('"
				+ arquivoXML
				+ "')/document/body/text/sc/p/s/w "

				+ " return if($s/e) then <span class=\"mod\" title=\"{data($s/o)}\">{data($s/e)}</span> else data($s/o)";
		return geraConsulta(expStr);
	}

	public List<Lexico> lexicoEdicoes(String arquivo) {
		String arquivoXML = FileService.convertToFileURL(arquivo);
		String expStr = "for $s in fn:doc('" + arquivoXML
				+ "')/document/body/text/sc/p/s/w " + "let $edicao := ($s/e) "
				+ "let $original := data($s/o)" + " where exists($s/e)"
				+ "return ($original,$edicao)";
		return geraLexico(expStr);
	}

	public List<Lexico> geraLexico(String expStr) {

		List<Lexico> listLexico = new ArrayList<Lexico>();

		String texto = "", tipo = "", original = "", editado = "";

		try {

			XQDataSource ds = new SaxonXQDataSource();
			XQConnection conn = ds.getConnection();
			XQPreparedExpression exp = conn.prepareExpression(expStr);

			XQResultSequence resultSequence = exp.executeQuery();

			while (resultSequence.next()) {

				texto = resultSequence.getItemAsString(null);
				if (texto.contains("</e>")) {
					editado = texto.substring(texto.indexOf(">") + 1,
							texto.indexOf("/") - 1);
					if (texto.contains("\"mod\""))
						tipo = "Modernização de grafia";
					else if (texto.contains("\"pun\""))
						tipo = "Uniformização de pontuação";
					else if (texto.contains("\"seg\""))
						tipo = "Segmentação";
					else if (texto.contains("\"jun\""))
						tipo = "Junção";
					else if (texto.contains("\"gra\""))
						tipo = "Uniformização grafemática";
					else if (texto.contains("\"cor\""))
						tipo = "Correção";
					else if (texto.contains("\"exp\""))
						tipo = "Expansão de abreviatura";

					listLexico.add(new Lexico(original, editado, tipo));
				} else
					original = texto;

			}
			conn.close();
			resultSequence.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return listLexico;
	}

	public String geraConsulta(String expStr) {

		String texto = "";

		try {

			XQDataSource ds = new SaxonXQDataSource();
			XQConnection conn = ds.getConnection();

			XQPreparedExpression exp = conn.prepareExpression(expStr);

			XQResultSequence resultSequence = exp.executeQuery();

			while (resultSequence.next()) {

				texto += resultSequence.getItemAsString(null) + " ";

			}
			conn.close();
			resultSequence.close();

		} catch (Exception e) {
			e.printStackTrace();
			return "Não foi possível gerar texto transcrito";
		}

		return texto;
	}

	public String converteJSON(String xml) throws Exception {
		String saida;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = builder.parse(is);
		Node raiz = doc.getFirstChild();
		saida = makeNo(raiz, raiz.getChildNodes(), null);
		return saida;
	}

	public String converteJSONMorfo(String xml) throws Exception {
		String saida;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = builder.parse(is);
		Node raiz = doc.getFirstChild();
		saida = makeWord(raiz, raiz.getChildNodes(), null);
		return saida;
	}

	public String makeNo(Node node, NodeList child, Node parent) {
		boolean temFilho = false;
		String temp = "";
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < child.getLength(); i++) {
			if (child.item(i).getNodeType() == Node.ELEMENT_NODE) {
				temFilho = true;
				break;
			}
		}

		result.append("{\n");
		if (node.getNodeName().equals("LEAF"))
			result.append("\"name\":\"" + node.getFirstChild().getNodeValue()
					+ "\",\n");
		else
			result.append("\"name\":\"" + node.getNodeName() + "\",\n");
		if (parent == null)
			result.append("\"parent\":\"null\",\n");
		else
			result.append("\"parent\":\"" + parent.getNodeName() + "\",\n");

		if (temFilho) {
			result.append("\"children\":[");

			for (int i = 0; i < child.getLength(); i++)
				if (child.item(i).getNodeType() == Node.ELEMENT_NODE)
					result.append(makeNo(child.item(i), child.item(i)
							.getChildNodes(), node)
							+ ",");

			temp = result.substring(0, result.length() - 1);
			result = new StringBuffer(temp);
			result.append("]");
		} else {
			temp = result.substring(0, result.length() - 2) + "\n";
			result = new StringBuffer(temp);
		}

		result.append("}\n");

		return result.toString();
	}

	public String makeWord(Node node, NodeList child, Node parent) {
		boolean temFilho = false;
		NamedNodeMap attr;
		Node nodeAttr;
		String temp = "";
		String atributo = "";
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < child.getLength(); i++) {
			if ((node.getNodeName().equals("o") || node.getNodeName().equals(
					"e"))
					|| (child.item(i).getNodeType() == Node.ELEMENT_NODE)) {
				temFilho = true;
				break;
			}
		}

		result.append("{\n");

		if ((node.getNodeName().equals("m"))) {
			attr = node.getAttributes();
			nodeAttr = attr.getNamedItem("v");
			atributo = nodeAttr.getNodeValue();
			result.append("\"name\":\"m=" + atributo + "\",\n");
		} else if ((node.getNodeName().equals("e"))) {
			attr = node.getAttributes();
			nodeAttr = attr.getNamedItem("t");
			atributo = nodeAttr.getNodeValue();
			result.append("\"name\":\"e=" + atributo + "\",\n");
		} else
			result.append("\"name\":\"" + node.getNodeName() + "\",\n");
		if (parent == null)
			result.append("\"parent\":\"null\",\n");
		else
			result.append("\"parent\":\"" + parent.getNodeName() + "\",\n");

		if (temFilho) {
			result.append("\"children\":[");

			for (int i = 0; i < child.getLength(); i++)
				if (node.getNodeName().equals("o")) {
					result.append("{\n\"name\":\""
							+ node.getFirstChild().getNodeValue() + "\",\n");
					result.append("\"parent\":\"o\"}]\n");
					break;
				}

				else if (node.getNodeName().equals("e")) {
					result.append("{\n\"name\":\""
							+ node.getFirstChild().getNodeValue() + "\",\n");
					result.append("\"parent\":\"e=" + atributo + "\"}]\n");
					break;
				}

				else if ((child.item(i).getNodeType() == Node.ELEMENT_NODE))
					result.append(makeWord(child.item(i), child.item(i)
							.getChildNodes(), node)
							+ ",");

			temp = result.substring(0, result.length() - 1);
			result = new StringBuffer(temp);
			if ((!node.getNodeName().equals("e"))
					&& (!node.getNodeName().equals("o")))
				result.append("]");
		} else {
			temp = result.substring(0, result.length() - 2) + "\n";
			result = new StringBuffer(temp);
		}

		result.append("}\n");

		return result.toString();
	}
}
