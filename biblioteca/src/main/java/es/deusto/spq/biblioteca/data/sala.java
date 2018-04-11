package es.deusto.spq.biblioteca.data;

public class sala {
	
	String id_sala;
	String id_reserva;
	String fecha;
	String hora;
	int capacidad;
	
	
	public sala(String id_sala, String id_reserva, String fecha, String hora, int capacidad) {
		super();
		this.id_sala = id_sala;
		this.id_reserva = id_reserva;
		this.fecha = fecha;
		this.hora = hora;
		this.capacidad = capacidad;
	}

	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}

	public String getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public void añadirNuevosUsuarios(int CantidadNuevosUsuarios) {
		this.capacidad = this.capacidad + CantidadNuevosUsuarios;
	}
}
