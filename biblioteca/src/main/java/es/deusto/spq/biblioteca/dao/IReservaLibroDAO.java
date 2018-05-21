package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de interfaz DAO de reserva de libros
 * @author Koldo
 */
import es.deusto.spq.biblioteca.data.ReservaLibro;

public interface IReservaLibroDAO {
	
	public void reservarLibro(ReservaLibro rl);
	public boolean consultarDisponibilidadLibro(String isbn);
	public ReservaLibro devolverLibro(String ISBN);

}
