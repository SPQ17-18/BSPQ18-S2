package es.deusto.spq.biblioteca.data;

public class sala {
	
	String id_sala;
	String id_reserva;
	String dni_respon;
	int capacidad;
	
	public sala(String id_sala, String id_reserva, String dni_respon, int capacidad) {
		super();
		this.id_sala = id_sala;
		this.id_reserva = id_reserva;
		this.dni_respon = dni_respon;
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

	public String getDni_respon() {
		return dni_respon;
	}

	public void setDni_respon(String dni_respon) {
		this.dni_respon = dni_respon;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
}
