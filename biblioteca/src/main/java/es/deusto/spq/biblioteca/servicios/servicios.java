package es.deusto.spq.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.data.reserva;
import es.deusto.spq.biblioteca.db.DB_reserva;

public class servicios extends Thread {
	
	public int reservaSala(String id_sala, int plazasReserva){
		List<reserva> listaDeReservas = new ArrayList<>();
		listaDeReservas = DB_reserva.getInstance().getListaEntera();
		//getListaReservas.consultarDisponibilidad(String Id_Sala, String fecha, String hora)
	}

	
	
}
