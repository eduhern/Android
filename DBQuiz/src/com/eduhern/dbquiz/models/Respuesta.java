package com.eduhern.dbquiz.models;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Respuesta implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private boolean correcta;

	@DatabaseField
	private String descripcion;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Pregunta pregunta;

	public Respuesta() {
	}

	public Respuesta(final boolean correcta, final String descripcion,
			final Pregunta pregunta) {
		super();
		this.correcta = correcta;
		this.descripcion = descripcion;
		this.pregunta = pregunta;
	}

	public Respuesta(final String descripcion, final Pregunta pregunta) {
		this(false, descripcion, pregunta);
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(final boolean correcta) {
		this.correcta = correcta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(final Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
