package br.uesb.dovic.servicos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;
import br.uesb.dovic.beans.ItemConsulta;
import br.uesb.dovic.beans.Resultado;
import br.uesb.dovic.enums.FuncaoSintatica;
import br.uesb.dovic.enums.Operacao;

public class SearchService {
	//private static String caminho = "C:\\workspace\\DovicWeb\\WebContent\\resources\\buscas\\";
	
	private static final String SEPARATOR  = File.separator;
	private static final String HOME_FOLDER = System.getProperty("user.home");
	private static String caminho = HOME_FOLDER + SEPARATOR + "websinc" + SEPARATOR + "uploads" + SEPARATOR;
	
	private XQDataSource ds;
	private Integer totalOcorrencias;

	public SearchService() {
		
		ds = new SaxonXQDataSource();
		totalOcorrencias = 0;

	}
	
	
	
	// ***************************************************************************
	//
	// BUSCA MORFOLÓGICA
	//
	// ***************************************************************************

	public String buildWhere(List<ItemConsulta> args1, String operacao1,
			Operacao operacaoMorfologica, String n) {
		String arg1 = "", where = "";
		if (operacao1.equals("or")) {
			arg1 = buildArgumentoOrMorfologico(args1, operacaoMorfologica, n);
			if (operacaoMorfologica.equals(Operacao.EXISTS))
				where = " ($s/w/" + arg1 + ")";
			else if (operacaoMorfologica.equals(Operacao.INICIAL))
				where = " ($s/w[1]/" + arg1 + ")";
			else if (operacaoMorfologica.equals(Operacao.FINAL))
				where = " ($s/w[last()-1]/" + arg1 + ")";
			else if (operacaoMorfologica.equals(Operacao.POSICAO))
				where = " ($s/w[" + n + "]/" + arg1 + ")";

		} else if (operacao1.equals("and")) {
			for (ItemConsulta arg_1 : args1)
				if (operacaoMorfologica.equals(Operacao.EXISTS))
					where += " ($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]) and";
				else
					where = " ($s/w[1]/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]) and";
			where = where.substring(0, where.length() - 4);

		}
		return where;
	}

	public String buildWhere(List<ItemConsulta> args1, String operacao1,
			List<ItemConsulta> args2, String operacao2,
			Operacao operacaoMorfologica, String axis, String n) {
		String arg1 = "", arg2 = "", where = "";
		int corte = 4;

		if (operacao1.equals("or") && operacao2.equals("or")) {

			arg1 = buildArgumentoOrMorfologico(args1, operacaoMorfologica, n);
			arg2 = buildArgumentoOrMorfologico(args2, operacaoMorfologica, n);
			if (operacaoMorfologica.equals(Operacao.PRECEDES))
				where = "($s/w/" + arg1 + "/parent::w/following-sibling::w/"
						+ arg2 + ")";
			if (operacaoMorfologica.equals(Operacao.IPRECEDES))
				// ($s/w/m[@v='N']/parent::w/following::w[1]/m[@v='Q'])
				where = "($s/w/" + arg1 + "/parent::w/following-sibling::w[1]/"
						+ arg2 + ")";
			if (operacaoMorfologica.equals(Operacao.VIZINHANCADIREITA))
				where = " ($s/w/" + arg1 + "/parent::w/following-sibling::w["
						+ n + "]/" + arg2 + ")";
			if (operacaoMorfologica.equals(Operacao.VIZINHANCAESQUERDA))
				where = " ($s/w/" + arg1 + "/parent::w/preceding-sibling::w["
						+ n + "]/" + arg2 + ")";
			if (operacaoMorfologica.equals(Operacao.VIZINHANCA))
				where = " ($s/w/" + arg1 + "/parent::w/(preceding-sibling::w["
						+ n + "]|following-sibling::w[" + n + "])/" + arg2
						+ ")";

		}

		if (operacao1.equals("and") && operacao2.equals("or")) {
			arg2 = buildArgumentoOrMorfologico(args2, operacaoMorfologica, n);
			for (ItemConsulta arg_1 : args1)
				if (operacaoMorfologica.equals(Operacao.PRECEDES))
					where += "($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]/parent::w/following-sibling::w/" + arg2
							+ ") and";
				else if (operacaoMorfologica.equals(Operacao.IPRECEDES))
					// ($s/w/m[@v='N']/parent::w/following::w[1]/m[@v='Q'])
					where += "($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]/parent::w/following-sibling::w[1]/" + arg2
							+ ") and";
				else if (operacaoMorfologica.equals(Operacao.VIZINHANCADIREITA))
					where += " ($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]/parent::w/following-sibling::w[" + n + "]/"
							+ arg2 + ") and";
				else if (operacaoMorfologica
						.equals(Operacao.VIZINHANCAESQUERDA))
					where += " ($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]/parent::w/preceding-sibling::w[" + n + "]/"
							+ arg2 + ") and";
				else if (operacaoMorfologica.equals(Operacao.VIZINHANCA))
					where += " ($s/w/m[@v=\"" + arg_1.getEtiqueta().getName()
							+ "\"]/parent::w/(preceding-sibling::w[" + n
							+ "]|following-sibling::w[" + n + "])/" + arg2
							+ ") and";
			where = where.substring(0, where.length() - corte);

		}

		if (operacao2.equals("and")) {
			for (ItemConsulta arg_1 : args1) {
				where += "(";
				for (ItemConsulta arg_2 : args2)
					if (operacaoMorfologica.equals(Operacao.PRECEDES))
						where += "($s/w/" + arg_1.getEtiqueta().getName()
								+ "/parent::w/following-sibling::w/"
								+ arg_2.getEtiqueta().getName() + ") and";
					else if (operacaoMorfologica.equals(Operacao.IPRECEDES))
						where += "($s/w/" + arg_1.getEtiqueta().getName()
								+ "/parent::w/following-sibling::w[1]/"
								+ arg_2.getEtiqueta().getName() + ") and";
					else if (operacaoMorfologica
							.equals(Operacao.VIZINHANCADIREITA))
						where += " ($s/w/" + arg_1.getEtiqueta().getName()
								+ "/parent::w/following-sibling::w[" + n + "]/"
								+ arg_2.getEtiqueta().getName() + ") and";
					else if (operacaoMorfologica
							.equals(Operacao.VIZINHANCAESQUERDA))
						where += " ($s/w/" + arg_1.getEtiqueta().getName()
								+ "/parent::w/preceding-sibling::w[" + n + "]/"
								+ arg_2.getEtiqueta().getName() + ") and";
					else if (operacaoMorfologica.equals(Operacao.VIZINHANCA))
						where += " ($s/w/" + arg_1.getEtiqueta().getName()
								+ "/parent::w/(preceding-sibling::w[" + n
								+ "]|following-sibling::w[" + n + "])/"
								+ arg_2.getEtiqueta().getName() + ") and";
				where = where.substring(0, where.length() - corte);

				if (operacao1.equals("or")) {
					corte = 3;
					where += ") or";
				} else
					where += ") and";
			}
			where = where.substring(0, where.length() - corte);
		}

		return where;
	}

