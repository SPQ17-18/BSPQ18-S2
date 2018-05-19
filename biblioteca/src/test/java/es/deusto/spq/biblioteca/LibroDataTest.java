package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
//import org.apache.log4j.spi.LoggerFactory;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.deusto.spq.biblioteca.data.Libro;
import junit.framework.JUnit4TestAdapter;

@PerfTest(invocations = 5)
@Required(max = 1500, average = 300)
public class LibroDataTest {
	
	private Libro l;
	private Libro l1;
	private Libro l2;
	private Libro l3;
	
	final Logger logger= LoggerFactory.getLogger(LibroDataTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservaDataTest.class);
	}
	
	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp: {}", iteration++);
		
		l = new Libro(1, "Libro 1", "Autor 1", "Editorial 1");
		l1 = new Libro(2, "Libro 2", "Autor 2", "Editorial 2");
		l2 = new Libro(3, "Libro 3", "Autor 3", "Editorial 3");
		l3 = new Libro(4, "Libro 4", "Autor 4", "Editorial 4");
		
		logger.info("Leaving setUp");


	}

	//Teses hechos para que no fallen
	@Test
	@PerfTest(invocations = 100, threads = 10)
    @Required(max = 500, average = 200)
	public void testCreacionLibro() throws Exception {
		logger.info("Iniciando test de creacion del primer libro");
		Libro expected = new Libro(111, "Libro 1", "Autor 1", "Editorial 1");
		assertEquals(expected.getIsbn(), l.getIsbn());
		assertEquals(expected.getnombre(), l.getnombre());
		assertEquals(expected.getAutor(), l.getAutor());
		assertEquals(expected.getEditorial(), l.getEditorial());
		logger.info("Fin de test de creacion del primer libro");
		
	}
	
	@Test
	@PerfTest(invocations = 200, threads = 30)
    @Required(max = 600, average = 20)
	public void testCreacionLibro2() throws Exception {
		logger.info("Iniciando test de creacion del primer libro");
		Libro expected = new Libro(222, "Libro 2", "Autor 2", "Editorial 2");
		assertEquals(expected.getIsbn(), l1.getIsbn());
		assertEquals(expected.getnombre(), l1.getnombre());
		assertEquals(expected.getAutor(), l1.getAutor());
		assertEquals(expected.getEditorial(), l1.getEditorial());
		logger.info("Fin de test de creacion del segundo libro");
		
	}
	
	@Test
	@PerfTest(invocations = 400, threads = 80)
    @Required(max = 200, average = 300)
	public void testCreacionLibro3() throws Exception {
		logger.info("Iniciando test de creacion del primer libro");
		Libro expected = new Libro(333, "Libro 3", "Autor 3", "Editorial 3");
		assertEquals(expected.getIsbn(), l2.getIsbn());
		assertEquals(expected.getnombre(), l.getnombre());
		assertEquals(expected.getAutor(), l2.getAutor());
		assertEquals(expected.getEditorial(), l2.getEditorial());
		logger.info("Fin de test de creacion del tercer libro");
		
	}
	
	//Test hecho para que falle a proposito
	@Test
	public void testCreacionLibro4() throws Exception {
		logger.info("Iniciando test de creacion de creacion mala del cuarto libro");
		Libro expected = new Libro(444, "Libro 4", "Autor 2", "Editorial 3");
		assertEquals(expected.getIsbn(), l3.getIsbn());
		assertEquals(expected.getnombre(), l3.getnombre());
		assertEquals(expected.getAutor(), l3.getAutor());
		assertEquals(expected.getEditorial(), l3.getEditorial());
		logger.info("Fin de test de creacion mala del cuarto libro");
		
	}
	


}
