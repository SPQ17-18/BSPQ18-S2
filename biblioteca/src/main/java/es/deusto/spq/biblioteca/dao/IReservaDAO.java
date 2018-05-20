package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.Reserva;

public interface IReservaDAO {

	public void anyadirReserva(Reserva r);
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	public void eliminarReserva(Reserva r);
	public ArrayList<String> verReservas(String dni);
	void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,String SalaNueva);
	public Reserva devolverReserva(String dni, String fecha, String hora);

}
