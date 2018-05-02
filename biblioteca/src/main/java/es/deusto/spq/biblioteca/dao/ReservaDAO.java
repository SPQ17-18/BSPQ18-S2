package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.apache.log4j.Logger;


import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;

public class ReservaDAO implements IReservaDAO {

	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaDAO.class);



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

	@Override
	public void editarReserva(String dni,String fecha,String hora,String sala, String fecha_nueva, String hora_nueva,String SalaNueva) {

		Reserva r = devolverReserva(dni, fecha, hora);
		if(sala.equals(r.getId_sala()))	logger.debug("Sala correcta");
		if (consultarDisponibilidad(SalaNueva, fecha_nueva, hora_nueva)) {
			Reserva aux = new Reserva(r.getId_reserva(), SalaNueva, r.getDni_respon(), fecha_nueva, hora_nueva,
					r.getPlazas());
			eliminarReserva(r);
			anyadirReserva(aux);
			//System.out.println("Reserva modificada satisfactoriamente");
			logger.info("Reserva modificada satisfactoriamente");

		} else {
			//System.out.println("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");
			logger.info("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");

		}

	}

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

	@Override
	public void anyadirUsuario(Reserva r) {

		Reserva aux = new Reserva(r.getId_reserva(), r.getId_sala(), r.getDni_respon(), r.getFecha(), r.getHora(),
				r.getPlazas() + 1);
		eliminarReserva(r);
		anyadirReserva(aux);

	}

	@Override
	public String verReservas(String dni) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String datos = null;
		try {
			//System.out.println("   * Consultado reservas de: " + dni);
			logger.info("   * Consultado reservas de: " + dni);
			
			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					System.out.println("============DNI : " + r.getDni_respon() +"==========================\nSala : " + r.getId_sala() 
					+ "\nFecha : " + r.getFecha()
					+ "\nHora : " + r.getHora()
					+"\nNº plazas : " + r.getPlazas()
					+ "\n======================================\n");
					datos += r.getDni_respon() + "#" + r.getId_sala() + "#" + r.getFecha() + "#" + r.getHora() + "#" + r.getPlazas() + "/" ;
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

	public void EliminarParticipanteR(String id_reserva, String plazas) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		int reserva = Integer.parseInt(id_reserva);
		int Plazas = Integer.parseInt(plazas);
		Reserva r = null;
		try {
			//System.out.println(" *Eliminando: " + Plazas);
			logger.info(" *Eliminando: " + Plazas);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE id_reserva == " + reserva);
			query.setUnique(true);
			r = (Reserva) query.execute();
			int nuevoNumero = r.getPlazas() - Plazas;
			tx.commit();
			tx.begin();
			r.setPlazas(nuevoNumero);
			pm.makePersistent(r);
			tx.commit();
			//System.out.println(" *Eliminando: " + Plazas + "Nuevo numero de asistentes: " + nuevoNumero);
			logger.info(" *Eliminando: " + Plazas + "Nuevo numero de asistentes: " + nuevoNumero);

		} catch (Exception ex) {
			//System.out.println(" $ Error eliminando participantes: " + ex.getMessage());
			logger.error(" $ Error eliminando participantes: " + ex.getMessage());

		}
	}

	@Override
	public Reserva devolverReserva(String dni, String fecha, String hora) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Reserva R = null;

		try {
			//System.out.println("   * Buscando reserva de: " + dni);
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
			//System.out.println("   $ Error devolviendo reserva: " + ex.getMessage());
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