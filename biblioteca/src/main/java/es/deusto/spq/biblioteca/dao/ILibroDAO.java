package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de interfaz DAO de libros
 * @author Koldo
 */
import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.Libro;


public interface ILibroDAO {
		
	public Libro getLibro(String nombre);
	public ArrayList<String> getLibros();
	public void almacenarLibro(Libro l);
	public Libro verLibro(String nombre);


}
