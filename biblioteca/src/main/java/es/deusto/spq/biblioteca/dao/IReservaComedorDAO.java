package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.ReservaMesa;

public interface IReservaComedorDAO {
	
	public void anyadirReservaC(ReservaMesa r);
	public boolean consultarDisponibilidad(String Id_Comedor, String fecha, String hora);
	public void verReservaComedor(String dni);
}
