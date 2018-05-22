package es.deusto.spq.biblioteca.client;

import es.deusto.spq.biblioteca.remote.IBiblioteca;
import org.apache.log4j.Logger;
/**
 * Clase Cliente 
 * @author Ariane y koldo
 *
 */

public class Client {

	private IBiblioteca service;
	private static final Logger logger = Logger.getLogger(Client.class);

	
	/**
	 * Metodo que proporciona el servicio al cliente.
	 * @param args argumentos.
	 */
	public void setService(String[] args) {

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			service=(IBiblioteca) java.rmi.Naming.lookup(name);
			logger.info("Conectado con el servidor: " + name + "\n"+ service  );

		} catch (Exception e) {
			logger.error("   $ RMI Client exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public IBiblioteca getService() {
		return service;
	}
}