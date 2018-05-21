package es.deusto.spq.biblioteca.dao;
/**
 * Clase de creación de interfaz DAO de sala
 * @author Ariane
 */
import java.util.List;

import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;

public interface ISalaDAO {

	public void anyadirSala(Sala s);
	public boolean consultarPlazas(String Id_Sala, int personas);
	
}

