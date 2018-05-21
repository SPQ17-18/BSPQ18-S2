package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de DAO de libros
 * @author Koldo
 */
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Libro;

/**
 * Clase para el manejo de la Base de Daatos
 * @author koldo
 *
 */
public class LibroDAO implements ILibroDAO{
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(LibroDAO.class);

	/**
	 * Contructor de la clase
	 */
	public LibroDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

}
	
	/**
	 * Metodo que almacena un libro.
	 * @Libro anyade libro.
	 * @throws Exception Lanza una excepccion si ocurre un error.
	 */
	@Override
	public void almacenarLibro(Libro l) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Almacenando libro: " + l);
			pm.makePersistent(l);
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error almacenando libro:" + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}
	
	/**
	 * Metodo que devuelve un libro de la BD dado su nombre.
	 * @nombre Nombre del libro.
	 * @return Devuleve el un libro de la BD
	 */
	//Funcion de busqueda de un libro por su nombre
	@Override
	public Libro getLibro(String nombre) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Libro l = null;

		try {
			logger.info("   * Buscando libro: " + nombre);


			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre +"'");
			query.setUnique(true);
			l = (Libro) query.execute();
			tx.commit();

		} catch (Exception ex) {
			logger.error("   $ Error buscando libro:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return l;
		
	}
	

	/**
	 * Metodo que devuelve todos los libros
	 * @return Devuelve todos los libros de la BD
	 */
	//Funcion que devuelve el catalogo de libros
	@Override
	public ArrayList<String> getLibros() {

		
		PersistenceManager pm = pmf.getPersistenceManager();


		Transaction tx = pm.currentTransaction();
		
		ArrayList<String> catalogo = new ArrayList<String>();
		
		try {

			logger.info("  * Mostrando catalogo de libros...");

			String libro=null;
			tx.begin();
			Query<Libro> query = pm.newQuery(Libro.class);
			@SuppressWarnings("unchecked")
			List<Libro> libros = (List<Libro>) query.execute();
			
			for (Libro l : libros) {
				libro = l.getIsbn() + "#" + l.getnombre() + "#" + l.getAutor() + "#" + l.getEditorial() + "/" ;
				catalogo.add(libro);
			}
			tx.commit();
  			
		} catch (Exception ex) {
			
			logger.error("   $ Error recuperando todos los libros: " + ex.getMessage());
		
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		
		
		return catalogo;
	}	
	
	/**
	 * Permite ver la información de un libro
	 * @param nombre Nombre del libro
	 */
	@Override
	public Libro verLibro(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Libro l = null;


		try {

			logger.info("\"   * Mostrando datos del libro: " + nombre);


			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre +"'");
			query.setUnique(true);
			l = (Libro) query.execute();


			System.out.println("\nisbn: " + l.getIsbn());
			System.out.println("\nNombre: " + l.getnombre());
			System.out.println("\nAutor: " + l.getAutor());
			System.out.println("\nEditorial: " + l.getEditorial());

			tx.commit();

		} catch (Exception ex) {
	
			logger.error("   $ Error mostrando datos del libro seleccionado:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return l;

	}
}
