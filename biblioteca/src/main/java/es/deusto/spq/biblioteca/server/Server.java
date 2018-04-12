package es.deusto.spq.biblioteca.server;

import java.rmi.Naming;

import es.deusto.spq.biblioteca.remote.Biblioteca;
import es.deusto.spq.biblioteca.remote.IBiblioteca;

public class Server {
	public static void main(String[] args) {

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IBiblioteca server = new Biblioteca(args[3],Integer.parseInt(args[4]));
			Naming.rebind(name, server);
			System.out.println("* Servidor '" + name + "' activo y esperando...");
		} catch (Exception e) {
			System.err.println("- Error en el servidor EasyBooking: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
