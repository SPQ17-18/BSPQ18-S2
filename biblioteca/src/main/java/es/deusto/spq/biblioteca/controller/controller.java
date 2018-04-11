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
