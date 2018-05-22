package es.deusto.spq.biblioteca;
/**
 * Test rmi
 * @author Ariane y Mikel
 *
 */

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.deusto.spq.biblioteca.remote.IBiblioteca;
import es.deusto.spq.biblioteca.remote.Biblioteca;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
@Ignore
public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	final static Logger logger= LoggerFactory.getLogger(RMITest.class);

	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private IBiblioteca biblioteca;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}


	@BeforeClass static public void setUp() {
		
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					System.out.println("BeforeClass: RMI registry ready.");
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					System.out.println("Exception starting RMI registry:");
					logger.info("Exception starting RMI registry:");
					e.printStackTrace();
				}
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
			
	class RMIServerRunnable implements Runnable {

		public void run() {
			System.out.println("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
			System.out.println("**************: " + cwd);
			System.setProperty("java.rmi.server.codebase", "file:" + cwd);
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
	
			String name = "//127.0.0.1:1099/BibliotecaRMI";
			System.out.println("BeforeClass - Setting the server ready TestServer name: " + name);
			logger.info("BeforeClass - Setting the server ready TestServer name: " + name);
			
			try {
				System.out.println("1");
				IBiblioteca biblioteca = new Biblioteca();
				System.out.println("2");
				Naming.rebind(name,biblioteca);
				System.out.println("3");
				System.out.println("* Servidor '" + name + "' activo y esperando...");
			} catch (RemoteException re) {
					System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					System.err.println(" # Biblioteca MalformedURLException: " + murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}

			}

		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}
	

	@Before	public void setUpClient() {
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			String name = "//127.0.0.1:1099/BibliotecaRMI";
			System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			biblioteca=(IBiblioteca) java.rmi.Naming.lookup(name);
			logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		} catch (Exception re) {
			System.err.println(" #Biblioteca RemoteException: " + re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		}

	}
	
	  @Test public void buscarLibroTest() { try{
	  logger.info("Test 2 - buscar libro"); String
	  a="Festin de cuervos, Cancion de Hielo y fuego IV";
	  biblioteca.buscarLibro(a); assertTrue( true ); 
	  } catch (Exception re) {
	  System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	  assertTrue( false ); 
	  	}
	  
	  }
	  	  
	@Test
	public void mostrarLibroTest() {
		try {
			logger.info("Test 4 - Register new user");
			String a = "Festin de cuervos, Cancion de Hielo y fuego IV";
			biblioteca.mostrarLibro(a);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void consultarDiponibilidadLibroTest() {
		try {
			logger.info("Test 5 - Register new user");
			String a = "Festin de cuervos, Cancion de Hielo y fuego IV";
			biblioteca.consultarDiponibilidadLibro(a);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void getLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.getLibros();
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void reservarLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.reservarLibro("1");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void devolverLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.DevolverLibro("1");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void devolverReservaTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.DevolverReserva("12345678X", "21-5-2018", "21:47");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void devolverReservaComedorTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.devolverReservaComedor("12345678X", "21-5-2018", "21:48");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void ayadirLibrosTest() {
		try {
			logger.info("Test 6 - Register new user");
			biblioteca.almacenarLibro("5", "V de vendetta", "Alan Moore", "Planeta");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void anyadirReservaTest() {
		try {
			logger.info("Test 7 - Register new user");
			biblioteca.anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void anyadirSalaTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.anyadirSala("S1", 10);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void consultarDisponibilidadTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void verReservasTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.verReservas("12345678X");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void editarReservaTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void anyadirReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void consultarDisponibilidadComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void anyadirMesaTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.anyadirMesa("M1", 4);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void anyadirMenuTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.anyadirMenu("21-5-2018", "Combo de entrantes", "The King's Bacon", "Sandy");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void anyadirValoracionTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.anyadirValoracion("M1", 8);
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void verReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.verReservaComedor("12345678X");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void eliminarReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.eliminarReservaComedor("12345678X", "30/04/18", "14:30");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void eliminarReservaTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.eliminarReserva("12345678X", "30/04/18", "14:30");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void consultaMenuTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.verMenu("12-05-2018");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

	}
	
	@Test
	public void editarReservaComedorTest() {
		try {
			logger.info("Test 8 - Register new user");
			biblioteca.editarReservaComedor("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue(true);
		} catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue(false);
		}

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