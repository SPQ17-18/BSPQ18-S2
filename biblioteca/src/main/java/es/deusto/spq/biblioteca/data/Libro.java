package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763690162513004091L;
	int isbn = 0;
	String nombre = null;
	String autor = null;
	String editorial = null;
	boolean isReservado = false; //Inicializado a false pues de primeras ninguno lo esta. Se cambiara a true cuando se reserve

	//Añadir mas???
	
	public Libro() {
		
	}
	
	public Libro(int isbn, String nombre, String autor, String editorial, boolean isReservado) {
		this.isbn = isbn;
		this.nombre = nombre;
		this.autor = autor;
		this.editorial = editorial;
		this.isReservado = isReservado;
	}

	//Getters y Setters
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
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
	
	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}
	
	
	@Override
	public String toString() {
		return "libro [isbn=" + isbn + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", isReservado=" + isReservado + "]";
	}

}
