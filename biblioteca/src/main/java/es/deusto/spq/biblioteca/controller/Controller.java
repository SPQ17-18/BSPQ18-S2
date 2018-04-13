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
		Controller c = new Controller(args);
		c.getCl().getService().anyadirSala("S1",10);
		c.getCl().getService().anyadirSala("S2",8);
		c.getCl().getService().anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
		c.getCl().getService().anyadirReserva("S2", "23456789Y", "12/05/18", "19:26", 2);
		Boolean disponible=c.getCl().getService().consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
		 if(disponible) {
		 c.getCl().getService().anyadirReserva("S1", "34567890X", "11/04/18", "10:00", 4);
		 }
	}
}
