package es.deusto.spq.biblioteca.db;

import es.deusto.spq.biblioteca.data.*;

import java.util.List;
import java.util.ArrayList;

public class DB_reserva{
	private List<Reserva> listaReserva = new ArrayList<>();
	public static DB_reserva instance = new DB_reserva();
	
	public DB_reserva(){
		Reserva reserva1 = new Reserva("01","S1","1234567X","10/02/17","09:00",4);
		Reserva reserva2 = new Reserva("03","S1","23456789Y","10/02/17","12:00",2);
		Reserva reserva3 = new Reserva("05", "S2","1234567X","02/12/14","09:00",6);
		listaReserva.add(reserva1);
		listaReserva.add(reserva2);
		listaReserva.add(reserva3);

	}
	public List<Reserva> getListaReservas(String codigo, String fecha, String hora, int numero){
		List<Reserva> lista =new ArrayList<Reserva>();
		for(int i= 0; i<listaReserva.size();i++){
			if(listaReserva.get(i).getId_sala().equals(codigo))
				if(listaReserva.get(i).getFecha().equals(fecha))
					if(listaReserva.get(i).getHora().equals(hora))
						if(listaReserva.get(i).getPlazas()==(numero))
						lista.add(listaReserva.get(i));
		}
		return lista;
	}
	
	public List<Reserva> getListaReservas(){
		return listaReserva;
	}
	
	public void setListaReserva(List<Reserva> nuevaLista){
		this.listaReserva = nuevaLista;
	}
	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}