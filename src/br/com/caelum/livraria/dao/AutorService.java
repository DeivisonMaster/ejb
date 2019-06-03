package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {

	@Inject
	private AutorDao dao;
	
	// required
	public void salvar(Autor autor) {
		this.dao.salva(autor);
		
		//throw new LivrariaException(); // exceção checked sem rollback
	}


	public List<Autor> todosAutores() {
		return this.dao.todosAutores();
	}
}
