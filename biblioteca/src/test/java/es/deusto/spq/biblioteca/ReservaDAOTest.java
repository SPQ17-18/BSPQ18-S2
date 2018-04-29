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
import es.deusto.spq.biblioteca.dao.IReservaDAO;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class ReservaDAOTest {

	Biblioteca b;

	@Mock
	IReservaDAO dao;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ReservaDAOTest.class);
	}

	@Before
	public void setUp() throws Exception {
		b = new Biblioteca(dao);

	}

	@Test
	public void testAnyadirReserva() throws RemoteException {
			Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
			when( dao.devolverReserva("19182138S", "29-04-2018", "18:27")).thenReturn(null);	
			b.anyadirReserva("S2", "20304050A", "29-04-2018", "18:42", 8);	
			ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass( Reserva.class );
			verify(dao).anyadirReserva(reservaCaptor.capture());
			System.out.println("Anyadiendo reserva ");
			Reserva rn=reservaCaptor.getValue();
			assertEquals(r.getDni_respon(), rn.getDni_respon());
			assertEquals(r.getId_sala(), rn.getId_sala());
			assertEquals(r.getFecha(), rn.getFecha());
			assertEquals(r.getHora(), rn.getHora());
			assertEquals(r.getPlazas(), rn.getPlazas());
	}
	
	@Test
	public void testEditarReserva() throws RemoteException {
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		when( dao.devolverReserva("20304050A", "29-04-2018", "18:42")).thenReturn(r);
		b.editarReserva(r, "19:00");
		ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass( Reserva.class );
		verify (dao).editarReserva(reservaCaptor.capture(), "19:00");
		Reserva rn = reservaCaptor.getValue();
		System.out.println("Editando reserva");
		assertEquals( "19:00", rn.getHora());
	}
	
	@Test(expected=RemoteException.class)
	public void testVerReservaNoExiste() throws RemoteException {	
		when( dao.devolverReserva("19182138S", "29-04-2018", "18:27")).thenReturn( null );
		System.out.println("No existe reserva");
		b.verReservas("1918218S");
			
	}
	
	@Test
	public void testVerReserva() throws RemoteException {
		// Setting up the test data
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		when( dao.devolverReserva("20304050A", "29-04-2018", "18:42")).thenReturn(r);
		b.verReservas("20304050A");	
	}
	
}
