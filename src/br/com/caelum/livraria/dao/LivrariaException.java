package br.com.caelum.livraria.dao;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)  // alterando o comportamento padrão tornando exception com rollback
public class LivrariaException extends Exception {
	private static final long serialVersionUID = 1L;

	
}
