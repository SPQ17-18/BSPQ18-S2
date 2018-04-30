package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Verifier;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LibroDAOTest {
	
	Biblioteca biblioteca;
	
	@Mock
	ILibroDAO ILibroDAO;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LibroDAOTest.class);
	}

	
	@Before
	public void setUp() throws Exception {
		
		biblioteca = new Biblioteca(ILibroDAO);
		
	}
	
	@Test
	public void testAlmacenarLibro() throws Exception {
		
		Libro l = new Libro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
		biblioteca.almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		verify(ILibroDAO).almacenarLibro(libroCaptor.capture());
		System.out.println("Almacenando libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(l.getIsbn(), book.getIsbn());
		assertEquals(l.getnombre(), book.getnombre());
		assertEquals(l.getAutor(), book.getAutor());
		assertEquals(l.getEditorial(), book.getEditorial());
		assertEquals(l.isReservado(), book.isReservado());

		
		
	}
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		
//	}
//
//	
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	
//	
//	
//	@After
//	public void tearDown() throws Exception {
//	}


}
