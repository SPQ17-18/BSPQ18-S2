package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;


import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.deusto.spq.biblioteca.data.Sala;
import junit.framework.JUnit4TestAdapter;
/**
 * Test Junit sala
 * @author Luis
 *
 */
@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class SalaDataTest {

	private Sala S1;
	private Sala S2;
	private Sala S3;
	private Sala S4;
	
	final Logger logger= LoggerFactory.getLogger(SalaDataTest.class);
	static int iteracion = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SalaDataTest.class);
	}
	
	@Before public void Datos() {
		//logger.info("Entering setUp");
		logger.info("Construyendo datos: {}", iteracion++);
		
		S1= new Sala("S1",10);
		S2= new Sala("S2",8);
		S3= new Sala("S3",30);
		S4= new Sala("S4",2);
		
		logger.info("Abandonando constructor de datos");
	}
	
	@Test 
    @PerfTest(invocations = 100, threads = 10)
    @Required(max = 500, average = 200)
	public void testCreacionSala1() throws Exception {
		logger.info("Empezando test de creacion de Sala1");
		Sala esperada=new Sala("S1",10);
		assertEquals(esperada.getId_sala(), S1.getId_sala());
		assertEquals(esperada.getCapacidad(), S1.getCapacidad());
		Thread.sleep(5);
		logger.debug("Finalizando test de creacion de Sala");
	}
	
	@Test 
    @PerfTest(invocations = 600, threads = 100)
    @Required(max = 1000, average = 400)
	public void testCreacionSala2() throws Exception {
		logger.info("Empezando test de creacion de Sala2");
		Sala esperada=new Sala("S2",8);
		assertEquals(esperada.getId_sala(), S2.getId_sala());
		assertEquals(esperada.getCapacidad(), S2.getCapacidad());
		Thread.sleep(85);
		logger.debug("Finalizando test de creacion de Sala2");
	}
	
	@Test 
    @PerfTest(invocations = 300, threads = 50)
    @Required(max = 200, average = 20)
	public void testCreacionSala3() throws Exception {
		logger.info("Empezando test de creacion de Sala3");
		Sala esperada=new Sala("S3",30);
		assertEquals(esperada.getId_sala(), S3.getId_sala());
		assertEquals(esperada.getCapacidad(), S3.getCapacidad());
		Thread.sleep(15);
		logger.debug("Finalizando test de creacion de Sala3");
	}
	@Ignore
	@Test
	@PerfTest(invocations = 1000, threads = 50)
    @Required(max = 100, average = 300)
	public void testCreacionSalaFallo1() throws Exception{
		logger.info("Empezando test de creación de SalaFallo1");
		Sala esperada = new Sala("S4",6);
		assertNotSame(esperada, S4);
		assertEquals(esperada.getId_sala(), S4.getId_sala());
		assertEquals(esperada.getCapacidad(), S4.getCapacidad());
	}

}
