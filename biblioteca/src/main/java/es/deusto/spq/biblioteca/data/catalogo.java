package es.deusto.spq.biblioteca.data;

import java.util.ArrayList;
import java.util.List;

public class catalogo {
	
	private List	<libro> listaLibros = new ArrayList<>(); //Mostramos todos los libros disponibles
	boolean isReservado = false; //Inicializado a false pues de primeras ninguno lo esta. Se cambiara a true cuando se reserve
	//Añadir mas cosas al catalogo???
	
	public catalogo() {
		//Algunos libros de muestra
		libro libro1 = new libro(0, "LAS ALMAS DE BRANDON", "CESAR BRANDON", "S.L.U. ESPASA LIBROS");
		libro libro2 = new libro(1, "MEMORIA DEL COMUNISMO", "FEDERICO JIMENEZ LOSANTOS", "LA ESFERA DE LOS LIBROS");
		libro libro3 = new libro(2, "FESTIN DE CUERVOS, CANCION DE HIELO Y FUEGO IV", "GEORGE R.R. MARTIN", "GIGAMESH");
		libro libro4 = new libro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "BORA KIM ", "EDITORIAL PLANETA S.A");
		libro libro5 = new libro(4, "PACO JEMEZ: GRANDES EXITOS EN EL RAYO VALLECANO", "JOSE ANTONIO CAMACHO", "FURIA VALLECANA");

		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		
	}
	
	/**
	 * Funcion que devuelve el listado de libros disponibles en el catalogo de la biblioteca
	 * @param isbn
	 * @param titulo
	 * @param autor
	 * @param editorial
	 */
	public List<libro> getListaLibros(int isbn, String titulo, String autor, String editorial) {
		return listaLibros;
	}

	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}

	

}
