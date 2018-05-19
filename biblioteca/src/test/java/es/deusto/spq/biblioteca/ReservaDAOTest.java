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
	public void testAnyadirUsuario() throws RemoteException {
		Reserva r = new Reserva("R5", "S2", "20304050A", "29-04-2018", "18:42", 8);
		b.anyadirUsuario(r);	
		ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass( Reserva.class );
		verify(dao).anyadirUsuario(reservaCaptor.capture());
		System.out.println("Anyadiendo usuario");
		Reserva rn=reservaCaptor.getValue();
		rn.setPlazas(rn.getPlazas()+1);
		assertEquals(9,rn.getPlazas());
	}
	
	@Test
	public void testEditarReserva() throws RemoteException {
		Reserva r = new Reserva("RE1", "S1", "12345678X", "11/04/18", "21:20", 3);
		Reserva rMod = new Reserva("RE1", "S2", "12345678X", "12/04/18", "21:21", 3);
		dao.editarReserva(r.getDni_respon(), r.getFecha(), r.getHora(), r.getId_sala(), "12/04/18", "21:21", "S2");
		Reserva rAct = b.DevolverReserva(r.getDni_respon(),"12/04/18" , "21:21");
		System.out.println(rMod.getFecha()+"  1"+"wqqe");
		System.out.println("  2");
		System.out.println(rAct.getFecha()+"  2");
		System.out.println("  3");
		assertEquals(rMod.getFecha(), rAct.getFecha());
		assertEquals(rMod.getHora(), rAct.getHora());
		assertEquals(rMod.getId_sala(), rAct.getId_sala());
	}
	
	@Test
	public void testEliminarReserva() throws RemoteException {
		dao.eliminarReserva(b.DevolverReserva("12345678X", "11/04/18", "21:20"));
		assertEquals(null, b.DevolverReserva("12345678X", "11/04/18", "21:20"));
	}
}








