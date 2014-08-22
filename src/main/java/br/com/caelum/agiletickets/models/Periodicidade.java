package br.com.caelum.agiletickets.models;

public enum Periodicidade {

	DIARIA(1), 
	SEMANAL(7);

	private final int dias;

	public int getDias() {
		return dias;
	}

	private Periodicidade(int dias) {
		this.dias = dias;
	}

}
