package es.deusto.spq.biblioteca.server;

import java.rmi.Naming;
import org.apache.log4j.Logger;
import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;

public class Server {
	
	private static final Logger logger = Logger.getLogger(Server.class);

	
	public static void main(String[] args) {

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			//IBiblioteca server = new Biblioteca(args[0],Integer.parseInt(args[1])); --> Estaba puesto antes asi
			IBiblioteca server = new Biblioteca();
			Naming.rebind(name, server);
			//System.out.println("* Servidor '" + name + "' activo y esperando...");
			logger.info("* Servidor '" + name + "' activo y esperando...");

			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			String line  = stdin.readLine();
		} catch (Exception e) {
			//System.err.println("- Error en el servidor CRAI: " + e.getMessage());
			logger.error("- Error en el servidor CRAI: " + e.getMessage());

			e.printStackTrace();
		}
	}
}
