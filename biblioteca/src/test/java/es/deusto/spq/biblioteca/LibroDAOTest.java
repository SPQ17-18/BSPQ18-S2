package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
	
	//No va a funcionar pues solo vale por objetos
	@Ignore
	@Test
	public void testVerLibro() throws Exception {
		Libro libro = new Libro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
		biblioteca.mostrarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		verify(ILibroDAO).verLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		System.out.println("Mostrando el libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(libro.getIsbn(), book.getIsbn());
		assertEquals(libro.getnombre(), book.getnombre());
		assertEquals(libro.getAutor(), book.getAutor());
		assertEquals(libro.getEditorial(), book.getEditorial());
		assertEquals(libro.isReservado(), book.isReservado());
		

	}
	//No va a funcionar pues solo vale por objetos
	@Ignore
	@Test
	public void testDeleteLibro() throws Exception {
		Libro libro = new Libro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);
		biblioteca.EliminarLibro(libro);
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		verify(ILibroDAO).EliminarLibro("3");
		System.out.println("Eliminando libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(libro.getIsbn(), book.getIsbn());
		assertEquals(libro.getnombre(), book.getnombre());
		assertEquals(libro.getAutor(), book.getAutor());
		assertEquals(libro.getEditorial(), book.getEditorial());
		assertEquals(libro.isReservado(), book.isReservado());


		
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
