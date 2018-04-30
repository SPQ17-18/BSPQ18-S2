package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.Comedor;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public class ReservaComedorDAO implements IReservaComedorDAO{

	private PersistenceManagerFactory pmf;

	public ReservaComedorDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void anyadirReservaC(ReservaMesa r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<Comedor> query = pm.newQuery(Comedor.class);
			@SuppressWarnings("unchecked")
			List<Comedor> mesas = (List<Comedor>) query.execute();
			for (Comedor m : mesas) {
				if (m.getId_Comedor().equals(r.getId_Comedor())) {
					m.getReservaMesas().add(r);
					System.out.println("   * Guardando reserva de comedor: " + r.getId_Comedor());
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
	public boolean consultarDisponibilidad(String Id_Comedor, String fecha, String hora) {

		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado disponibilidad de: " + Id_Comedor);
			tx.begin();
			Query<ReservaMesa> query = pm.newQuery(ReservaMesa.class);
			@SuppressWarnings("unchecked")
			List<ReservaMesa> reservas = (List<ReservaMesa>) query.execute();
			for (ReservaMesa r : reservas) {
				if (r.getId_Comedor().equals(Id_Comedor) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
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
}
