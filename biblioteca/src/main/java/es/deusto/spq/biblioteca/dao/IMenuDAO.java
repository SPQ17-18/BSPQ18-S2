package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.Menu;

public interface IMenuDAO {
	
	public void anyadirMenu(Menu m);
	public void anyadirValoracion(String ID_Menu, int valoracion);
	public void verMenu(String fecha);

}
