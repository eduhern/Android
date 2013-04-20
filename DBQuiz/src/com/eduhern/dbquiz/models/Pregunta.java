package com.eduhern.dbquiz.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pregunta implements Parcelable {

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
		respuestas = respuestas;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		imagen = imagen;
	}

	public Pregunta() {
	}

	public Pregunta(int id, String descripcion, String imagen,
			Categoria categoria, int dificultad) {
		id = id;
		descripcion = descripcion;
		imagen = imagen;
		categoria = categoria;
		dificultad = dificultad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		descripcion = descripcion;
	}

	public Categoria getIdCategoria() {
		return categoria;
	}

	public void setIdCategoria(Categoria categoria) {
		categoria = categoria;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		dificultad = dificultad;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(descripcion);
		dest.writeString(imagen);
		dest.writeParcelable(categoria, flags);
		dest.writeInt(dificultad);
	}

	public static final Parcelable.Creator<Pregunta> CREATOR = new Parcelable.Creator<Pregunta>() {
		public Pregunta createFromParcel(Parcel in) {
			return new Pregunta(in);
		}

		public Pregunta[] newArray(int size) {
			return new Pregunta[size];
		}
	};

	public Pregunta(Parcel source) {
		id = source.readInt();
		descripcion = source.readString();
		imagen = source.readString();
		categoria = source.readParcelable(Categoria.class.getClassLoader());
		dificultad = source.readInt();
	}
}
