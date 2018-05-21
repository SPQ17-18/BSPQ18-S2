package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase de sala
 * @author Ariane
 *
 */
@PersistenceCapable(detachable = "true")
public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String id_sala;
	int capacidad;

	@Join
	List<Reserva> reservas = new ArrayList<Reserva>();

	/**
	 * Constructor vacio
	 */
	public Sala() {

	}
	
	/**
	 * Constructor de la clase Sala.
	 * @param id_sala
	 * @param capacidad
	 */
	public Sala(String id_sala, int capacidad) {
		super();
		this.id_sala = id_sala;
		this.capacidad = capacidad;
	}
	
	/**
	 * Devuelve la lista de reservas de sala.
	 * @return reservas.
	 */
	public List<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * Para poner lista de reservas de salas.
	 * @param reservas
	 */
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	/**
	 * Devuelve el identificador de la sala.
	 * @return id_sala Identificador de la sala.
	 */
	public String getId_sala() {
		return id_sala;
	}

	/**
	 * Pone el identificador de la sala.
	 * @param id_sala Identificador.
	 */
	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}

	/**
	 * Devuelve cuanta gente entra en la sala.
	 * @return capacidad Numero de personas que puede haber en la sala.
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Pone la capacidad de una sala.
	 * @param capacidad Numero de personas que puede haber en la sala
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

}