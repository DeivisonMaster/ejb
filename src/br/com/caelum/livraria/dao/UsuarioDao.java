package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	
	public Usuario buscaPeloLogin(String login) {
		Usuario usuario = null;
		
		try {
			TypedQuery<Usuario> consulta = this.entityManager.createQuery("select u from Usuario u where u.login = :pLogin", Usuario.class);
			consulta.setParameter("pLogin", login);
			usuario = consulta.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
