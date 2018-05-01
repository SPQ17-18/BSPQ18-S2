package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.client.Client;
import es.deusto.spq.biblioteca.client.gui.VentanaLogin;
import es.deusto.spq.biblioteca.client.gui.VerReservas;
import es.deusto.spq.biblioteca.dao.LibroDAO;import es.deusto.spq.biblioteca.data.Reserva;

public class Controller {
	@SuppressWarnings("unused")
	private Client cl;
private VentanaLogin vl;
	private VentanaBuscar vb;
	private VerReservas vr;	private static final Logger logger = Logger.getLogger(Controller.class);

	public Controller(String[] args) throws RemoteException {

		cl = new Client();
		cl.setService(args);
		vl = new VentanaLogin(this);
		vl.ejecutarVentana();
		
	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public static void main(String[] args) throws Exception {
		Controller c = new Controller(args);

//		System.out.println(c.getCl().getService());
//		System.out.println("Hola");
//
//		//Almacenamos libros 
//		c.getCl().getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
//		c.getCl().getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
//		c.getCl().getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);	
//
//		//Busqueda de libro por nombre
//		c.getCl().getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
//		c.getCl().getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");
//
//		/**
//		 * Consultar disponiblididad de un libro
//		 * Primero buscamos el libro, luego lo reservamos y despues miramos si ha cambiado su disponibilidad
//		 */
//		c.getCl().getService().buscarLibro("Las almas de Brandom");
//		c.getCl().getService().reserveBook("Las almas de Brandom");
//		c.getCl().getService().mostrarLibro("Las almas de Brandom");
//
//		//Anyadir sala
//		c.getCl().getService().anyadirSala("S1", 10);
//		c.getCl().getService().anyadirSala("S2", 8);
//		c.getCl().getService().anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
//		c.getCl().getService().anyadirReserva("S2", "23456789Y", "12/05/18", "19:26", 2);
//		Boolean disponible = c.getCl().getService().consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
//		if (disponible) {
//			c.getCl().getService().anyadirReserva("S1", "34567890X", "11/04/18", "10:00", 4);
//		}
//		c.getCl().getService().verReservas("12345678X");
//
//		c.getCl().getService().editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");
//
//		c.getCl().getService().verReservas("12345678X");
//
//		//Anyadir mesa
//		c.getCl().getService().anyadirMesa("M1", 4);
//		c.getCl().getService().anyadirMesa("M2", 6);
//		c.getCl().getService().anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
//		c.getCl().getService().anyadirReservaComedor("M2", "19182138S", "1/05/18", "15:00", 6);
//		Boolean disponibleComedor = c.getCl().getService().consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3);
//		if (disponibleComedor) {
//			c.getCl().getService().anyadirReservaComedor("M1", "34567890X", "2/05/18", "14:00", 4);
//		}
//
//		c.getCl().getService().verReservaComedor("12345678X");
//		c.getCl().getService().eliminarReservaComedor("12345678X", "30/04/18", "14:30");
//	}

	}
}