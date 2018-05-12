package es.deusto.spq.biblioteca.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.spq.biblioteca.client.Client;
import es.deusto.spq.biblioteca.client.gui.VentanaBuscar;
import es.deusto.spq.biblioteca.client.gui.VentanaBusquedaComedor;
import es.deusto.spq.biblioteca.client.gui.VentanaBusquedaMenu;
import es.deusto.spq.biblioteca.client.gui.VentanaCatalogoLibros;
import es.deusto.spq.biblioteca.client.gui.VentanaComedor;
import es.deusto.spq.biblioteca.client.gui.VentanaLogin;
import es.deusto.spq.biblioteca.client.gui.VerReservas;
import es.deusto.spq.biblioteca.data.Libro;
import es.deusto.spq.biblioteca.data.Mesa;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.ReservaMesa;

public class Controller {
	@SuppressWarnings("unused")
	private Client cl;
	private VentanaLogin vl;
	private VentanaBuscar vb;
	private VerReservas vr;
	private VentanaComedor vc;
	private VentanaBusquedaComedor vbc;
	private VentanaBusquedaMenu vbm;
	private VentanaCatalogoLibros vcl;
	
	private static final Logger logger = Logger.getLogger(Controller.class);

	public Controller(String[] args) throws RemoteException {
		cl = new Client();
		cl.setService(args);
		vl = new VentanaLogin(this);
		vl.ejecutarVentana();
		
	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}
	
