package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.data.Reserva;
import junit.framework.JUnit4TestAdapter;
/**
 * Test JUnit
 * @author Ariane
 *
 */
@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class ReservaDataTest {

	private Reserva R1;
	private Reserva R2;
	private Reserva R3;
	private Reserva R4;
	
	final Logger logger= LoggerFactory.getLogger(ReservaDataTest.class);
	static int iteracion = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservaDataTest.class);
	}
	
	@Before public void Datos() {
		logger.info("Construyendo datos: {}", iteracion++);
		
		R1= new Reserva("R1","S1","12345678X","1-05-2018","17:59",7);
		R2= new Reserva("R2","S1","23456789X","2-05-2018","20:00",6);
		R3= new Reserva("R3","S2","34567890X","28-05-2018","17:59",4);
		R4= new Reserva("R4","S3","45678901X","28-05-2018","12:00",5);
	
		logger.info("Abandonando constructor de datos");
	}
	
	@Test 
    @PerfTest(invocations = 100, threads = 10)
    @Required(max = 500, average = 200)
	public void testCreacionReserva() throws Exception {
		logger.info("Empezando test de creacion de Reserva1");
		Reserva esperada= new Reserva("R1","S1","12345678X","1-05-2018","17:59",7);
		assertEquals(esperada.getDni_respon(), R1.getDni_respon());
		assertEquals(esperada.getId_sala(), R1.getId_sala());
		assertEquals(esperada.getDni_respon(), R1.getDni_respon());
		assertEquals(esperada.getFecha(), R1.getFecha());
		assertEquals(esperada.getHora(), R1.getHora());
		assertEquals(esperada.getPlazas(), R1.getPlazas());
		Thread.sleep(5);
		logger.debug("Finalizando test de creacion de Reserva");
	}
	
	@Test 
    @PerfTest(invocations = 600, threads = 100)
    @Required(max = 1000, average = 400)
	public void testCreacionReserva2() throws Exception {
		logger.info("Empezando test de creacion de Reserva2");
		Reserva esperada= new Reserva("R2","S1","23456789X","2-05-2018","20:00",6);
		assertEquals(esperada.getDni_respon(), R2.getDni_respon());
		assertEquals(esperada.getId_sala(), R2.getId_sala());
		assertEquals(esperada.getDni_respon(), R2.getDni_respon());
		assertEquals(esperada.getFecha(), R2.getFecha());
		assertEquals(esperada.getHora(), R2.getHora());
		assertEquals(esperada.getPlazas(), R2.getPlazas());
		Thread.sleep(85);
		logger.debug("Finalizando test de creacion de Reserva");
	}
	
	@Test 
    @PerfTest(invocations = 200, threads = 90)
    @Required(max = 400, average = 300)
	public void testCreacionReserva4() throws Exception {
		logger.info("Empezando test de creacion de Reserva4");
		Reserva esperada= new Reserva("R4","S3","45678901X","28-05-2018","12:00",5);
		assertEquals(esperada.getDni_respon(), R4.getDni_respon());
		assertEquals(esperada.getId_sala(), R4.getId_sala());
		assertEquals(esperada.getDni_respon(), R4.getDni_respon());
		assertEquals(esperada.getFecha(), R4.getFecha());
		assertEquals(esperada.getHora(), R4.getHora());
		assertEquals(esperada.getPlazas(), R4.getPlazas());
		Thread.sleep(90);
		logger.debug("Finalizando test de creacion de Reserva");
	}
	
	@Test 
	public void plazasNegativas() throws Exception {
		logger.info("Empezando test de plazas de reserva");
		Reserva esperada= new Reserva("R2","S1","23456789X","2-05-2018","20:00",6);
		assertTrue(esperada.getPlazas()>0);
		logger.info("Finalizando test de plazas de reserva");
	}
}

