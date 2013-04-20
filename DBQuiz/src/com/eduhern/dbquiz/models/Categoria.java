package com.eduhern.dbquiz.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Categoria implements Parcelable {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String descripcion;

	@DatabaseField
	private String fondo;

	@DatabaseField
	private String descripcion_ext;

	public String getDescripcion_ext() {
		return descripcion_ext;
	}

	public void setDescripcion_ext(final String descripcion_ext) {
		this.descripcion_ext = descripcion_ext;
	}

	public String getFondo() {
		return fondo;
	}

	public void setFondo(final String fondo) {
		this.fondo = fondo;
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

	public Categoria(final int id, final String descripcion,
			final String fondo, final String descripcion_ext) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fondo = fondo;
		this.descripcion_ext = descripcion_ext;
	}

	public Categoria() {
	}

	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
		public Categoria createFromParcel(final Parcel in) {
			return new Categoria(in);
		}

		public Categoria[] newArray(final int size) {
			return new Categoria[size];
		}
	};

	public Categoria(final Parcel source) {
		id = source.readInt();
		descripcion = source.readString();
		descripcion_ext = source.readString();
		fondo = source.readString();
	}

	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(id);
		dest.writeString(descripcion);
		dest.writeString(descripcion_ext);
		dest.writeString(fondo);
	}

}
