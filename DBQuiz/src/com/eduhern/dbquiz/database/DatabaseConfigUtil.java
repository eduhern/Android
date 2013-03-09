package com.eduhern.dbquiz.database;

import com.eduhern.dbquiz.models.Pregunta;
import com.eduhern.dbquiz.models.Respuesta;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

	private static final Class<?>[] CLASSES = new Class[] { Pregunta.class,
			Respuesta.class, };

	public static void main(final String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt", CLASSES);
	}
}