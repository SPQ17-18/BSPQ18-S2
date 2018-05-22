package es.deusto.spq.biblioteca;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;

import es.deusto.spq.biblioteca.controller.Controller;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;
import junit.framework.JUnit4TestAdapter;

public class TestController {

	
	
	private static Controller controller;

	private static String cwd = TestController.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private static Logger logger = Logger.getLogger(TestController.class.getName());
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestController.class);
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
		
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
	public void setUp() throws Exception {
		//Inicializado para todos los teses
//		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String[] args = new String[3];
		args[0] = "127.0.0.1";
		args[1] = "1099";
		args[2] = "BibliotecaRMI";
		controller = new Controller(args);
		
	}
	

	

	
	  @Test public void buscarLibroTest() { try{
	  logger.info("Test 2 - buscar libro"); String
	  a="Festin de cuervos, Cancion de Hielo y fuego IV";
	  controller.buscarLibro(a); assertTrue(false); 
	  } catch (Exception re) {
	  System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	  assertTrue(true); 
	  	}
	  
	  }
	  	  
	@Test
	public void mostrarLibroTest() {
		try {
			logger.info("Test 4 - Register new user");
			String a = "Festin de cuervos, Cancion de Hielo y fuego IV";
			controller.mostrarLibro(a);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void consultarDiponibilidadLibroTest() {
		try {
			logger.info("Test 5 - Register new user");
			String a = "Festin de cuervos, Cancion de Hielo y fuego IV";
			controller.consultarDiponibilidadLibro(a);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void getLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.verCatalogoDeLibros();
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void reservarLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.reservarLibro("1");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
		}

	}
	
	@Test
	public void devolverLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.DevolverLibro("1");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void devolverReservaTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.verReservas("12345678X");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void devolverReservaComedorTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.verReservas("12345678X");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void ayadirLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			controller.almacenarLibro("5", "V de vendetta", "Alan Moore", "Planeta");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void anyadirReservaTest() {
		try {
			logger.info("Test 7 - Register new user");
			controller.anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void anyadirSalaTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.anyadirSala("S1", 10);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void consultarDisponibilidadTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void editarReservaTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void anyadirReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	@Test
	public void consultarDisponibilidadComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			assertEquals(true,controller.consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3));
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
		}

	}

	@Test
	public void anyadirMesaTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.anyadirMesa("M1", 4);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void anyadirMenuTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.verMenu("21-5-2018");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void anyadirValoracionTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.anyadirValoracion("M1", 8);
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	
	@Test
	public void eliminarReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.eliminarReservaComedor("12345678X", "30/04/18", "14:30");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}
	
	@Test
	public void eliminarReservaTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.eliminarReserva("12345678X", "30/04/18", "14:30");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

	
	@Test
	public void editarReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			controller.editarReservaComedor("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(false);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(true);
		}

	}

}
