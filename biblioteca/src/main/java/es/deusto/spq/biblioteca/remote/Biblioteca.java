//DAO DATA RMI del SERVER
package es.deusto.spq.biblioteca.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.IMesaDAO;
import es.deusto.spq.biblioteca.dao.IReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.dao.MesaDAO;
import es.deusto.spq.biblioteca.dao.ReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.IReservaDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.dao.ISalaDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;

public class Biblioteca extends UnicastRemoteObject implements IBiblioteca {

	private IReservaDAO reservaDAO;

	private ISalaDAO salaDAO;

	private ILibroDAO libroDAO;
	
	private IMesaDAO mesaDAO;
	
	private IReservaComedorDAO rComedorDAO;

	public Biblioteca(IReservaDAO reservaDAO ) throws RemoteException {
		super();
		this.reservaDAO = reservaDAO;
	}
	
	public Biblioteca(ILibroDAO libroDAO ) throws RemoteException {
		super();
		this.libroDAO = libroDAO;
	}

	public Biblioteca(String serverIP, int ServerPort) throws RemoteException {
		super();
		this.reservaDAO = new ReservaDAO();
		this.salaDAO = new SalaDAO();
		this.libroDAO = new LibroDAO();
		this.mesaDAO = new MesaDAO();
		this.rComedorDAO = new ReservaComedorDAO();

	}

	@Override
	public void eliminarReserva(String id_Sala, String dni_respon) throws RemoteException {
		// TODO Auto-generated method stub
		int cod = 00;
		cod++;
		String codg = "";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);

		Reserva r = new Reserva(codg, id_Sala, dni_respon, null, null, 0);
		reservaDAO.eliminarReserva(r);
	}

	@Override
	public Libro buscarLibro(String nombre) {
		// TODO Auto-generated method stub
		// libro l = new libro(1, "Las almas de Brandoom", "Cesar Brandom", "S.L.U.
		// Espasa Libros", false);
		// libroDAO.almacenarLibro(l); //No esta almacenando el libro
		// libroDAO.buscarLibro("Las almas de Brandoom");
		Libro lib = libroDAO.getLibro(nombre);

		return lib;
	}

	@Override
	public void verReservas(String dni) throws RemoteException {
		// TODO Auto-generated method stub
		reservaDAO.verReservas(dni);
	}

	@Override
	public void editarReserva(Reserva r, String fecha_nueva, String hora_nueva) throws RemoteException {
		// TODO Auto-generated method stub
		reservaDAO.editarReserva(r, fecha_nueva, hora_nueva);
	}

	@Override
	public void anyadirUsuario(Reserva r) throws RemoteException {
		// TODO Auto-generated method stub
		reservaDAO.anyadirUsuario(r);
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
		int cod = 00;
		cod++;
		String codg = "";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);

		Reserva r = new Reserva(codg, id_Sala, dni_respon, fecha, hora, plazas);
		reservaDAO.anyadirReserva(r);
	}

	@Override
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora, int personas) {
		// TODO Auto-generated method stub
		boolean disponible = false;
		boolean libre = reservaDAO.consultarDisponibilidad(Id_Sala, fecha, hora);
		boolean plazas = salaDAO.consultarPlazas(Id_Sala, personas);
		if (plazas && libre) {
			disponible = true;
		}
		return disponible;
	}

	@Override
	public void anyadirSala(String id_sala, int capacidad) throws RemoteException {
		Sala s = new Sala(id_sala, capacidad);
		salaDAO.anyadirSala(s);
	}

	public void EliminarParticipante(Reserva r) {
		String plazas = "";
		plazas = String.valueOf(r.getPlazas());
		reservaDAO.EliminarParticipanteR(r.getId_reserva(), plazas);
	}

	public void EliminarLibro(Libro l) {
		// int numeroEjemplares = 00;
		// numeroEjemplares
		String Isbn;
		Isbn = String.valueOf(l.getIsbn());
		// String nya="R#";
		// nya=nya.concat(Isbn);
		libroDAO.EliminarLibro(Isbn);

	}

	@Override
	public boolean reserveBook(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		boolean isReservado = false;
		boolean reserva = libroDAO.reservarLibro(nombre);
		if (reserva) {
			isReservado = true;
		}
		
	return isReservado;
		
	}

	@Override
	public void mostrarLibro(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		libroDAO.verLibro(nombre);
	}

	
	@Override
	public Reserva DevolverReserva(String dni, String fecha, String hora) throws RemoteException {
		Reserva r = null;
		r = reservaDAO.devolverReserva(dni, fecha, hora);
		return r;
	}

	@Override
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora, int personas)
			throws RemoteException {
		boolean disponible = false;
		boolean libre = rComedorDAO.consultarDisponibilidadComedor(Id_Mesa, fecha, hora);
		boolean plazas = mesaDAO.consultarPlazasComedor(Id_Mesa, personas);
		if (plazas && libre) {
			disponible = true;
		}
		return disponible;
	}

	@Override
	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas)
			throws RemoteException {
		int cod = 00;
		cod++;
		String codg = "";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);

<<<<<<< HEAD
		ReservaMesa r = new ReservaMesa(codg, id_Mesa, dni_respon, fecha, hora, plazas);
=======
		ReservaMesa r = new ReservaMesa(id_Mesa,codg,dni_respon, fecha, hora, plazas);
>>>>>>> remotes/origin/master
		rComedorDAO.anyadirReservaComedor(r);
	}

	@Override
	public void anyadirMesa(String id_mesa, int capacidad) throws RemoteException {
		Mesa m = new Mesa(id_mesa, capacidad);
		mesaDAO.anyadirMesa(m);
	}

	@Override
	public void verReservaComedor(String dni) throws RemoteException {
		rComedorDAO.verReservaComedor(dni);
		}

	@Override
	public void eliminarReservaComedor(ReservaMesa m) throws RemoteException {
		rComedorDAO.eliminarReservaComedor(m);
	}

	@Override
	public void editarReservaComedor(ReservaMesa m, String fecha_nueva, String hora_nueva) throws RemoteException {
		rComedorDAO.editarReservaComedor(m, fecha_nueva, hora_nueva);
		}

	
}

	
