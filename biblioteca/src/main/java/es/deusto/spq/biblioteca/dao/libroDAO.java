package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.libro;

public class libroDAO implements ILibroDAO{
	
	private PersistenceManagerFactory pmf;

	public libroDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

}

	//Para  sprint 1 solo esta funcion tiene que ser funcional
	@Override
	public libro buscarLibro(String titulo) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		libro l = null;

		try {
			System.out.println("   * Buscando libro: " + titulo);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + libro.class.getName() + " WHERE titulo == '" + titulo);
			query.setUnique(true);
			l = (libro) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return l;
		
	}

	//Funcion en proceso
	@Override
	public String EstaDisponible(String titulo, boolean isReservado) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String disp = "";
	
		try {
			System.out.println("   * Buscando reserva del libro: " + titulo);
			tx.begin();
			Query<libro> query = pm.newQuery(libro.class);
			@SuppressWarnings("unchecked")
			List<libro> book = (List<libro>) query.execute();
			for (libro l : book) {
				if (l.getTitulo().equals(titulo)) {
					String libro = l.getTitulo() + "#" + l.getIsbn() + "#" + l.getAutor() + "#" + l.getEditorial() + "#" + l.isReservado()+ "/";
					disp += libro;
				}
				if(l.isReservado() == false) {
					System.out.println(" * El libro" + titulo + "se encuentra disponible");
					
				} else {
					System.out.println(" * El libro no se encuentra disponible");
				}

			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		return disp;
	}

}