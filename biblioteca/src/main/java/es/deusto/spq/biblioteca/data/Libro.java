package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase de los libros
 * @author koldo
 *
 */
@PersistenceCapable(detachable = "true")
public class Libro implements Serializable {
	
	private static final long serialVersionUID = 2763690162513004091L;
	String isbn = null; //0
	String nombre = null;
	String autor = null;
	String editorial = null;
	String premio = null;
	
	//Añadir mas???
	@Join List<ReservaLibro> reservasLibros = new ArrayList<ReservaLibro>();
	
	/**
	 * Constructor vacio
	 */
	public Libro() {
		
	}
	
	/**
	 * Constructor de libro.
	 * @param isbn Identificativo del libro.
	 * @param nombre Nombre del libro.
	 * @param autor Autor del libro.
	 * @param editorial Editorial del libro.
	 */
	public Libro(String isbn, String nombre, String autor, String editorial) {
		this.isbn = isbn;
		this.nombre = nombre;
		this.autor = autor;
		this.editorial = editorial;
		this.premio = premio;
	}
	

	//Getters y Setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	/**
	 * Metodo que devuelve la lista de reservas los libros.
	 * @return lista de reservas.
	 */
	public List<ReservaLibro> getReservasLibros() {
		return reservasLibros;
	}

	/**
	 * Metodo para pone las listas de reservas.
	 * @param reservasLibros Lista de reservas.
	 */
	public void setReservasLibros(List<ReservaLibro> reservasLibros) {
		this.reservasLibros = reservasLibros;
	}

	/**
	 * Metodo que devuelve el ISBN de un libro.
	 * @return isbn ISBN del libro.
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Metodo que pone el ISBN de un libro.
	 * @param isbn ISBN del libro.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Metodo que devuelve el nombre de un libro.
	 * @return nombre Nombre del libro.
	 */
	public String getnombre() {
		return nombre;
	}

	/**
	 * Metodo que pone el Nombre de un libro.
	 * @param nombre Nombre del libro.
	 */
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que devuelve el autor de un libro.
	 * @return autor Autor del libro.
	 */
	public String getAutor() {
		return autor;
	}
	
	/**
	 * Metodo que pone el Autor de un libro.
	 * @param autor Autor del libro.
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Metodo que devuelve la editorial de un libro.
	 * @return editorial Editorial del libro.
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Metodo que pone la editorial de un libro.
	 * @param editorial Editorial del libro.
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	String numeroEjemplares = null;
	
	/**
	 * Metodo que devuelve el numero de ejmplares de un libro.
	 * @return numeroEjemplares Numero de Ejemplares del libro.
	 */
	public String getNumeroEjemplares() {
		return numeroEjemplares;
	}

	/**
	 * Metodo que pone el numero de ejemplares de un libro.
	 * @param numeroEjemplares Numero de Ejemplares del libro.
	 */
	public void setNumeroEjemplares(String nuevaLista) {
		this.numeroEjemplares = nuevaLista;
	}

	/**
	 * Metodo To String
	 */
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", premio=" + premio + ", reservasLibros=" + reservasLibros + ", numeroEjemplares=" + numeroEjemplares
				+ "]";
	}


	

}