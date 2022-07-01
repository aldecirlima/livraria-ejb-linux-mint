package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Usuario;

//A anotação @Stateless transforma a Classe DAO em um EJB.

@Stateless
public class UsuarioDao {

	@PersistenceContext
	EntityManager manager;

	public Usuario buscaPeloLogin(String login) {
		Usuario usuario = (Usuario) this.manager.createQuery("SELECT u FROM Usuario u WHERE u.login=:pLogin")
				.setParameter("pLogin", login).getSingleResult();
		return usuario;
	}

}
