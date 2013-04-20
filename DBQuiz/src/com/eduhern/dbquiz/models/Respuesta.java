package com.eduhern.dbquiz.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Respuesta implements Parcelable {

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

	public Respuesta(final int id, final boolean correcta,
			final String descripcion, final Pregunta pregunta) {
		super();
		this.correcta = correcta;
		this.descripcion = descripcion;
		this.pregunta = pregunta;
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

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(id);
		dest.writeByte((byte) (correcta ? 1 : 0));
		dest.writeString(descripcion);
		dest.writeParcelable(pregunta, flags);
	}

	public static final Parcelable.Creator<Respuesta> CREATOR = new Parcelable.Creator<Respuesta>() {
		public Respuesta createFromParcel(final Parcel in) {
			return new Respuesta(in);
		}

		public Respuesta[] newArray(final int size) {
			return new Respuesta[size];
		}
	};

	public Respuesta(final Parcel source) {
		id = source.readInt();
		correcta = source.readByte() == 1 ? true : false;
		descripcion = source.readString();
		pregunta = source.readParcelable(Pregunta.class.getClassLoader());
	}

}
