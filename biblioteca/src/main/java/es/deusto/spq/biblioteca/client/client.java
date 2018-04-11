package es.deusto.spq.biblioteca.client;

import es.deusto.spq.biblioteca.remote.IBiblioteca;

public class client {

	private IBiblioteca service;
	
	public client() {
	
	}

	public void setService(String[] args) {
	
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			service=(IBiblioteca) java.rmi.Naming.lookup(name);
			System.out.println("Conectado con el servidor: " + name);
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public IBiblioteca getService() {
		return service;
	}
}
