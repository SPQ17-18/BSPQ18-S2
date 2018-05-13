package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Sala implements Serializable {

	String id_sala;
	int capacidad;

	@Join
	List<Reserva> reservas = new ArrayList<Reserva>();

	public Sala() {

	}
	
	public Sala(String id_sala, int capacidad) {
		super();
		this.id_sala = id_sala;
		this.capacidad = capacidad;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

}