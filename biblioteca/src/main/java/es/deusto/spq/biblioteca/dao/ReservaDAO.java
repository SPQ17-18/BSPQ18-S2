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

	@Override
	public void verReservas(String dni) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Consultado reservas de: " + dni);
			tx.begin();
			Query<Reserva> query = pm.newQuery(Reserva.class);
			@SuppressWarnings("unchecked")
			List<Reserva> reservas = (List<Reserva>) query.execute();
			for (Reserva r : reservas) {
				if (r.getDni_respon().equals(dni)) {
					System.out.println("======================================");
					System.out.println("\nSala : " + r.getId_sala());
					System.out.println("\nFecha : " + r.getFecha());
					System.out.println("\nHora : " + r.getHora());
					System.out.println("\nNº plazas : " + r.getPlazas());
					System.out.println("\n======================================\n");
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
	}

	@Override
	public void editarReserva(Reserva r, String fecha_nueva, String hora_nueva) {
		
		if(consultarDisponibilidad(r.getId_sala(), fecha_nueva, hora_nueva)) {
			Reserva aux = new Reserva(r.getId_reserva(), r.getId_sala(), r.getDni_respon(), fecha_nueva, hora_nueva, r.getPlazas());
		//	eliminarReserva(r);
			anyadirReserva(aux);
			System.out.println("Reserva modificada satisfactoriamente");
		}else {
			System.out.println("Reserva no modificada.No se puede reservar en la fecha/hora seleccionadas");
		}
		
	}

	@Override
	public void editarReserva(Reserva r, String hora_nueva) {
		editarReserva(r, r.getFecha(), hora_nueva);
	}

	@Override
	public void eliminarReserva(Reserva r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Eliminando reserva *   " );
			pm.deletePersistent(r);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error Eliminando reserva: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	@Override
	public void anyadirUsuario(Reserva r) {
		
		
			Reserva aux = new Reserva(r.getId_reserva(), r.getId_sala(), r.getDni_respon(), r.getFecha() , r.getHora(), r.getPlazas()+1);
			eliminarReserva(r);
			anyadirReserva(aux);
			
		
	}


}
