package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaLibro;

/**
 * Clase para el manejo de la Base de Daatos
 * @author koldo
 *
 */
public class ReservaLibroDAO implements IReservaLibroDAO {
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaLibroDAO.class);
	
	
	/**
	 * Contructor de la clase
	 */
	public ReservaLibroDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Reserva un libro de la bibilioteca
	 * @ReservaLibro
	 */
	@Override
	public void reservarLibro(ReservaLibro rl) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<Libro> q = pm.newQuery(Libro.class);
			
			@SuppressWarnings("unchecked")
			List<Libro> libros = (List<Libro>) q.execute();
			
			for(Libro l : libros) 
			{
				if(l.getIsbn().equals(rl.getIsbn())) {
					l.getReservasLibros().add(rl);
					
					logger.info("   * Guardando reserva del libro: " + rl.getIsbn());

					pm.makePersistent(l);
					tx.commit();
				}
			}
			
		} catch (Exception ex) {
	
			logger.error("   $ Error guardando reserva del libro:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}	
		
	}

	/**
	 * Consulta la disponibilidad de un libro.
	 * @param isbn Identificativo del libro.
	 */
	@Override
	public boolean consultarDisponibilidadLibro(String isbn) {
	
		boolean isReservado = true;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {

			logger.info("   * Consultado disponibilidad de: " + isbn);

			tx.begin();
			Query<ReservaLibro> query = pm.newQuery(ReservaLibro.class);
			@SuppressWarnings("unchecked")
			List<ReservaLibro> reservasLibros = (List<ReservaLibro>) query.execute();
			for (ReservaLibro rl : reservasLibros) {
				if (rl.getIsbn().equals(isbn)) {
					// No hay salas disponibles
					isReservado = false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			
			logger.error("   $ Error consultando disponibilidad del libro " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		return isReservado;
	}

	/**
	 * Permite devolver un libro reservado.
	 * @param isbn Identificativo del libro.
	 */
	@Override
	public ReservaLibro devolverLibro(String isbn) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		ReservaLibro rl = null;
		try {
			tx.begin();

			logger.info("   * Buscando reserva de: " + isbn);
			Query<ReservaLibro> query = pm.newQuery(ReservaLibro.class);
			@SuppressWarnings("unchecked")
			List<ReservaLibro> reservasLibros = (List<ReservaLibro>) query.execute();
			for(ReservaLibro rel : reservasLibros)
			{
				if(rel.getIsbn().equals(isbn)) {
					rl = rel;
				}
			}
			tx.commit();

		
			} catch (Exception e) {
		
				logger.error("   $ Error devolviendo reserva: " + e.getMessage());

			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}	

				pm.close();
			}
			return rl;
	}	
}
