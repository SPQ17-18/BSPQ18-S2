package es.deusto.spq.biblioteca.db;

import es.deusto.spq.biblioteca.data.*;

import java.util.List;
import java.util.ArrayList;

public class DB_reserva{
	private List<reserva> listaReserva = new ArrayList<>();
	public static DB_reserva instance = new DB_reserva();
	
	public DB_reserva(){
		reserva reserva1 = new reserva("01","S1","10/02/17","09:00",4);
		reserva reserva2 = new reserva("03","S1","10/02/17","12:00",2);
		reserva reserva3 = new reserva("05", "S2","02/12/14","09:00",6);
		listaReserva.add(reserva1);
		listaReserva.add(reserva2);
		listaReserva.add(reserva3);

	}
	public List<reserva> getListaReservas(String codigo, String fecha, String hora, int numero){
		List<reserva> lista =new ArrayList<reserva>();
		for(int i= 0; i<listaReserva.size();i++){
			if(listaReserva.get(i).getId_sala().equals(codigo))
				if(listaReserva.get(i).getFecha().equals(fecha))
					if(listaReserva.get(i).getHora().equals(hora))
						if(listaReserva.get(i).getPlazas()==(numero))
						lista.add(listaReserva.get(i));
		}
		return lista;
	}
	
	public List<reserva> getListaReservas(){
		return listaReserva;
	}
	
	public void setListaReserva(List<reserva> nuevaLista){
		this.listaReserva = nuevaLista;
	}
	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}