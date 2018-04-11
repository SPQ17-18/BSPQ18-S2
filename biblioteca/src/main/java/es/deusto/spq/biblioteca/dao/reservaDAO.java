package es.deusto.spq.biblioteca.dao;
import javax.jdo.Extent;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.libro;
import es.deusto.spq.biblioteca.data.reserva;

public class reservaDAO implements IReservasDAO{
	
		private PersistenceManagerFactory pmf;
		
		public reservaDAO(){
			pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		}
		public void almacenarReserva(reserva res) {
				// TODO Auto-generated method stub
				PersistenceManager pm = pmf.getPersistenceManager();
				Transaction tx = pm.currentTransaction();

				try {
					tx.begin();
					System.out.println("   * Almacenando reserva: " + res);
					pm.makePersistent(res);
					tx.commit();
				} catch (Exception ex) {
					System.out.println("   $ Error almacenando reserva: " + ex.getMessage());
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}

					pm.close();
				}
				
			}
		@Override
		public reserva verReserva(String id_reserva) {
			// TODO Auto-generated method stub
			PersistenceManager pm = pmf.getPersistenceManager();
			pm.getFetchPlan().setMaxFetchDepth(3);

			Transaction tx = pm.currentTransaction();
			reserva r = null;

			try {
				System.out.println("   * Buscando reserva: " + id_reserva);

				tx.begin();
				Query<?> query = pm.newQuery("SELECT FROM " + reserva.class.getName() + " WHERE id_reserva == '" + id_reserva);
				query.setUnique(true);
				r = (reserva) query.execute();
				tx.commit();

			} catch (Exception ex) {
				System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}

			return r;
			
		}
		//necesita GUI
		public void verReservas() {
			new GUIreservas;
		}

		//Provisional para el sprint 1
		@Override
		public void editarReserva(String id_reserva, reserva nueva_res) {
		eliminarReserva(verReserva(id_reserva));
		almacenarReserva(nueva_res);
		}
=======
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



>>>>>>> remotes/origin/Koldo
=======

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



>>>>>>> refs/remotes/origin/Koldo
}
