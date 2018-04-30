package es.deusto.spq.biblioteca.client;

import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.remote.IBiblioteca;
import org.apache.log4j.Logger;


public class Client {

	private IBiblioteca service;
	private static final Logger logger = Logger.getLogger(Client.class);


	public Client() {

	}

	public void setService(String[] args) {

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

			service=(IBiblioteca) java.rmi.Naming.lookup(name);
			//System.out.println("Conectado con el servidor: " + name + "\n"+ service  );
			logger.info("Conectado con el servidor: " + name + "\n"+ service  );


			service = (IBiblioteca) java.rmi.Naming.lookup(name);
			//System.out.println("Conectado con el servidor: " + name);
			logger.info("Conectado con el servidor: " + name);


		} catch (Exception e) {
			//System.err.println("RMI Client exception: " + e.getMessage());
			logger.error("   $ RMI Client exception: " + e.getMessage());

			e.printStackTrace();
		}
	}

	public IBiblioteca getService() {
		return service;
	}
}