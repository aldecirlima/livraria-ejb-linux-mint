package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

// A anotação @Stateless transforma a Classe DAO em um EJB.

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // opcional
//@Interceptors({LogInterceptador.class})
public class AutorDao {

//	No EJB a anotação de injeção do EntityManager é @PersistenceContext
	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDao foi criado.");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salva(Autor autor) {

		System.out.println("Salvando Autor " + autor.getNome());

		manager.persist(autor);

		System.out.println("Salvou Autor " + autor.getNome());

//		Chamada ao serviço externo que gera um erro...
//		throw new RuntimeException("Serviço externo deu erro!");

	}

	public List<Autor> todosAutores() {
		return manager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}

}
