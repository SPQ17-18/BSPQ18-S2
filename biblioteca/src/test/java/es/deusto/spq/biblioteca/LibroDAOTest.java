package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LibroDAOTest {
	
	private static final Logger logger = Logger.getLogger(LibroDAOTest.class);

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
	public void testAlmacenarLibro() throws RemoteException {
		
		Libro l = new Libro("1", "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros");
		biblioteca.almacenarLibro("1", "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros");
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		verify(ILibroDAO).almacenarLibro(libroCaptor.capture());
		logger.info("Almacenando libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(l.getIsbn(), book.getIsbn());
		assertEquals(l.getnombre(), book.getnombre());
		assertEquals(l.getAutor(), book.getAutor());
		assertEquals(l.getEditorial(), book.getEditorial());

	}
	
	//No va a funcionar pues solo vale por objetos
	@Ignore
	@Test
	public void testVerLibro() throws Exception {
		Libro libro = new Libro("2", "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh");
		biblioteca.mostrarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		//verify(ILibroDAO).verLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		//verify(ILibroDAO).verLibro(libroCaptor.capture("Festin de cuervos, Cancion de Hielo y fuego IV"));
		//verify(ILibroDAO).verLibro(libroCaptor.capture().getnombre());
		//verify(ILibroDAO, Times().verLibro("Festin de cuervos, Cancion de Hielo y fuego IV"));
		System.out.println("Mostrando el libro...");
		verify(ILibroDAO).verLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		logger.info("Mostrando el libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(libro.getIsbn(), book.getIsbn());
		assertEquals(libro.getnombre(), book.getnombre());
		assertEquals(libro.getAutor(), book.getAutor());
		assertEquals(libro.getEditorial(), book.getEditorial());		

	}
	//No va a funcionar pues solo vale por objetos
	@Ignore
	@Test
	public void testDeleteLibro() throws Exception {
		Libro libro = new Libro("3", "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A");
		biblioteca.EliminarLibro(libro);
		ArgumentCaptor<Libro> libroCaptor = ArgumentCaptor.forClass(Libro.class);
		//verify(ILibroDAO).EliminarLibro("3");
		//verify(ILibroDAO).EliminarLibro(libroCaptor.capture("3"));
		//verify(ILibroDAO).EliminarLibro(libroCaptor.capture().getIsbn());
		System.out.println("Eliminando libro...");
		verify(ILibroDAO).EliminarLibro("3");
		logger.info("Eliminando libro...");
		
		Libro book = libroCaptor.getValue();
		assertEquals(libro.getIsbn(), book.getIsbn());
		assertEquals(libro.getnombre(), book.getnombre());
		assertEquals(libro.getAutor(), book.getAutor());
		assertEquals(libro.getEditorial(), book.getEditorial());

		
	}



}
