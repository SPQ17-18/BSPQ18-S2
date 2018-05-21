package es.deusto.spq.biblioteca.dao;
import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.ReservaMesa;
/**
 * Clase de creación de interfaz DAO de reserva Comedor
 * @author Ariane
 */
public interface IReservaComedorDAO {
	
	public void anyadirReservaComedor(ReservaMesa r);
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora);
	public ArrayList<String> verReservaComedor(String dni);
	public void eliminarReservaComedor(ReservaMesa r);
	public void editarReservaComedor(String dni,String fecha,String hora,String mesa, String fecha_nueva, String hora_nueva,String mesa_nueva);
	public ReservaMesa devolverReservaComedor(String dni, String fecha, String hora);


}