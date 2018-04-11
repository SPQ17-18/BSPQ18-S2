package es.deusto.spq.biblioteca.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.data.libro;

public interface IBiblioteca extends Remote{
	
	public libro buscarLibro(String nombre) throws RemoteException;
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado) throws RemoteException;
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas )throws RemoteException;
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	//Añadir lo que falta
}
