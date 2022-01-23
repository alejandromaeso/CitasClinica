package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

	public static final String FORMATO_FECHA_HORA = ("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHora;
	
	public Paciente paciente;

	//Constructor con parámetros
	
	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		super();
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}

	//Constructor copia
	
	public Cita(Cita copiaCita)
	{
		if (copiaCita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setFechaHora(copiaCita.getFechaHora());
			setPaciente(copiaCita.getPaciente());
		}
	}
	
	//Getters & Setters
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		LocalDateTime fechaActual = LocalDateTime.now();
		if(fechaHora.compareTo(fechaActual) < 0) {
			throw new IllegalArgumentException("ERROR: No puedes introducir una fecha anterior a la actual.");
		}
		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}

	//Equals y hashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
	}

	//ToString
	
	@Override
	public String toString() {
		return paciente.toString() + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)) + "";
	}
}