	public String buildArgumentoOrMorfologico(List<ItemConsulta> args,
			Operacao operacao, String n) {

		String result = "";
		if (args.isEmpty())
			return "CONTAINER DE ITENS VAZIO";

		if (args.size() == 1) {
			result=args.get(0).getEtiqueta().getName();
			if (args.get(0).getEtiqueta().isGrupo()) // tem que verificar padrão de expressão regular
				result = "m[@v='*[starts-with(name()," + result.replace("*", "") + "']]";
			else
				result = "m[@v='" + args.get(0).getEtiqueta().getName() + "']";
		} else // mais de 1 item no container
		{
			result += "(";
			for (ItemConsulta item : args)
			{
				if (item.getEtiqueta().isGrupo()) // tem que verificar padrão de expressão regular
					result+= "m[@v='*[starts-with(name()," + item.getEtiqueta().getName().replace("*", "") + "']]|";
				else
					result += "m[@v='" + item.getEtiqueta().getName() + "']|";
				
		    }
			
			
			
			result = result.substring(0, result.length() - 1) + ")";
		}

		return result;

	}

	// ***************************************************************************
	//
	// BUSCA SINTÁTICA
	//
	// ***************************************************************************

	public String buildLet() {
		String result = "";

		return result;
	}

	// 4 argumentos - Funções com apenas 1 lista de argumentos
	public String buildWhere(List<ItemConsulta> args1, String operacao1,
			String n, FuncaoSintatica funcao) {
		String arg1 = "", operador = "=", where = "";
		if (funcao.equals(FuncaoSintatica.IDOMSTOTALMAIOR)
				|| funcao.equals(FuncaoSintatica.DOMSWORDSMAIOR))
			operador = ">";
		else if (funcao.equals(FuncaoSintatica.IDOMSTOTALMENOR)
				|| funcao.equals(FuncaoSintatica.DOMSWORDSMENOR))
			operador = "<";

		if (operacao1.equals("or")) {
			arg1 = buildArgumentoOr(args1);
			if (funcao.equals(FuncaoSintatica.IDOMSTOTAL)
					|| funcao.equals(FuncaoSintatica.IDOMSTOTALMENOR)
					|| funcao.equals(FuncaoSintatica.IDOMSTOTALMAIOR))
				where += " ($ipmat//" + arg1 + "[count(* except (POINT|COMMA))"
						+ operador + n + "])";
			else if (funcao.equals(FuncaoSintatica.DOMSWORDS)
					|| funcao.equals(FuncaoSintatica.DOMSWORDSMAIOR))
				where += " ($ipmat//" + arg1
						+ "[count(descendant::LEAF[@W='yes'])" + operador + n
						+ "])";
			else if (funcao.equals(FuncaoSintatica.DOMSWORDSMENOR))
				where += " ($ipmat//" + arg1
						+ "[count(descendant::LEAF[@W='yes'])" + operador + n
						+ " and count(descendant::LEAF[@W='yes'])>0])";
			else if (funcao.equals(FuncaoSintatica.EXISTS))
				where += " ($ipmat//" + arg1 + ")";
		}

		if (operacao1.equals("and")) {
			for (ItemConsulta arg_1 : args1)
				if (funcao.equals(FuncaoSintatica.IDOMSTOTAL)
						|| funcao.equals(FuncaoSintatica.IDOMSTOTALMENOR)
						|| funcao.equals(FuncaoSintatica.IDOMSTOTALMAIOR))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "[count(* except (POINT|COMMA))" + operador + n
							+ "]) and";
				else if (funcao.equals(FuncaoSintatica.DOMSWORDS)
						|| funcao.equals(FuncaoSintatica.DOMSWORDSMAIOR))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "[count(descendant::LEAF[@W='yes'])" + operador
							+ n + "]) and";
				else if (funcao.equals(FuncaoSintatica.DOMSWORDSMENOR))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "[count(descendant::LEAF[@W='yes'])" + operador
							+ n
							+ " and count(descendant::LEAF[@W='yes'])>0]) and";

				else if (funcao.equals(FuncaoSintatica.EXISTS))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ ") and";
			where = where.substring(0, where.length() - 4);

		}

		return where;

	}

