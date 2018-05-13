package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Reserva implements Serializable  {
	
	String id_reserva;
	String id_sala;
	String dni_respon;
	String fecha;
	String hora;
	int plazas;

	public Reserva() {
		
	}

	public Reserva(String id_reserva, String id_sala, String dni_respon, String fecha, String hora, int plazas) {
		super();
		this.id_reserva = id_reserva;
		this.id_sala = id_sala;
		this.dni_respon = dni_respon;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
	}
	
	public String getDni_respon() {
		return dni_respon;
	}

	public void setDni_respon(String dni_respon) {
		this.dni_respon = dni_respon;
	}

	public String getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}

	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
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

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	@Override
	public String toString() {
		return "Reserva [id_reserva=" + id_reserva + ", id_sala=" + id_sala + ", dni_respon=" + dni_respon + ", fecha="
				+ fecha + ", hora=" + hora + ", plazas=" + plazas + "]";
	}	
}