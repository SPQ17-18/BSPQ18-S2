package es.deusto.spq.biblioteca.data;

public class reserva {
	String id_sala;
	String fecha;
	String hora;
	int plazas;

	public reserva(String id_sala, String fecha, String hora, int plazas){
		super();
		this.id_sala = id_sala;
		this.fecha = fecha;
		this.hora = hora;
		this.plazas = plazas;
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
	
}