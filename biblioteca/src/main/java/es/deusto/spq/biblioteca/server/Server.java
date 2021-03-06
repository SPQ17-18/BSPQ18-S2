package es.deusto.spq.biblioteca.server;

import java.rmi.Naming;
import org.apache.log4j.Logger;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;
/**
 * Clase de servidor
 * @author Ariane y Koldo
 *
 */
public class Server {
	
	private static final Logger logger = Logger.getLogger(Server.class);

	/**
	 * Metodo main del servidor
	 */
	public static void main(String[] args) {

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			IBiblioteca server = new Biblioteca();
			Naming.rebind(name, server);
			logger.info("* Servidor '" + name + "' activo y esperando...");

			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			String line  = stdin.readLine();
		} catch (Exception e) {
			logger.error("- Error en el servidor CRAI: " + e.getMessage());

			e.printStackTrace();
		}
	}
}
