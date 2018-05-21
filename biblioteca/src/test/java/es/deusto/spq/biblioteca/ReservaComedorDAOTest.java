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
import es.deusto.spq.biblioteca.dao.IReservaComedorDAO;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import junit.framework.JUnit4TestAdapter;
/**
 * Test mockito
 * @author Julen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservaComedorDAOTest {

	Biblioteca b;

	@Mock
	IReservaComedorDAO dao;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ReservaComedorDAOTest.class);
	}

	@Before
	public void setUp() throws Exception {
		b = new Biblioteca(dao);

	}

	@Test
	public void testAnyadirReserva() throws RemoteException {
			ReservaMesa rm = new ReservaMesa("M1", "R2", "45678912F", "29-04-2018", "15:34", 3);
			b.anyadirReservaComedor("M1", "45678912F", "29-04-2018", "15:34", 3);	
			ArgumentCaptor<ReservaMesa> reservaCaptor = ArgumentCaptor.forClass( ReservaMesa.class );
			verify(dao).anyadirReservaComedor(reservaCaptor.capture());
			System.out.println("Anyadiendo reserva ");
			ReservaMesa rmn=reservaCaptor.getValue();
			assertEquals(rm.getDni_respon(), rmn.getDni_respon());
			assertEquals(rm.getId_Mesa(), rmn.getId_Mesa());
			assertEquals(rm.getFecha(), rmn.getFecha());
			assertEquals(rm.getHora(), rmn.getHora());
			assertEquals(rm.getPersonas(), rmn.getPersonas());
	}
	
	
	//test
	@Test
	public void consultarDisponibilidadComedorTest() throws RemoteException {
		ReservaMesa rm = new ReservaMesa("M1", "R2", "45678912F", "29-04-2018", "15:34", 3);
		dao.anyadirReservaComedor(rm);
		assertEquals(false,dao.consultarDisponibilidadComedor(rm.getId_Mesa(), rm.getFecha(), rm.getHora()));
	}
	
	@Test
	public void devolverReservaTest() throws RemoteException {
		ReservaMesa rm = new ReservaMesa("M1", "R2", "45678912F", "29-04-2018", "15:34", 3);
		dao.anyadirReservaComedor(rm);
		ReservaMesa n = dao.devolverReservaComedor("45678912F", "29-04-2018", "15:34");
		
		assertEquals(rm.getDni_respon(),n.getDni_respon());
		assertEquals(rm.getFecha(),n.getFecha());
		assertEquals(rm.getHora(),n.getHora());
	}

	@Test
	public void verReservasTest() throws RemoteException {
		ReservaMesa rm = new ReservaMesa("M1", "R2", "45678912F", "29-04-2018", "15:34", 3);
		dao.anyadirReservaComedor(rm);
		ArgumentCaptor<ReservaMesa> reservaCaptor = ArgumentCaptor.forClass( ReservaMesa.class );
		verify(dao).anyadirReservaComedor(reservaCaptor.capture());
		ArrayList<String> a = new ArrayList<String>();
		System.out.println(rm.getDni_respon());
		a = dao.verReservaComedor(rm.getDni_respon());
		String b="M1#45678912F#S2#29-04-2018#15:34#3";
		String h = null;

		for (String string : a) {
			System.out.println(string);
			h+=string;
		}
				
		assertEquals("20304050A",h);
		
	}
	
	
	
}