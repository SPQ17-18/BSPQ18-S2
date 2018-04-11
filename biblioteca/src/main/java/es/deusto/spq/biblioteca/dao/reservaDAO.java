package es.deusto.spq.biblioteca.dao;
import javax.jdo.Extent;
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

		//Provisional para el sprint 1
		@Override
		public void editarReserva(String id_reserva, reserva nueva_res) {
		eliminarReserva(verReserva(id_reserva));
		almacenarReserva(nueva_res);
		}
}
