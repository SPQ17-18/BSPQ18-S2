package es.deusto.spq.biblioteca.remote;

import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.data.libro;

public interface IBiblioteca {
	
	public libro buscarLibro(String nombre);
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado);
	//Añadir lo que falte
}
