package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de interfaz DAO de menu
 * @author Ariane
 */
import es.deusto.spq.biblioteca.data.Menu;

public interface IMenuDAO {
	
	public void anyadirMenu(Menu m);
	public void anyadirValoracion(String ID_Menu, int valoracion);
	public String verMenu(String fecha);

}
