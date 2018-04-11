package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.libro;


public interface ILibroDAO {
		
	public libro buscarLibro(String titulo);
	public String EstaDisponible(String titulo, boolean isReservado);


}
