package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN) // opcional. Ao definir como bean, a classe cuida da transação
public class LivroDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction userTransaction;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED) // opcional pois este valor já o padrão
	public void salva(Livro livro) {
		try {
			this.userTransaction.begin();
			
			entityManager.persist(livro);
			
			this.userTransaction.commit();
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}
	
	public List<Livro> todosLivros() {
		return entityManager.createQuery("select a from Livro a", Livro.class).getResultList();
	}

	public List<Livro> livrosPeloNome(String nome) {
		TypedQuery<Livro> query = this.entityManager.createQuery("select l from Livro l where l.titulo like :pTitulo", Livro.class);
		query.setParameter("pTitulo", "%" + nome + "%");
		List<Livro> livros = query.getResultList();
		
		return livros;
	}
	
}
