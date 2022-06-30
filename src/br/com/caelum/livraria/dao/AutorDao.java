package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

// A anotação @Stateless transforma a Classe DAO em um EJB.

@Stateless
public class AutorDao {

	@Inject
	private Banco banco;
	
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("AutorDao foi criado.");
	}

	public void salva(Autor autor) {
		System.out.println("Salvando Autor " + autor.getNome());
		
//		try {
//			Thread.sleep(20000); //20s
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		banco.save(autor);
		
		System.out.println("Salvou Autor " + autor.getNome());
	}
	
	public List<Autor> todosAutores() {
		return banco.listaAutores();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.banco.buscaPelaId(autorId);
		return autor;
	}
	
}
