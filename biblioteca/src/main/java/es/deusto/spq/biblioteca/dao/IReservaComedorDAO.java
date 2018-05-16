package es.deusto.spq.biblioteca.dao;
import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.ReservaMesa;

public interface IReservaComedorDAO {
	
	public void anyadirReservaComedor(ReservaMesa r);
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora);
	public ArrayList <String> verReservaComedor(String dni);
	public void eliminarReservaComedor(ReservaMesa r) throws Exception;
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva);
	ReservaMesa devolverReservaComedor(String dni, String fecha, String hora);
}
