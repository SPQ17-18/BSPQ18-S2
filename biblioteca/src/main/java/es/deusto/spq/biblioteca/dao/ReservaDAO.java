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

/**
 * Clase para el manejo de la Base de Daatos
 * @author koldo
 *
 */
public class ReservaDAO implements IReservaDAO {

	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaDAO.class);


	/**
	 * Contructor de la clase
	 */
	public ReservaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Metodo que anyade una reserva.
	 * @Reserva anyade reserva.
	 * @throws Exception Lanza una excepccion si ocurre un error.
	 */
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

	/**
	 * Permite consultar la disponibilidad de una sala.
	 * @param Id_Sala Identificador de la sala.
	 * @param fecha Fecha.
	 * @param hora Hora.
	 */
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

	/**
	 * Para editar una reserva.
	 * @param dni DNI de la persona.
	 * @param fecha Fecha actual de la reserva.
	 * @param hora Hora actual de la reserva.
	 * @param sala Sala para la que esta hecha la reserva.
	 * @param fecha_nueva Nueva fecha de reserva.
	 * @param hora_nueva Nueva hora de reserva.
	 * @param sala_nueva Sala nueva para la nueva reserva.
	 */
	@Override
	public void editarReserva(String dni,String fecha,String hora,String sala, String fecha_nueva, String hora_nueva,String SalaNueva) {
		//TODO
	}

	/**
	 * Elimina una reserva de la BD.
	 * @Reserva Reserva que se elimina.
	 */
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

	/**
	 * Devuelve las reservas de una persona de la BD
	 * @param dni DNI de la persona
	 */
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