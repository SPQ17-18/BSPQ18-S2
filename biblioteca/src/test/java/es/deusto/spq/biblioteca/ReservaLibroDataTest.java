package es.deusto.spq.biblioteca;
/**
 * Test JUnit
 * @author Koldo
 *
 */
import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.spq.biblioteca.data.ReservaLibro;
import junit.framework.JUnit4TestAdapter;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class ReservaLibroDataTest {
	
	private ReservaLibro RL1;
	private ReservaLibro RL2;
	private ReservaLibro RL3;
	private ReservaLibro RL4;
	
	final Logger logger= LoggerFactory.getLogger(ReservaLibroDataTest.class);
	static int iteracion = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservaLibroDataTest.class);
	}
	
	

	@Before
	public void setUp() throws Exception {
		logger.info("Construyendo datos: {}", iteracion++);
		RL1 = new ReservaLibro("1");
		RL2 = new ReservaLibro("2");
		RL2 = new ReservaLibro("3");
		RL2 = new ReservaLibro("4");

		
	}
	
	@Test 
    @PerfTest(invocations = 100, threads = 10)
    @Required(max = 500, average = 200)
	public void testCreacionReservaDeLibro1() throws Exception {
		logger.info("Empezando test de creacion de reserva del libro con isbn 1");
		ReservaLibro waiting = new ReservaLibro("1");
		assertEquals(waiting.getIsbn(), RL1.getIsbn());
		Thread.sleep(5);
		logger.debug("Finalizando test de creacion de la reserva de isbn 1");
	}
	
	@Test 
    @PerfTest(invocations = 600, threads = 100)
    @Required(max = 1000, average = 400)
	public void testCreacionReservaDeLibro2() throws Exception {
		logger.info("Empezando test de creacion de reserva del libro con isbn 2");
		ReservaLibro waiting = new ReservaLibro("2");
		assertEquals(waiting.getIsbn(), RL2.getIsbn());
		Thread.sleep(85);
		logger.debug("Finalizando test de creacion de la reserva de isbn 2");
	}
	
	@Test 
    @PerfTest(invocations = 300, threads = 50)
    @Required(max = 200, average = 20)
	public void testCreacionReservaDeLibro3() throws Exception {
		logger.info("Empezando test de creacion de reserva del libro con isbn 3");
		ReservaLibro waiting = new ReservaLibro("3");
		assertEquals(waiting.getIsbn(), RL3.getIsbn());
		Thread.sleep(15);
		logger.debug("Finalizando test de creacion de la reserva de isbn 3");
	}
	
	@Test 
    @PerfTest(invocations = 200, threads = 90)
    @Required(max = 400, average = 300)
	public void testCreacionReservaDeLibro4() throws Exception {
		logger.info("Empezando test de creacion de reserva del libro con isbn 4");
		ReservaLibro waiting = new ReservaLibro("4");
		assertEquals(waiting.getIsbn(), RL4.getIsbn());
		Thread.sleep(90);
		logger.debug("Finalizando test de creacion de la reserva de isbn 4");
	}
	
	//Test de error provocado a proposito
	@Test
    @PerfTest(invocations = 2000, threads = 10)
    @Required(max = 60, average = 100)
	public void testDeErrorForzado() {
		logger.info("Empezando test de error forzado");
		ReservaLibro waiting = new ReservaLibro("44");
		assertEquals(waiting.getIsbn(), RL4.getIsbn());
		logger.info("Finalizando test de error forzado ");

		fail("Not yet implemented");
	}

}
