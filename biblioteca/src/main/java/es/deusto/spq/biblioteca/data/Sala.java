package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Sala implements Serializable{
	
	String id_sala;
	int capacidad;
	
	public Sala() {

	}
	public Sala(String id_sala, int capacidad) {
		super();
		this.id_sala = id_sala;
		this.capacidad = capacidad;
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
