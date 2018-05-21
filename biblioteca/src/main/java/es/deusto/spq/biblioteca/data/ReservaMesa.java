package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase de reservar mesa
 * @author Ariane
 *
 */
@PersistenceCapable(detachable = "true")
public class ReservaMesa implements Serializable{

	private static final long serialVersionUID = 1L;
	String id_Mesa;
	String id_Reserva;
	String dni_respon;
	String fecha;
	String hora;
	int Personas;
	
	/**
	 * Constructor vacio
	 */
	public ReservaMesa() {
		
	}
	
	/**
	 * Constructor de la clase ReservaMesa.
	 * @param id_Mesa
	 * @param id_Reserva
	 * @param dni_respon
	 * @param fecha
	 * @param hora
	 * @param personas
	 */
	public ReservaMesa(String id_Mesa, String id_Reserva, String dni_respon, String fecha, String hora, int personas) {
		super();
		this.id_Mesa = id_Mesa;
		this.id_Reserva = id_Reserva;
		this.dni_respon = dni_respon;
		this.fecha = fecha;
		this.hora = hora;
		Personas = personas;
	}

	/**
	 * Devuelve el identificador de una mesa.
	 * @return id_Mesa Identificador de la mesa
	 */
	public String getId_Mesa() {
		return id_Mesa;
	}

	/**
	 * Pone el identificador a una mesa.
	 * @param id_Mesa Identificador de la mesa.
	 */
	public void setId_Mesa(String id_Mesa) {
		this.id_Mesa = id_Mesa;
	}

	/**
	 * Devuelve el identificador de la reserva de la mesa.
	 * @return id_reserva Identificador de la reserva de la mesa.
	 */
	public String getId_Reserva() {
		return id_Reserva;
	}

	/**
	 * Pone el identificador a una reserva de una mesa.
	 * @param id_reserva Identificador de la reserva de la mesa.
	 */
	public void setId_Reserva(String id_Reserva) {
		this.id_Reserva = id_Reserva;
	}

	/**
	 * Devuelve el DNI del responabale.
	 * @return dni_respon DNI del responsable.
	 */
	public String getDni_respon() {
		return dni_respon;
	}

	/**
	 * Pone el DNI del responsable.
	 * @param dni_respon DNI del responsable.
	 */
	public void setDni_respon(String dni_respon) {
		this.dni_respon = dni_respon;
	}

	/**
	 * Devuelve la fecha.
	 * @return fecha Fecha.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Pone la fecha.
	 * @param fecha Fecha.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la hora.
	 * @return hora Hora.
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Pone la hora.
	 * @param hora Hora.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Devuelve el numero de persona para las que es la reserva de la mesa.
	 * @return personas Numero de personas para el que es la reserva.
	 */
	public int getPersonas() {
		return Personas;
	}

	/**
	 * Pone el numero de persona para las que es la reserva de la mesa.
	 * @param personas Numero de personas para el que es la reserva.
	 */
	public void setPersonas(int personas) {
		Personas = personas;
	}
	
	/**
	 * Metodo To String
	 */
	@Override
	public String toString() {
		return "ReservaMesa [id_Mesa=" + id_Mesa + ", id_Reserva=" + id_Reserva + ", dni_respon=" + dni_respon
				+ ", fecha=" + fecha + ", hora=" + hora + ", Personas=" + Personas + "]";
	}

}
