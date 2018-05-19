package es.deusto.spq.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.apache.log4j.Logger;


import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;

public class ReservaDAO implements IReservaDAO {

	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaDAO.class);



	public ReservaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void anyadirReserva(Reserva r) {
		// TODO Auto-generated method stub

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<Sala> query = pm.newQuery(Sala.class);
			@SuppressWarnings("unchecked")
			List<Sala> salas = (List<Sala>) query.execute();
			for (Sala s : salas) {
				if (s.getId_sala().equals(r.getId_sala())) {
					s.getReservas().add(r);
					//System.out.println("   * Guardando reserva: " + r.getId_reserva());
					logger.info("   * Guardando reserva: " + r.getId_reserva());

					pm.makePersistent(s);
					tx.commit();
				}
			}
		} catch (Exception ex) {
			//System.out.println("   $ Error guardando reserva: " + ex.getMessage());
			logger.error("   $ Error guardando reserva:" + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	@Override
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora) {
		// TODO Auto-generated method stub

		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			//System.out.println("   * Consultado disponibilidad de: " + Id_Sala);
			logger.info("   * Consultado disponibilidad de: " + Id_Sala);

			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getId_sala().equals(Id_Sala) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
					// No hay salas disponibles
					disponible = false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
			logger.error("   $ Error retreiving an extent: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return disponible;

	}

	@Override
	public void editarReserva(String dni,String fecha,String hora,String sala, String fecha_nueva, String hora_nueva,String SalaNueva) {
		//TODO
	}

	@Override
	public void eliminarReserva(Reserva r) {
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
	public ArrayList<String> verReservas(String dni) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		ArrayList<String> datos = new ArrayList<String>();
		try {
			//System.out.println("   * Consultado reservas de: " + dni);
			logger.info("   * Consultado reservas de: " + dni);
			
			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					String reserva;
					reserva = r.getId_reserva()+"#"+  r.getId_sala()  + "#" +r.getDni_respon() + "#" + r.getFecha() + "#" + r.getHora() + "#" + r.getPlazas() + "/" ;
					datos.add(reserva);
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
			logger.error("   $ Error retreiving an extent: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return datos;
	}
}