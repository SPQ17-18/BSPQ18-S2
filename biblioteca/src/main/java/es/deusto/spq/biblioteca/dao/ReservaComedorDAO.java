package es.deusto.spq.biblioteca.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.naming.spi.DirStateFactory.Result;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.data.Sala;
import es.deusto.spq.biblioteca.data.Menu;

public class ReservaComedorDAO implements IReservaComedorDAO{
	
	
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaComedorDAO.class);

	public ReservaComedorDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void anyadirReservaComedor(ReservaMesa r) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<Mesa> query = pm.newQuery(Mesa.class);
			@SuppressWarnings("unchecked")
			List<Mesa> mesas = (List<Mesa>) query.execute();
			for (Mesa m : mesas) {
				if (m.getId_Mesa().equals(r.getId_Mesa())) {
					m.getReservaMesas().add(r);
					// System.out.println(" * Guardando reserva: " + r.getId_reserva());
					logger.info("   * Guardando reserva: " + r.getId_Reserva());
					pm.makePersistent(m);
					tx.commit();
				}
			}
		} catch (Exception ex) {
			// System.out.println(" $ Error guardando reserva: " + ex.getMessage());
			logger.error("   $ Error guardando reserva:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
	@Override
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora) {

		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado disponibilidad de: " + Id_Mesa);
			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class);
			@SuppressWarnings("unchecked")
			List<ReservaMesa> reservas = (List<ReservaMesa>) query.execute();
			for (ReservaMesa r : reservas) {
				if (r.getId_Mesa().equals(Id_Mesa) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
					// No hay salas disponibles
					disponible = false;
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
		return disponible;

	}

	@Override
	public void verReservaComedor(String dni) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado reservas de: " + dni);
			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class);
			@SuppressWarnings("unchecked")
			List<ReservaMesa> reservas = (List<ReservaMesa>) query.execute();
			for (ReservaMesa r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					System.out.println("======================================");
					System.out.println("\nReserva : " + r.getId_Reserva());
					System.out.println("\nMesa : " + r.getId_Mesa());
					System.out.println("\nFecha : " + r.getFecha());
					System.out.println("\nHora : " + r.getHora());
					System.out.println("\nNº personas : " + r.getPersonas());
					System.out.println("\n======================================\n");
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
	}

	//Koldo: Tengo que editarlo
	@Override
	public void eliminarReservaComedor(ReservaMesa r) throws Exception {
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx = pm.currentTransaction();
//
//		try {
//			tx.begin();
//			System.out.println("   * Eliminando reserva: " + r.getId_Reserva());
//			pm.deletePersistent(r);
//			tx.commit();
//		} catch (Exception ex) {
//			System.out.println("   $ Error Eliminando reserva de comedor: " + ex.getMessage());
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//
//			pm.close();
//		}
		
		//No se si va. Hay que mirarlo
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			
			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class, "id_reserva =='" + r.getId_Reserva() + "'");
			Collection<?> e = (Collection<?>) query.execute();
			
			ReservaMesa rm = (ReservaMesa) e.iterator().next();
					
			query.close();
			pm.deletePersistent(rm);
			tx.commit();
			logger.info("Se ha eliminado correctamente la reserva");
			
		} catch (Exception ex) {
			logger.error("Error eliminando una reserva de una mesa: " + ex.getMessage());
			throw new Exception();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		
	}

	//Koldo: Tengo que editarlo
	@Override
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva) {
//		if (consultarDisponibilidadComedor(r.getId_Mesa(), fecha_nueva, hora_nueva)) {
//			ReservaMesa aux = new ReservaMesa(r.getId_Reserva(),r.getId_Mesa(), r.getDni_respon(),r.getFecha(),r.getHora(),r.getPersonas());
//			// eliminarReserva(r);
//			anyadirReservaComedor(aux);
//			System.out.println("Reserva modificada satisfactoriamente");
//		} else {
//			System.out.println("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");
//		}
		
		//Hay que verificar si funciona
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		tx.begin();
		Extent<ReservaMesa> extent = pm.getExtent(ReservaMesa.class, true);
		
		for (ReservaMesa rm : extent) {
			if (id_reserva.equals(rm.getId_Reserva())) {
				rm.setFecha(fecha_nueva);
				rm.setHora(hora_nueva);
				pm.makePersistent(rm);
				
				logger.info("Se ha editado correctamente la reserva del comedor");
				break;
					
			}
			
		}
		tx.commit();
		
		try {
			
		} catch (Exception e) {
			logger.error("Hubo un error editando la reserva" + e.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}

	@Override
	public ReservaMesa devolverReservaComedor(String dni, String fecha, String hora) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		ReservaMesa R = null;

		try {
			//System.out.println("   * Buscando reserva de: " + dni);
			logger.info("   * Buscando reserva de: " + dni);

			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class);
			@SuppressWarnings("unchecked")
			List<ReservaMesa> reservas = (List<ReservaMesa>) query.execute();
			for (ReservaMesa r : reservas) {
				if (r.getDni_respon().equals(dni) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
					R = r;
				}

			}
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error devolviendo reserva: " + ex.getMessage());
			logger.error("   $ Error devolviendo reserva: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return R;
	}
	
	
	
	public void consultaMenu(Menu menu){
		try {
			//System.out.println("   * Mostrando datos del libro: " + nombre);
			logger.info("\"   * Mostrando menu: " + menu.toString());

			System.out.println(menu.toString());
			
		} catch (Exception ex) {
			//System.out.println("   $ Error mostrando datos del libro seleccionado: " + ex.getMessage());
			logger.error("   $ Error mostrando datos del libro seleccionado:" + ex.getMessage());

		}
	}
	

	public void seleccionarMenu(){
		
	}


	public void comprarMenu(){
		
	}
	
}
