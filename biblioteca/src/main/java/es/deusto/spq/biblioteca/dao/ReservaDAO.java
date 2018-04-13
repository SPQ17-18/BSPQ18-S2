package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;

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
			Query<Sala> query = pm.newQuery(Sala.class);
			@SuppressWarnings("unchecked")
			List<Sala> salas = (List<Sala>) query.execute();
			for (Sala s : salas) {
				if (s.getId_sala().equals(r.getId_sala())) {
					s.getReservas().add(r);
					System.out.println("   * Guardando reserva: " + r.getId_reserva());
					pm.makePersistent(s);
					tx.commit();
				}
			}
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
			System.out.println("   * Eliminando reserva: " + cont++);
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

	@Override
	public void verReservas(String dni) {
		// TODO Auto-generated method stub
		
	}


}
