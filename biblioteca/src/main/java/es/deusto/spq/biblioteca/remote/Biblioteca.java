//DAO DATA RMI del SERVER
package es.deusto.spq.biblioteca.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import es.deusto.spq.biblioteca.dao.ILibroDAO;
import es.deusto.spq.biblioteca.dao.IMenuDAO;
import es.deusto.spq.biblioteca.dao.IMesaDAO;
import es.deusto.spq.biblioteca.dao.IReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.LibroDAO;
import es.deusto.spq.biblioteca.dao.MenuDAO;
import es.deusto.spq.biblioteca.dao.MesaDAO;
import es.deusto.spq.biblioteca.dao.ReservaComedorDAO;
import es.deusto.spq.biblioteca.dao.IReservaDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.dao.ISalaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Sala;

public class Biblioteca extends UnicastRemoteObject implements IBiblioteca {

	private IReservaDAO reservaDAO;

	private ISalaDAO salaDAO;

	private ILibroDAO libroDAO;
	
	private IMesaDAO mesaDAO;
	
	private IReservaComedorDAO rComedorDAO;
	
	private IMenuDAO menuDAO;

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
		this.menuDAO = new MenuDAO();

	}

	@Override
	public void eliminarReserva(String id_Sala, String dni_respon) throws RemoteException {
		// TODO Auto-generated method stub
//		int cod = 00;
//		cod++;
//		String codg = "";
//		codg = String.valueOf(cod);
//		codg = Integer.toString(cod);
//
//		Reserva r = new Reserva(codg, id_Sala, dni_respon, null, null, 0);
//		reservaDAO.eliminarReserva(r);
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
	public ArrayList<String> verReservas(String dni) throws RemoteException {
		return reservaDAO.verReservas(dni);
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
		String c="RE";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);

		Reserva r = new Reserva(c.concat(codg), id_Sala, dni_respon, fecha, hora, plazas);
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

	public void EliminarParticipante(Reserva r)throws RemoteException {
		String plazas = "";
		plazas = String.valueOf(r.getPlazas());
		reservaDAO.EliminarParticipanteR(r.getId_reserva(), plazas);
	}

	public void EliminarLibro(Libro l)throws RemoteException {
		// int numeroEjemplares = 00;
		// numeroEjemplares
		String Isbn;
		Isbn = String.valueOf(l.getIsbn());
		// String nya="R#";
		// nya=nya.concat(Isbn);
		libroDAO.EliminarLibro(Isbn);

	}
	
	@Override
	public boolean consultarDiponibilidadLibro(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		boolean isReservado = false;
		boolean disponible = libroDAO.consultarDisponibilidadLibro(nombre);
		if (disponible) {
			isReservado = true;
		}
		return isReservado;
	}

	//Esta descartado
	@Override
	public boolean reserveBook(Libro l/*String nombre*/) throws RemoteException {
//		// TODO Auto-generated method stub
//		boolean isReservado = false;
//		boolean reserva = libroDAO.reservarLibro(nombre);
//		if (reserva) {
//			isReservado = true;
//		}
//		libroDAO.reservarLibro(l);
//		
//	return isReservado;
	return false;
		
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
		String c="REC";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);
		ReservaMesa r = new ReservaMesa(c.concat(codg),codg,dni_respon, fecha, hora, plazas);
		rComedorDAO.anyadirReservaComedor(r);
	}

	@Override
	public void anyadirMesa(String id_mesa, int capacidad) throws RemoteException {
		Mesa m = new Mesa(id_mesa, capacidad);
		mesaDAO.anyadirMesa(m);
	}

	@Override
	public ArrayList<String> verReservaComedor(String dni) throws RemoteException {
		return rComedorDAO.verReservaComedor(dni);
		}

	@Override
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva) throws RemoteException {
		rComedorDAO.editarReservaComedor(id_reserva, fecha_nueva, hora_nueva);
		}

	@Override
	public ReservaMesa DevolverReservaMesa(String dni, String fecha, String hora) throws RemoteException {
		ReservaMesa r = rComedorDAO.devolverReservaComedor(dni, fecha, hora);
		return r;
	}

	@Override
	public void eliminarReservaComedor(String dni, String fecha, String hora) throws Exception {
		// TODO Auto-generated method stub
		ReservaMesa r = DevolverReservaMesa(dni, fecha, hora);
		rComedorDAO.eliminarReservaComedor(r);
	}


	@Override
	public void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,
			String SalaNueva) throws RemoteException {
		reservaDAO.editarReserva(dni, fecha, hora, sala, fecha_nueva, hora_nueva, SalaNueva);
		
	}

	@Override
	public ArrayList<Libro> getLibros() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Libro> catalogo = libroDAO.getLibros();
		return catalogo;

	}

	@Override
	public void anyadirMenu(String fecha, String plato1, String plato2, String postre)
			throws Exception {
		// TODO Auto-generated method stub
		int cod = 00;
		cod++;
		String codg = "";
		String c="MEN";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);

		Menu m = new Menu(c.concat(codg), fecha, plato1, plato2, postre);
		menuDAO.anyadirMenu(m);
		
	}

	@Override
	public void anyadirValoracion(String id_menu, int valoracion) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.anyadirValoracion(id_menu, valoracion);
		
	}

	@Override
	public void verMenu(String fecha) throws Exception {
		menuDAO.verMenu(fecha);
	}	
}

	
