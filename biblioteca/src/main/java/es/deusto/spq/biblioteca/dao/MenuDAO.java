package es.deusto.spq.biblioteca.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Sala;

/**
 * Clase para el manejo de la Base de Daatos
 * @author koldo
 *
 */
public class MenuDAO implements IMenuDAO{
	
	private PersistenceManagerFactory pmf;
	private static final Logger logger = Logger.getLogger(ReservaComedorDAO.class);
	
	/**
	 * Contructor de la clase
	 */
	public MenuDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Metodo que anyade un libro.
	 * @Menu anyade menu.
	 * @throws Exception Lanza una excepccion si ocurre un error.
	 */
	@Override
	public void anyadirMenu(Menu m) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			logger.info("   * Guardando menu: " + m.getID_menu());
			pm.makePersistent(m);
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error devolviendo menu: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	/**
	 * Permite añyadr una valoracion al menu.
	 * @param ID_menu Identificador del menu.
	 * @param valoracion Valoracion dada al menu.
	 */
	@Override
	public void anyadirValoracion(String ID_Menu, int valoracion) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			logger.info("   * Anyadiendo valoración a menu: " + ID_Menu);
			tx.begin();
			Query<Menu> query = pm.newQuery(Menu.class);
			@SuppressWarnings("unchecked")
			List<Menu> menus = (List<Menu>) query.execute();
			for (Menu m : menus) {
				if (m.getID_menu().equals(ID_Menu)) {
					int valor=m.getValor();
					int media=0;
					if(valor==0) {
						m.setValor(valoracion);
					}else {
						media=(valor+valoracion)/2;
						m.setValor(media);
					}
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
	}

	/**
	 * Permite ver el menu.
	 * @param fecha Fecha del dia que quieres ver el menu
	 */
	@Override
	public String verMenu(String fecha) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String datos = null;
		try {
			logger.info("   *Consultando Menu del dia: " + fecha);
			tx.begin();
			Query<Menu> query = pm.newQuery(Menu.class);
			@SuppressWarnings("unchecked")
			List<Menu> menus = (List<Menu>) query.execute();
			for (Menu m : menus) {
				if (m.getFecha().equals(fecha)) {
//					System.out.println("======================================");
//					System.out.println("\nFecha : " + m.getFecha());
//					System.out.println("\n1º Plato : " + m.getPlato1());
//					System.out.println("\n2º Plato : " + m.getPlato2());
//					System.out.println("\nPostre : " + m.getPostre());
//					System.out.println("\nValoración : " + m.getValor());
//					System.out.println("\n======================================\n");
					datos += m.getFecha() + "#" + m.getPlato1() + "#" + m.getPlato2() + "#" + m.getPostre() + "#" +m.getValor() + "/" ;

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
		return datos;
	}

}