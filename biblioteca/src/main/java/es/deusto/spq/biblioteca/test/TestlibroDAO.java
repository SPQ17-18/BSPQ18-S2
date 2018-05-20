//package es.deusto.spq.biblioteca.test;
//
//import es.deusto.spq.biblioteca.dao.ILibroDAO;
//import es.deusto.spq.biblioteca.dao.LibroDAO;
//import es.deusto.spq.biblioteca.data.Libro;
//
//public class TestlibroDAO {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ILibroDAO libroDAO = new LibroDAO();
//		
//		crearTest(libroDAO);
//
//	}
//
//	private static void crearTest(ILibroDAO libroDAO) {
//		// TODO Auto-generated method stub
//		
//		try {
//			Libro l = new Libro("1", "Las almas de Brandoom", "Cesar Brandom", "S.L.U. Espasa Libros");
//			libroDAO.almacenarLibro(l); //No esta almacenando el libro
////			libroDAO.buscarLibro("Las almas de Brandoom");
//			
//			
//			
//			
//		} catch (Exception ex) {
//			System.out.println(" $ Error consultando libro: " + ex.getMessage());
//		}
//		
//	}
//
//}