	// 4 argumentos - Funções sem axis
	public String buildWhere(List<ItemConsulta> args1,
			List<ItemConsulta> args2, String operacao1, String operacao2,
			FuncaoSintatica funcao) {
		String arg1 = "", arg2 = "", where = "";
		int corte = 4;
		if (operacao1.equals("or") && operacao2.equals("or")) {
			arg1 = buildArgumentoOr(args1);
			arg2 = buildArgumentoOr(args2);
			if (funcao.equals(FuncaoSintatica.IDOMINATES))
				where = " ($ipmat//" + arg1 + "/" + arg2 + ")";
			else if (funcao.equals(FuncaoSintatica.IDOMSONLY))
				where = " (($ipmat//" + arg1 + "/" + arg2 + "/parent::" + arg1
						+ "[count(*)=1]))";

		}

		if (operacao1.equals("and") && operacao2.equals("or")) {
			arg2 = buildArgumentoOr(args2);
			for (ItemConsulta arg_1 : args1)
				if (funcao.equals(FuncaoSintatica.IDOMINATES))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName() + "/"
							+ arg2 + ") and";
				else if (funcao.equals(FuncaoSintatica.IDOMSONLY))
					where += " (($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/" + arg2 + "/parent::"
							+ arg_1.getEtiqueta().getName()
							+ "[count(*)=1])) and";
			where = where.substring(0, where.length() - corte);

		}

		if (operacao2.equals("and")) {
			for (ItemConsulta arg_1 : args1) {
				where += "(";
				for (ItemConsulta arg_2 : args2)
					if (funcao.equals(FuncaoSintatica.IDOMINATES))
						where += " ($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/" + arg_2.getEtiqueta().getName() + ") and";
					else if (funcao.equals(FuncaoSintatica.IDOMSONLY))
						where += " (" + "($ipmat//"
								+ arg_1.getEtiqueta().getName() + "/"
								+ arg_2.getEtiqueta().getName() + "/parent::"
								+ arg_1.getEtiqueta().getName()
								+ "[count(*)=1])) and";

				where = where.substring(0, where.length() - corte);

				if (operacao1.equals("or")) {
					corte = 3;
					where += ") or";
				} else
					where += ") and";
			}
			where = where.substring(0, where.length() - corte);
		}

		return where;

	}

