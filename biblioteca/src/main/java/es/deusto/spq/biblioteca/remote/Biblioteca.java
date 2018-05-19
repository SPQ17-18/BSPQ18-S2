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
import es.deusto.spq.biblioteca.dao.IReservaLibroDAO;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.dao.ReservaLibroDAO;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Menu;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaLibro;
import es.deusto.spq.biblioteca.data.ReservaMesa;
import es.deusto.spq.biblioteca.dao.ISalaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;
import es.deusto.spq.biblioteca.data.Sala;

public class Biblioteca extends UnicastRemoteObject implements IBiblioteca {

	private static final long serialVersionUID = 1L;

	private IReservaDAO reservaDAO;

	private ISalaDAO salaDAO;

	private ILibroDAO libroDAO;
	
	private IMesaDAO mesaDAO;
	
	private IReservaComedorDAO rComedorDAO;
	
	private IMenuDAO menuDAO;
	
	private IReservaLibroDAO reservaLibroDAO;

	public Biblioteca(IReservaDAO reservaDAO ) throws RemoteException {
		super();
		this.reservaDAO = reservaDAO;
	}
	
	public Biblioteca(ILibroDAO libroDAO ) throws RemoteException {
		super();
		this.libroDAO = libroDAO;
	}
	
	public Biblioteca(IReservaLibroDAO reservaLibroDAO) throws RemoteException {
		super();
		this.reservaLibroDAO = reservaLibroDAO;
	}

	public Biblioteca(String serverIP, int ServerPort) throws RemoteException {
		super();
		this.reservaDAO = new ReservaDAO();
		this.salaDAO = new SalaDAO();
		this.libroDAO = new LibroDAO();
		this.mesaDAO = new MesaDAO();
		this.rComedorDAO = new ReservaComedorDAO();
		this.menuDAO = new MenuDAO();
		this.reservaLibroDAO = new ReservaLibroDAO();
		

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
		Libro lib = libroDAO.getLibro(nombre);

		return lib;
	}

	@Override
	public ArrayList<String> verReservaComedor(String dni) throws RemoteException {
		ArrayList<String> s = rComedorDAO.verReservaComedor(dni);
		return s;
		}

	@Override
	public ArrayList<String> verReservas(String dni) throws RemoteException {
		ArrayList<String> s = reservaDAO.verReservas(dni);
		return s;
	}

	@Override
	public void almacenarLibro(String isbn, String nombre, String autor, String editorial) {
		// TODO Auto-generated method stub
		Libro l = new Libro(isbn, nombre, autor, editorial);
		libroDAO.almacenarLibro(l);

	}
	
	@Override
	public void reservarLibro(String isbn) throws RemoteException {
		// TODO Auto-generated method stub
		ReservaLibro rl = new ReservaLibro(isbn);
		reservaLibroDAO.reservarLibro(rl);

		
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


	public void EliminarLibro(Libro l)throws RemoteException {
	//Que lo arregle quien lo hizo pls
		// int numeroEjemplares = 00;
		// numeroEjemplares
		String isbn;
		isbn = l.getIsbn();  
		// String nya="R#";
		// nya=nya.concat(Isbn);
		libroDAO.EliminarLibro(isbn);

	}
	
	@Override
	public boolean consultarDiponibilidadLibro(String isbn) throws RemoteException {
		// TODO Auto-generated method stub
		boolean isReservado = false;
		boolean free = reservaLibroDAO.consultarDisponibilidadLibro(isbn);
		if (free) {
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
	public ReservaLibro DevolverLibro (String isbn) throws RemoteException {
		ReservaLibro rl = null;
		rl = reservaLibroDAO.devolverLibro(isbn);
		return rl;
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
	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva) throws RemoteException {
		rComedorDAO.editarReservaComedor(id_reserva, fecha_nueva, hora_nueva);
	}

	@Override
	public void eliminarReservaComedor(String dni, String fecha, String hora) throws Exception {
		// TODO Auto-generated method stub
//		ReservaMesa r = DevolverReservaMesa(dni, fecha, hora);
//		rComedorDAO.eliminarReservaComedor(r);
	}


	@Override
	public void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva, String SalaNueva) throws RemoteException {
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
	public String verMenu(String fecha) throws Exception {
		String menu = null;
		menu = menuDAO.verMenu(fecha);
		return menu;
	}

	
}

	
