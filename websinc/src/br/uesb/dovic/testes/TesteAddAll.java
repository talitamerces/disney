package br.uesb.dovic.testes;

import java.util.ArrayList;
import java.util.List;

public class TesteAddAll {
	public static void main(String args[]){
	Aluno a=new Aluno("Aline");
	Aluno b=new Aluno("Bruno");
	Aluno c=new Aluno("Carla");
	Aluno d=new Aluno("Jadiel");
	Aluno e=new Aluno("Arthur");
	List<Aluno> lista=new ArrayList<Aluno>();
	List<Aluno> lista2=new ArrayList<Aluno>();
	lista.add(a);
	lista.add(b);
	lista.add(c);
	
	lista2.addAll(lista);
	
	c.setNome("Creusa");
	for (Aluno i:lista)
		System.out.println(i.getNome());
	
	
	
	

}
	}