	// 8 argumentos - Funções com axis
	public String buildWhere(List<ItemConsulta> args1,
			List<ItemConsulta> args2, String operacao1, String operacao2,
			String axis1, String axis2, FuncaoSintatica funcao, String n) {
		String arg1 = "", arg2 = "", arg3 = "", arg4 = "", where = "";
		int corte = 4;
		if (funcao.equals(FuncaoSintatica.IPRECEDES)
				|| funcao.equals(FuncaoSintatica.EIRMAO)
				|| funcao.equals(FuncaoSintatica.CCOMMANDS))
			arg3 = buildArgumentoOr(args2, axis2);
		if (funcao.equals(FuncaoSintatica.CCOMMANDS))
			arg4 = buildArgumentoOr(args2, "descendant");
		if (operacao1.equals("or") && operacao2.equals("or")) {
			arg1 = buildArgumentoOr(args1);
			arg2 = buildArgumentoOr(args2, axis1);
			if (funcao.equals(FuncaoSintatica.DOMINATES))
				where = " ($ipmat//" + arg1 + "/" + arg2 + ")";
			else if (funcao.equals(FuncaoSintatica.PRECEDES))
				where = "(($ipmat//" + arg1 + "/following::ID[1]/LEAF[@v])"
						+ "=($ipmat//" + arg1 + "/" + arg2
						+ "/following::ID[1]/LEAF[@v]))";
			else if (funcao.equals(FuncaoSintatica.IDOMSFIRST))
				where = " ($ipmat//" + arg1 + "/*[1][" + arg2 + "])";
			else if (funcao.equals(FuncaoSintatica.IDOMSLAST))
				where = " ($ipmat//" + arg1 + "/*[last()][" + arg2 + "])";
			else if (funcao.equals(FuncaoSintatica.IDOMSNUMBER))
				where = " ($ipmat//" + arg1 + "/*[" + n + "][" + arg2 + "])";
			else if (funcao.equals(FuncaoSintatica.IPRECEDES))
				where = " (($ipmat//" + arg1 + "/following-sibling::*[1]["
						+ arg2 + "]) or ($ipmat//" + arg1
						+ "/following-sibling::*[1]/" + arg3
						+ "[position()=1]))";
			else if (funcao.equals(FuncaoSintatica.EIRMAO))
				where = " ($ipmat//((" + arg1 + "/" + arg2 + ")|(" + arg1 + "/"
						+ arg3 + ")))";
			else if (funcao.equals(FuncaoSintatica.CCOMMANDS))
				where = " ($ipmat//((" + arg1 + "/" + arg2 + ")|(" + arg1 + "/"
						+ arg3 + ")|(" + arg1 + "/following-sibling::*/" + arg4
						+ ")|(" + arg1 + "/preceding-sibling::*/" + arg4
						+ ")))";

		} else if (operacao1.equals("and") && operacao2.equals("or")) {
			arg2 = buildArgumentoOr(args2, axis1);
			for (ItemConsulta arg_1 : args1)
				if (funcao.equals(FuncaoSintatica.DOMINATES))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName() + "/"
							+ arg2 + ") and";
				else if (funcao.equals(FuncaoSintatica.PRECEDES))
					where += "(($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/following::ID[1]/LEAF[@v])" + "=($ipmat//"
							+ arg_1.getEtiqueta().getName() + "/" + arg2
							+ "/following::ID[1]/LEAF[@v])) and";
				else if (funcao.equals(FuncaoSintatica.IDOMSFIRST))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/*[1][" + arg2 + "]) and";
				else if (funcao.equals(FuncaoSintatica.IDOMSLAST))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/*[last()][" + arg2 + "]) and";
				else if (funcao.equals(FuncaoSintatica.IDOMSNUMBER))
					where += " ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/*[" + n + "][" + arg2 + "]) and";
				else if (funcao.equals(FuncaoSintatica.IPRECEDES))
					where += " (($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/following-sibling::*[1][" + arg2
							+ "]) or ($ipmat//" + arg_1.getEtiqueta().getName()
							+ "/following-sibling::*[1]/" + arg3
							+ "[position()=1])) and";
				else if (funcao.equals(FuncaoSintatica.EIRMAO))
					where += " ($ipmat//((" + arg_1.getEtiqueta().getName()
							+ "/" + arg2 + ")|("
							+ arg_1.getEtiqueta().getName() + "/" + arg3
							+ "))) and";
				else if (funcao.equals(FuncaoSintatica.CCOMMANDS))
					where += " ($ipmat//((" + arg_1.getEtiqueta().getName()
							+ "/" + arg2 + ")|("
							+ arg_1.getEtiqueta().getName() + "/" + arg3
							+ ")|(" + arg_1.getEtiqueta().getName()
							+ "/following-sibling::*/" + arg4 + ")|("
							+ arg_1.getEtiqueta().getName()
							+ "/preceding-sibling::*/" + arg4 + "))) and";
			where = where.substring(0, where.length() - corte);

		}

		else if (operacao2.equals("and")) {
			for (ItemConsulta arg_1 : args1) {
				where += "(";
				for (ItemConsulta arg_2 : args2)
					if (funcao.equals(FuncaoSintatica.DOMINATES))
						where += " ($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + ") and";
					else if (funcao.equals(FuncaoSintatica.PRECEDES))
						where = "(($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/following::ID[1]/LEAF[@v])" + "=($ipmat//"
								+ arg_1.getEtiqueta().getName() + "/" + axis1
								+ "::" + arg_2.getEtiqueta().getName()
								+ "/following::ID[1]/LEAF[@v])) and";
					else if (funcao.equals(FuncaoSintatica.IDOMSFIRST))
						where += " ($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/*[1][" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + "]) and";
					else if (funcao.equals(FuncaoSintatica.IDOMSLAST))
						where += " ($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/*[last()][" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + "]) and";
					else if (funcao.equals(FuncaoSintatica.IDOMSNUMBER))
						where += " ($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/*[" + n + "][" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + "]) and";
					else if (funcao.equals(FuncaoSintatica.IPRECEDES))
						where += " (($ipmat//" + arg_1.getEtiqueta().getName()
								+ "/following-sibling::*[1][" + axis1 + "::"
								+ arg_2.getEtiqueta().getName()
								+ "]) or ($ipmat//"
								+ arg_1.getEtiqueta().getName()
								+ "/following-sibling::*[1]/" + axis2 + "::"
								+ arg_2.getEtiqueta().getName()
								+ "[position()=1])) and";
					else if (funcao.equals(FuncaoSintatica.EIRMAO))
						where += " ($ipmat//((" + arg_1.getEtiqueta().getName()
								+ "/" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + ")|("
								+ arg_1.getEtiqueta().getName() + "/" + axis2
								+ "::" + arg_2.getEtiqueta().getName()
								+ "))) and";
					else if (funcao.equals(FuncaoSintatica.CCOMMANDS))
						where += " ($ipmat//((" + arg_1.getEtiqueta().getName()
								+ "/" + axis1 + "::"
								+ arg_2.getEtiqueta().getName() + ")|("
								+ arg_1.getEtiqueta().getName() + "/" + axis2
								+ "::" + arg_2.getEtiqueta().getName() + ")|("
								+ arg_1.getEtiqueta().getName()
								+ "/following-sibling::*/descendant::"
								+ arg_2.getEtiqueta().getName() + ")|("
								+ arg_1.getEtiqueta().getName()
								+ "/preceding-sibling::*/descendant::"
								+ arg_2.getEtiqueta().getName() + "))) and";

				where = where.substring(0, where.length() - 4);
				if (operacao1.equals("or")) {
					where += ") or";
					corte = 3;
				} else
					where += ") and";
			}
			where = where.substring(0, where.length() - corte);
		}

