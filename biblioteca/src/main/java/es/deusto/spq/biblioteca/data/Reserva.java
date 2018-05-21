package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
/**
 * Clase de Reserva
 * @author Julen
 *
 */
@PersistenceCapable(detachable = "true")
public class Reserva implements Serializable  {	
	/**
	 * Clase de Reserva
	 * @author koldo
	 *
	 */
	private static final long serialVersionUID = 1L;
	String id_reserva;
	String id_sala;
	String dni_respon;
	String fecha;
	String hora;
	int plazas;

	/**
	 * Constructor vacio
	 */
	public Reserva() {
		
	}

	/**
	 * Constructor de Reserva
	 * @param id_reserva
	 * @param id_sala
	 * @param dni_respon
	 * @param fecha
	 * @param hora
	 * @param plazas
	 */
	public Reserva(String id_reserva, String id_sala, String dni_respon, String fecha, String hora, int plazas) {
		super();
		this.id_reserva = id_reserva;
		this.id_sala = id_sala;
		this.dni_respon = dni_respon;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
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
	 * Devuelve el identificador de la reserva.
	 * @return id_reserva Identificador de la reserva.
	 */
	public String getId_reserva() {
		return id_reserva;
	}

	/**
	 * Pone el identificador a una reserva.
	 * @param id_reserva Identificador de la reserva.
	 */
	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}

	/**
	 * Devuelve el identificador de una sala.
	 * @return id_sala Identificador de la sala.
	 */
	public String getId_sala() {
		return id_sala;
	}

	/**
	 * Pone el identificador de una sala.
	 * @param id_sala Identificador de la sala.
	 */
	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
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
	 * Devuelve el numero de plazas.
	 * @return plazas Numero de sitios.
	 */
	public int getPlazas() {
		return plazas;
	}
	/**
	 * Pone el numero de plazas.
	 * @param plazas Numero de sitios.
	 */
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	/**
	 * Metodo To String
	 */
	@Override
	public String toString() {
		return "Reserva [id_reserva=" + id_reserva + ", id_sala=" + id_sala + ", dni_respon=" + dni_respon + ", fecha="
				+ fecha + ", hora=" + hora + ", plazas=" + plazas + "]";
	}	
}