package com.eduhern.dbquiz.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.models.Pregunta;
import com.eduhern.dbquiz.models.Respuesta;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "helloAndroid.db";
	private static final int DATABASE_VERSION = 23;

	private RuntimeExceptionDao<Pregunta, Integer> preguntaDAO = null;
	private RuntimeExceptionDao<Respuesta, Integer> respuestaDAO = null;

	public DatabaseHelper(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	@Override
	public void onCreate(final SQLiteDatabase db,
			final ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Pregunta.class);
			TableUtils.createTable(connectionSource, Respuesta.class);
			poblateDatabase();
		} catch (final SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db,
			final ConnectionSource connectionSource, final int oldVersion,
			final int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Pregunta.class, true);
			TableUtils.dropTable(connectionSource, Respuesta.class, true);
			onCreate(db, connectionSource);
		} catch (final java.sql.SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	private void poblateDatabase() throws SQLException {
		final Pregunta pregunta1 = new Pregunta(
				"¿Cuál es el nombre de estos tres famosos villanos?", "imagen1");
		add(pregunta1);
		add(new Respuesta(true, "Pilaf, Mai y Suu", pregunta1));
		add(new Respuesta("Pilaf, Mai y Ginew", pregunta1));
		add(new Respuesta("Vegeta, Mai y Zarbon", pregunta1));
		add(new Respuesta("Dodoria, Kiwi y Zarbon", pregunta1));
		final Pregunta pregunta2 = new Pregunta(
				"¿Cuál es el nombre de este famoso personaje?", "imagen2");
		add(pregunta2);
		final Pregunta pregunta3 = new Pregunta("¿Cómo se llama el personaje?",
				"imagen3");
		add(pregunta3);
		final Pregunta pregunta4 = new Pregunta(
				"¿Cuál es el nombre de este androide de base humana creado por el Dr. Gero?",
				"imagen4");
		add(pregunta4);
		final Pregunta pregunta5 = new Pregunta(
				"Fue el segundo maestro de Goku, ¿a qué nombre responde?",
				"imagen5");
		add(pregunta5);
		final Pregunta pregunta6 = new Pregunta(
				"¿En qué año se empezó a emitir la serie de animación que continuaba de forma canónica el manga de Toriyama?",
				"imagen6");
		add(pregunta6);
		final Pregunta pregunta7 = new Pregunta(
				"¿Cuál es el título del primer tankobon del manga de Dragon Ball?",
				"imagen7");
		add(pregunta7);
		final Pregunta pregunta8 = new Pregunta(
				"¿En qué año se estrenó el film de Dragon Ball 'Saikyo e no michi'?",
				"imagen8");
		add(pregunta8);
		final Pregunta pregunta9 = new Pregunta(
				"¿Cómo se llama el hermano de Freezer?", "imagen9");
		add(pregunta9);
		final Pregunta pregunta10 = new Pregunta(
				"¿Cuál es el nombre de estos dos personajes del film de 2013 Dragon Ball Z 'Battle of Gods'?",
				"imagen10");
		add(pregunta10);
		final Pregunta pregunta11 = new Pregunta(
				"¿Para qué sirve el objeto de la fotografía?", "imagen11");
		add(pregunta11);
		final Pregunta pregunta12 = new Pregunta(
				"¿Para qué sirve el objeto de la fotografía?", "imagen12");
		add(pregunta12);
		final Pregunta pregunta13 = new Pregunta(
				"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
				"imagen13");
		add(pregunta13);
		final Pregunta pregunta14 = new Pregunta(
				"¿Cuál era el principal poder del personaje de la imagen?",
				"imagen14");
		add(pregunta14);
		final Pregunta pregunta15 = new Pregunta(
				"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
				"imagen15");
		add(pregunta15);
		final Pregunta pregunta16 = new Pregunta(
				"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
				"imagen16");
		add(pregunta16);
		final Pregunta pregunta17 = new Pregunta(
				"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
				"imagen17");
		add(pregunta17);

		add(new Respuesta("Cell", pregunta2));
		add(new Respuesta("Super Bu", pregunta2));
		add(new Respuesta(true, "Mr Bu", pregunta2));
		add(new Respuesta("Kid Bu", pregunta2));
		add(new Respuesta("Ten Shin Han", pregunta3));
		add(new Respuesta(true, "Yamcha", pregunta3));
		add(new Respuesta("Puar", pregunta3));
		add(new Respuesta("Oolong", pregunta3));
		add(new Respuesta("C17", pregunta4));
		add(new Respuesta(true, "C18", pregunta4));
		add(new Respuesta("C19", pregunta4));
		add(new Respuesta("C20", pregunta4));
		add(new Respuesta("Gyumaoh", pregunta5));
		add(new Respuesta("Youmaoh", pregunta5));
		add(new Respuesta(true, "Mutenroshi", pregunta5));
		add(new Respuesta("Mutaito", pregunta5));
		add(new Respuesta("1994", pregunta6));
		add(new Respuesta("1995", pregunta6));
		add(new Respuesta(true, "1996", pregunta6));
		add(new Respuesta("1997", pregunta6));
		add(new Respuesta(true, "Son Goku y sus amigos", pregunta7));
		add(new Respuesta("El gran combate", pregunta7));
		add(new Respuesta("¡Adelante Son Goku!", pregunta7));
		add(new Respuesta("Un plan diabólico", pregunta7));
		add(new Respuesta("1989", pregunta8));
		add(new Respuesta("1990", pregunta8));
		add(new Respuesta("1993", pregunta8));
		add(new Respuesta(true, "1996", pregunta8));
		add(new Respuesta("Raditz", pregunta9));
		add(new Respuesta("King Cold", pregunta9));
		add(new Respuesta(true, "Cooler", pregunta9));
		add(new Respuesta("Broly", pregunta9));
		add(new Respuesta("Abo y Kado", pregunta10));
		add(new Respuesta(true, "Birusu y Wisu", pregunta10));
		add(new Respuesta("Zarbón y Dodoria", pregunta10));
		add(new Respuesta("Vegeta y Nappa", pregunta10));
		add(new Respuesta(
				"Todo aquel que lleva puesto el pendiente incrementa su fuerza.",
				pregunta11));
		add(new Respuesta("Es meramente un elemento estético", pregunta11));
		add(new Respuesta(
				true,
				"Es un pendiente de los dioses kaio shin. Permite realizar una ancestral fusión",
				pregunta11));
		add(new Respuesta("Es un simple reloj", pregunta12));
		add(new Respuesta(true, "Permite detectar las bolas de dragón",
				pregunta12));
		add(new Respuesta("Permite localizar focos de ki", pregunta12));
		add(new Respuesta("Es el nuevo smartphone de apple", pregunta12));
		add(new Respuesta(true, "Sega Megadrive / Genesis", pregunta13));
		add(new Respuesta("Super nintendo / Famicom", pregunta13));
		add(new Respuesta("Nintendo Game Boy", pregunta13));
		add(new Respuesta("Neo Geo", pregunta13));
		add(new Respuesta("Puede realizar el Kame Hame Ha", pregunta14));
		add(new Respuesta(true,
				"Tiene la capacidad de variar el tamaño de su cuerpo",
				pregunta14));
		add(new Respuesta("Tiene poderes curativos", pregunta14));
		add(new Respuesta("Ninguno, es un simple humano", pregunta14));
		add(new Respuesta("Episodio 6", pregunta15));
		add(new Respuesta("Episodio 25", pregunta15));
		add(new Respuesta(true, "Episodio 29", pregunta15));
		add(new Respuesta("Episodio 33", pregunta15));
		add(new Respuesta("Sobrino", pregunta16));
		add(new Respuesta("Hijo", pregunta16));
		add(new Respuesta("Hermano", pregunta16));
		add(new Respuesta(true, "Tataranieto", pregunta16));
		add(new Respuesta("No, no lo es", pregunta17));
		add(new Respuesta(
				true,
				"Sí, aparece como personaje seleccionable en el Dragon Ball 'Final Bout' de PSX",
				pregunta17));
		add(new Respuesta(
				"Sí, en el primer capítulo de DBGT le podemos ver en este estado",
				pregunta17));
		add(new Respuesta(
				"Sí, en el último capítulo de DBGT le podemos ver en este estado",
				pregunta17));
	}

	private int add(final Pregunta pregunta) {
		return getPreguntaDAO().create(pregunta);
	}

	private void add(final Respuesta respuesta) {
		getRespuestaDAO().create(respuesta);
	}

	public RuntimeExceptionDao<Pregunta, Integer> getPreguntaDAO() {
		if (preguntaDAO == null) {
			preguntaDAO = getRuntimeExceptionDao(Pregunta.class);
		}
		return preguntaDAO;
	}

	public RuntimeExceptionDao<Respuesta, Integer> getRespuestaDAO() {
		if (respuestaDAO == null) {
			respuestaDAO = getRuntimeExceptionDao(Respuesta.class);
		}
		return respuestaDAO;
	}

	@Override
	public void close() {
		super.close();
		preguntaDAO = null;
		respuestaDAO = null;
	}

}