package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)

public class libro implements Serializable {

	int isbn = 0;
	String titulo = null;
	String autor = null;
	String editorial = null;
	boolean isReservado = false; //Inicializado a false pues de primeras ninguno lo esta. Se cambiara a true cuando se reserve

	//Añadir mas???
	
	public libro(int isbn, String titulo, String autor, String editorial, boolean isReservado) {
		this.isbn = isbn;
		this.titulo = titulo;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		return "libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", isReservado=" + isReservado + "]";
	}

}
