package com.eduhern.dbquiz.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Categoria {

@DatabaseField(generatedId = true)	
int id;

@DatabaseField
String descripcion;

@DatabaseField
String fondo;

@DatabaseField
String descripcion_ext;

public String getDescripcion_ext() {
	return descripcion_ext;
}
public void setDescripcion_ext(String descripcion_ext) {
	this.descripcion_ext = descripcion_ext;
}
public String getFondo() {
	return fondo;
}
public void setFondo(String fondo) {
	this.fondo = fondo;
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

public Categoria(int id, String descripcion, String fondo, String descripcion_ext) {
	super();
	this.id = id;
	this.descripcion = descripcion;
	this.fondo = fondo;
	this.descripcion_ext = descripcion_ext;
}

public Categoria() {
}
	
}
