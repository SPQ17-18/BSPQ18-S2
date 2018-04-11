package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;

import es.deusto.spq.biblioteca.data.libro;


public interface ILibroDAO {
		
	public libro getLibro(String nombre);
	public ArrayList<libro> getLibros();
	public void almacenarLibro(libro l);
	//public String EstaDisponible(String titulo, boolean isReservado);


}
