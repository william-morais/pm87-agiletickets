package br.com.caelum.agiletickets.domain;

public class PeriodoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public PeriodoInvalidoException() {
	}

	public PeriodoInvalidoException(String message) {
		super(message);
	}

}
