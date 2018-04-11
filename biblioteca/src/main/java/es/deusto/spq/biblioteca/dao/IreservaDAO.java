package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.reserva;

public interface IreservaDAO {

	public void anyadirReserva(reserva r);
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	
}
