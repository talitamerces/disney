package br.uesb.dovic.testes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.uesb.dovic.entidades.Autor;

import br.uesb.dovic.entidades.DocumentoMicro;
import br.uesb.dovic.entidades.Imagem;
import br.uesb.dovic.entidades.Usuario;
import br.uesb.dovic.enums.TipoImagem;
import br.uesb.dovic.jpa.EntityManagerUtil;

import br.uesb.dovic.modelo.DAOUsuario;
import br.uesb.dovic.servicos.Criptografia;


public class ClasseTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//				EntityManager em= EntityManagerUtil.getEntityManager();
//				System.out.println("teste");
//				DAOColaborador<Colaborador> dao=new DAOColaborador<Colaborador>();
//				DAOUsuario<Usuario> daou=new DAOUsuario<Usuario>();
//				DAOTipoMacro<TipoDocumentoMacro> dao= new DAOTipoMacro<TipoDocumentoMacro>();
//				DAOCidade<Cidade> cidade= new DAOCidade<Cidade>();
//				DAOEstado<Estado> estado=new DAOEstado<Estado>();
//				
//				List<TipoDocumentoMacro> tipos= dao.listarTodos();
//				estado.iniciarTransacao();
//				List<Estado> estados = estado.getByPais(2);
//				estado.commitTransacao();
				
				
								
//				for (Estado e: estados)
//				{
//					System.out.println(e.getId()+"- "+ e.getNomeEstado());
//					
//					
//				}
//				DAOMaterialCapaForro<MaterialCapaForro> daoM= new DAOMaterialCapaForro<MaterialCapaForro>();
//				DAODocumentoMacro<DocumentoMacro> daoDM= new DAODocumentoMacro<DocumentoMacro>();
//				MaterialCapaForro m=em.find(MaterialCapaForro.class,2);
//				
//				
//				MaterialCapaForro m2=em.find(MaterialCapaForro.class,3);
//				
//				List<MaterialCapaForro> lista = new ArrayList<MaterialCapaForro>();
//				lista.add(m);
//				lista.add(m2);
//				
////				i.setCodigoImagem("123");
////				i.setEnderecoImagem("nova imagem com dao");
////				i.setTipoImagem(new TipoImagem(3, "CAPA"));
//				
//				DocumentoMacro dm=new DocumentoMacro();
//				dm.setTitulo("Com lista de forro de novo");
//				dm.setMateriaisForro(lista);
////				dm.setIdDocumentoFisico("1-1958");
////				dm.setTipoMacro(tdm);
////				dm.setImagemCapa(i);
////				TipoImagem ti=new TipoImagem();
////				ti.setDescricaoTipoImagem("CAPA");
//				
//				
////				TrabalhoCorpus t1=new TrabalhoCorpus();
////				t1.setTipoTrabalhoCorpus(1);
////				t1.setDataInicioTrabalho(Calendar.getInstance());
////				t1.setDataFimTrabalho(Calendar.getInstance());
//				Colaborador c1=new Colaborador();
//				//c1.setAtivoColaborador(true);
//				c1.setNomeColaborador("Aline");
//				c1.setEmailColaborador("aline@ifba.edu.br");
//				c1.setLoginColaborador("aline");
//				c1.setSenhaColaborador("senha");
//				
			
//				Colaborador c2=new Colaborador();
//				//c2.setAtivoColaborador(true);
//				c2.setNomeColaborador("Bruno");
//				c2.setEmailColaborador("bruno@ifba.edu.br");
//				c2.setLoginColaborador("bruno");
//				c2.setSenhaColaborador("senha");
////				
////				t1.adicionarColaborador(c1);
////				t1.adicionarColaborador(c2);
////				
////		
				
//				DocumentoMicro dm= new DocumentoMicro();
//				dm.setTitulo("teste com micro");
//				Imagem capa = new Imagem();
//				capa.setEnderecoImagem("D:imagensdovicteste");
//				Imagem capa2 = new Imagem();
//				capa2.setEnderecoImagem("e:imagensdovicteste");
//				
//				capa.setTipoImagem(TipoImagem.FRENTE);
//				capa.setTipoImagem(TipoImagem.VERSO);
//			List<Imagem> lista = new ArrayList<Imagem>();
//			lista.add(capa);
//			lista.add(capa2);
//			dm.setImagens(lista);
////				if (daoImagem.persist(capa))
//					//objeto.setImagemCapa(capa); 
//			em.getTransaction().begin();
//			em.persist(lista.get(0));
//			em.persist(lista.get(1));
//		   em.persist(dm);
//
//				
//				
//			em.getTransaction().commit();
//				daoDM.persist(dm);
				
//				Autor a=new Autor();
//				Autor a2=new Autor();
//				Autor a3=new Autor();
//				Usuario u=new Usuario();
//				u.setNomeUsuario("Jorge");
//				u.setSenhaUsuario("12345");
//				u.setLoginUsuario("jorge");
//				em.getTransaction().begin();
//				em.persist(u);
//				em.getTransaction().commit();
//		String senha="ali123";
		String senha="12345";
		String senhaCriptografada= Criptografia.criptografar(senha);
		System.out.println(senhaCriptografada);
				
				
				
//				
	}

}
