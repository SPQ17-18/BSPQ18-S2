package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	public void consultarDisponibilidadTest() throws RemoteException {
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		dao.anyadirReserva(r);
		assertEquals(false,dao.consultarDisponibilidad(r.getId_sala(), r.getFecha(), r.getHora()));
	}
	/*
	@Test
	public void devolverReservaTest() throws RemoteException {
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		dao.anyadirReserva(r);
		Reserva n = dao.devolverReserva("20304050A", "29-04-2018", "18:42");
		
		assertEquals(r.getDni_respon(),n.getDni_respon());
		assertEquals(r.getFecha(),n.getFecha());
		assertEquals(r.getHora(),n.getHora());
	}
*/
//commit
	@Test
	public void verReservasTest() throws RemoteException {
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		dao.anyadirReserva(r);
		ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass( Reserva.class );
		verify(dao).anyadirReserva(reservaCaptor.capture());
		ArrayList<String> a = new ArrayList<String>();
		System.out.println(r.getDni_respon());
		a = dao.verReservas(r.getDni_respon());
		String b="R5#20304050A#S2#29-04-2018#18:42#8";
		String h = null;

		for (String string : a) {
			System.out.println(string);
			h+=string;
		}
				
		assertEquals("20304050A",h);
		
	}
	
	
	
}


