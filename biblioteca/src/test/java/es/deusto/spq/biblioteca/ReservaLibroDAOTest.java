package es.deusto.spq.biblioteca;
/**
 * Test mockito
 * @author Koldo
 *
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import es.deusto.spq.biblioteca.dao.IReservaLibroDAO;
import es.deusto.spq.biblioteca.data.ReservaLibro;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class ReservaLibroDAOTest {
	
	private static final Logger logger = Logger.getLogger(ReservaLibroDAOTest.class);

	Biblioteca biblio;
	
	@Mock
	IReservaLibroDAO reservaLibroDAO;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ReservaDAOTest.class);
	}
	
	@Before
	public void setUp() throws Exception {
		//Inicializado para todos los teses
		biblio = new Biblioteca(reservaLibroDAO);

	}
	
	@Test
	public void testReservarLibro() throws RemoteException {
		ReservaLibro rl = new ReservaLibro("1");
		biblio.reservarLibro("1");
		ArgumentCaptor<ReservaLibro> captor = ArgumentCaptor.forClass(ReservaLibro.class);
		verify(reservaLibroDAO).reservarLibro(captor.capture());
		logger.info("El libro ha sido reservado");
		ReservaLibro xD = captor.getValue();
		assertEquals(rl.getIsbn(), xD.getIsbn());
	}

}
