package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class ReservaLibro implements Serializable {
	
	String isbn;

	public ReservaLibro() {
		
	}

	public ReservaLibro(String isbn) {
		super();
		this.isbn = isbn;

	}


	public String getIsbn() {
		return isbn;
	}

}
