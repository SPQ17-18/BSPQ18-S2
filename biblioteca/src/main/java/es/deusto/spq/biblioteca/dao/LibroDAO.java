package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;

public class LibroDAO implements ILibroDAO{
	
	private PersistenceManagerFactory pmf;

	public LibroDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

}
	
	@Override
	public void almacenarLibro(Libro l) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Almacenando libro: " + l);
			pm.makePersistent(l);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error almacenando libro: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}

	//Funcion de busqueda de un libro por su nombre
	@Override
	public Libro getLibro(String nombre) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Libro l = null;
	//	libro l = new libro(); descomentar este si no funciona el otro

		try {
			System.out.println("   * Buscando libro: " + nombre);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre +"'");
			query.setUnique(true);
			l = (Libro) query.execute();
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

	//Funcion que devuelve el catalogo de libros
	@Override
	public ArrayList<Libro> getLibros() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		
		ArrayList<Libro> catalogo = new ArrayList<Libro>();
		
		try {
			System.out.println("   * Mostrando catalogo de libros...");

			tx.begin();
			Query<?> query2 = pm.newQuery("SELECT FROM " + Libro.class.getName());
	
			List<Libro> l = (List<Libro>) query2.execute();
		
			for(int i = 0; i < l.size(); i++) {
				catalogo.add(new Libro());
				catalogo.get(i);
			}
		
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error recuperando todos los libros: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		
		
		return null;
	}	
	
	public void EliminarLibro(String isbn) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		int codigo = Integer.parseInt(isbn);
		Libro l = null;
		try {
			System.out.println(" *Eliminando: " + codigo );
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE isbn == " + codigo );
			query.setUnique(true);
			l = (Libro) query.execute();
			int nuevaLista = l.getNumeroEjemplares()-codigo;
			tx.commit();
			tx.begin();
			l.setNumeroEjemplares(nuevaLista);
			pm.makePersistent(l);
			tx.commit();
			System.out.println(" *Eliminando: " + codigo +  "Nuevo numero de asistentes: " + nuevaLista);

		} catch (Exception ex) {
			System.out.println(" $ Error eliminando participantes: " + ex.getMessage());
		}
	}

	
	/**
	 * OLVIDAR POR AHORA. ESPARA CUANDO SE HAGA LA RESERVA. NO ES DE SPRINT 1
	 */
//	@Override
//	public String EstaDisponible(String nombre, boolean isReservado) {
//		// TODO Auto-generated method stub
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx = pm.currentTransaction();
//		String disp = "";
//	
//		try {
//			System.out.println("   * Buscando reserva del libro: " + nombre);
//			tx.begin();
//			Query<libro> query = pm.newQuery(libro.class);
//			@SuppressWarnings("unchecked")
//			List<libro> book = (List<libro>) query.execute();
//			for (libro l : book) {
//				if (l.getnombre().equals(nombre)) {
//					String libro = l.getnombre() + "#" + l.getIsbn() + "#" + l.getAutor() + "#" + l.getEditorial() + "#" + l.isReservado()+ "/";
//					disp += libro;
//				}
//				if(l.isReservado() == false) {
//					System.out.println(" * El libro" + nombre + "se encuentra disponible");
//					
//				} else {
//					System.out.println(" * El libro no se encuentra disponible");
//				}
//
//			}
//			tx.commit();
//		} catch (Exception ex) {
//			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//
//			pm.close();
//		}
//		
//		return disp;
//	}

}
