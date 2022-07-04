package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Livro;

//A anotação @Stateless transforma a Classe DAO em um EJB.

@Stateless
public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	private TypedQuery<Livro> query;

	public void salva(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> todosLivros() {
		return manager.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
	}

	public List<Livro> livrosPeloNome(String nome) {

		query = this.manager.createQuery("SELECT l FROM Livro l WHERE l.titulo LIKE :pTitulo",
				Livro.class);
		query.setParameter("pTitulo", "%" + nome + "%");
		return query.getResultList();
	}

}
