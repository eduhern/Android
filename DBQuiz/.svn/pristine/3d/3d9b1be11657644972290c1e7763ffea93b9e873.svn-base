package com.eduhern.dbquiz.database;

import com.eduhern.dbquiz.models.Categoria;
import com.eduhern.dbquiz.models.Pregunta;
import com.eduhern.dbquiz.models.Respuesta;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

	private static final Class<?>[] classes = new Class[] { 
			Pregunta.class,
			Respuesta.class,
			Categoria.class,};

	public static void main(final String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt", classes);
	}
}