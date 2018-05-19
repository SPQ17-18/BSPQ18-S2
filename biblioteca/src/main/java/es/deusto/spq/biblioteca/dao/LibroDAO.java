package es.deusto.spq.biblioteca.dao;

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
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.data.Sala;

public class LibroDAO implements ILibroDAO{
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(LibroDAO.class);


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
		//	System.out.println("   * Almacenando libro: " + l);
			logger.info("   * Almacenando libro: " + l);
			pm.makePersistent(l);
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error almacenando libro: " + ex.getMessage());
			logger.error("   $ Error almacenando libro:" + ex.getMessage());
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
			//System.out.println("   * Buscando libro: " + nombre);
			logger.info("   * Buscando libro: " + nombre);


			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Libro.class.getName() + " WHERE nombre == '" + nombre +"'");
			query.setUnique(true);
			l = (Libro) query.execute();
			tx.commit();

		} catch (Exception ex) {
			//System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
			logger.error("   $ Error buscando libro:" + ex.getMessage());

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
			//System.out.println("   * Mostrando catalogo de libros...");
			logger.info("  * Mostrando catalogo de libros...");
			
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			
//			Query<?> query2 = pm.newQuery("SELECT FROM " + Libro.class.getName());
//			List<Libro> l = (List<Libro>) query2.execute();
			
			Extent<Libro> extent = pm.getExtent(Libro.class, true);
			
//			for(int i = 0; i < l.size(); i++) {
//				catalogo.add(new Libro());
//				catalogo.get(i);
//			}
			for (Libro libro : extent) {
				catalogo.add(new Libro(libro.getIsbn(), libro.getnombre(), libro.getAutor(), libro.getEditorial()));
				
			}
			
			tx.commit();

		} catch (Exception ex) {
			//System.out.println("   $ Error recuperando todos los libros: " + ex.getMessage());
			logger.error("   $ Error recuperando todos los libros: " + ex.getMessage());
		
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		
		
		return null;
	}	
	
	public void EliminarLibro(int isbn) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		//int codigo = Integer.parseInt(isbn);
		Libro l = null;
		try {
			//System.out.println(" *Eliminando: " + codigo );
			logger.info(" *Eliminando: " + isbn );
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE isbn == " + isbn );
			query.setUnique(true);
			l = (Libro) query.execute();
			int nuevaLista = l.getNumeroEjemplares()-isbn;
			tx.commit();
			tx.begin();
			l.setNumeroEjemplares(nuevaLista);
			pm.makePersistent(l);
			tx.commit();
			//System.out.println(" *Eliminando: " + codigo +  "Nuevo numero de asistentes: " + nuevaLista);
			logger.info(" *Eliminando: " + isbn +  "Nuevo numero de asistentes: " + nuevaLista);


		} catch (Exception ex) {
			//System.out.println(" $ Error eliminando libro: " + ex.getMessage());
			logger.error("   $ Error eliminando libro:" + ex.getMessage());

		}
	}

//	@Override
//	public void reservarLibro(Libro l) {
		// TODO Auto-generated method stub
//		boolean disponible = true;
//		
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx = pm.currentTransaction();
//		try {
//			tx.begin();
//			Query<Libro> query = pm.newQuery(Libro.class);
//			@SuppressWarnings("unchecked")
//			List<Libro> libro = (List<Libro>) query.execute();
//			for (Libro l : libro) {
//				
//				if (l.getnombre().equals(nombre)){
//					// No hay salas disponibles
//					disponible = false;
//				} 
//			}
//			tx.commit();
//		} catch (Exception ex) {
//			//System.out.println("   $ Error reservando un libro: " + ex.getMessage());
//			logger.error("   $ Error reservando un libro:" + ex.getMessage());
//
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//
//			pm.close();
//		}
//		return disponible;
		
		
//	}
	
	@Override
	public boolean reservarLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean reservarLibro = false;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			
			tx.begin();
			Query<Libro> query = pm.newQuery(Libro.class);
			List<Libro> libros = (List<Libro>) query.execute();
			
			if(l.getnombre().equals(l.getnombre())) {
				pm.makePersistent(l);
				logger.info("   * Reservando libro: " + l.getnombre());

				tx.commit();
			}
			

			
			
		}catch (Exception ex) {
			//System.out.println("   $ Error reservando un libro: " + ex.getMessage());
			logger.error("   $ Error reservando un libro:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return reservarLibro;
		
	}

	
	@Override
	public Libro verLibro(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Libro l = null;
		//	libro l = new libro(); descomentar este si no funciona el otro

		try {
			//System.out.println("   * Mostrando datos del libro: " + nombre);
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
			//System.out.println("   $ Error mostrando datos del libro seleccionado: " + ex.getMessage());
			logger.error("   $ Error mostrando datos del libro seleccionado:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return l;

	}

	@Override
	public boolean consultarDisponibilidadLibro(String nombre) {
		// TODO Auto-generated method stub
		boolean is_Reservado = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado disponibilidad de: " + nombre);
			tx.begin();
			Query<Libro> query = pm.newQuery(Libro.class);
			@SuppressWarnings("unchecked")
			List<Libro> libros = (List<Libro>) query.execute();
			for (Libro l : libros) {
				if (l.getnombre().equals(nombre)) {
					// No hay salas disponibles
					is_Reservado = false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
			logger.info("   $ Error comprobando disponibilidad del libro: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return is_Reservado;

	}


	
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
