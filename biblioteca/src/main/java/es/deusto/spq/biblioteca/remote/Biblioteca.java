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
	
	public Biblioteca(IReservaComedorDAO reservaComedorDAO ) throws RemoteException {
		super();
		this.rComedorDAO = reservaComedorDAO;
	}
	
	public Biblioteca(IReservaLibroDAO reservaLibroDAO) throws RemoteException {
		super();
		this.reservaLibroDAO = reservaLibroDAO;
	}

	/**
	 * Constructor de Biblioteca.
	 * @throws RemoteException Lanza una excepcion en caso de error.
	 */
	public Biblioteca() throws RemoteException {
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
	public Reserva DevolverReserva(String dni, String fecha, String hora) throws RemoteException {
		Reserva r = null;
		r = reservaDAO.devolverReserva(dni, fecha, hora);
		return r;
	
	}

	

	
	/**
	 * Metodo que elimina una reserva.
	 * @param id_sala Identificador de la sala.
	 * @param dni_respon DNI del responsable del a reserva.
	 */
	public void eliminarReserva(String dni_respon,String fecha,String hora) throws RemoteException {
		reservaDAO.eliminarReserva(DevolverReserva(dni_respon, fecha, hora));
		}

	/**
	 * Metodo que busca un libro.
	 * @param nombre Nombre del libro.
	 */
	@Override
	public Libro buscarLibro(String nombre) {
		// TODO Auto-generated method stub
		Libro lib = libroDAO.getLibro(nombre);

		return lib;
	}

	/**
	 * Metodo que permite ver todas las reservas de comedor una persona.
	 * @param dni DNI de la persona.
	 */
	@Override
	public ArrayList<String> verReservaComedor(String dni) throws RemoteException {
		ArrayList<String> s = rComedorDAO.verReservaComedor(dni);
		return s;
	}

	/**
	 * Metodo que permite ver las reservas de una persona.
	 * @param dni DNI de la persona.
	 */
	@Override
	public ArrayList<String> verReservas(String dni) throws RemoteException {
		ArrayList<String> s = reservaDAO.verReservas(dni);
		return s;
	}

	/**
	 * Metodo que permite almacenar un libro nuevo.
	 * @param isbn Identificador del libro.
	 * @param nombre Nombre del libro.
	 * @param autor Escritor del libro.
	 * @param editorial Distribuidora del libro (Editorial).
	 */
	@Override
	public void almacenarLibro(String isbn, String nombre, String autor, String editorial) {
		// TODO Auto-generated method stub
		Libro l = new Libro(isbn, nombre, autor, editorial);
		libroDAO.almacenarLibro(l);

	}
	
	/**
	 * Metodo que permite reservar un libro.
	 * @param isbn Identificador del libro.
	 */
	@Override
	public void reservarLibro(String isbn) throws RemoteException {
		// TODO Auto-generated method stub
		ReservaLibro rl = new ReservaLibro(isbn);
		reservaLibroDAO.reservarLibro(rl);

		
	}

	/**
	 * Metodo que permite anydir una reserva nueva.
	 * @param id_sala Identificador de la sala.
	 * @param dni_respon DNI del responsable de la reserva.
	 * @param fecha Fecha de la reserva.
	 * @param hora Hora de la reserva.
	 * @param plazas Numero de personas para el que es la reserva.
	 */
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

	/**
	 * Metodo que permite consultar la disponibilidad de una sala.
	 * @param id_sala Identificador de la sala.
	 * @param fecha Fecha.
	 * @param hora Hora.
	 * @param personas Numero de personas.
	 */
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

	/**
	 * Metodo para anyadir una sala nueva.
	 * @param id_sala Identificador de la sala.
	 * @param capacidad Capacidad de la sala.
	 */
	@Override
	public void anyadirSala(String id_sala, int capacidad) throws RemoteException {
		Sala s = new Sala(id_sala, capacidad);
		salaDAO.anyadirSala(s);
	}


	/**
	 * Metodo para eliminar un libro de la BD.
	 * @param Libro Libro.
	 */
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
	
	/**
	 * Metodo que permite consultar la disponibilidad de un libro.
	 * @param isbn Identificador del libro.
	 */
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

	/**
	 * Metodo que muestra un libro.
	 * @param nombre Nombre del libro.
	 */
	@Override
	public void mostrarLibro(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		libroDAO.verLibro(nombre);
	}
	
	/**
	 * Metodo para devolver un libro reservado.
	 * @param isbn Identificador del libro.
	 */
	@Override
	public ReservaLibro DevolverLibro (String isbn) throws RemoteException {
		ReservaLibro rl = null;
		rl = reservaLibroDAO.devolverLibro(isbn);
		return rl;
	}

	/**
	 * Metodo para consultar la disponibilidad de una mesa en el comedor.
	 * @param Id_Mesa Identificador de para la mesa que se esta comprobando la disponibilidad.
	 * @param fecha Fecha.
	 * @param hora Hora.
	 * @param personas Numero de personas.
	 */
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

	/**
	 * Metodo para anyadir una reserva en el comedor.
	 * @param Id_Mesa Identificador de para la mesa que se hace la reserva.
	 * @param dni_repon DNI del responsable de la reserva.
	 * @param fecha Fecha.
	 * @param hora Hora.
	 * @param personas Numero de personas.
	 */
	@Override
	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas)
			throws RemoteException {
		int cod = 00;
		cod++;
		String codg = "";
		String c="REC";
		codg = String.valueOf(cod);
		codg = Integer.toString(cod);
		ReservaMesa r = new ReservaMesa(id_Mesa,c.concat(codg),dni_respon, fecha, hora, plazas);
		rComedorDAO.anyadirReservaComedor(r);
	}

	/**
	 * Metodo para anyadir una mesa nueva.
	 * @param id_mesa Identificador de la mesa nueva.
	 * @param capacidad Numero de personas que entran en la mesa.
	 */
	@Override
	public void anyadirMesa(String id_mesa, int capacidad) throws RemoteException {
		Mesa m = new Mesa(id_mesa, capacidad);
		mesaDAO.anyadirMesa(m);
	}

	/**
	 * Funcion que permite editar una reserva del comedor.
	 * @param id_reserva Identificador de la reserva.
	 * @param fecha_nueva Nueva fecha de reserva.
	 * @param hora_nueva Nueva hora de reserva.
	 */
	@Override
	public void editarReservaComedor(String dni,String fecha,String hora,String mesa, String fecha_nueva, String hora_nueva,String mesa_nueva) throws RemoteException {
		rComedorDAO.editarReservaComedor(dni, fecha, hora, mesa,  fecha_nueva,  hora_nueva, mesa_nueva);
	}
	
	@Override
	public ReservaMesa devolverReservaComedor(String dni, String fecha, String hora) throws RemoteException {
		ReservaMesa r = rComedorDAO.devolverReservaComedor(dni, fecha, hora);
		return r;
	}

	/**
	 * Metodo para eliminar una reserva del comedor
	 * @param dni DNI.
	 * @param fecha Fecha.
	 * @param hora Hora.
	 */
	@Override
	public void eliminarReservaComedor(String dni, String fecha, String hora) throws Exception {
		// TODO Auto-generated method stub
		ReservaMesa r = devolverReservaComedor(dni, fecha, hora);
		rComedorDAO.eliminarReservaComedor(r);
	}


	/**
	 * Metodo para editar una resrva
	 * @param dni DNI de la persona que hizo la reserva.
	 * @param fecha Fecha actual de la reserva.
	 * @param hora Hora actual de la reserva.
	 * @param sala Sala actual reservada.
	 * @param fecha_nueva Nueva fecha de reserva.
	 * @param hora_nueva Nueva hora de reserva.
	 * @param SalaNueva Sala nueva que se va a reservar. 
	 */
	@Override
	public void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva, String SalaNueva) throws RemoteException {
		reservaDAO.editarReserva(dni, fecha, hora, sala, fecha_nueva, hora_nueva, SalaNueva);
		
	}

	/**
	 * Metodo que devuelve el catalogo de libros.
	 */
	@Override
	public ArrayList<String> getLibros() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<String> catalogo = libroDAO.getLibros();
		return catalogo;

	}

	/**
	 * Metodo para anyadir un menu al comedor.
	 * @param fecha Fecha para la que es el menu.
	 * @param plato1 Primer plato del menu.
	 * @param plato2 Segundo plato del menu.
	 * @param postre Postre del menu.
	 */
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

	/**
	 * Funcion para anyadir valoracion a un menu.
	 * @param id_menu Identificdor del menu a valorar.
	 * @param valoracion Puntuacion que se le da al menu.
	 */
	@Override
	public void anyadirValoracion(String id_menu, int valoracion) throws Exception {
		// TODO Auto-generated method stub
		menuDAO.anyadirValoracion(id_menu, valoracion);
		
	}

	/**
	 * Funcion que permite ver el menu de una fecha.
	 * @param fecha Fecha para ver menu.
	 */
	@Override
	public String verMenu(String fecha) throws Exception {
		String menu = null;
		menu = menuDAO.verMenu(fecha);
		return menu;
	}

	
}

	
