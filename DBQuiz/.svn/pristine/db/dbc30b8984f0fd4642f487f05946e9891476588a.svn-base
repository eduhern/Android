package com.eduhern.dbquiz.models;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pregunta {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String descripcion;

	@DatabaseField
	private String imagen;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Categoria categoria;

	@DatabaseField
	private int dificultad;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Respuesta> respuestas;

	public List<Respuesta> getRespuestas() {
		return new ArrayList<Respuesta>(respuestas);
	}

	public void setRespuestas(ForeignCollection<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Pregunta() {
	}

	public Pregunta(int id, String descripcion, String imagen,
			Categoria categoria, int dificultad) {
		this.id = id;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.categoria = categoria;
		this.dificultad = dificultad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getIdCategoria() {
		return categoria;
	}

	public void setIdCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

}
