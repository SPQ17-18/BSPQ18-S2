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

public class ReservaLibroDAO implements IReservaLibroDAO {
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaLibroDAO.class);
	
	public ReservaLibroDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void reservarLibro(ReservaLibro rl) {
		// TODO Auto-generated method stub
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
			//System.out.println("   $ Error guardando reserva: " + ex.getMessage());
			logger.error("   $ Error guardando reserva del libro:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}	
		
	}

	@Override
	public boolean consultarDisponibilidadLibro(String isbn) {
		// TODO Auto-generated method stub
		boolean isReservado = true;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			//System.out.println("   * Consultado disponibilidad de: " + Id_Sala);
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
			//System.out.println("   $ Error consultando disponibilidad del libro: " + ex.getMessage());
			logger.error("   $ Error consultando disponibilidad del libro " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		return isReservado;
	}

	@Override
	public ReservaLibro devolverLibro(String isbn) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		ReservaLibro rl = null;
		try {
			tx.begin();

			//System.out.println("   * Buscando reserva de: " + dni);
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
				// TODO: handle exception
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
