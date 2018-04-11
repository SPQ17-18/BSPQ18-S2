package es.deusto.spq.biblioteca.data;

public class reserva {
	
	//Julen tendras que modificar cosas en base al id de reserva no lo se
	String id_reserva;
	String id_sala;
	String dni_respon;
	String fecha;
	String hora;
	int plazas;

	public reserva(String id_reserva, String id_sala, String dni_respon, String fecha, String hora, int plazas) {
		super();
		this.id_reserva = id_reserva;
		this.id_sala = id_sala;
		this.dni_respon = dni_respon;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
	}
	
	public String getDni_respon() {
		return dni_respon;
	}

	public void setDni_respon(String dni_respon) {
		this.dni_respon = dni_respon;
	}

	public String getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(String id_reserva) {
		this.id_reserva = id_reserva;
	}

	public String getId_sala() {
		return id_sala;
	}

	public void setId_sala(String id_sala) {
		this.id_sala = id_sala;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	public void añadirNuevosUsuarios(int CantidadNuevosUsuarios) {
		this.plazas = this.plazas + CantidadNuevosUsuarios;
	}

	//borrar
}