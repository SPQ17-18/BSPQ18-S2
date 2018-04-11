package es.deusto.spq.biblioteca.test;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.libroDAO;
import es.deusto.spq.biblioteca.data.libro;

public class testlibroDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ILibroDAO libroDAO = new libroDAO();
		
		crearTest(libroDAO);

	}

	private static void crearTest(ILibroDAO libroDAO) {
		// TODO Auto-generated method stub
		
		try {
			libro l = new libro(1, "Las almas de Brandoom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
			libroDAO.buscarLibro("Las almas de Brandoom");
			
			
			
			
		} catch (Exception ex) {
			System.out.println(" $ Error consultando libro: " + ex.getMessage());
		}
		
	}

}
