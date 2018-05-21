package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Mesa;

/**
 * Clase para el manejo de la Base de Daatos
 * @author Ariane
 *
 */
public class MesaDAO implements IMesaDAO{

	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(MesaDAO.class);
	
	/**
	 * Contructor de la clase
	 */
	public MesaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	/**
	 * Metodo que anyade una mesa.
	 * @Mesa anyade mesa.
	 * @throws Exception Lanza una excepccion si ocurre un error.
	 */
	@Override
	public void anyadirMesa(Mesa m) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			logger.info("* Guardando mesa: " + m.getId_Mesa());
			pm.makePersistent(m);
			tx.commit();
		} catch (Exception ex) {
			logger.error("$ Error guardando mesa: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	/**
	 * Permite consultar plazas en el comedor.
	 * @param ID_Mesa Identificador de la mesa.
	 * @param personas Numero de personas que van a comer en la mesa.
	 */
	@Override
	public boolean consultarPlazasComedor(String Id_Mesa, int personas) {
		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			logger.info("* Consultado plazas de: " + Id_Mesa);
			tx.begin();
			Query<Mesa> query = pm.newQuery(Mesa.class);
			@SuppressWarnings("unchecked")
			List<Mesa> mesas = (List<Mesa>) query.execute();
			for (Mesa m : mesas) {
				if (m.getId_Mesa().equals(Id_Mesa) && personas >= m.getCapacidad()) {
					disponible = false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("$ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return disponible;
	}

}
