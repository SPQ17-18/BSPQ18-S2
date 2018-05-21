package es.deusto.spq.biblioteca.dao;
/**
 * Clase para el manejo de la Base de Datos
 * @author Ariane
 *
 */
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

/**
 * Clase para el manejo de la Base de Daatos
 * @author koldo
 *
 */
public class SalaDAO implements ISalaDAO {

	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(SalaDAO.class);

	/**
	 * Contructor de la clase
	 */
	public SalaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Anyade una nueva sala.
	 * @Sala Sala de la biblioteca.
	 */
	@Override
	public void anyadirSala(Sala s) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			//System.out.println("   * Guardando sala: " + s.getId_sala());
			logger.info("   * Guardando sala: " + s.getId_sala());
			pm.makePersistent(s);
			tx.commit();
		} catch (Exception ex) {
			//System.out.println("   $ Error guardando sala: " + ex.getMessage());
			logger.error("   $ Error devolviendo reserva: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	/**
	 * Consulta las plazas que hay para una sala.
	 * @param Id_sala Identificador de la sala.
	 * @param Personas Numero de personas.
	 */
	@Override
	public boolean consultarPlazas(String Id_Sala, int personas) {
		
		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			//System.out.println("   * Consultado plazas de: " + Id_Sala);
			logger.info("   * Consultado plazas de: " + Id_Sala);

			tx.begin();
			Query<Sala> query = pm.newQuery(Sala.class);
			@SuppressWarnings("unchecked")
			List<Sala> salas = (List<Sala>) query.execute();
			for (Sala s : salas) {
				if (s.getId_sala().equals(Id_Sala) && personas >= s.getCapacidad()) {
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
}

