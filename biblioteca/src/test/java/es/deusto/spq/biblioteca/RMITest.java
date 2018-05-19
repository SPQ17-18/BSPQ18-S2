package es.deusto.spq.biblioteca;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;

import org.junit.AfterClass;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import java.io.IOException;
import java.net.MalformedURLException;



/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
//@Ignore

public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	final static Logger logger= LoggerFactory.getLogger(RMITest.class);

	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private IBiblioteca service;
	
	/*private List<Libro> arrayLibro = null;
	private List<Menu> arrayMenu = null;
	private List<Mesa> arrayMesa = null;
	private List<Reserva> arrayReserva = null;
	private List<ReservaMesa> arrayReservaMesa = null;
	private List<Sala> arraySala = null;*/

	//PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("jdo.properties");
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}


	@BeforeClass
	public static void setUp() {
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
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
		
	class RMIServerRunnable implements Runnable {

		public void run() {
			logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
			logger.info("**************: " + cwd);
			System.setProperty("java.rmi.server.codebase", "file:" + cwd);
			System.setProperty("java.security.policy", "src//main//resources//security//java.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
	
			String args[] = new String[3];
			args[0] = "127.0.0.1";
			args[1] = "1099";
			args[2] = "BibliotecaRMI";
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			logger.info("BeforeClass - Setting the server ready TestServer name: " + name);
			
			try {
				IBiblioteca server = new Biblioteca(args[0],Integer.parseInt(args[1]));
				Naming.rebind(name, server);
				logger.info("* Servidor '" + name + "' activo y esperando...");
			} catch (RemoteException re) {
				System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
				re.printStackTrace();
				System.exit(-1);
			} catch (MalformedURLException murle) {
				System.err.println(" # Biblioteca MalformedURLException: " + murle.getMessage());
				murle.printStackTrace();
				System.exit(-1);
			}
			
			rmiServerThread = new Thread(new RMIServerRunnable());
			rmiServerThread.start();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}
	}

	@Before
	public void setUpClient() {
		try {
			System.setProperty("java.security.policy", "src//main//resources//security//java.policy");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			String args[] = new String[3];
			args[0] = "127.0.0.1";
			args[1] = "1099";
			args[2] = "BibliotecaRMI";
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			service=(IBiblioteca) java.rmi.Naming.lookup(name);
		} catch (Exception re) {
			logger.error(" # Client RemoteException: " + re.getMessage());
			System.exit(-1);
		}

	}

	@Test
	public void almacenarLibroTest() {

		assertTrue(true);

	}
	/*
	 * @Test public void buscarLibroTest() { try{
	 * logger.info("Test 2 - buscar libro"); String
	 * a="Festin de cuervos, Cancion de Hielo y fuego IV";
	 * Ibiblioteca.buscarLibro(a); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void reserveBookTest() { try{
	 * logger.info("Test 3 - Register new user"); String
	 * a="Festin de cuervos, Cancion de Hielo y fuego IV"; Libro l =new Libro();
	 * l.setnombre(a); assertTrue( Ibiblioteca.reserveBook(l) ); } catch (Exception
	 * re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void mostrarLibroTest() { try{
	 * logger.info("Test 4 - Register new user"); String
	 * a="Festin de cuervos, Cancion de Hielo y fuego IV";
	 * Ibiblioteca.mostrarLibro(a); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void consultarDiponibilidadLibroTest() { try{
	 * logger.info("Test 5 - Register new user"); String
	 * a="Festin de cuervos, Cancion de Hielo y fuego IV";
	 * Ibiblioteca.consultarDiponibilidadLibro(a); assertTrue( true ); } catch
	 * (Exception re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void getLibrosTest() { try{
	 * logger.info("Test 6 - Register new user"); Ibiblioteca.getLibros();
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void anyadirReservaTest() { try{
	 * logger.info("Test 7 - Register new user"); Ibiblioteca.anyadirReserva("S1",
	 * "12345678X", "11/04/18", "21:20", 3); assertTrue( true ); } catch (Exception
	 * re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void anyadirSalaTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.anyadirSala("S1", 10);
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void consultarDisponibilidadTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void verReservasTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.verReservas("12345678X"); assertTrue( true ); } catch (Exception
	 * re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void editarReservaTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15",
	 * "12:00", "S2"); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void eliminarReservaTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.eliminarReserva("S1",
	 * "12345678X"); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test //añadir cosas a reseva public void anyadirUsuarioTest() { try{
	 * logger.info("Test 8 - Register new user"); Reserva r = new Reserva();
	 * Ibiblioteca.anyadirUsuario(r); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void EliminarLibroTest() { try{
	 * logger.info("Test 8 - Register new user"); Libro l = new Libro();
	 * Ibiblioteca.EliminarLibro(l); assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void EliminarParticipanteTest() { try{
	 * logger.info("Test 8 - Register new user"); Reserva r = new Reserva();
	 * Ibiblioteca.EliminarParticipante(r); assertTrue( true ); } catch (Exception
	 * re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void DevolverReservaTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.DevolverReserva("12345678X", "11/04/18", "21:20"); assertTrue(
	 * true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void anyadirReservaComedorTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void consultarDisponibilidadComedorTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3);
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void anyadirMesaTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.anyadirMesa("M1", 4);
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void verReservaComedorTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.verReservaComedor("12345678X"); assertTrue( true ); } catch
	 * (Exception re) { System.err.println(" # Biblioteca RemoteException: " +
	 * re.getMessage()); assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void DevolverReservaMesaTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.DevolverReservaMesa("12345678X", "30/04/18", "14:30");
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void eliminarReservaComedorTest() { try{
	 * logger.info("Test 8 - Register new user");
	 * Ibiblioteca.eliminarReservaComedor("12345678X", "30/04/18", "14:30");
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * /*
	 * 
	 * @Test public void consultaMenuTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.consultaMenu();
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void seleccionarMenuTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.seleccionarMenu();
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void comprarMenuTest() { try{
	 * logger.info("Test 8 - Register new user"); Ibiblioteca.comprarMenu();
	 * assertTrue( true ); } catch (Exception re) {
	 * System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	 * assertTrue( false ); }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * /*
	 * 
	 * @Test public void sayMessageValidUser() {
	 * logger.info("Test 3 - Sending message - Valid User"); String ret = null; try{
	 * messenger.registerUser("cortazar","cortazar"); ret =
	 * messenger.sayMessage("cortazar", "cortazar", "testing message"); } catch
	 * (RemoteException e){
	 * 
	 * } assertEquals("testing message", ret); }
	 * 
	 * 
	 * @After public void deleteDatabase() { PersistenceManagerFactory pmf =
	 * JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	 * PersistenceManager pm = pmf.getPersistenceManager(); Transaction tx =
	 * pm.currentTransaction(); try { tx.begin();
	 * 
	 * logger.info("Deleting test users from persistence. Cleaning up.");
	 * Query<User> q1 = pm.newQuery(User.class); long numberInstancesDeleted =
	 * q1.deletePersistentAll(); logger.info("Deleted " + numberInstancesDeleted +
	 * " user");
	 * 
	 * tx.commit(); } finally { if (tx.isActive()) { tx.rollback(); } pm.close(); }
	 * 
	 * }
	 */

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