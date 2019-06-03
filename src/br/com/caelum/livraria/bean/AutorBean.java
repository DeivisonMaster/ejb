package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.AutorService;
import br.com.caelum.livraria.dao.LivrariaException;
import br.com.caelum.livraria.modelo.Autor;

@Named
@RequestScoped
public class AutorBean {
	
	private Autor autor = new Autor();
	
	@Inject
	private AutorService autorService;
	
	
	public void cadastra() {
		this.autorService.salvar(autor);
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores() {
		return this.autorService.todosAutores();
	}
	
	public Autor getAutor() {
		return autor;
	}
}
