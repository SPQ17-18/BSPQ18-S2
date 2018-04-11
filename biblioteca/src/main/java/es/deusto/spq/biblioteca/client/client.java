package es.deusto.spq.biblioteca.client;

public class client {

	public static void setService(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			// Aqui se mete el servicio del remote
			System.out.println("Conectado con el servidor: " + name);

		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Aquí hay que crear un metodo para hacer un get del remote
}
