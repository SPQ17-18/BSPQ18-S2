package es.deusto.spq.biblioteca.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;

public interface IBiblioteca extends Remote{
	
	public Libro buscarLibro(String nombre) throws RemoteException;
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado, int numeroEjemplares) throws RemoteException;
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas )throws RemoteException;
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora)throws RemoteException;
	public void EliminarLibro(Libro l)throws RemoteException;
	public void EliminarParticipante(Reserva r) throws RemoteException;
	//Añadir lo que falta
}
