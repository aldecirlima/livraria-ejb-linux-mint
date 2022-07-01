package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.modelo.Autor;

// A anotação @Stateless transforma a Classe DAO em um EJB.

@Stateless
@TransactionManagement(TransactionManagementType.BEAN) // opcional
public class AutorDao {

//	No EJB a anotação de injeção do EntityManager é @PersistenceContext
	@PersistenceContext
	private EntityManager manager;
	
	@Inject
	UserTransaction tx;

	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDao foi criado.");
	}

	public void salva(Autor autor) {
		System.out.println("Salvando Autor " + autor.getNome());

		try {
			tx.begin();
		manager.persist(autor);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Salvou Autor " + autor.getNome());
	}

	public List<Autor> todosAutores() {
		return manager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}

}
