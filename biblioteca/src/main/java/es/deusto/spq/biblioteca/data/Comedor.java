package es.deusto.spq.biblioteca.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Comedor implements Serializable{

	String id_Comedor;
	int mesas;
	
	@Join
	List<ReservaMesa> reservaMesas = new ArrayList<ReservaMesa>();

	public Comedor() {
		super();
	}

	public Comedor(String id_Comedor, int mesas, List<ReservaMesa> reservaMesas) {
		super();
		this.id_Comedor = id_Comedor;
		this.mesas = mesas;
		this.reservaMesas = reservaMesas;
	}

	public String getId_Comedor() {
		return id_Comedor;
	}

	public void setId_Comedor(String id_Comedor) {
		this.id_Comedor = id_Comedor;
	}

	public int getMesas() {
		return mesas;
	}

	public void setMesas(int mesas) {
		this.mesas = mesas;
	}

	public List<ReservaMesa> getReservaMesas() {
		return reservaMesas;
	}

	public void setReservaMesas(List<ReservaMesa> reservaMesas) {
		this.reservaMesas = reservaMesas;
	}
}