		return where;

	}
	
	
	//************************************
	// Esta função não está mais sendo chamada
	//************************************
	public String buildWhere(FuncaoSintatica funcaoSintatica, String arg1,
			String arg2, String n) {

		if (funcaoSintatica.equals(FuncaoSintatica.EXISTS))
			// exists($ipmat//CONJ-NEG)
			return " exists($ipmat//" + arg1 + ")";
		else if (funcaoSintatica.equals(FuncaoSintatica.DOMINATES))
			// exists($ipmat//NP-ACC/descendant::NP)
			return " exists($ipmat//" + arg1 + "/descendant::" + arg2 + ")";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMINATES))
			// exists($ipmat//PP/P)
			return " exists($ipmat//" + arg1 + "/" + arg2 + ")";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSFIRST))
			// exists($ipmat//NP/*[1][self::D-F])
			return " exists($ipmat//" + arg1 + "/*[1][self::" + arg2 + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSLAST))
			// exists($ipmat//NP/*[last()][self::PP])
			return " exists($ipmat//" + arg1 + "/*[last()][self::" + arg2
					+ "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSNUMBER))
			// exists($ipmat//NP/*[2][self::N])
			return " exists($ipmat//" + arg1 + "/*[" + n + "][self::" + arg2
					+ "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSTOTAL))
			// exists($ipmat//NP[count(*)=3])
			return " exists($ipmat//" + arg1 + "[count(*)=" + n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSTOTALMENOR))
			return " exists($ipmat//" + arg1 + "[count(*)<" + n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSTOTALMAIOR))
			return " exists($ipmat//" + arg1 + "[count(*)>" + n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.IDOMSONLY))
			// SEM CONSIDERAR NÓ TEXTO:
			// " exists($ipmat//ADJX/ADJ) and ($ipmat//ADJX[count(*)=1])";
			return " (exists($ipmat//" + arg1 + "/" + arg2 + ") and ($ipmat//"
					+ arg1 + "[count(*)=1]))";
		else if (funcaoSintatica.equals(FuncaoSintatica.EIRMAO))
			// " exists($ipmat//((P/following-sibling::NP)|(P/preceding-sibling::NP))) ";
			return " exists($ipmat//((" + arg1 + "/following-sibling::" + arg2
					+ ")|(" + arg1 + "/preceding-sibling::" + arg2 + ")))";
		else if (funcaoSintatica.equals(FuncaoSintatica.DOMSWORDS))
			// ($ipmat//XXX[count(descendant::GGG)=2])
			return " ($ipmat//" + arg1 + "[count(descendant::LEAF[@W='yes'])="
					+ n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.DOMSWORDSMENOR))
			return " ($ipmat//" + arg1 + "[count(descendant::LEAF[@W='yes'])<"
					+ n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.DOMSWORDSMAIOR))
			return " ($ipmat//" + arg1 + "[count(descendant::LEAF[@W='yes'])>"
					+ n + "])";
		else if (funcaoSintatica.equals(FuncaoSintatica.PRECEDES))
			// exists($ipmat//XXX/following::BRUNO)";
			return " ($ipmat//" + arg1 + "/following::" + arg2 + ")";
		else if (funcaoSintatica.equals(FuncaoSintatica.IPRECEDES))
			// (($ipmat//NPR-P/following-sibling::*[1][self::S]) or
			// ($ipmat//NPR-P/following-sibling::*[1]/descendant::S[position()=1]))
			return " (($ipmat//" + arg1 + "/following-sibling::*[1][self::"
					+ arg2 + "]) or ($ipmat//" + arg1
					+ "/following-sibling::*[1]/descendant::" + arg2
					+ "[position()=1]))";
		else if (funcaoSintatica.equals(FuncaoSintatica.CCOMMANDS))
			//
			return " exists ($ipmat//((" + arg1 + "/following-sibling::" + arg2
					+ ")|(" + arg1 + "/preceding-sibling::" + arg2 + ")|("
					+ arg1 + "/following-sibling::*/descendant::" + arg2
					+ ")|(" + arg1 + "/preceding-sibling::*/descendant::"
					+ arg2 + ")))";

		return "";

	}

	public String buildArgumentoOr(List<ItemConsulta> args) {

		String result = "";
		if (args.isEmpty())
			return "CONTAINER DE ITENS VAZIO";

		if (args.size() == 1) {
			result = args.get(0).getEtiqueta().getName();
			if (result.equals("LEAF"))
				result = "LEAF[@v='" + args.get(0).getValor() + "']";
			if (args.get(0).getEtiqueta().isGrupo()) // tem que verificar padrão de expressão regular
				result = "*[starts-with(name(),'" + result.replace("*", "")
						+ "')]";

		} else {
			result = "(";
			for (ItemConsulta item : args) {
				if (item.getEtiqueta().getName().equals("LEAF"))
					result += "LEAF[@v='" + item.getValor() + "']|";
				else if (item.getEtiqueta().isGrupo()) // tem que verificar
														// padrão de expressão
														// regular
					result += "*[starts-with(name(),'"
							+ item.getEtiqueta().getName().replace("*", "")
							+ "')]|";
				else
					result += item.getEtiqueta().getName() + "|";
			}
			result = result.substring(0, result.length() - 1) + ")";
		}

		return result;

	}

	public String buildArgumentoOr(List<ItemConsulta> args, String axis) {

		String result = "";
		if (args.isEmpty())
			return "CONTAINER DE ITENS VAZIO";

		if (args.size() == 1)
			if (args.get(0).getEtiqueta().isGrupo()) // tem que verificar padrão
														// de expressão regular
				result = "*[starts-with(name(),'"
						+ args.get(0).getEtiqueta().getName().replace("*", "")
						+ "')]";
			else
				result = axis + "::" + args.get(0).getEtiqueta().getName();
		else {
			result = "(";
			for (ItemConsulta item : args)
				if (item.getEtiqueta().isGrupo()) // tem que verificar padrão de
													// expressão regular
					result += axis + "::*[starts-with(name(),'"
							+ item.getEtiqueta().getName().replace("*", "")
							+ "')]|";
				else
					result += axis + "::" + item.getEtiqueta().getName() + "|";

			result = result.substring(0, result.length() - 1) + ")";
		}

		return result;

	}

	public List<Resultado> buscaSintatica(List<ItemConsulta> argumentos1,
			List<ItemConsulta> argumentos2, List<ItemConsulta> argumentos3,
			List<ItemConsulta> argumentos4, List<ItemConsulta> argumentos5,
			List<ItemConsulta> argumentos6, String operacaoLogica1,
			String operacaoLogica2, String operacaoLogica3,
			String operacaoLogica4, String operacaoLogica5,
			String operacaoLogica6, FuncaoSintatica funcaoSintatica1,
			FuncaoSintatica funcaoSintatica2, FuncaoSintatica funcaoSintatica3,
			FuncaoSintatica funcaoSintatica5, boolean negacao1,
			boolean negacao2, boolean negacao3, boolean negacao5, String n1,
			String n2, String n3, String n5, String arquivo) {
		String result = "", sentenca = "", where = "", arvore = "";
		String expStr = "";
		Integer ocorrenciasDaSentenca = 0, id = 1;
		int i = 1, j = 0;
		List<Resultado> resultado = new ArrayList<Resultado>();

		if (funcaoSintatica1.equals(FuncaoSintatica.IDOMINATES)
				|| funcaoSintatica1.equals(FuncaoSintatica.IDOMSONLY))
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, funcaoSintatica1);

		if (funcaoSintatica1.equals(FuncaoSintatica.DOMINATES))
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, "descendant", "", funcaoSintatica1, n1);

		if (funcaoSintatica1.equals(FuncaoSintatica.PRECEDES))
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, "following", "", funcaoSintatica1, n1);

		if (funcaoSintatica1.equals(FuncaoSintatica.EIRMAO)
				|| funcaoSintatica1.equals(FuncaoSintatica.CCOMMANDS))
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, "following-sibling", "preceding-sibling",
					funcaoSintatica1, n1);

		if (funcaoSintatica1.equals(FuncaoSintatica.IPRECEDES)) {
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, "self", "descendant", funcaoSintatica1, n1);
		}
		if (funcaoSintatica1.equals(FuncaoSintatica.IDOMSFIRST)
				|| funcaoSintatica1.equals(FuncaoSintatica.IDOMSLAST)
				|| funcaoSintatica1.equals(FuncaoSintatica.IDOMSNUMBER)) {
			where += buildWhere(argumentos1, argumentos2, operacaoLogica1,
					operacaoLogica2, "self", "", funcaoSintatica1, n1);
		}

		if (funcaoSintatica1.equals(FuncaoSintatica.IDOMSTOTAL)
				|| funcaoSintatica1.equals(FuncaoSintatica.IDOMSTOTALMENOR)
				|| funcaoSintatica1.equals(FuncaoSintatica.IDOMSTOTALMAIOR)
				|| funcaoSintatica1.equals(FuncaoSintatica.DOMSWORDS)
				|| funcaoSintatica1.equals(FuncaoSintatica.DOMSWORDSMENOR)
				|| funcaoSintatica1.equals(FuncaoSintatica.EXISTS)
				|| funcaoSintatica1.equals(FuncaoSintatica.DOMSWORDSMAIOR)) {
			where += buildWhere(argumentos1, operacaoLogica1, n1,
					funcaoSintatica1);
		}

		if (funcaoSintatica2 != null) {
			if (funcaoSintatica2.equals(FuncaoSintatica.IDOMINATES)
					|| funcaoSintatica2.equals(FuncaoSintatica.IDOMSONLY))
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, funcaoSintatica2);
			if (funcaoSintatica2.equals(FuncaoSintatica.DOMINATES))
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, "descendant", "",
								funcaoSintatica2, n2);

			if (funcaoSintatica2.equals(FuncaoSintatica.PRECEDES))
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, "", "", funcaoSintatica2, n2);

			if (funcaoSintatica2.equals(FuncaoSintatica.EIRMAO)
					|| funcaoSintatica2.equals(FuncaoSintatica.CCOMMANDS))
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, "following-sibling",
								"preceding-sibling", funcaoSintatica2, n2);

			if (funcaoSintatica2.equals(FuncaoSintatica.IPRECEDES)) {
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, "self", "descendant",
								funcaoSintatica2, n2);
			}
			if (funcaoSintatica2.equals(FuncaoSintatica.IDOMSFIRST)
					|| funcaoSintatica2.equals(FuncaoSintatica.IDOMSLAST)
					|| funcaoSintatica2.equals(FuncaoSintatica.IDOMSNUMBER)) {
				where += " and "
						+ buildWhere(argumentos2, argumentos3, operacaoLogica2,
								operacaoLogica3, "self", "", funcaoSintatica2,
								n2);
			}

			if (funcaoSintatica2.equals(FuncaoSintatica.IDOMSTOTAL)
					|| funcaoSintatica2.equals(FuncaoSintatica.IDOMSTOTALMENOR)
					|| funcaoSintatica2.equals(FuncaoSintatica.IDOMSTOTALMAIOR)
					|| funcaoSintatica2.equals(FuncaoSintatica.DOMSWORDS)
					|| funcaoSintatica2.equals(FuncaoSintatica.DOMSWORDSMENOR)
					|| funcaoSintatica2.equals(FuncaoSintatica.EXISTS)
					|| funcaoSintatica2.equals(FuncaoSintatica.DOMSWORDSMAIOR)) {
				where += " and "
						+ buildWhere(argumentos2, operacaoLogica2, n2,
								funcaoSintatica2);
			}
		}

		// FUNÇÃO 3

		if (funcaoSintatica3 != null) {
			if (funcaoSintatica3.equals(FuncaoSintatica.IDOMINATES)
					|| funcaoSintatica3.equals(FuncaoSintatica.IDOMSONLY))
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, funcaoSintatica3);
			if (funcaoSintatica3.equals(FuncaoSintatica.DOMINATES))
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, "descendant", "",
								funcaoSintatica3, n3);

			if (funcaoSintatica3.equals(FuncaoSintatica.PRECEDES))
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, "following", "",
								funcaoSintatica3, n3);

			if (funcaoSintatica3.equals(FuncaoSintatica.EIRMAO)
					|| funcaoSintatica3.equals(FuncaoSintatica.CCOMMANDS))
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, "following-sibling",
								"preceding-sibling", funcaoSintatica3, n3);

			if (funcaoSintatica3.equals(FuncaoSintatica.IPRECEDES)) {
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, "self", "descendant",
								funcaoSintatica3, n3);
			}
			if (funcaoSintatica3.equals(FuncaoSintatica.IDOMSFIRST)
					|| funcaoSintatica3.equals(FuncaoSintatica.IDOMSLAST)
					|| funcaoSintatica3.equals(FuncaoSintatica.IDOMSNUMBER)) {
				where += " and "
						+ buildWhere(argumentos3, argumentos4, operacaoLogica3,
								operacaoLogica4, "self", "", funcaoSintatica3,
								n3);
			}

			if (funcaoSintatica3.equals(FuncaoSintatica.IDOMSTOTAL)
					|| funcaoSintatica3.equals(FuncaoSintatica.IDOMSTOTALMENOR)
					|| funcaoSintatica3.equals(FuncaoSintatica.IDOMSTOTALMAIOR)
					|| funcaoSintatica3.equals(FuncaoSintatica.DOMSWORDS)
					|| funcaoSintatica3.equals(FuncaoSintatica.DOMSWORDSMENOR)
					|| funcaoSintatica3.equals(FuncaoSintatica.EXISTS)
					|| funcaoSintatica3.equals(FuncaoSintatica.DOMSWORDSMAIOR)) {
				where += " and "
						+ buildWhere(argumentos3, operacaoLogica3, n3,
								funcaoSintatica3);
			}
		}

		// FUNÇÃO 5

		if (funcaoSintatica5 != null) {
			if (funcaoSintatica5.equals(FuncaoSintatica.IDOMINATES)
					|| funcaoSintatica5.equals(FuncaoSintatica.IDOMSONLY))
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, funcaoSintatica5);
			if (funcaoSintatica5.equals(FuncaoSintatica.DOMINATES))
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, "descendant", "",
								funcaoSintatica5, n5);

			if (funcaoSintatica5.equals(FuncaoSintatica.PRECEDES))
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, "", "", funcaoSintatica5, n5);

			if (funcaoSintatica5.equals(FuncaoSintatica.EIRMAO)
					|| funcaoSintatica5.equals(FuncaoSintatica.CCOMMANDS))
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, "following-sibling",
								"preceding-sibling", funcaoSintatica5, n5);

			if (funcaoSintatica5.equals(FuncaoSintatica.IPRECEDES)) {
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, "self", "descendant",
								funcaoSintatica5, n5);
			}
			if (funcaoSintatica5.equals(FuncaoSintatica.IDOMSFIRST)
					|| funcaoSintatica5.equals(FuncaoSintatica.IDOMSLAST)
					|| funcaoSintatica5.equals(FuncaoSintatica.IDOMSNUMBER)) {
				where += " and "
						+ buildWhere(argumentos5, argumentos6, operacaoLogica5,
								operacaoLogica6, "self", "", funcaoSintatica5,
								n5);
			}

			if (funcaoSintatica5.equals(FuncaoSintatica.IDOMSTOTAL)
					|| funcaoSintatica5.equals(FuncaoSintatica.IDOMSTOTALMENOR)
					|| funcaoSintatica5.equals(FuncaoSintatica.IDOMSTOTALMAIOR)
					|| funcaoSintatica5.equals(FuncaoSintatica.DOMSWORDS)
					|| funcaoSintatica5.equals(FuncaoSintatica.DOMSWORDSMENOR)
					|| funcaoSintatica5.equals(FuncaoSintatica.EXISTS)
					|| funcaoSintatica5.equals(FuncaoSintatica.DOMSWORDSMAIOR)) {
				where += " and "
						+ buildWhere(argumentos5, operacaoLogica5, n5,
								funcaoSintatica5);
			}
		}
		try {

			XQConnection conn = ds.getConnection();

			String ocorrencias = where.trim();
			if (negacao1 == true) {
				where = "where not" + where;
				ocorrencias = "not" + ocorrencias;

			} else
				where = "where " + where;
			
			String uri=FileService.convertToFileURL(caminho+arquivo);
			//expStr += "for $ipmat in fn:doc('C:/workspace/DovicWeb/WebContent/resources/arquivosxml/g_008_newxml5.xml')";
			expStr += "for $ipmat in fn:doc('"+uri+"')";
			expStr += "//DOCUMENTO/(*[starts-with(name(),'IP-MAT')]|IP-IMP|IP-SUB|FRAG|CP-THT|CONJP)";
			expStr += " let $sentenca := fn:string($ipmat)";
			expStr += " let $arvore := ($ipmat)";
			expStr += " let $ocorrencias:=count(" + ocorrencias + ")";
			expStr += where;
			expStr += " return ($ocorrencias, $sentenca, $arvore)";

			XQPreparedExpression exp = conn.prepareExpression(expStr);
			XQResultSequence resultSequence = exp.executeQuery();
			
			//System.out.print(expStr);

			while (resultSequence.next()) {

				result = resultSequence.getItemAsString(null);

				result = result.replaceAll("(\n)+", " ");
				if (i % 3 != 0)// se não for a árvore, remove sujeitos nulos
				{
					result = result.replaceAll(
							"\\*(T|ICH|exp|arb|pro)\\*(-\\d)?", "");// excluir
																	// sujeitos
																	// nulos
					result = result.replaceAll("\\*-\\d", "");
				}

				if (j % 3 == 0) // se for o número de ocorrências, grava em
								// ocorrências
					ocorrenciasDaSentenca = Integer.parseInt(result);
				else if (i % 3 == 0) // se for a árvore, grava em árvore
					arvore = result;
				else
					// se for a sentença, grava em sentença
					sentenca = result;

				if (i % 3 == 0) // a cada três vezes, insere resultado na lista
				{
					resultado.add(new Resultado(id, sentenca, arvore,
							ocorrenciasDaSentenca));
					id++;
				}

				i++;
				j++;

			}
			conn.close();
			resultSequence.close();
			totalOcorrencias = (i / 3);

			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Resultado> buscaMorfologica(List<ItemConsulta> argumentos1,
			List<ItemConsulta> argumentos2, List<ItemConsulta> argumentos3,
			List<ItemConsulta> argumentos4, List<ItemConsulta> argumentos5,
			List<ItemConsulta> argumentos6, String operacaoLogica1,
			String operacaoLogica2, String operacaoLogica3,
			String operacaoLogica4, String operacaoLogica5,
			String operacaoLogica6, Operacao operacaoMorfologica1,
			Operacao operacaoMorfologica2, Operacao operacaoMorfologica3,
			Operacao operacaoMorfologica5, boolean negacao1, boolean negacao2,
			boolean negacao3, boolean negacao5, String n1, String n2,
			String n3, String n5, String arquivo) {
		String result = "", sentenca="", where = "", arvore = "";
		String expStr = "";

		
		int i = 1;
		List<Resultado> resultado = new ArrayList<Resultado>();

		if (operacaoMorfologica1.equals(Operacao.EXISTS)
				|| operacaoMorfologica1.equals(Operacao.FINAL)
				|| operacaoMorfologica1.equals(Operacao.INICIAL)
				|| operacaoMorfologica1.equals(Operacao.POSICAO))
			where += buildWhere(argumentos1, operacaoLogica1,
					operacaoMorfologica1, n1);

		if (operacaoMorfologica1.equals(Operacao.PRECEDES)
				|| operacaoMorfologica1.equals(Operacao.IPRECEDES)
				|| operacaoMorfologica1.equals(Operacao.VIZINHANCADIREITA)
				|| operacaoMorfologica1.equals(Operacao.VIZINHANCAESQUERDA)
				|| operacaoMorfologica1.equals(Operacao.VIZINHANCA))
			where += buildWhere(argumentos1, operacaoLogica1, argumentos2,
					operacaoLogica2, operacaoMorfologica1, "", n1);

		if (operacaoMorfologica2 != null) {
			if (operacaoMorfologica2.equals(Operacao.PRECEDES)
					|| operacaoMorfologica2.equals(Operacao.IPRECEDES)
					|| operacaoMorfologica2.equals(Operacao.VIZINHANCADIREITA)
					|| operacaoMorfologica2.equals(Operacao.VIZINHANCAESQUERDA)
					|| operacaoMorfologica2.equals(Operacao.VIZINHANCA))
				where += " and "
						+ buildWhere(argumentos2, operacaoLogica2, argumentos3,
								operacaoLogica3, operacaoMorfologica2, "", n2);
		}

		if (operacaoMorfologica3 != null) {
			if (operacaoMorfologica3.equals(Operacao.PRECEDES)
					|| operacaoMorfologica3.equals(Operacao.IPRECEDES)
					|| operacaoMorfologica3.equals(Operacao.VIZINHANCADIREITA)
					|| operacaoMorfologica3.equals(Operacao.VIZINHANCAESQUERDA)
					|| operacaoMorfologica3.equals(Operacao.VIZINHANCA))
				where += " and "
						+ buildWhere(argumentos3, operacaoLogica3, argumentos4,
								operacaoLogica4, operacaoMorfologica3, "", n3);
		}

		if (operacaoMorfologica5 != null) {
			if (operacaoMorfologica5.equals(Operacao.EXISTS)
					|| operacaoMorfologica5.equals(Operacao.FINAL)
					|| operacaoMorfologica5.equals(Operacao.INICIAL)
					|| operacaoMorfologica5.equals(Operacao.POSICAO))
				where += " and "
						+ buildWhere(argumentos5, operacaoLogica5,
								operacaoMorfologica5, n5);

			if (operacaoMorfologica5.equals(Operacao.PRECEDES)
					|| operacaoMorfologica5.equals(Operacao.IPRECEDES)
					|| operacaoMorfologica5.equals(Operacao.VIZINHANCADIREITA)
					|| operacaoMorfologica5.equals(Operacao.VIZINHANCAESQUERDA)
					|| operacaoMorfologica5.equals(Operacao.VIZINHANCA))
				where += " and "
						+ buildWhere(argumentos5, operacaoLogica5, argumentos6,
								operacaoLogica5, operacaoMorfologica5, "", n5);
		}
		try {

			XQConnection conn = ds.getConnection();

			String ocorrencias = where.trim().replaceAll("exists", "");

			if (negacao1 == true) {
				where = "where not" + where;
				ocorrencias = "not " + ocorrencias;

			} else
				where = "where " + where;
			
			String uri=FileService.convertToFileURL(caminho+arquivo);
			//String uri=convertToFileURL(caminho+"1.29.xml");
			expStr += "for $s in fn:doc('"+uri+"')//document/body/text/sc/p[not(@t)]/s";
			expStr += " let $sentenca:= data($s/w/o)";
			expStr += " let $ocorrencias:=count(" + ocorrencias + ")";
			expStr += " let $arvore := ($s)";
			expStr += where;
			expStr += "  return <li>{$sentenca}<enter/> {$arvore}</li>";

			XQPreparedExpression exp = conn.prepareExpression(expStr);
			XQResultSequence resultSequence = exp.executeQuery();
			//System.out.print(expStr);

			while (resultSequence.next()) {

				result = resultSequence.getItemAsString(null);
				result = result.replaceAll("(\n)+", " ");
				arvore=result.substring(result.indexOf("<enter/>"));
				arvore=arvore.replace("<enter/>","");
				arvore=arvore.replace("</li>","");
				sentenca=result.substring(0,result.indexOf("<enter/>"));
				sentenca = sentenca.replaceAll("(<li>|</li>)", "");

				resultado.add(new Resultado(i, sentenca, arvore,i));
				
				i++;
				

			}

			conn.close();
			resultSequence.close();
			totalOcorrencias = i-1;

			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}



	

	public Integer getTotalOcorrencias() {
		return totalOcorrencias;
	}

	public void setTotalOcorrencias(Integer totalOcorrencias) {
		this.totalOcorrencias = totalOcorrencias;
	}

}
