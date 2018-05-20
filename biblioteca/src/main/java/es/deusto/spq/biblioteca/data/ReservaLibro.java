package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase de Reservar Libro
 * @author koldo
 *
 */
@PersistenceCapable(detachable = "true")
public class ReservaLibro implements Serializable {
	
	String isbn;

	/**
	 * Constructor vacio.
	 */
	public ReservaLibro() {
		
	}

	/**
	 * Constructor de ReservaLibro.
	 * @param isbn 
	 */
	public ReservaLibro(String isbn) {
		super();
		this.isbn = isbn;

	}

	/**
	 * Devuelve el ISBN.
	 * @return isbn ISBN del libro reservado.
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * Pone el ISBN.
	 * @param isbn ISBN del libro que se va a reservar.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	

}
