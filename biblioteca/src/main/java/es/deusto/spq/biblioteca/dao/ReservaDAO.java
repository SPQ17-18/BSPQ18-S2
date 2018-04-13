package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.Reserva;

public class ReservaDAO implements IReservaDAO {

	private PersistenceManagerFactory pmf;
	
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
			System.out.println("   * Guardando reserva: " + r.getId_reserva());
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
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return disponible;

	}
	
	public void EliminarParticipanteR(String id_reserva, String plazas) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		int reserva = Integer.parseInt(id_reserva);
		int Plazas = Integer.parseInt(plazas);
		Reserva r = null;
		try {
			System.out.println(" *Eliminando: " + Plazas );
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE id_reserva == " + reserva );
			query.setUnique(true);
			r = (Reserva) query.execute();
			int nuevoNumero = r.getPlazas()-Plazas;
			tx.commit();
			tx.begin();
			r.setPlazas(nuevoNumero);
			pm.makePersistent(r);
			tx.commit();
			System.out.println(" *Eliminando: " + Plazas +  "Nuevo numero de asistentes: " + nuevoNumero);

		} catch (Exception ex) {
			System.out.println(" $ Error eliminando participantes: " + ex.getMessage());
		}
	}


}




