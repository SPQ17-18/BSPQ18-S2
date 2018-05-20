package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import es.deusto.spq.biblioteca.dao.ISalaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class SalaDAOTest {

	Biblioteca b;

	@Mock
	ISalaDAO dao;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ReservaDAOTest.class);
	}

	@Before
	public void setUp() throws Exception {
		b = new Biblioteca(dao);

	}

	@Test
	public void anyadirSala() throws RemoteException {
		Sala s = new Sala("ID3",4);
		b.anyadirSala(s.getId_sala(),s.getCapacidad());
		ArgumentCaptor<Sala> reservaCaptor = ArgumentCaptor.forClass( Sala.class );
		verify(dao).anyadirSala(reservaCaptor.capture());
		System.out.println("Anyadiendo reserva ");
		Sala rn=reservaCaptor.getValue();
		
		assertEquals(s.getId_sala(), rn.getId_sala());
		assertEquals(s.getCapacidad(), rn.getCapacidad());
		
	}
	@Test
	public void consultarPlazas() throws RemoteException {
		//Sala s = new Sala("ID3",2);		
		//b.anyadirSala(s.getId_sala(),s.getCapacidad());
		boolean bol =	dao.consultarPlazas("S1", 200);
		assertEquals(false,bol );
	}
	
}
