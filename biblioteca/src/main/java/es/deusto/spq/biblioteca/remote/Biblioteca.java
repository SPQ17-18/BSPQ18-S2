package es.deusto.spq.biblioteca.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.libroDAO;
import es.deusto.spq.biblioteca.dao.IreservaDAO;
import es.deusto.spq.biblioteca.dao.reservaDAO;
import es.deusto.spq.biblioteca.data.libro;

public class Biblioteca extends UnicastRemoteObject implements IBiblioteca {
	
	private IreservaDAO reservaDAO;
	private ILibroDAO libroDAO;
	
	public Biblioteca(String serverIP, int ServerPort) throws RemoteException {
		super();
		this.reservaDAO = new reservaDAO();
		this.libroDAO = new libroDAO();
			
	}

	@Override
	public libro buscarLibro(String nombre) {
		// TODO Auto-generated method stub
//		libro l = new libro(1, "Las almas de Brandoom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
//		libroDAO.almacenarLibro(l); //No esta almacenando el libro
//		libroDAO.buscarLibro("Las almas de Brandoom");
		libro lib = libroDAO.getLibro(nombre);
		
		return lib;
	}

	@Override
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado) {
		// TODO Auto-generated method stub
		libro l = new libro(isbn, nombre, autor, editorial, isReservado);
		libroDAO.almacenarLibro(l);

	}	
	

}
//DAO DATA RMI del SERVER
