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
			
			//Para que este todo el rato escuchando el servidor
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			String line  = stdin.readLine();
		} catch (Exception e) {
			System.err.println("- Error en el servidor EasyBooking: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
