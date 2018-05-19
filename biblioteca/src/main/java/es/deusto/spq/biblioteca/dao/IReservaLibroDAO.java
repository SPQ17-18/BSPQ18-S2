package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.ReservaLibro;

public interface IReservaLibroDAO {
	
	public void reservarLibro(ReservaLibro rl);
	public boolean consultarDisponibilidadLibro(String isbn);

}
