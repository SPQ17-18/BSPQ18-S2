package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.client.client;

public class controller {
	@SuppressWarnings("unused")
	private client cl;
	
	public controller(String[] args) throws RemoteException{
		
		cl = new client();
		cl.setService(args);
		
		//desde aqui haremos un cl.getservicio e iremos anyadiendo las diferentes funciones
		
	}
}
