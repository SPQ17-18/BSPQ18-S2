package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.Libro;


public interface ILibroDAO {
		
	public Libro getLibro(String nombre);
	public ArrayList<Libro> getLibros();
	public void almacenarLibro(Libro l);
	//public String EstaDisponible(String titulo, boolean isReservado);


}
