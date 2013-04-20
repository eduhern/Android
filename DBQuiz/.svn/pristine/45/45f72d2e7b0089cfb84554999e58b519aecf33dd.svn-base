package com.eduhern.dbquiz.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Respuesta {

	@DatabaseField
	private int id;

	@DatabaseField
	private boolean correcta;

	@DatabaseField
	private String descripcion;

	@DatabaseField(foreign = true)
	private Pregunta pregunta;

	public Respuesta() {
	}

	public Respuesta(int id, boolean correcta, String descripcion,
			Pregunta pregunta) {
		super();
		this.id = id;
		this.correcta = correcta;
		this.descripcion = descripcion;
		this.pregunta = pregunta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
