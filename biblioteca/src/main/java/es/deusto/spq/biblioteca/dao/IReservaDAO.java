package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.Reserva;

public interface IReservaDAO {

	public void anyadirReserva(Reserva r);
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	public void editarReserva(Reserva r, String fecha_nueva, String hora_nueva);
	public void eliminarReserva(Reserva r);
	public void verReservas(String dni);
	public void editarReserva(Reserva r, String hora_nueva);
	public void anyadirUsuario(Reserva r);
	public void EliminarParticipanteR(String id_reserva, String plazas);
	Reserva devolverReserva(String dni, String fecha, String hora);
	
}

