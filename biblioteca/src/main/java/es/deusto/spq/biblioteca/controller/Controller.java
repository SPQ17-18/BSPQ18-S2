package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.client.Client;


public class Controller {
	@SuppressWarnings("unused")
	private Client cl;

	public Controller(String[] args) throws RemoteException {

		cl = new Client();
		cl.setService(args);

		// desde aqui haremos un cl.getservicio e iremos anyadiendo las diferentes
		// funciones
//		Boolean disponible=cl.getService().consultarDisponibilidad("S1", "11/04/18", "21:20");
//		if(disponible) {
//		cl.getService().anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
//		}
//		
//		//Almacenamos libros 
//		cl.getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
//		cl.getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
//		cl.getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);				cl.getService().almacenarLibro(4, "Paco Jemez: Grandes exitos en el Rayo Vallecano", "Jose Antonio Camacho", "Furia Vallecana", false);
//		
//		//Busqueda de libro por nombre
//		cl.getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
//		cl.getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");
		
		//cl.getService().consultarCatalogo();  esta sin hacer aun
	}
	
	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public static void main(String[] args) throws RemoteException {
		
		Controller c = new Controller(args);
		System.out.println(c.getCl().getService());
		System.out.println("Hola");
		
		
		c.getCl().getService().anyadirReserva("S1", "1A", "1-1-1", "12:32", 2);
		c.getCl().getService().consultarDisponibilidad("S1", "1-1-1", "12:32");
		
		
		//Almacenamos libros 
		c.getCl().getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
		c.getCl().getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
		c.getCl().getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);	
		
		//Busqueda de libro por nombre
		c.getCl().getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		c.getCl().getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");
		
		//Consultar catalogo
		System.out.println(c.getCl().getService().consultarCatalogo());
		
	}
}
