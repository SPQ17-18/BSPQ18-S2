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
public class ReservaLibro implements Serializable {
	
	String id_reserva;
	int isbn_libro;
	String fecha;
	String hora;
	int plazas;

	public ReservaLibro() {
		
	}

	public ReservaLibro(String id_reserva, int isbn_libro, String dni_respon, String fecha, String hora, int plazas) {
		super();
		this.id_reserva = id_reserva;
		this.isbn_libro = isbn_libro;
		this.fecha = fecha;
		this.hora = hora;

	}

}
