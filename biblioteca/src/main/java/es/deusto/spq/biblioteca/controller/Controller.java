package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import es.deusto.spq.biblioteca.client.Client;
import es.deusto.spq.biblioteca.data.Reserva;

public class Controller {
	@SuppressWarnings("unused")
	private Client cl;

	public Controller(String[] args) throws RemoteException {

		cl = new Client();
		cl.setService(args);

	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public static void main(String[] args) throws RemoteException {

		new Controller(args);

		Controller c = new Controller(args);
		// Almacenamos libros

		c.getCl().getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros",
				false);
		c.getCl().getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin",
				"Gigamesh", false);
		c.getCl().getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends",
				"Bora Kim ", "Editorial Planeta S.A", false);

		// Busqueda de libro por nombre
		c.getCl().getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		c.getCl().getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");
	}
}
