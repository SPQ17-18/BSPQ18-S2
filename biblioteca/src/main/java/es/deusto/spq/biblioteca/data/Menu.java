package es.deusto.spq.biblioteca.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Menu implements Serializable {


	String plato1 = null;
	String plato2 = null;
	String postre = null;
	
	
	public Menu() {
		
	}
	
	public Menu(String plato1, String plato2, String postre) {
		this.plato1 = plato1;
		this.plato2 = plato2;
		this.postre = postre;
	}
	
	//Getters y Setters
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

	@Override
	public String toString() {
		return "Menu:" + "\nPrimer plato: " + plato1 + "\nSegundo plato: " + plato2 + "\nPostre: " + postre;
	}

}