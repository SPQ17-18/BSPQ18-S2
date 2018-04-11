package es.deusto.spq.biblioteca.server;

public class server {
	public static void main(String[] args) {

		// Esta línea de codigo el if a veces da problema si vemos que no funciona se
		// quita

		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			// TODO
			// Aquí se mete el remote
			System.out.println("* Servidor '" + name + "' activo y esperando...");
		} catch (Exception e) {
			System.err.println("- Error en el servidor EasyBooking: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
