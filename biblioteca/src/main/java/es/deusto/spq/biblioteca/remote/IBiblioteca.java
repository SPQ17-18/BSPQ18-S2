package es.deusto.spq.biblioteca.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.data.Libro;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public interface IBiblioteca extends Remote {

	public Libro buscarLibro(String nombre) throws RemoteException;

	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado)
			throws RemoteException;
		
	public void mostrarLibro(String nombre) throws RemoteException;
	
	public boolean consultarDiponibilidadLibro(String nombre) throws RemoteException;
	
	public ArrayList<Libro> getLibros() throws RemoteException;
	
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas)
			throws RemoteException;

	public void anyadirSala(String id_sala, int capacidad) throws RemoteException;

	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora, int personas)
			throws RemoteException;

	public String verReservas(String dni) throws RemoteException;

	void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,
			String SalaNueva) throws RemoteException;

	public void eliminarReserva(String id_Sala, String dni_respon) throws RemoteException;

	public void anyadirUsuario(Reserva r) throws RemoteException;

	public void EliminarLibro(Libro l) throws RemoteException;

	public void EliminarParticipante(Reserva r) throws RemoteException;
	
	public Reserva DevolverReserva(String dni, String fecha, String hora) throws RemoteException;
	
	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas)throws RemoteException;

	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora, int personas) throws RemoteException;
	
	public void anyadirMesa(String id_mesa, int capacidad) throws RemoteException;
	
	public void verReservaComedor(String dni) throws RemoteException;
	
	public void eliminarReservaComedor(String dni, String fecha, String hora) throws RemoteException, Exception;
	
	public ReservaMesa DevolverReservaMesa(String dni, String fecha, String hora) throws RemoteException;
	
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva) throws RemoteException;

	public void anyadirMenu(String fecha, String plato1, String plato2, String postre) throws Exception;

	public void anyadirValoracion(String id_menu, int valoracion) throws Exception;

	public String verMenu(String fecha) throws Exception;
	
	
}
