package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.client.client;

public class controller {
	@SuppressWarnings("unused")
	private client cl;

	public controller(String[] args) throws RemoteException {

		cl = new client();
		cl.setService(args);

		// desde aqui haremos un cl.getservicio e iremos anyadiendo las diferentes
		// funciones
		Boolean disponible=cl.getService().consultarDisponibilidad("S1", "11/04/18", "21:20");
		if(disponible) {
		cl.getService().anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
		}
		
		//Almacenamos libros 
		cl.getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
		cl.getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
		cl.getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);				cl.getService().almacenarLibro(4, "Paco Jemez: Grandes exitos en el Rayo Vallecano", "Jose Antonio Camacho", "Furia Vallecana", false);
		
		//Busqueda de libro por nombre
		cl.getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		cl.getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");
		
		//cl.getService().consultarCatalogo();  esta sin hacer aun
	}
	
	public client getCl() {
		return cl;
	}

	public void setCl(client cl) {
		this.cl = cl;
	}

	public static void main(String[] args) throws RemoteException {

		new controller(args);
	}
}
