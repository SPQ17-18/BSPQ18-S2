package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.reserva;

public class reservaDAO implements IreservaDAO {

	private PersistenceManagerFactory pmf;
	
	public reservaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void anyadirReserva(reserva r) {
		// TODO Auto-generated method stub
		int cont=0;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Guardando reserva: " + cont++);
			pm.makePersistent(r);
			tx.commit();
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
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora) {
		// TODO Auto-generated method stub

		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado disponibilidad de: " + Id_Sala);
			tx.begin();
			Query<reserva> query = pm.newQuery(reserva.class);
			@SuppressWarnings("unchecked")
			List<reserva> reservas = (List<reserva>) query.execute();
			for (reserva r : reservas) {
				if (r.getId_sala().equals(Id_Sala) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
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
	


}
