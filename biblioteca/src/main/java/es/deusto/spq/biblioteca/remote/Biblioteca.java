package es.deusto.spq.biblioteca.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.dao.IreservaDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;

public class Biblioteca extends UnicastRemoteObject implements IBiblioteca {
	
	private IreservaDAO reservaDAO;
	private ILibroDAO libroDAO;
	
	public Biblioteca(String serverIP, int ServerPort) throws RemoteException {
		super();
		this.reservaDAO = new ReservaDAO();
		this.libroDAO = new LibroDAO();
			
	}

	@Override
	public Libro buscarLibro(String nombre) {
		// TODO Auto-generated method stub
//		libro l = new libro(1, "Las almas de Brandoom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
//		libroDAO.almacenarLibro(l); //No esta almacenando el libro
//		libroDAO.buscarLibro("Las almas de Brandoom");
		Libro lib = libroDAO.getLibro(nombre);
		
		return lib;
	}

	@Override
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado) {
		// TODO Auto-generated method stub
		Libro l = new Libro(isbn, nombre, autor, editorial, isReservado);
		libroDAO.almacenarLibro(l);

	}

	@Override
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas)
			throws RemoteException {
		// TODO Auto-generated method stub
		int cod=00;
		cod++;
		String codg = "";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);
		
		Reserva r = new Reserva(codg,id_Sala, dni_respon, fecha, hora, plazas);
		reservaDAO.anyadirReserva(r);
	}

	@Override
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora) {
		// TODO Auto-generated method stub
		boolean disponible = reservaDAO.consultarDisponibilidad(Id_Sala, fecha, hora);
		
		return disponible;
	}	
	

}
//DAO DATA RMI del SERVER
