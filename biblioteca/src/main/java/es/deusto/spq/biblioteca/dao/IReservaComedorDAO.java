package es.deusto.spq.biblioteca.dao;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public interface IReservaComedorDAO {
	
	public void anyadirReservaComedor(ReservaMesa r);
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora);
	public void verReservaComedor(String dni);
	public void eliminarReservaComedor(ReservaMesa r) throws Exception;
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva);
	ReservaMesa devolverReservaComedor(String dni, String fecha, String hora);
	public void consultaMenu(Menu menu);
	public void seleccionarMenu(Menu menu, int eleccion);
	public void comprarMenu(Menu menu, ReservaMesa rm);
}
