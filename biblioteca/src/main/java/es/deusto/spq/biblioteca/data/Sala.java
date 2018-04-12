package es.deusto.spq.biblioteca.data;

public class Sala {
	
	String id_sala;
	int capacidad;
	
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
