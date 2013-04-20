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

	public Respuesta(final int id, boolean correcta, String descripcion,
			Pregunta pregunta) {
		super();
		correcta = correcta;
		descripcion = descripcion;
		pregunta = pregunta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		correcta = correcta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		descripcion = descripcion;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(final Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(final Parcel dest, final int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeByte((byte) (correcta ? 1 : 0));
		dest.writeString(descripcion);
		dest.writeParcelable(pregunta, flags);
	}

	public static final Parcelable.Creator<Respuesta> CREATOR = new Parcelable.Creator<Respuesta>() {
		public Respuesta createFromParcel(Parcel in) {
			return new Respuesta(in);
		}

		public Respuesta[] newArray(int size) {
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
