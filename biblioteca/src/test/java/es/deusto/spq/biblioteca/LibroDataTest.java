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

@PerfTest(invocations = 4)
@Required(max = 1200, average = 250)
public class LibroDataTest {
	
	private Libro L1;
	private Libro L2;
	private Libro L3;
	private Libro L4;
	
	final Logger logger= LoggerFactory.getLogger(LibroDataTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservaDataTest.class);
	}
	
	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp: {}", iteration++);
		
		
		L1 = new Libro("1", "Libro 1", "Autor 1", "Editorial 1");
		L1 = new Libro("2", "Libro 2", "Autor 2", "Editorial 2");
		L2 = new Libro("3", "Libro 3", "Autor 3", "Editorial 3");
		L3 = new Libro("4", "Libro 4", "Autor 4", "Editorial 4");
		
		logger.info("Leaving setUp");


	}

	//Teses hechos para que no fallen
	@Test 
    @PerfTest(invocations = 100, threads = 10)
    @Required(max = 500, average = 200)
	public void testCreacionLibro() throws Exception {
		Libro expected = new Libro("111", "Libro 1", "Autor 1", "Editorial 1");
		assertEquals(expected.getIsbn(), L1.getIsbn());
		assertEquals(expected.getnombre(), L1.getnombre());
		assertEquals(expected.getAutor(), L1.getAutor());
		assertEquals(expected.getEditorial(), L1.getEditorial());
		logger.info("Fin de test de creacion del primer libro");
		
	}
	
	@Test 
    @PerfTest(invocations = 600, threads = 100)
    @Required(max = 1000, average = 400)
	public void testCreacionLibro2() throws Exception {
		logger.info("Iniciando test de creacion del del segundo libro");
		Libro expected = new Libro("2", "Libro 2", "Autor 2", "Editorial 2");
		assertEquals(expected.getIsbn(), L2.getIsbn());
		assertEquals(expected.getnombre(), L2.getnombre());
		assertEquals(expected.getAutor(), L2.getAutor());
		assertEquals(expected.getEditorial(), L2.getEditorial());
		logger.info("Fin de test de creacion del segundo libro");
		
	}
	
	@Test 
    @PerfTest(invocations = 300, threads = 50)
    @Required(max = 200, average = 20)
	public void testCreacionLibro3() throws Exception {
		logger.info("Iniciando test de creacion del primer libro");
		
		Libro expected = new Libro("3", "Libro 3", "Autor 3", "Editorial 3");
		assertEquals(expected.getIsbn(), L3.getIsbn());
		assertEquals(expected.getnombre(), L3.getnombre());
		assertEquals(expected.getAutor(), L3.getAutor());
		assertEquals(expected.getEditorial(), L3.getEditorial());
		logger.info("Fin de test de creacion del tercer libro");
		
	}
	
	@Test
	public void testCreacionLibro4() throws Exception {
		logger.info("Iniciando test de creacion de creacion mala del cuarto libro");
	
		Libro expected = new Libro("4", "Libro 4", "Autor 2", "Editorial 3");
		assertEquals(expected.getIsbn(), L4.getIsbn());
		assertEquals(expected.getnombre(), L4.getnombre());
		assertEquals(expected.getAutor(), L4.getAutor());
		assertEquals(expected.getEditorial(), L4.getEditorial());
		logger.info("Fin de test de creacion del cuarto libro");
		
	}
	
	//Test de error forzado
	@Test
	public void testDeErrorEnCreaciónDeLibro() throws Exception {
		logger.info("Iniciando test de error");
		Libro expected = new Libro("4", "Libro 4", "Autor 2", "Editorial 3");
		assertEquals(expected.getIsbn(), L2.getIsbn());
		assertEquals(expected.getnombre(), L4.getnombre());
		assertEquals(expected.getAutor(), L4.getAutor());
		assertEquals(expected.getEditorial(), L4.getEditorial());
		logger.info("Fin de test de error forzado");
	}
	


}
