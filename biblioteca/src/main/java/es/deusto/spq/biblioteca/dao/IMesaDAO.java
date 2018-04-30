package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.Mesa;

public interface IMesaDAO {

	public void anyadirMesa(Mesa m);
	public boolean consultarPlazasComedor(String Id_Mesa, int personas);
}
