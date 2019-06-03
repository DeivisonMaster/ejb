package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // opcional, ejb ja usa por padrão
@Interceptors({LogInterceptador.class})  // define esta classe sera interceptador por LogInterceptador
public class AutorDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDAO foi criado!");
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY) // opcional. Por padrão o ejb faz uso do tipo required. Mandatory requer transação aberta
	public void salva(Autor autor) {
		
		System.out.println("salvando o autor" + autor.getNome());
		
//		try {
//			Thread.sleep(20000); // 20seg
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		entityManager.persist(autor); 
		
		System.out.println("salvou o autor" + autor.getNome());
		
		//throw new RuntimeException("Erro em serviço externo"); // exceção unchecked com roolback
	}
	
	public List<Autor> todosAutores() {
		return entityManager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.entityManager.find(Autor.class, autorId);
		return autor;
	}
	
}
