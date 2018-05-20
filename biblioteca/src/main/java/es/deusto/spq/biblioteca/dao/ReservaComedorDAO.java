package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;

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
	public ArrayList<String> verReservaComedor(String dni) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		ArrayList<String> datos  = new ArrayList<String>();
		String d=null;
		
		try {
			System.out.println("   * Consultado reservas de: " + dni);
			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class);
			@SuppressWarnings("unchecked")
			List<ReservaMesa> reservas = (List<ReservaMesa>) query.execute();
			for (ReservaMesa r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					logger.info("============DNI : " + r.getDni_respon() + "==========================\nSala : "
							+ r.getId_Mesa() + "\nFecha : " + r.getFecha() + "\nHora : " + r.getHora()
							+ "\nNº plazas : " + r.getPersonas() + "\n======================================\n");
					d += r.getId_Reserva() + "#"+r.getDni_respon() + "#" + r.getId_Mesa() + "#" + r.getFecha() + "#" + r.getHora() + "#" + r.getPersonas() + "/" ;
					datos.add(d);
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
		return datos;
	}

	@Override
	public void eliminarReservaComedor(ReservaMesa r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			//System.out.println("   * Eliminando reserva: ");
			logger.info("   * Eliminando reserva: ");

			pm.deletePersistent(r);
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error Eliminando reserva: " + ex.getMessage());
			logger.error("	$ Error Eliminando reserva: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
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
	@Override
	public void editarReservaComedor(String dni,String fecha,String hora,String mesa, String fecha_nueva, String hora_nueva,String mesa_nueva) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		ReservaMesa rm = devolverReservaComedor(dni, fecha, hora);
		if(mesa.equals(rm.getId_Mesa()))	logger.debug("Mesa correcta");
		if (consultarDisponibilidadComedor(mesa_nueva, fecha_nueva, hora_nueva)) {
			ReservaMesa aux = new ReservaMesa(rm.getId_Mesa(), mesa_nueva, rm.getDni_respon(), fecha_nueva, hora_nueva,rm.getPersonas());
			eliminarReservaComedor(rm);
			anyadirReservaComedor(aux);
			//System.out.println("Reserva modificada satisfactoriamente");
			logger.info("Reserva modificada satisfactoriamente");

		} else {
			//System.out.println("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");
			logger.info("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");

		}
	}
}
