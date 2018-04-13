package es.deusto.spq.biblioteca.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;

public interface IBiblioteca extends Remote{
	
	public Libro buscarLibro(String nombre) throws RemoteException;
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado) throws RemoteException;
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas )throws RemoteException;
	public void anyadirSala(String id_sala, int capacidad)throws RemoteException;
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora, int personas)throws RemoteException;
	//Añadir lo que falta
}
