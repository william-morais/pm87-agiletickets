package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import br.com.caelum.agiletickets.domain.PeriodoInvalidoException;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void criaSessaoDiariaIniciaETerminaNoMesmoDia() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 10), new LocalTime(20, 0), Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessaos.size());
	}
	
	@Test
	public void criaSessaoDiariaIniciaETerminaNoMesmoDiaComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 10), new LocalTime(20, 0), Periodicidade.DIARIA);
		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
	}
	
	@Test
	public void criaSessaoDiariaQueTerminaUmaSemanaDepois() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 17), new LocalTime(20, 0), Periodicidade.DIARIA);
		
		Assert.assertEquals(8, sessaos.size());
	}
	
	@Test
	public void criaSessaoDiariaQueTerminaUmaSemanaDepoisComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 17), new LocalTime(20, 0), Periodicidade.DIARIA);
		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 17, 20, 0), sessaos.get(7).getInicio());
		
	}
	
	@Test
	public void criaSessaoSemanalIniciaETerminaNoMesmoDia() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 10), new LocalTime(20, 0), Periodicidade.SEMANAL);
		
		Assert.assertEquals(1, sessaos.size());
	}
	
	@Test
	public void criaSessaoSemanalIniciaETerminaNoMesmoDiaComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 10), new LocalTime(20, 0), Periodicidade.SEMANAL);
		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
	}
	
	@Test
	public void criaSessaoSemanalQueTerminaUmaSemanaDepois() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 17), new LocalTime(20, 0), Periodicidade.SEMANAL);
		
		Assert.assertEquals(2, sessaos.size());
	}
	
	@Test
	public void criaSessaoSemanalQueTerminaUmaSemanaDepoisComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 17), new LocalTime(20, 0), Periodicidade.SEMANAL);		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 17, 20, 0), sessaos.get(1).getInicio());
		
	}
	
	@Test
	public void criaSessaoSemanalQueTerminaTresSemanaDepoisComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 31), new LocalTime(20, 0), Periodicidade.SEMANAL);		
		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 17, 20, 0), sessaos.get(1).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 24, 20, 0), sessaos.get(2).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 31, 20, 0), sessaos.get(3).getInicio());
		
	}
	
	@Test
	public void criaSessaoSemanalQueTerminaUmaSemanaEMeiaDepois() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 20), new LocalTime(20, 0), Periodicidade.SEMANAL);
		
		Assert.assertEquals(2, sessaos.size());
	}
	
	@Test
	public void criaSessaoSemanalQueTerminaUmaSemanaEMeiaDepoisComDatasValidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		List<Sessao> sessaos = ivete.criaSessoes(new LocalDate(2014, 8, 10), new LocalDate(2014, 8, 20), new LocalTime(20, 0), Periodicidade.SEMANAL);		
		
		Assert.assertEquals(new DateTime(2014, 8, 10, 20, 0), sessaos.get(0).getInicio());
		Assert.assertEquals(new DateTime(2014, 8, 17, 20, 0), sessaos.get(1).getInicio());
		
	}
	
	@Test(expected=PeriodoInvalidoException.class)
	public void criaSessaoSemanalQueTerminaUmaSemanaEMeiaDepoisComDatasInvertidas() throws Exception {
		Espetaculo ivete = new Espetaculo();
		
		ivete.criaSessoes(new LocalDate(2014, 8, 20), new LocalDate(2014, 8, 10), new LocalTime(20, 0), Periodicidade.SEMANAL);
	}
}
