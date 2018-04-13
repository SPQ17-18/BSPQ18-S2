package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.Reserva;

public interface IReservaDAO {

	public void anyadirReserva(Reserva r);
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	public void EliminarParticipanteR(String id_reserva, String plazas);
	
}
