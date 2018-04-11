package es.deusto.spq.biblioteca.dao;

import es.deusto.spq.biblioteca.data.reserva;

public interface IReservasDAO {

	
	// Julen, si cambias algun atributo en las reservas cambialo aqui tambien
	public void almacenarReserva(reserva res);//en la BD
	public reserva verReserva (String id_reserva); 
	public void editarReserva(String id_reserva,reserva res_nueva);//(Provisionalmente) se pasará solo el id de la reserva a modificar y una reserva con los datos corregidos,llamando asi a ver reserva y mostrando que datos son editables
	//Esta parte va a tener los metodos de ver reserva y editar reserva
	
}
