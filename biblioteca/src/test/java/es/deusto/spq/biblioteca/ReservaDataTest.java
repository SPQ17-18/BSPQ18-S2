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
		//logger.info("Entering setUp");
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
		logger.info("Empezando test de creacion de Reserva");
		Reserva esperada= new Reserva("R1","S1","12345678X","1-05-2018","17:59",7);
		assertEquals(esperada.getDni_respon(), R1.getDni_respon());
		assertEquals(esperada.getId_sala(), R1.getId_sala());
		assertEquals(esperada.getDni_respon(), R1.getDni_respon());
		assertEquals(esperada.getFecha(), R1.getFecha());
		assertEquals(esperada.getFecha(), R1.getHora());
		assertEquals(esperada.getFecha(), R1.getPlazas());
		Thread.sleep(2);
		logger.debug("Finalizando test de creacion de Reserva");
	}
	
	@Test 
	public void plazasNegativas() throws Exception {
		logger.info("Empezando test de plazas de reserva");
		Reserva esperada= new Reserva("R2","S1","23456789X","2-05-2018","20:00",-6);
		assertTrue(esperada.getPlazas()>0);
		logger.info("Finalizando test de plazas de reserva");
	}
}
