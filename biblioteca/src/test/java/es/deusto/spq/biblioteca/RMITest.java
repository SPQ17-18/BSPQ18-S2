package es.deusto.spq.biblioteca;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.AfterClass;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
//import org.junit.Ignore;

import es.deusto.spq.biblioteca.client.Client;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.data.Sala;
import es.deusto.spq.biblioteca.remote.*;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.net.MalformedURLException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;



/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
//@Ignore

public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	private static String[] args = { "127.0.0.1", "1099", "RMITest" };
	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private static IBiblioteca Ibiblioteca;
	private static Client cliente;
	
	private List<Libro> arrayLibro = null;
	private List<Menu> arrayMenu = null;
	private List<Mesa> arrayMesa = null;
	private List<Reserva> arrayReserva = null;
	private List<ReservaMesa> arrayReservaMesa = null;
	private List<Sala> arraySala = null;
	
	final Logger logger= LoggerFactory.getLogger(RMITest.class);
	static int iteracion = 0;
	
	
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}


	@BeforeClass static public void setUp() {
		cliente = new Client();
		cliente.setService(args);
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					System.out.println("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					System.out.println("Exception starting RMI registry:");
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

				String name = "//127.0.0.1:1099/RMITest";
				System.out.println("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					Ibiblioteca = new Biblioteca("127.0.0.1",1099);
					Naming.rebind(name, Ibiblioteca);
				} catch (RemoteException re) {
					System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
					re.printStackTrace();//dist
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
/*
	@Before 
	public void setUpClient() {
		try {
		System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//127.0.0.1:1099/BibliotecaRMIDAO";
		System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		Ibiblioteca = (IBiblioteca) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
	//		re.printStackTrace();
			System.exit(-1);
		} 
		
	}*/
	
	@Test 
	public void almacenarLibroTest() {
		System.out.println("\nPRUEBA1");
		try{
			logger.info("Test 1 - almacena libro");
			System.out.println("PRUEBA2");
			Ibiblioteca.almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
			System.out.println("PRUEBA3");
			assertTrue( true );
		}
		catch (Exception re) {
			System.out.println("PRUEBA4");
			logger.info(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		/*
		 * Very simple test 
		 */
		
	}
	
	@Test 
	public void buscarLibroTest() {
		try{
			System.out.println("Test 2 - buscar libro");
			String a="Festin de cuervos, Cancion de Hielo y fuego IV";
			Ibiblioteca.buscarLibro(a);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}

		
		
	@Test 
	public void mostrarLibroTest() {
		try{
			System.out.println("Test 4 - Register new user");
			String a="Festin de cuervos, Cancion de Hielo y fuego IV";
			Ibiblioteca.mostrarLibro(a);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void consultarDiponibilidadLibroTest() {
		try{
			System.out.println("Test 5 - Register new user");
			String a="Festin de cuervos, Cancion de Hielo y fuego IV";
			Ibiblioteca.consultarDiponibilidadLibro(a);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void getLibrosTest() {
		try{
			System.out.println("Test 6 - Register new user");
			Ibiblioteca.getLibros();
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void anyadirReservaTest() {
		try{
			System.out.println("Test 7 - Register new user");
			Ibiblioteca.anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void anyadirSalaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.anyadirSala("S1", 10);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void consultarDisponibilidadTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void verReservasTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.verReservas("12345678X");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void editarReservaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void eliminarReservaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.eliminarReserva("S1", "12345678X");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test //añadir cosas a reseva
	public void anyadirUsuarioTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Reserva r = new Reserva();
			Ibiblioteca.anyadirUsuario(r);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void EliminarLibroTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Libro l = new Libro();
			Ibiblioteca.EliminarLibro(l);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void EliminarParticipanteTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Reserva r = new Reserva();
			Ibiblioteca.EliminarParticipante(r);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void DevolverReservaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.DevolverReserva("12345678X", "11/04/18", "21:20");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void anyadirReservaComedorTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void consultarDisponibilidadComedorTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void anyadirMesaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.anyadirMesa("M1", 4);
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void verReservaComedorTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.verReservaComedor("12345678X");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void DevolverReservaMesaTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.DevolverReservaMesa("12345678X", "30/04/18", "14:30");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void eliminarReservaComedorTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.eliminarReservaComedor("12345678X", "30/04/18", "14:30");
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	/*
	
	@Test 
	public void consultaMenuTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.consultaMenu();
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void seleccionarMenuTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.seleccionarMenu();
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	@Test 
	public void comprarMenuTest() {
		try{
			System.out.println("Test 8 - Register new user");
			Ibiblioteca.comprarMenu();
			assertTrue( true );
		}
		catch (Exception re) {
			System.err.println(" # Biblioteca RemoteException: " + re.getMessage());
			assertTrue( false );
		} 
		
		
	}
	
	
	/*
	@Test public void sayMessageValidUser() {
		System.out.println("Test 3 - Sending message - Valid User");
		String ret = null;
		try{
			messenger.registerUser("cortazar","cortazar");
			ret = messenger.sayMessage("cortazar", "cortazar", "testing message");
		} catch (RemoteException e){
			
		}
		assertEquals("testing message", ret);
	}
	
	
	@After public  void deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
	
            System.out.println("Deleting test users from persistence. Cleaning up.");
            Query<User> q1 = pm.newQuery(User.class);
            long numberInstancesDeleted = q1.deletePersistentAll();
            System.out.println("Deleted " + numberInstancesDeleted + " user");
			
            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	*/

	@AfterClass static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		

	}
	
}