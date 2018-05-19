package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.IMenuDAO;
import es.deusto.spq.biblioteca.dao.IMesaDAO;
import es.deusto.spq.biblioteca.dao.IReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.IReservaDAO;
import es.deusto.spq.biblioteca.dao.ISalaDAO;
import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.dao.MenuDAO;
import es.deusto.spq.biblioteca.dao.MesaDAO;
import es.deusto.spq.biblioteca.dao.ReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.data.Sala;

public class CargaDatosBDTest {

	private static final Logger logger = Logger.getLogger(CargaDatosBDTest.class);
	
	private IMenuDAO menuDAO;
	private ILibroDAO libroDAO;
	private IMesaDAO mesaDAO;
	private IReservaComedorDAO reservaCDAO;
	private ISalaDAO salaDAO;	
	private IReservaDAO reservaDAO;
	
	@Before
	public void setUp() throws Exception {
		
		menuDAO = new MenuDAO();
		libroDAO = new LibroDAO();
		mesaDAO = new MesaDAO();
		reservaCDAO = new ReservaComedorDAO();
		salaDAO = new SalaDAO();	
		reservaDAO = new ReservaDAO();
	
	}
	
	@Test
	public void testMenu() {
		try {
			logger.info("-Creando 5 menus...");
			
			Menu m1 = new Menu("MEN1","13-5-2018","Alubias","Filete","Chocolate");
			menuDAO.anyadirMenu(m1);
			
			Menu m2 = new Menu("MEN2","25-3-2018","Menestra","Salmon","Tarta");
			menuDAO.anyadirMenu(m2);
			
			Menu m3 = new Menu("MEN3","8-4-2018","Lentejas","Merluza","Queso");
			menuDAO.anyadirMenu(m3);
			
			Menu m4 = new Menu("MEN4","29-11-2018","Pasta","Pollo","Fruta");
			menuDAO.anyadirMenu(m4);
			
			Menu m5 = new Menu("MEN5","16-9-2018","Sopa","Sepia","Natillas");
			menuDAO.anyadirMenu(m5);
			
		}catch(Exception ex) {
			logger.error("-Error creando nuevo menu: " + ex.getMessage());
		}
	}
	
	@Test
	public void testLibro() {
		try {
			logger.info("-Creando 3 libros...");
			
			Libro l1 = new Libro("1", "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros");
			libroDAO.almacenarLibro(l1);
			
			Libro l2 = new Libro("2", "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh");
			libroDAO.almacenarLibro(l2);
			
			Libro l3 = new Libro("3", "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A");
			libroDAO.almacenarLibro(l3);
				
		}catch(Exception ex) {
			logger.error("-Error creando nuevo libro: " + ex.getMessage());
		}
	}
	
	@Test
	public void testMesa() {
		try {
			logger.info("-Creando 5 mesas...");
			
			Mesa me1 = new Mesa("M1",4);
			mesaDAO.anyadirMesa(me1);
			
			Mesa me2 = new Mesa("M2",6);
			mesaDAO.anyadirMesa(me2);
			
			Mesa me3 = new Mesa("M3",8);
			mesaDAO.anyadirMesa(me3);
			
			Mesa me4 = new Mesa("M4",6);
			mesaDAO.anyadirMesa(me4);
			
			Mesa me5 = new Mesa("M5",2);
			mesaDAO.anyadirMesa(me5);
		
				
		}catch(Exception ex) {
			logger.error("-Error creando nueva mesa: " + ex.getMessage());
		}
	}

	
	@Test
	public void testRMesa() {
		try {
			logger.info("-Creando 4 reservas de comedor...");
			
			ReservaMesa rM1 = new ReservaMesa("M1", "REC1 ", "12345678X", "30-04-2018", "14:30", 2);
			reservaCDAO.anyadirReservaComedor(rM1);
			
			ReservaMesa rM2 = new ReservaMesa("M2", "REC1", "19182138S", "1-05-2018", "15:00", 6);
			reservaCDAO.anyadirReservaComedor(rM2);
			
			ReservaMesa rM3 = new ReservaMesa("M3", "REC2 ", "78691386P", "13-05-2018", "13:30", 8);
			reservaCDAO.anyadirReservaComedor(rM3);
			
			ReservaMesa rM4 = new ReservaMesa("M4", "REC3 ", "12345678X", "31/02/18", "14:00", 1);
			reservaCDAO.anyadirReservaComedor(rM4);
				
		}catch(Exception ex) {
			logger.error("-Error creando nueva reserva comedor: " + ex.getMessage());
		}
	}
	
	@Test
	public void testSala() {
		try {
			logger.info("-Creando 5 salas...");
			
			Sala s1 = new Sala("S1",10);
			salaDAO.anyadirSala(s1);
			
			Sala s2 = new Sala("S2",8);
			salaDAO.anyadirSala(s2);
			
			Sala s3 = new Sala("S3",6);
			salaDAO.anyadirSala(s3);
			
			Sala s4 = new Sala("S4",8);
			salaDAO.anyadirSala(s4);
			
			Sala s5 = new Sala("S5",10);
			salaDAO.anyadirSala(s5);
						
		}catch(Exception ex) {
			logger.error("-Error creando nueva sala: " + ex.getMessage());
		}
	}
	
	@Test
	public void testReserva() {
		try {
			logger.info("-Creando 4 reservas...");
			
			Reserva R1 = new Reserva("RE1", "S1", "12345678X", "30-04-2018", "17:30", 2);
			reservaDAO.anyadirReserva(R1);
			
			Reserva R2 = new Reserva("RE2", "S1", "23456789Y", "15-11-2018", "12:30", 4);
			reservaDAO.anyadirReserva(R2);
			
			Reserva R3 = new Reserva("RE3", "S2", "12345678X", "7-02-2018", "19:00", 6);
			reservaDAO.anyadirReserva(R3);
			
			Reserva R4 = new Reserva("RE4", "S3", "639710897Q", "28-09-2018", "18:45", 8);
			reservaDAO.anyadirReserva(R4);
				
		}catch(Exception ex) {
			logger.error("-Error creando nueva reserva: " + ex.getMessage());
		}
	}
}
