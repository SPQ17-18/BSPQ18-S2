package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
/**
 * Clase de Mesa
 * @author Ariane
 *
 */
@PersistenceCapable(detachable = "true")
public class Mesa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id_Mesa;
	int capacidad;
	
	@Join
	List<ReservaMesa> reservaMesas = new ArrayList<ReservaMesa>();

	public Mesa() {
		
	}

	public Mesa(String id_Mesa, int capacidad) {
		super();
		this.id_Mesa = id_Mesa;
		this.capacidad = capacidad;
	}

	public String getId_Mesa() {
		return id_Mesa;
	}

	public void setId_Mesa(String id_Mesa) {
		this.id_Mesa = id_Mesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<ReservaMesa> getReservaMesas() {
		return reservaMesas;
	}

	public void setReservaMesas(List<ReservaMesa> reservaMesas) {
		this.reservaMesas = reservaMesas;
	}
}
