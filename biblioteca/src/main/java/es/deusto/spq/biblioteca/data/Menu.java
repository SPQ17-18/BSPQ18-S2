package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Menu implements Serializable {
	//atributos
	int ID_menu;
	String fecha;
	String plato1;
	String plato2;
	String postre;
	int valor;
	
	public Menu() {
		
	}

	public Menu(int iD_menu, String fecha, String plato1, String plato2, String postre) {
		super();
		ID_menu = iD_menu;
		this.fecha = fecha;
		this.plato1 = plato1;
		this.plato2 = plato2;
		this.postre = postre;
	}

	public int getID_menu() {
		return ID_menu;
	}

	public void setID_menu(int iD_menu) {
		ID_menu = iD_menu;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPlato1() {
		return plato1;
	}

	public void setPlato1(String plato1) {
		this.plato1 = plato1;
	}

	public String getPlato2() {
		return plato2;
	}

	public void setPlato2(String plato2) {
		this.plato2 = plato2;
	}

	public String getPostre() {
		return postre;
	}

	public void setPostre(String postre) {
		this.postre = postre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Menu [ID_menu=" + ID_menu + ", fecha=" + fecha + ", plato1=" + plato1 + ", plato2=" + plato2
				+ ", postre=" + postre + ", valor=" + valor + "]";
	}
}