	public void anyadirSala(String id_sala, int capacidad){
		try{
    		cl.getService().anyadirSala(id_sala, capacidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public void anyadirReserva(String id_Sala, String dni_respon, String fecha, String hora, int plazas){
		try{
    		cl.getService().anyadirReserva(id_Sala, dni_respon, fecha,hora,plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	public String verReservas(String dni) {
		String s = null;
		try{
			
    	 s = cl.getService().verReservas(dni);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return s;
	}
	
	public void eliminarReserva(String id_Sala, String dni_respon){
	try{
		cl.getService().eliminarReserva(id_Sala, dni_respon);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	public Libro buscarLibro(String nombre) {
		Libro lib = null;
		try{
		 lib = cl.getService().buscarLibro(nombre);
		}catch(Exception e){
			e.printStackTrace();
			}
		return lib;
	}
	
	public void anyadirUsuario(Reserva r){
	try{
		cl.getService().anyadirUsuario(r);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public void almacenarLibro(int isbn, String nombre, String autor, String editorial, boolean isReservado) {
		try{
		cl.getService().almacenarLibro(isbn, nombre, autor, editorial, isReservado);
		}catch(Exception e){
			e.printStackTrace();
			}
	}
	
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
	
	public void EliminarParticipante(Reserva r) {
	try{
		cl.getService().EliminarParticipante(r);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public void EliminarLibro(Libro l){
	try{
		cl.getService().EliminarLibro(l);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	
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
	
	public void mostrarLibro(String nombre) {
	try{
		cl.getService().mostrarLibro(nombre);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public Reserva DevolverReserva(String dni, String fecha, String hora) {
		Reserva r = null;
	try{
		r = cl.getService().DevolverReserva(dni, fecha, hora);
	}catch(Exception e){
		e.printStackTrace();
		}
	return r;
	}
	
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
	
	public void anyadirReservaComedor(String id_Mesa, String dni_respon, String fecha, String hora, int plazas){
		try{
    		cl.getService().anyadirReservaComedor(id_Mesa, dni_respon, fecha,hora,plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public void anyadirMesa(String id_mesa, int capacidad) {
		try{
    		cl.getService().anyadirMesa(id_mesa,capacidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public void verReservaComedor(String dni){
		try{
		cl.getService().verReservaComedor(dni);
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}

	public void editarReservaComedor(String id_reserva, String fecha_nueva, String hora_nueva){
		try{
		cl.getService().editarReservaComedor(id_reserva, fecha_nueva, hora_nueva);
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public ReservaMesa DevolverReservaMesa(String dni, String fecha, String hora){
		ReservaMesa r = null;
		try{
		r = cl.getService().DevolverReservaMesa(dni, fecha, hora);
		}catch(Exception e){
    		e.printStackTrace();
    	}
		return r;
	}

	public void eliminarReservaComedor(String dni, String fecha, String hora){
		try{
		cl.getService().eliminarReservaComedor( dni, fecha, hora);
		}catch(Exception e){
    		e.printStackTrace();
    	}
		}


	public void editarReserva(String dni, String fecha, String hora, String sala, String fecha_nueva, String hora_nueva,String SalaNueva){
		try{
		cl.getService().editarReserva(dni, fecha, hora, sala, fecha_nueva, hora_nueva, SalaNueva);
		}catch(Exception e){
    		e.printStackTrace();
    	}
		}
	public ArrayList<Libro> getLibros() throws RemoteException {
		ArrayList<Libro> catalogo = null;
		try{
		catalogo = cl.getService().getLibros();
		
		}catch(Exception e){
    		e.printStackTrace();
    	}
		return catalogo;
	}
	
	public static void main(String[] args) throws Exception {
		Controller c = new Controller(args);

		System.out.println(c.getCl().getService());
		System.out.println("Hola");

		//Almacenamos libros 
		c.getCl().getService().almacenarLibro(1, "Las almas de Brandom", "Cesar Brandom", "S.L.U. Espasa Libros", false);
		c.getCl().getService().almacenarLibro(2, "Festin de cuervos, Cancion de Hielo y fuego IV", "George R.R. Martin", "Gigamesh", false);
		c.getCl().getService().almacenarLibro(3, "FYellowstar: Conviértete en un campeón de League of Legends", "Bora Kim ", "Editorial Planeta S.A", false);	

		//Busqueda de libro por nombre
		c.getCl().getService().buscarLibro("Festin de cuervos, Cancion de Hielo y fuego IV");
		c.getCl().getService().buscarLibro("Paco Jemez: Grandes exitos en el Rayo Vallecano");

		/**
		 * Consultar disponiblididad de un libro
		 * Primero buscamos el libro, luego lo reservamos y despues miramos si ha cambiado su disponibilidad
		 */
		c.getCl().getService().buscarLibro("Las almas de Brandom");
		//c.getCl().getService().reserveBook("Las almas de Brandom");
		c.getCl().getService().consultarDiponibilidadLibro("Las almas de Brandom");
		c.getCl().getService().mostrarLibro("Las almas de Brandom");
		
		//Mostrar catalogo y prueba en logger
		c.getCl().getService().getLibros();
		logger.info("Mostrando catalogo de libros...\n" + c.getCl().getService().getLibros());

		//Anyadir sala
		c.getCl().getService().anyadirSala("S1", 10);
		c.getCl().getService().anyadirSala("S2", 8);
		c.getCl().getService().anyadirReserva("S1", "12345678X", "11/04/18", "21:20", 3);
		c.getCl().getService().anyadirReserva("S2", "23456789Y", "12/05/18", "19:26", 2);
		Boolean disponible = c.getCl().getService().consultarDisponibilidad("S1", "11/04/18", "10:00", 4);
		if (disponible) {
			c.getCl().getService().anyadirReserva("S1", "34567890X", "11/04/18", "10:00", 4);
		}
		c.getCl().getService().verReservas("12345678X");

		c.getCl().getService().editarReserva("12345678X", "11/04/18", "21:20", "S1", "20/12/15", "12:00", "S2");

		c.getCl().getService().verReservas("12345678X");

		//Anyadir mesa
		c.getCl().getService().anyadirMesa("M1", 4);
		c.getCl().getService().anyadirMesa("M2", 6);
		c.getCl().getService().anyadirReservaComedor("M1", "12345678X", "30/04/18", "14:30", 2);
		c.getCl().getService().anyadirReservaComedor("M2", "19182138S", "1/05/18", "15:00", 6);
		Boolean disponibleComedor = c.getCl().getService().consultarDisponibilidadComedor("M1", "2/05/18", "14:00", 3);
		if (disponibleComedor) {
			c.getCl().getService().anyadirReservaComedor("M1", "34567890X", "2/05/18", "14:00", 4);
		}

		c.getCl().getService().verReservaComedor("12345678X");
		c.getCl().getService().eliminarReservaComedor("12345678X", "30/04/18", "14:30");
	}

	
}