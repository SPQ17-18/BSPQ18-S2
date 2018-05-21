package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de interfaz DAO de mesa
 * @author Ariane
 */
import es.deusto.spq.biblioteca.data.Mesa;

public interface IMesaDAO {

	public void anyadirMesa(Mesa m);
	public boolean consultarPlazasComedor(String Id_Mesa, int personas);
}
