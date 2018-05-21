package es.deusto.spq.biblioteca.controller;

/**
 * Clase de creación del controller y añadido de métodos
 * @author Julen
 */
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.client.Client;
import es.deusto.spq.biblioteca.client.gui.MenuPrincipal;
import es.deusto.spq.biblioteca.client.gui.VentanaBusquedaMenu;
import es.deusto.spq.biblioteca.client.gui.VentanaCatalogoLibros;
import es.deusto.spq.biblioteca.client.gui.VentanaComedor;
import es.deusto.spq.biblioteca.client.gui.VentanaHacerReservaMesa;
import es.deusto.spq.biblioteca.client.gui.VentanaHacerReservaSala;
import es.deusto.spq.biblioteca.client.gui.VentanaLibros;
import es.deusto.spq.biblioteca.client.gui.VentanaMensaje;
import es.deusto.spq.biblioteca.client.gui.VentanaMensaje2;
import es.deusto.spq.biblioteca.client.gui.VentanaMensaje3;
import es.deusto.spq.biblioteca.client.gui.VentanaReservaLibro;
import es.deusto.spq.biblioteca.client.gui.VentanaReservaMesa;
import es.deusto.spq.biblioteca.client.gui.VentanaSala;
import es.deusto.spq.biblioteca.client.gui.VentanaValoracion;
import es.deusto.spq.biblioteca.client.gui.VentanaVerReservas;
import es.deusto.spq.biblioteca.client.gui.VentanaVerReservasSala;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaLibro;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public class Controller {
	@SuppressWarnings("unused")
	private Client cl;
	private MenuPrincipal mp;
	private VentanaBusquedaMenu vbm;
	private VentanaCatalogoLibros vcl;
	private VentanaComedor vc;
	private VentanaHacerReservaMesa vhrm;
	private VentanaHacerReservaSala vhrs;
	private VentanaLibros vl;
	private VentanaMensaje vm;
	private VentanaMensaje2 vm2;
	private VentanaMensaje3 vm3;
	private VentanaReservaLibro vrl;
	private VentanaReservaMesa vrm;
	private VentanaSala vs;
	private VentanaValoracion vv;
	private VentanaVerReservas vvr;
	private VentanaVerReservasSala vvrs;
	
	private static final Logger logger = Logger.getLogger(Controller.class);

	/**
	 * Constructor de la clase.
	 * @param args argumentos de la linea de comandos
	 * @throws RemoteException
	 */
	public Controller(String[] args) throws RemoteException {
		cl = new Client();
		cl.setService(args);
		mp = new MenuPrincipal(this);
		mp.ejecutarVentana();
		
	}
	
	/**
	 * Obtiene el servicio del cliente.
	 * @return cl
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * Proporciona el sevicio al cliente.
	 * @param cl
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}
	
	/**
	 * Anyade una nueva sala.
	 * @param id_sala
	 * @param capacidad
	 */
	public void anyadirSala(String id_sala, int capacidad){
		try{
    		cl.getService().anyadirSala(id_sala, capacidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	/**
	 * Anyade una nueva reserva.
	 * @param id_Sala
	 * @param dni_respon
	 * @param fecha
	 * @param hora
	 * @param plazas
	 */
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas){
		try{
    		cl.getService().anyadirReserva(id_Sala, dni_respon, fecha,hora,plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	/** 
	 * Permite consultar la disponibilidad de un Libro.
	 * @param isbn
	 * @return is_reservado --> Si esta disponible true, en caso contrario false.
	 * @throws RemoteException
	 */
	public boolean consultarDiponibilidadLibro(String isbn) throws RemoteException {
		
		boolean is_reservado = false;
		try{
			boolean free =cl.getService().consultarDiponibilidadLibro(isbn);
			if (free == true) {
				is_reservado = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		return is_reservado;
		
	}
	
	/**
	 * Reserva un libro.
	 * @param isbn
	 * @throws RemoteException
	 */
	public void reservarLibro(String isbn) throws RemoteException {
		
		try {
			cl.getService().reservarLibro(isbn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permite ver las reservas que ha realizado una persona.
	 * @param dni
	 * @return lista de reservas
	 */
	public ArrayList<String> verReservas(String dni) {
		ArrayList<String> s = null;
		try{
			
    	 s = cl.getService().verReservas(dni);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return s;
	}
	
	/**
	 * Permite eliminar una reserva.
	 * @param dni_respon
	 * @param fecha
	 * @param hora
	 */
	public void eliminarReserva(String dni_respon,String fecha,String hora){
		try{
			cl.getService().eliminarReserva(dni_respon,fecha,hora);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	
	/**
	 * Permite buscar un libro de todo el catalogo.
	 * @param nombre
	 * @return Libro
	 */
	public Libro buscarLibro(String nombre) {
		Libro lib = null;
		try{
		 lib = cl.getService().buscarLibro(nombre);
		}catch(Exception e){
			e.printStackTrace();
			}
		return lib;
	}
	
	/**
	 * Permite almacenar un libro nuevo en la biblioteca.
	 * @param isbn
	 * @param nombre
	 * @param autor
	 * @param editorial
	 */
	public void almacenarLibro(String isbn, String nombre, String autor, String editorial) {
		try{
		cl.getService().almacenarLibro(isbn, nombre, autor, editorial);
		}catch(Exception e){
			e.printStackTrace();
			}
	}
	
	/**
	 * Permite consultar la disponibilidad de una sala de estudio en la biblioteca.
	 * @param Id_Sala
	 * @param fecha
	 * @param hora
	 * @param personas
	 * @return True or False
	 */
	public boolean consultarDisponibilidad(String Id_Sala, String fecha, String hora, int personas) {
		boolean reservar = false;
		try{
			boolean disponible =cl.getService().consultarDisponibilidad(Id_Sala, fecha, hora, personas);
			if (disponible == true) {
				reservar = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		return reservar;
	
	}
	
	/**
	 * Permite consultar la disponibilidad de un libro dado su nombre.
	 * @param nombre
	 * @return True or False
	 */ 
	public boolean consultarDisponibilidadLibro(String nombre){
		boolean isReservado = false;
		try{
		boolean disponible = cl.getService().consultarDiponibilidadLibro(nombre);
		if (disponible == true) {
			isReservado = true;
		}
	}catch(Exception e){
		e.printStackTrace();
		}
		return isReservado;
	}
	
	/**
	 * Muestra la información de un libro.
	 * @param nombre
	 */
	public void mostrarLibro(String nombre) {
	try{
		cl.getService().mostrarLibro(nombre);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	/**
	 * Función que permite devolver un libro tras su reserva.
	 * @param isbn
	 * @return rl
	 */
	public ReservaLibro DevolverLibro(String isbn) {
		ReservaLibro  rl = null;
		try {
			rl = cl.getService().DevolverLibro(isbn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rl;
	}
	
	/**
	 * Función que permite consultar la disponibilidad de una sala del comedor de la biblioteca.
	 * @param Id_Mesa
	 * @param fecha
	 * @param hora
	 * @param personas
	 * @return True or False
	 */
	public boolean consultarDisponibilidadComedor(String Id_Mesa, String fecha, String hora, int personas){
		boolean disponible = false;
	try{
		boolean disponibleComedor = cl.getService().consultarDisponibilidadComedor(Id_Mesa, fecha, hora, personas);
		if (disponibleComedor == true) {
			disponible = true;
		}
		}catch(Exception e){
			e.printStackTrace();
			}
		return disponible;
	}
	
	/**
	 * Función que permite anyadir una reserva nueva para el comedor.
	 * @param id_Mesa
	 * @param dni_respon
	 * @param fecha
	 * @param hora
	 * @param plazas
	 */
	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas){
		try{
    		cl.getService().anyadirReservaComedor(id_Mesa, dni_respon, fecha,hora,plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	/**
	 * Anyade mesesas al comedor.
	 * @param id_mesa
	 * @param capacidad
	 */
	public void anyadirMesa(String id_mesa, int capacidad) {
		try{
    		cl.getService().anyadirMesa(id_mesa,capacidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	/**
	 * Para ver reservas de una persona en el comedor.
	 * @param dni
	 */
	public void verReservaComedor(String dni){
		try{
		cl.getService().verReservaComedor(dni);
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}

	/**
	 * Para editar una reserva del comedor.
	 * @param dni 
	 * @param fecha
	 * @param hora
	 * @param id_reserva
	 * @param fecha_nueva
	 * @param hora_nueva
	 */
	
	public void editarReservaComedor(String dni,String fecha,String hora,String mesa, String fecha_nueva, String hora_nueva,String mesa_nueva){
		try{
		cl.getService().editarReservaComedor(dni,fecha,hora, mesa, fecha_nueva,hora_nueva, mesa_nueva);
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}

	/**
	 * Permite eliminar una reserva del comedor.
	 * @param dni
	 * @param fecha
	 * @param hora
	 */
	public void eliminarReservaComedor(String dni, String fecha, String hora){
		try{
		cl.getService().eliminarReservaComedor( dni, fecha, hora);
		}catch(Exception e){
    		e.printStackTrace();
    	}
		}


	/**
	 * Permite editar una reserva.
	 * @param dni
	 * @param fecha
	 * @param hora
	 * @param sala
	 * @param fecha_nueva
	 * @param hora_nueva
	 * @param SalaNueva
	 */
	public void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,String SalaNueva){
		try{
		cl.getService().editarReserva(dni, fecha, hora, sala, fecha_nueva, hora_nueva, SalaNueva);
		}catch(Exception e){
    		e.printStackTrace();
    	}
		}
	
	/**
	 * Permite ver el catalogo de libros al completo.
	 * @throws RemoteException
	 */
	public void verCatalogoDeLibros() throws RemoteException {
		//ArrayList<Libro> catalogo = null;
		try{
		//catalogo = cl.getService().getLibros();
		cl.getService().getLibros();
		
		}catch(Exception e){
    		e.printStackTrace();
    	}
		//return catalogo;
	
	}
	
	/**
	 * Permite ver el menu disponible del comedor.
	 * @param fecha
	 * @return
	 * @throws Exception
	 */
	public String verMenu(String fecha) throws Exception {
		String menu = null;
		try{
		menu = cl.getService().verMenu(fecha);
		
		}catch(Exception e){
    		e.printStackTrace();
    	}
		return menu;
	}
	
	public void anyadirValoracion(String id, int val) {
		try {
			cl.getService().anyadirValoracion(id, val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Inicializa un controller nuevo
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Controller c = new Controller(args);
	}

	
}