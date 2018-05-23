package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import es.deusto.spq.biblioteca.controller.Controller;
import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.dao.MenuDAO;
import es.deusto.spq.biblioteca.dao.MesaDAO;
import es.deusto.spq.biblioteca.dao.ReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.dao.ReservaLibroDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaLibro;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.data.Sala;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;
import junit.framework.JUnit4TestAdapter;

public class TestController {

	
	
	private static Controller controller;

	private static String cwd = TestController.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private static LibroDAO libroDAO;
	private static MenuDAO menuDAO;
	private static MesaDAO mesaDAO;
	private static SalaDAO salaDAO;
	private static ReservaComedorDAO reservaComedorDAO;
	private static ReservaDAO reservaDAO;
	private static ReservaLibroDAO reservaLibroDAO;
	
	
	private static Logger logger = Logger.getLogger(TestController.class.getName());
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestController.class);
	}
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		
		logger.info("Launch the RMI registry");
		class RMIRegistryRunnable implements Runnable {
			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					logger.info("Exception starting RMI registry:");
					e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			logger.info("sleeping 4000ms");
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		//Lanzar servidor
		class RMIServerRunnable implements Runnable {

			public void run() {
				logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				logger.info("**************: " + cwd);
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target\\classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/BibliotecaRMI";
				logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					IBiblioteca biblioteca = new Biblioteca();
					Naming.rebind(name, biblioteca);
				} catch (RemoteException re) {
					logger.error(" # BibliotecaRMI RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # BibliotecaRMI MalformedURLException: " + murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			logger.info("sleeping 4000ms");
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}}

	
	@Before
	public void setUpClass() throws Exception {
		//Inicializado para todos los teses
//		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String[] args = new String[3];
		args[0] = "127.0.0.1";
		args[1] = "1099";
		args[2] = "BibliotecaRMI";
		controller = new Controller(args);
		
		
		 libroDAO = new LibroDAO();
		  menuDAO = new MenuDAO();
		  mesaDAO = new MesaDAO();
		  salaDAO = new SalaDAO();
		  reservaComedorDAO = new ReservaComedorDAO();
		  reservaDAO = new ReservaDAO();
		  reservaLibroDAO = new ReservaLibroDAO();
		  
		  Libro L = new Libro("I5", "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A");
		  Libro L1 = new Libro("I6", "Festin de cuervos, Cancion de Hielo y fuego 5", "George R.R. Martin", "Gigamesh");
		  
		  Menu m = new Menu("MEN3","29-3-2018","Menestra","Salmon","Tarta");
		  Menu m1 = new Menu("MEN4","30-5-2018","Alubias","Filete","Chocolate");
		  
		  Mesa me1 = new Mesa("M8",4);			
		  Mesa me2 = new Mesa("M9",6);
		    
		  Sala s1 = new Sala("S8",10);		
		  Sala s2 = new Sala("S9",8);
		
		  Reserva r1 = new Reserva("RE8", "S8", "23456789Y", "15-11-2018", "12:30", 4);
		  Reserva r2 = new Reserva("RE9", "S9", "12345678X", "7-02-2018", "19:00", 6);
		  
		  ReservaMesa r3 = new ReservaMesa("M8", "REC1", "19182138S", "1-05-2018", "15:00", 6);
		  ReservaMesa r4 = new ReservaMesa("M9", "REC2 ", "78691386P", "13-05-2018", "13:30", 8);
		  
		  ReservaLibro r5 = new ReservaLibro("I5");
		  ReservaLibro r6 = new ReservaLibro("I6");
		 
		  libroDAO.almacenarLibro(L);
		  libroDAO.almacenarLibro(L1);
		  menuDAO.anyadirMenu(m);
		  menuDAO.anyadirMenu(m1);
		  mesaDAO.anyadirMesa(me1);
		  mesaDAO.anyadirMesa(me2);
		  salaDAO.anyadirSala(s1);
		  salaDAO.anyadirSala(s2);
		  reservaDAO.anyadirReserva(r1);
		  reservaDAO.anyadirReserva(r2);
		  reservaComedorDAO.anyadirReservaComedor(r3);
		  reservaComedorDAO.anyadirReservaComedor(r4);
		  reservaLibroDAO.reservarLibro(r5);
		  reservaLibroDAO.reservarLibro(r6);
		  



	}
	
	
	  @Test 
	  public void buscarLibroTest() {
		  logger.info("Test 1 - Buscar libro");
		  controller.buscarLibro("Festin de cuervos, Cancion de Hielo y fuego 5");
	  
//			  try{
//					logger.info("Test 1 - Buscar libro");
//					controller.buscarLibro("Festin de cuervos, Cancion de Hielo y fuego 5");
//					assertEquals(controller.buscarLibro("Festin de cuervos, Cancion de Hielo y fuego 5"),"Festin de cuervos, Cancion de Hielo y fuego 5" );
//				}
//				catch (Exception re) {
//					logger.error(" # RDCar RemoteException: " + re.getMessage());
//					re.printStackTrace();
//				} 						
	  }
	  	  
	@Test
	public void mostrarLibroTest() {
		logger.info("Test 2 - ver reserva");
		controller.mostrarLibro("Festin de cuervos, Cancion de Hielo y fuego 5");
//		try {
//			logger.info("Test 2 - Mostrar libro");
//			String a = "Festin de cuervos, Cancion de Hielo y fuego 5";
//			controller.mostrarLibro(a);
//			assertTrue(false);
//		} catch (Exception re) {
//			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
//		}
//		assertTrue(true);
//
	}

	@Test
	public void consultarDiponibilidadLibroTest() {
		try {
			logger.info("Test 3 - Consultar disponibilidad Libro");
			assertEquals(false,controller.consultarDiponibilidadLibro("I5"));
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
		}

	}
	
	@Test
	public void reservarLibrosTest() throws RemoteException {
			logger.info("Test 4 - Reservar libro");
			String l = "I6";
			controller.reservarLibro(l);

	}
	
	@Ignore
	public void devolverLibrosTest()throws RemoteException {
		logger.info("Test 5 - Consultar disponibilidad Libro");
		assertEquals(String.valueOf(controller.DevolverLibro("I5").getIsbn()),"I5");

	}
	
	
	
	@Test
	public void consultarDisponibilidadTest() {
		try {
			logger.info("Test 6 - Consultar disponibilidad");
			assertEquals(true,controller.consultarDisponibilidad("S1", "16-11-2018", "12:30", 3));
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
		}

	}
	
	
	@Test
	public void ayadirLibrosTest()throws RemoteException {
		logger.info("Test 7 - añadir libro");
			controller.almacenarLibro("5", "V de vendetta", "Alan Moore", "Planeta");

	}
	
	@Test
	public void anyadirReservaTest()throws RemoteException {
		logger.info("Test 8 - añadir reserva");
		controller.anyadirReserva( "S6", "23456789Y", "15-11-2018", "12:30", 4);
	}
	
	@Test
	public void consultarDisponibilidadComedorTest() {
		try {
			logger.info("Test 9 - Consultar disponibilidad Comedor");
			assertEquals(true,controller.consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3));
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
		}

	}
	

	@Test
	public void anyadirSalaTest()throws RemoteException {
		logger.info("Test 10 - Añadir reserva");
		controller.anyadirSala("S10", 5);

	}


	@Test
	public void verReservasTest()throws RemoteException {
		logger.info("Test 11 - ver reserva");
		controller.verReservas("23456789Y");
	}

	@Test
	public void editarReservaTest() {
		try {
			logger.info("Test 12 - Editar reserva ");
			controller.editarReserva("12345678X", "11-04-2018", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void anyadirReservaComedorTest()throws RemoteException {
		logger.info("Test 13 - Añadir reserva comedor");
		controller.anyadirReservaComedor("M10", "78691386O", "20-10-2019", "14:17", 4);

	}


	@Test
	public void anyadirMesaTest() throws RemoteException {
		logger.info("Test 14 - Añadir mesa");
		controller.anyadirMesa("M10", 5);

	}
	
	@Test
	public void anyadirValoracionTest() throws RemoteException {
		logger.info("Test 15 - Añadir valoracion");
		controller.anyadirValoracion("MEN4", 9);

	}

	@Test
	public void verReservaComedorTest() throws RemoteException {
		logger.info("Test 16 - Ver reserva comedor");
		controller.verReservaComedor("19182138S");

	}
	
	@Test
	public void consultaMenuTest() throws Exception {
		logger.info("Test 17 - Consultar menu");
		controller.verMenu("30-5-2018");


	}
	
	@Test
	public void editarReservaComedorTest() {
		try {
			logger.info("Test 18 - Editar reserva comedor");
			controller.editarReservaComedor("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void eliminarReservaComedorTest() throws RemoteException{
		logger.info("Test 19 - eliminar reserva comedor");
		controller.editarReservaComedor("19182138S", "1-05-2018", "15:00", "M8", "2-05-2018", "14:20", "M10");
//		try {
//			logger.info("Test 8 - Register new user");
//			biblioteca.eliminarReservaComedor("12345678X", "30/04/18", "14:30");
//			assertTrue(true);
//		} catch (Exception re) {
//			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
//			assertTrue(false);
//		}

	}
	
	@Test
	public void eliminarReservaTest() {
		logger.info("Test 20 - eliminar reserva ");
		controller.eliminarReserva( "23456789Y", "15-11-2018", "12:30");
//		try {
//			logger.info("Test 8 - Register new user");
//			biblioteca.eliminarReserva("12345678X", "30/04/18", "14:30");
//			assertTrue(true);
//		} catch (Exception re) {
//			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
//			assertTrue(false);
//		}

	}
	
	
	@AfterClass
	static public void tearDown() {
		try {
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}
