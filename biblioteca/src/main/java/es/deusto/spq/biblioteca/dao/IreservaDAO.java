package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.Reserva;

public interface IreservaDAO {

	public void anyadirReserva(Reserva r);
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora);
	
}
