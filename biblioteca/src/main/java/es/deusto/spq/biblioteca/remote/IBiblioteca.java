package es.deusto.spq.biblioteca.remote;
/**
 * Clase de interfaz remota realizada por
 * @author Mikel, Ariane, Koldo y Luis
 *
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaLibro;
import es.deusto.spq.biblioteca.data.ReservaMesa;

import java.util.ArrayList;

public interface IBiblioteca extends Remote {

	public Libro buscarLibro(String nombre) throws RemoteException;
		
	public void mostrarLibro(String nombre) throws RemoteException;
	
	public boolean consultarDiponibilidadLibro(String nombre) throws RemoteException;
	
	public ArrayList<String> getLibros() throws RemoteException;
	
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas)
			throws RemoteException;

	public void anyadirSala(String id_sala, int capacidad) throws RemoteException;

	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora, int personas)
			throws RemoteException;

	public ArrayList<String> verReservas(String dni) throws RemoteException;

	void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,
			String SalaNueva) throws RemoteException;

	public void eliminarReserva(String dni_respon,String fecha,String hora) throws RemoteException ;

	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas)throws RemoteException;

	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora, int personas) throws RemoteException;
	
	public void anyadirMesa(String id_mesa, int capacidad) throws RemoteException;
	
	public ArrayList<String> verReservaComedor(String dni) throws RemoteException;
	
	public void eliminarReservaComedor(String dni, String fecha, String hora) throws RemoteException, Exception;
	
	public void editarReservaComedor(String dni,String fecha,String hora,String mesa, String fecha_nueva, String hora_nueva,String mesa_nueva) throws RemoteException;

	public void anyadirMenu(String fecha, String plato1, String plato2, String postre) throws Exception;

	public void anyadirValoracion(String id_menu, int valoracion) throws Exception;

	public String verMenu(String fecha) throws Exception;

	public void almacenarLibro(String isbn, String nombre, String autor, String editorial) throws RemoteException;

	public void reservarLibro(String isbn) throws RemoteException;

	public ReservaLibro DevolverLibro(String isbn) throws RemoteException;
	
	public Reserva DevolverReserva(String dni, String fecha, String hora) throws RemoteException;
		
	public ReservaMesa devolverReservaComedor(String dni, String fecha, String hora) throws RemoteException;
	
	public void anyadirPremio(String isbn, String nombrePremio) throws RemoteException;
	
	
}