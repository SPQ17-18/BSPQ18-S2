package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public class ReservaComedorDAO implements IReservaComedorDAO{

	private PersistenceManagerFactory pmf;

	public ReservaComedorDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void anyadirReservaComedor(ReservaMesa r) {
		// TODO Auto-generated method stub
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
					System.out.println("   * Guardando reserva de comedor: " + r.getId_Mesa());
					pm.makePersistent(m);
					tx.commit();
				}
			}
		} catch (Exception ex) {
			System.out.println("   $ Error guardando reserva: " + ex.getMessage());
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

	@Override
	public void eliminarReservaComedor(ReservaMesa r) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Eliminando reserva: " + r.getId_Reserva());
			pm.deletePersistent(r);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error Eliminando reserva de comedor: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	@Override
	public void editarReservaComedor(ReservaMesa r, String fecha_nueva, String hora_nueva) {
		if (consultarDisponibilidadComedor(r.getId_Mesa(), fecha_nueva, hora_nueva)) {
			ReservaMesa aux = new ReservaMesa(r.getId_Reserva(),r.getId_Mesa(), r.getDni_respon(),r.getFecha(),r.getHora(),r.getPersonas());
			// eliminarReserva(r);
			anyadirReservaComedor(aux);
			System.out.println("Reserva modificada satisfactoriamente");
		} else {
			System.out.println("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");
		}

	}
}
