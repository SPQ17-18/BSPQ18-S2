package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.ReservaMesa;

public interface IReservaComedorDAO {
	
	public void anyadirReservaC(ReservaMesa r);
	public boolean consultarDisponibilidadC(String Id_Mesa, String fecha, String hora);
	public void verReservaComedor(String dni);
	public void eliminarReservaComedor(ReservaMesa r);
	public void editarReserva(ReservaMesa r, String fecha_nueva, String hora_nueva);
	
}
