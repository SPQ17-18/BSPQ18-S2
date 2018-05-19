package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763690162513004091L;
	String isbn = null; //0
	String nombre = null;
	String autor = null;
	String editorial = null;
	
	//Añadir mas???
	@Join List<ReservaLibro> reservasLibros = new ArrayList<ReservaLibro>();
	
	public Libro() {
		
	}
	
	public Libro(String isbn, String nombre, String autor, String editorial) {
		this.isbn = isbn;
		this.nombre = nombre;
		this.autor = autor;
		this.editorial = editorial;
	}
	

	//Getters y Setters
	
	public List<ReservaLibro> getReservasLibros() {
		return reservasLibros;
	}

	public void setReservasLibros(List<ReservaLibro> reservasLibros) {
		this.reservasLibros = reservasLibros;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	String numeroEjemplares = null;
	public String getNumeroEjemplares() {
		return numeroEjemplares;
	}

	public void setNumeroEjemplares(String nuevaLista) {
		this.numeroEjemplares = nuevaLista;
	}

	@Override
	public String toString() {
		return "libro [isbn=" + isbn + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", numeroEjemplares=" + numeroEjemplares +"]";
	}

}