package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {
	
	/**
	 * @description Verifica o tempo gasto para acesso aos dados
	 * */
	@AroundInvoke  // define ao ejb container que este metodo é um interceptador
	public Object intercepta(InvocationContext context) throws Exception {
		long tempoInicial = System.currentTimeMillis();
		
		// invoca o método interceptado no sistema
		Object proceed = context.proceed(); 
		
		String nomeMetodoInterceptado = context.getMethod().getName();
		String nomeClasseInterceptada = context.getTarget().getClass().getSimpleName();
		
		System.out.println(nomeMetodoInterceptado + " - " + nomeClasseInterceptada);
		
		long tempoFinal = System.currentTimeMillis();
		
		System.out.println("Tempo gasto: " + (tempoFinal - tempoInicial));

		return proceed; 
	}
}
