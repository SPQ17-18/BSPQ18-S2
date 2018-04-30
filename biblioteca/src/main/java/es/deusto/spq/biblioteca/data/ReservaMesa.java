package es.deusto.spq.biblioteca.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class ReservaMesa {

	String id_Mesa;
	String id_Reserva;
	String dni_respon;
	String fecha;
	String hora;
	int Personas;
	
	public ReservaMesa() {
		super();
	}
	
	public ReservaMesa(String id_Mesa, String id_Reserva, String dni_respon, String fecha, String hora, int personas) {
		super();
		this.id_Mesa = id_Mesa;
		this.id_Reserva = id_Reserva;
		this.dni_respon = dni_respon;
		this.fecha = fecha;
		this.hora = hora;
		Personas = personas;
	}

	public String getId_Mesa() {
		return id_Mesa;
	}

	public void setId_Mesa(String id_Mesa) {
		this.id_Mesa = id_Mesa;
	}

	public String getId_Reserva() {
		return id_Reserva;
	}

	public void setId_Reserva(String id_Reserva) {
		this.id_Reserva = id_Reserva;
	}

	public String getDni_respon() {
		return dni_respon;
	}

	public void setDni_respon(String dni_respon) {
		this.dni_respon = dni_respon;
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

	public int getPersonas() {
		return Personas;
	}

	public void setPersonas(int personas) {
		Personas = personas;
	}
}
