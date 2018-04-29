package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import org.apache.log4j.spi.LoggerFactory;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;

import es.deusto.spq.biblioteca.data.Libro;
import junit.framework.JUnit4TestAdapter;

public class LibroDataTest {
	
	private Libro l;
	private Libro l1;
	private Libro l2;
	
	final Logger logger= LoggerFactory.getLogger(LibroDataTest.class);
	static int iteration = 0;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ReservaDataTest.class);
	}
	
	@Before
	public void setUp() throws Exception {
		
		logger.info("Entering setUp: {}", iteration++);
		
		l = new Libro(1, "Libro 1", "Autor 1", "Editorial 1", false);
		l1 = new Libro(2, "Libro 2", "Autor 2", "Editorial 2", false);
		l2 = new Libro(3, "Libro 3", "Autor 3", "Editorial 3", false);
		
		logger.info("Leaving setUp");


	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
