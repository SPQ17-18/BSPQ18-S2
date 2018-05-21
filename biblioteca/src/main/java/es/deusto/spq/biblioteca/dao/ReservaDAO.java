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
 * Clase para el manejo de la Base de Datos
 * Metodos de anyadir y consutar disponibilidad realizados por
 * @author Ariane
 * Metodos de devolver, eliminar y editar reserva realizados por
 *  @author Julen y Mikel
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
					
					logger.info("   * Guardando reserva: " + r.getId_reserva());

					pm.makePersistent(s);
					tx.commit();
				}
			}
		} catch (Exception ex) {
	
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
		

		boolean disponible = true;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			logger.info("   * Consultado disponibilidad de: " + Id_Sala);

			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getId_sala().equals(Id_Sala) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
					disponible = false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reserva r = devolverReserva(dni, fecha, hora);
		if(sala.equals(r.getId_sala()))	logger.debug("Sala correcta");
		if (consultarDisponibilidad(SalaNueva, fecha_nueva, hora_nueva)) {
			Reserva aux = new Reserva(r.getId_reserva(), SalaNueva, r.getDni_respon(), fecha_nueva, hora_nueva,r.getPlazas());
			eliminarReserva(r);
			anyadirReserva(aux);
	
			logger.info("Reserva modificada satisfactoriamente");

		} else {
		
			logger.info("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");

		}
	}

	/**
	 * Elimina una reserva de la BD.
	 * @Reserva Reserva que se elimina.
	 */
	@Override
	public void eliminarReserva(Reserva r) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Eliminando reserva: ");

			pm.deletePersistent(r);
			tx.commit();
		} catch (Exception ex) {
	
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
	
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		ArrayList<String> datos  = new ArrayList<String>();
		String d=null;
		
		try {
		
			logger.info("   * Consultado reservas de: " + dni);
			
			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					logger.info("============DNI : " + r.getDni_respon() + "==========================\nSala : "
							+ r.getId_sala() + "\nFecha : " + r.getFecha() + "\nHora : " + r.getHora()
							+ "\nNº plazas : " + r.getPlazas() + "\n======================================\n");
					d = r.getId_reserva() + "#"+r.getDni_respon() + "#" + r.getId_sala() + "#" + r.getFecha() + "#" + r.getHora() + "#" + r.getPlazas() + "/" ;
					datos.add(d);
				}
			}
			tx.commit();
		} catch (Exception ex) {
	
			logger.error("   $ Error retreiving an extent: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return datos;
	}

	@Override
	public Reserva devolverReserva(String dni, String fecha, String hora) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reserva R = null;

		try {
		
			logger.info("   * Buscando reserva de: " + dni);

			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getDni_respon().equals(dni) && r.getFecha().equals(fecha) && r.getHora().equals(hora)) {
					R = r;
				}

			}
			tx.commit();
		} catch (Exception ex) {

			logger.error("   $ Error devolviendo reserva: " + ex.getMessage());

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return R;
	}
}