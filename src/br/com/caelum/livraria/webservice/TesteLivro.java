package br.com.caelum.livraria.webservice;

import java.util.List;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

public class TesteLivro {
	public static void main(String[] args) {
		LivroDao livroDao = new LivroDao();
		
		List<Livro> livrosPeloNome = livroDao.livrosPeloNome("JSF");
		for (Livro livro : livrosPeloNome) {
			System.out.println(livro.getTitulo());
		}
	}
}
