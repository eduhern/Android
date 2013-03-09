package com.eduhern.dbquiz.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String descripcion;

	@DatabaseField
	private String imagen;

	@ForeignCollectionField(eager = true)
	private ForeignCollection<Respuesta> respuestas;

	public Pregunta() {
	}

	public Pregunta(final String descripcion, final String imagen)
			throws SQLException {
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(final String imagen) {
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Respuesta> getRespuestas() {
		return new ArrayList<Respuesta>(respuestas);
	}

	public void setRespuestas(final ForeignCollection<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}
