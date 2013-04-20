package com.eduhern.dbquiz.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.models.Categoria;
import com.eduhern.dbquiz.models.Pregunta;
import com.eduhern.dbquiz.models.Respuesta;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "dbquiz.db";
	private static final int DATABASE_VERSION = 50;

	private RuntimeExceptionDao<Pregunta, Integer> preguntaDAO = null;
	private RuntimeExceptionDao<Respuesta, Integer> respuestaDAO = null;
	private RuntimeExceptionDao<Categoria, Integer> categoriaDAO = null;

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
			TableUtils.createTable(connectionSource, Categoria.class);
			inserciones();
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
			TableUtils.dropTable(connectionSource, Categoria.class, true);

			onCreate(db, connectionSource);
		} catch (final java.sql.SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
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

	public RuntimeExceptionDao<Categoria, Integer> getCategoriaDAO() {
		if (categoriaDAO == null) {
			categoriaDAO = getRuntimeExceptionDao(Categoria.class);
		}
		return categoriaDAO;
	}

	private void inserciones() {
		final Categoria geografia = new Categoria(
				1,
				"Geografía",
				"imagen_geografia",
				"Preguntas relacionadas con los distintos lugares de la partícular tierra y universo que envuelve a la obra de Toriyama y la Toei Animation");
		final Categoria ciencia = new Categoria(
				2,
				"Ciencia, Tecnología y Videojuegos",
				"imagen_ciencias",
				"Aparecerán preguntas vinculadas a distintos videojuegos relacionados con la serie, así como referencias a la propia tecnología utilizada en el Dragon World");
		final Categoria arte = new Categoria(
				3,
				"Arte, Literatura y Cine",
				"imagen_literatura",
				"Se tratarán preguntas sobre el propio manga creado por Toriyama, así como sus distintos libros de arte publicados en paralelo. También aparecerán aquí las cuestiones en relación a la filmografía de Dragon Ball creada por la Toei");
		final Categoria torneos = new Categoria(
				4,
				"Torneos y Técnicas",
				"imagen_deportes",
				"¿Cuánto sabes sobre los torneos de artes marciales celebrados a lo largo de Dragon Ball? ¿Conoces las técnicas desarrolladas por los distintos personajes?");
		final Categoria dragon = new Categoria(
				5,
				"Dragon World",
				"imagen_espectaculos",
				"¿Te sabes el nombre de todos los personajes de Dragon Ball (tanto canónicos como no)? Quizás te sorprenda lo que puedas descubrir al adentrarte en las distintas relaciones entre ellos");
		final Categoria historia = new Categoria(
				6,
				"Historia",
				"imagen_historia",
				"Si conoces la cronología oficial creada para el Dragon Ball más canónico no tendrás ningún problema, sin embargo, ¿qué me dices de la historia real? ¿sabes cuándo han sucedido los estrenos y acontecimientos más importantes de la historia de Dragon Ball?");

		getCategoriaDAO().create(geografia);
		getCategoriaDAO().create(ciencia);
		getCategoriaDAO().create(arte);
		getCategoriaDAO().create(torneos);
		getCategoriaDAO().create(dragon);
		getCategoriaDAO().create(historia);
		final Pregunta pregunta1 = new Pregunta(1,
				"¿Cuál es el nombre de estos tres famosos villanos?",
				"imagen1", dragon, 1);
		final Pregunta pregunta2 = new Pregunta(2,
				"¿Cuál es el nombre de este famoso personaje?", "imagen2",
				dragon, 1);
		final Pregunta pregunta3 = new Pregunta(3,
				"¿Cómo se llama el personaje?", "imagen3", dragon, 1);
		final Pregunta pregunta4 = new Pregunta(
				4,
				"¿Cuál es el nombre de este androide de base humana creado por el Dr. Gero?",
				"imagen4", dragon, 1);
		final Pregunta pregunta5 = new Pregunta(5,
				"Fue el segundo maestro de Goku, ¿a qué nombre responde?",
				"imagen5", dragon, 1);
		final Pregunta pregunta6 = new Pregunta(
				6,
				"¿En qué año se empezó a emitir la serie de animación que continuaba de forma canónica el manga de Toriyama?",
				"imagen6", historia, 2);
		final Pregunta pregunta7 = new Pregunta(
				7,
				"¿Cuál es el título del primer tankobon del manga de Dragon Ball?",
				"imagen7", arte, 2);
		final Pregunta pregunta8 = new Pregunta(
				8,
				"¿En qué año se estrenó el film de Dragon Ball 'Saikyo e no michi'?",
				"imagen8", arte, 2);
		final Pregunta pregunta9 = new Pregunta(9,
				"¿Cómo se llama el hermano de Freezer?", "imagen9", dragon, 1);
		final Pregunta pregunta10 = new Pregunta(
				10,
				"¿Cuál es el nombre de estos dos personajes del film de 2013 Dragon Ball Z 'Battle of Gods'?",
				"imagen10", dragon, 2);
		getPreguntaDAO().create(pregunta1);
		getPreguntaDAO().create(pregunta2);
		getPreguntaDAO().create(pregunta3);
		getPreguntaDAO().create(pregunta4);
		getPreguntaDAO().create(pregunta5);
		getPreguntaDAO().create(pregunta6);
		getPreguntaDAO().create(pregunta7);
		getPreguntaDAO().create(pregunta8);
		getPreguntaDAO().create(pregunta9);
		getPreguntaDAO().create(pregunta10);
		getRespuestaDAO().create(
				new Respuesta(0, true, "Pilaf, Mai y Suu", pregunta1));
		getRespuestaDAO().create(
				new Respuesta(1, false, "Pilaf, Mai y Ginew", pregunta1));
		getRespuestaDAO().create(
				new Respuesta(2, false, "Vegeta, Mai y Zarbon", pregunta1));
		getRespuestaDAO().create(
				new Respuesta(3, false, "Dodoria, Kiwi y Zarbon", pregunta1));
		getRespuestaDAO().create(new Respuesta(4, false, "Cell", pregunta2));
		getRespuestaDAO()
				.create(new Respuesta(5, false, "Super Bu", pregunta2));
		getRespuestaDAO().create(new Respuesta(6, true, "Mr Bu", pregunta2));
		getRespuestaDAO().create(new Respuesta(7, false, "Kid Bu", pregunta2));
		getRespuestaDAO().create(
				new Respuesta(8, false, "Ten Shin Han", pregunta3));
		getRespuestaDAO().create(new Respuesta(9, true, "Yamcha", pregunta3));
		getRespuestaDAO().create(new Respuesta(10, false, "Puar", pregunta3));
		getRespuestaDAO().create(new Respuesta(11, false, "Oolong", pregunta3));
		getRespuestaDAO().create(new Respuesta(12, false, "C17", pregunta4));
		getRespuestaDAO().create(new Respuesta(13, true, "C18", pregunta4));
		getRespuestaDAO().create(new Respuesta(14, false, "C19", pregunta4));
		getRespuestaDAO().create(new Respuesta(15, false, "C20", pregunta4));
		getRespuestaDAO()
				.create(new Respuesta(16, false, "Gyumaoh", pregunta5));
		getRespuestaDAO()
				.create(new Respuesta(17, false, "Youmaoh", pregunta5));
		getRespuestaDAO().create(
				new Respuesta(18, true, "Mutenroshi", pregunta5));
		getRespuestaDAO()
				.create(new Respuesta(19, false, "Mutaito", pregunta5));
		getRespuestaDAO().create(new Respuesta(20, false, "1994", pregunta6));
		getRespuestaDAO().create(new Respuesta(21, false, "1995", pregunta6));
		getRespuestaDAO().create(new Respuesta(22, true, "1996", pregunta6));
		getRespuestaDAO().create(new Respuesta(23, false, "1997", pregunta6));
		getRespuestaDAO().create(
				new Respuesta(24, true, "Son Goku y sus amigos", pregunta7));
		getRespuestaDAO().create(
				new Respuesta(25, false, "El gran combate", pregunta7));
		getRespuestaDAO().create(
				new Respuesta(26, false, "¡Adelante Son Goku!", pregunta7));
		getRespuestaDAO().create(
				new Respuesta(27, false, "Un plan diabólico", pregunta7));
		getRespuestaDAO().create(new Respuesta(28, false, "1989", pregunta8));
		getRespuestaDAO().create(new Respuesta(29, false, "1990", pregunta8));
		getRespuestaDAO().create(new Respuesta(30, false, "1993", pregunta8));
		getRespuestaDAO().create(new Respuesta(31, true, "1996", pregunta8));
		getRespuestaDAO().create(new Respuesta(32, false, "Raditz", pregunta9));
		getRespuestaDAO().create(
				new Respuesta(33, false, "King Cold", pregunta9));
		getRespuestaDAO().create(new Respuesta(34, true, "Cooler", pregunta9));
		getRespuestaDAO().create(new Respuesta(35, false, "Broly", pregunta9));
		getRespuestaDAO().create(
				new Respuesta(36, false, "Abo y Kado", pregunta10));
		getRespuestaDAO().create(
				new Respuesta(37, true, "Birusu y Wisu", pregunta10));
		getRespuestaDAO().create(
				new Respuesta(38, false, "Zarbón y Dodoria", pregunta10));
		getRespuestaDAO().create(
				new Respuesta(39, false, "Vegeta y Nappa", pregunta10));
		final Pregunta pregunta48 = new Pregunta(48,
				"¿Cómo se llama el hermano de Vegeta?", "imagen48", dragon, 2);
		final Pregunta pregunta49 = new Pregunta(49,
				"¿Cómo se llamaba C18 antes de ser convertida en cyborg?",
				"imagen49", historia, 1);
		final Pregunta pregunta50 = new Pregunta(
				50,
				"¿Cuál es el nombre de este importantísimo personaje de Dragon Ball?",
				"imagen50", dragon, 1);
		final Pregunta pregunta51 = new Pregunta(51,
				"¿Cómo se llama este personaje secundario?", "imagen51",
				dragon, 2);
		final Pregunta pregunta52 = new Pregunta(52,
				"¿Quién es el personaje de la imagen?", "imagen52", dragon, 1);
		final Pregunta pregunta53 = new Pregunta(53,
				"¿Cómo se titula este film de Dragon Ball?", "imagen53", arte,
				1);
		final Pregunta pregunta54 = new Pregunta(54,
				"¿Cómo se titula este film de Dragon Ball?", "imagen54", arte,
				1);
		final Pregunta pregunta55 = new Pregunta(55,
				"¿Cómo se titula este film de Dragon Ball?", "imagen55", arte,
				1);
		final Pregunta pregunta56 = new Pregunta(56,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen56",
				arte, 1);
		final Pregunta pregunta57 = new Pregunta(57,
				"¿Quién es la autora del manga Dragon Ball SD?", "imagen57",
				arte, 2);
		final Pregunta pregunta58 = new Pregunta(58,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen58",
				arte, 1);
		final Pregunta pregunta59 = new Pregunta(59,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen59",
				arte, 1);
		final Pregunta pregunta60 = new Pregunta(60,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen60",
				arte, 1);
		final Pregunta pregunta61 = new Pregunta(61,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen61",
				arte, 1);
		final Pregunta pregunta62 = new Pregunta(62,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen62",
				arte, 1);
		final Pregunta pregunta63 = new Pregunta(63,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen63",
				arte, 1);
		final Pregunta pregunta64 = new Pregunta(64,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen64",
				arte, 1);
		final Pregunta pregunta65 = new Pregunta(65,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen65",
				arte, 1);
		final Pregunta pregunta66 = new Pregunta(66,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen66",
				arte, 1);
		final Pregunta pregunta67 = new Pregunta(67,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen67",
				arte, 1);
		final Pregunta pregunta68 = new Pregunta(68,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen68",
				arte, 1);
		final Pregunta pregunta69 = new Pregunta(69,
				"¿Cuál es el videojuego de la imagen?", "imagen69", ciencia, 2);
		final Pregunta pregunta70 = new Pregunta(70,
				"¿Cuál es el videojuego de la imagen?", "imagen70", ciencia, 2);
		final Pregunta pregunta71 = new Pregunta(71,
				"¿Cuál es el videojuego de la imagen?", "imagen71", ciencia, 2);
		final Pregunta pregunta72 = new Pregunta(72,
				"¿En qué edición del Tenkaichi Budokai participó Ran Fan?",
				"imagen72", torneos, 2);
		final Pregunta pregunta73 = new Pregunta(73,
				"¿Sobre qué número está etiquetado este androide?", "imagen73",
				dragon, 2);
		final Pregunta pregunta74 = new Pregunta(74,
				"¿A qué se dedicaba Cynthia, el personaje de la imagen?",
				"imagen74", historia, 2);
		final Pregunta pregunta75 = new Pregunta(
				75,
				"¿Qué fusión está tras este personaje del manga/arcade Dragon Ball Heroes?",
				"imagen75", torneos, 2);
		final Pregunta pregunta76 = new Pregunta(76,
				"¿Cómo se llama el protagonista de Dragon Ball?", "imagen76",
				dragon, 1);
		final Pregunta pregunta77 = new Pregunta(77,
				"¿En qué lugar habita Enma, la anciana de la foto?",
				"imagen77", geografia, 2);
		final Pregunta pregunta78 = new Pregunta(78,
				"¿Cómo se llama esta joven?", "imagen78", dragon, 1);
		final Pregunta pregunta79 = new Pregunta(79,
				"¿Qué técnica está realizando Krilín en esta foto?",
				"imagen79", torneos, 1);
		final Pregunta pregunta80 = new Pregunta(80,
				"Gotenks es la fusión de dos personajes, ¿quiénes?",
				"imagen80", torneos, 1);
		final Pregunta pregunta81 = new Pregunta(81,
				"¿Qué opina Zarbon de su metamorfosis?", "imagen81", torneos, 1);
		final Pregunta pregunta82 = new Pregunta(82,
				"¿Cómo se llama el personaje de la imagen?", "imagen82",
				dragon, 2);
		final Pregunta pregunta83 = new Pregunta(
				83,
				"¿Qué rango tenía, dentro del ejército de la Red Ribbon, Violet?",
				"imagen83", historia, 2);
		final Pregunta pregunta84 = new Pregunta(
				84,
				"¿Cómo se llama esta saiyana pura perteneciente al escuadrón de Bardock?",
				"imagen84", dragon, 1);
		final Pregunta pregunta85 = new Pregunta(85,
				"¿Cómo se llama este personaje?", "imagen85", dragon, 1);
		final Pregunta pregunta86 = new Pregunta(86,
				"¿De qué dos personajes es fusión Vegetto?", "imagen86",
				torneos, 1);
		final Pregunta pregunta87 = new Pregunta(87,
				"¿Para qué propósito está reuniendo Goku las bolas de dragón?",
				"imagen87", historia, 1);
		final Pregunta pregunta88 = new Pregunta(88,
				"¿A qué nombre responde este personaje?", "imagen88", dragon, 1);
		final Pregunta pregunta89 = new Pregunta(89,
				"¿En qué posición quedó Goku en el 23º Tenkaichi Budokai?",
				"imagen89", torneos, 1);
		final Pregunta pregunta90 = new Pregunta(
				90,
				"¿Cuántas ediciones hubo - en Japón - de la Dragon Ball Daizenshuu, la enciclopedia de Dragon Ball?",
				"imagen90", arte, 2);
		final Pregunta pregunta91 = new Pregunta(
				91,
				"¿Cuál es el título del último tankobon del manga de Dragon Ball?",
				"imagen91", arte, 2);
		final Pregunta pregunta92 = new Pregunta(
				92,
				"¿En qué fecha salió a la venta el videojuego de NES de Dragon Ball titulado Shenlong no nazo?",
				"imagen92", ciencia, 2);
		final Pregunta pregunta93 = new Pregunta(
				93,
				"¿En qué fecha se publicó el primer capítulo del manga de Dragon Ball, realizado por Akira Toriyama?",
				"imagen93", historia, 2);
		final Pregunta pregunta94 = new Pregunta(
				94,
				"¿Cuándo se emitió, en Japón, el primer episodio de la serie de anime Dragon Ball Z?",
				"imagen94", historia, 2);
		final Pregunta pregunta95 = new Pregunta(
				95,
				"¿Cuándo se emitió, en Japón, el primer episodio de la serie de anime Dragon Ball Kai?",
				"imagen95", historia, 2);
		final Pregunta pregunta96 = new Pregunta(
				96,
				"¿Cuándo se emitió, en Japón, el primer episodio de la serie de anime Dragon Ball?",
				"imagen96", historia, 2);
		final Pregunta pregunta97 = new Pregunta(97,
				"¿Cuál es el título de este film de Dragon Ball Z?",
				"imagen97", arte, 1);
		final Pregunta pregunta98 = new Pregunta(98,
				"¿En qué estado está Son Goku en esta fotografía?", "imagen98",
				dragon, 2);
		final Pregunta pregunta99 = new Pregunta(
				99,
				"¿Cómo se llama la chica que acompaña, en el anime, a Mr Satán al Cell Game?",
				"imagen99", dragon, 2);
		final Pregunta pregunta100 = new Pregunta(
				100,
				"¿Cómo se titula el episodio especial de Dragon Ball Z emtido en 2008?",
				"imagen100", arte, 2);
		final Pregunta pregunta101 = new Pregunta(
				101,
				"¿Qué talla de sujetador/brasier tiene bulma al comienzo del manga?",
				"imagen101", historia, 2);
		final Pregunta pregunta102 = new Pregunta(102,
				"¿Cómo se titula este film de Dragon Ball Z?", "imagen102",
				arte, 1);
		final Pregunta pregunta103 = new Pregunta(103,
				"¿Cómo se llama este poderoso enemigo?", "imagen103", dragon, 1);
		final Pregunta pregunta104 = new Pregunta(104,
				"¿Cómo se llama este histórico villano?", "imagen104", dragon,
				2);
		final Pregunta pregunta105 = new Pregunta(105,
				"¿Cómo se llama esta villana?", "imagen105", dragon, 2);
		final Pregunta pregunta106 = new Pregunta(
				106,
				"¿Cuál es el título de este tankobon del manga de Dragon Ball?",
				"imagen106", arte, 2);
		final Pregunta pregunta107 = new Pregunta(107,
				"¿Cuál es el videojuego de la imagen?", "imagen107", ciencia, 2);
		final Pregunta pregunta108 = new Pregunta(108,
				"¿Cuál es el videojuego de la imagen?", "imagen108", ciencia, 2);
		final Pregunta pregunta109 = new Pregunta(109,
				"¿Cuál es el videojuego de la imagen?", "imagen109", ciencia, 2);
		final Pregunta pregunta110 = new Pregunta(110,
				"¿Quién es el personaje de la foto?", "imagen110", historia, 2);
		final Pregunta pregunta111 = new Pregunta(111,
				"¿Cómo se llama este planeta?", "imagen111", geografia, 1);
		final Pregunta pregunta112 = new Pregunta(112,
				"¿Cómo se llama este planeta?", "imagen112", geografia, 2);
		final Pregunta pregunta113 = new Pregunta(113,
				"¿A qué se debe esta fría situación?", "imagen113", historia, 2);
		final Pregunta pregunta114 = new Pregunta(114,
				"¿Cómo se llama el padre de Goku?", "imagen114", dragon, 1);
		final Pregunta pregunta115 = new Pregunta(115,
				"¿Qué nombre tiene este dragón maligno?", "imagen115", dragon,
				1);
		final Pregunta pregunta116 = new Pregunta(116,
				"¿Qué nombre tiene este dragón maligno?", "imagen116", dragon,
				1);
		final Pregunta pregunta117 = new Pregunta(117,
				"¿Quiénes son los miembros del comando Ginew?", "imagen117",
				dragon, 1);
		final Pregunta pregunta118 = new Pregunta(118,
				"¿Cómo se llama el mejor amigo de Goku?", "imagen118", dragon,
				1);
		final Pregunta pregunta119 = new Pregunta(119,
				"¿Cómo se llama el inseparable amigo de Yamcha?", "imagen119",
				dragon, 1);
		final Pregunta pregunta120 = new Pregunta(120,
				"¿Cómo se llama la mujer de Goku?", "imagen120", dragon, 1);
		final Pregunta pregunta121 = new Pregunta(121,
				"¿Cómo se llama el hijo menor de Goku?", "imagen121", dragon, 1);
		final Pregunta pregunta122 = new Pregunta(122,
				"¿Cómo se llama el carismático enemigo de la imagen?",
				"imagen122", dragon, 1);
		final Pregunta pregunta123 = new Pregunta(123,
				"¿Cómo se conoce este libro basado en Dragon Ball?",
				"imagen123", arte, 2);
		final Pregunta pregunta124 = new Pregunta(
				124,
				"¿Cuál es el título de este tankobon del manga de Dragon Ball?",
				"imagen124", arte, 2);
		final Pregunta pregunta125 = new Pregunta(125,
				"¿Cómo se llama este coronel?", "imagen125", dragon, 2);
		final Pregunta pregunta126 = new Pregunta(126,
				"¿Cuál es el videojuego de la imagen?", "imagen126", ciencia, 2);
		final Pregunta pregunta127 = new Pregunta(127,
				"¿En qué consiste el 'puff, puff' ('paffu, paffu')?",
				"imagen127", historia, 2);

		getPreguntaDAO().create(
				new Pregunta(11, "¿Para qué sirve el objeto de la fotografía?",
						"imagen11", ciencia, 1));
		getPreguntaDAO().create(
				new Pregunta(12, "¿Para qué sirve el objeto de la fotografía?",
						"imagen12", ciencia, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						13,
						"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
						"imagen13", ciencia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						14,
						"¿Cuál era el principal poder del personaje de la imagen?",
						"imagen14", ciencia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						15,
						"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
						"imagen15", historia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						16,
						"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
						"imagen16", historia, 1));
		getPreguntaDAO().create(
				new Pregunta(17,
						"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
						"imagen17", ciencia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						18,
						"¿Qué videojuego es el que se corresponde con el de la imagen?",
						"imagen18", ciencia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						19,
						"¿Cómo se llama el curioso personaje de la fotografía?",
						"imagen19", dragon, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						20,
						"¿Cuál es el nombre del guardian de la puerta del otro mundo?",
						"imagen20", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						21,
						"¿A qué se debe el aspecto que presenta Cooler en esta imagen?",
						"imagen21", ciencia, 2));
		getPreguntaDAO().create(
				new Pregunta(22, "¿Cómo se llama la novia de Son Gohan?",
						"imagen22", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						23,
						"¿En qué edición del Tenkaichi Budokai participó Punta, el personaje de la imagen?",
						"imagen23", torneos, 2));
		getPreguntaDAO().create(
				new Pregunta(24,
						"¿Cuál es el nombre del principe de los saiyans?",
						"imagen24", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						25,
						"¿En qué fase se encuentra Freezer en esta monstruosa forma?",
						"imagen25", ciencia, 1));
		getPreguntaDAO().create(
				new Pregunta(26,
						"¿De qué dos luchadores es fusión este personaje?",
						"imagen26", ciencia, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						27,
						"¿Por qué personaje es poseído Vegeta en Dragon Ball GT?",
						"imagen27", ciencia, 1));
		getPreguntaDAO().create(
				new Pregunta(28,
						"¿Cuántas veces muere y resucita Chaoz en el manga?",
						"imagen28", historia, 2));
		getPreguntaDAO().create(
				new Pregunta(29, "¿Cómo se llama la nieta de Goku?",
						"imagen29", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						30,
						"¿Para qué plataforma estuvo disponible el juego 'Dragon Ball Z: SAGAS'?",
						"imagen30", ciencia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						31,
						"¿Cómo se llama este androide completamente artificial?",
						"imagen31", dragon, 1));
		getPreguntaDAO().create(
				new Pregunta(32, "¿Quién es este personaje?", "imagen32",
						dragon, 1));
		getPreguntaDAO().create(
				new Pregunta(33, "¿Quién es este personaje?", "imagen33",
						dragon, 1));
		getPreguntaDAO().create(
				new Pregunta(34,
						"¿Dónde esconde Bulma el diamante de la fotografía?",
						"imagen34", historia, 2));
		getPreguntaDAO().create(
				new Pregunta(35,
						"¿Qué deseo pidió Piccolo Daimaoh al dragon Shenron?",
						"imagen35", historia, 1));
		getPreguntaDAO().create(
				new Pregunta(36, "¿Cómo se llama este personaje de tres ojos?",
						"imagen36", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						37,
						"¿Contra qué enemigo se transforma Goku por primera vez en SSJ en Dragon Ball GT?",
						"imagen37", historia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						38,
						"¿Cómo se llama este importante personaje del ejército de Freezer?",
						"imagen38", dragon, 1));
		getPreguntaDAO().create(
				new Pregunta(39,
						"Si este personaje no es Freezer, ¿de quién se trata?",
						"imagen39", dragon, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						40,
						"¿Qué (supuesto) parentesco comparte este personaje con Freezer?",
						"imagen40", dragon, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						41,
						"¿Cómo se llama este importante personaje del ejército de Freezer?",
						"imagen41", dragon, 1));
		getPreguntaDAO().create(
				new Pregunta(42, "¿Cuál es el nombre de este dragón maligno?",
						"imagen42", dragon, 2));
		getPreguntaDAO().create(
				new Pregunta(43, "¿Cómo se llama este poderoso enemigo?",
						"imagen43", dragon, 1));
		getPreguntaDAO()
				.create(new Pregunta(
						44,
						"¿El odio de que antigua civilización tiene acumulado Hatchiyack?",
						"imagen44", historia, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						45,
						"¿Cómo se llama este gracioso villano del manga Neko Majin Z?",
						"imagen45", dragon, 2));
		getPreguntaDAO()
				.create(new Pregunta(
						46,
						"¿En qué episodio de Dragon Ball Z aparece el personaje de la imagen?",
						"imagen46", historia, 2));
		getPreguntaDAO().create(
				new Pregunta(47,
						"¿Comparte Turles algún vínculo familiar con Goku?",
						"imagen47", historia, 1));
		getPreguntaDAO().create(pregunta48);
		getPreguntaDAO().create(pregunta49);
		getPreguntaDAO().create(pregunta50);
		getPreguntaDAO().create(pregunta51);
		getPreguntaDAO().create(pregunta52);
		getPreguntaDAO().create(pregunta53);
		getPreguntaDAO().create(pregunta54);
		getPreguntaDAO().create(pregunta55);
		getPreguntaDAO().create(pregunta56);
		getPreguntaDAO().create(pregunta57);
		getPreguntaDAO().create(pregunta58);
		getPreguntaDAO().create(pregunta59);
		getPreguntaDAO().create(pregunta60);
		getPreguntaDAO().create(pregunta61);
		getPreguntaDAO().create(pregunta62);
		getPreguntaDAO().create(pregunta63);
		getPreguntaDAO().create(pregunta64);
		getPreguntaDAO().create(pregunta65);
		getPreguntaDAO().create(pregunta66);
		getPreguntaDAO().create(pregunta67);
		getPreguntaDAO().create(pregunta68);
		getPreguntaDAO().create(pregunta69);
		getPreguntaDAO().create(pregunta70);
		getPreguntaDAO().create(pregunta71);
		getPreguntaDAO().create(pregunta72);
		getPreguntaDAO().create(pregunta73);
		getPreguntaDAO().create(pregunta74);
		getPreguntaDAO().create(pregunta75);
		getPreguntaDAO().create(pregunta76);
		getPreguntaDAO().create(pregunta77);
		getPreguntaDAO().create(pregunta78);
		getPreguntaDAO().create(pregunta79);
		getPreguntaDAO().create(pregunta80);
		getPreguntaDAO().create(pregunta81);
		getPreguntaDAO().create(pregunta82);
		getPreguntaDAO().create(pregunta83);
		getPreguntaDAO().create(pregunta84);
		getPreguntaDAO().create(pregunta85);
		getPreguntaDAO().create(pregunta86);
		getPreguntaDAO().create(pregunta87);
		getPreguntaDAO().create(pregunta88);
		getPreguntaDAO().create(pregunta89);
		getPreguntaDAO().create(pregunta90);
		getPreguntaDAO().create(pregunta91);
		getPreguntaDAO().create(pregunta92);
		getPreguntaDAO().create(pregunta93);
		getPreguntaDAO().create(pregunta94);
		getPreguntaDAO().create(pregunta95);
		getPreguntaDAO().create(pregunta96);
		getPreguntaDAO().create(pregunta97);
		getPreguntaDAO().create(pregunta98);
		getPreguntaDAO().create(pregunta99);
		getPreguntaDAO().create(pregunta100);
		getPreguntaDAO().create(pregunta101);
		getPreguntaDAO().create(pregunta102);
		getPreguntaDAO().create(pregunta103);
		getPreguntaDAO().create(pregunta104);
		getPreguntaDAO().create(pregunta105);
		getPreguntaDAO().create(pregunta106);
		getPreguntaDAO().create(pregunta107);
		getPreguntaDAO().create(pregunta108);
		getPreguntaDAO().create(pregunta109);
		getPreguntaDAO().create(pregunta110);
		getPreguntaDAO().create(pregunta111);
		getPreguntaDAO().create(pregunta112);
		getPreguntaDAO().create(pregunta113);
		getPreguntaDAO().create(pregunta114);
		getPreguntaDAO().create(pregunta115);
		getPreguntaDAO().create(pregunta116);
		getPreguntaDAO().create(pregunta117);
		getPreguntaDAO().create(pregunta118);
		getPreguntaDAO().create(pregunta119);
		getPreguntaDAO().create(pregunta120);
		getPreguntaDAO().create(pregunta121);
		getPreguntaDAO().create(pregunta122);
		getPreguntaDAO().create(pregunta123);
		getPreguntaDAO().create(pregunta124);
		getPreguntaDAO().create(pregunta125);
		getPreguntaDAO().create(pregunta126);
		getPreguntaDAO().create(pregunta127);

		getRespuestaDAO()
				.create(new Respuesta(
						40,
						false,
						"Todo aquel que lleva puesto el pendiente incrementa su fuerza.",
						new Pregunta(11,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen11", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(41, false, "Es meramente un elemento estético",
						new Pregunta(11,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen11", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						42,
						true,
						"Es un pendiente de los dioses kaio shin. Permite realizar una ancestral fusión",
						new Pregunta(11,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen11", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(43, false, "Es un simple reloj", new Pregunta(12,
						"¿Para qué sirve el objeto de la fotografía?",
						"imagen12", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(44, true, "Permite detectar las bolas de dragón",
						new Pregunta(12,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen12", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(45, false, "Permite localizar focos de ki",
						new Pregunta(12,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen12", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(46, false, "Es el nuevo smartphone de apple",
						new Pregunta(12,
								"¿Para qué sirve el objeto de la fotografía?",
								"imagen12", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						47,
						true,
						"Sega Megadrive / Genesis",
						new Pregunta(
								13,
								"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
								"imagen13", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						48,
						false,
						"Super nintendo / Famicom",
						new Pregunta(
								13,
								"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
								"imagen13", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						49,
						false,
						"Nintendo Game Boy",
						new Pregunta(
								13,
								"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
								"imagen13", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						50,
						false,
						"Neo Geo",
						new Pregunta(
								13,
								"¿En qué plataforma podemos disfrutar del juego Dragon Ball Z 'Buyu Retsuden'?",
								"imagen13", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						51,
						false,
						"Puede realizar el Kame Hame Ha",
						new Pregunta(
								14,
								"¿Cuál era el principal poder del personaje de la imagen?",
								"imagen14", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						52,
						true,
						"Tiene la capacidad de variar el tamaño de su cuerpo",
						new Pregunta(
								14,
								"¿Cuál era el principal poder del personaje de la imagen?",
								"imagen14", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						53,
						false,
						"Tiene poderes curativos",
						new Pregunta(
								14,
								"¿Cuál era el principal poder del personaje de la imagen?",
								"imagen14", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						54,
						false,
						"Ninguno, es un simple humano",
						new Pregunta(
								14,
								"¿Cuál era el principal poder del personaje de la imagen?",
								"imagen14", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						55,
						false,
						"Episodio 6",
						new Pregunta(
								15,
								"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
								"imagen15", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						56,
						false,
						"Episodio 25",
						new Pregunta(
								15,
								"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
								"imagen15", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						57,
						true,
						"Episodio 29",
						new Pregunta(
								15,
								"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
								"imagen15", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						58,
						false,
						"Episodio 33",
						new Pregunta(
								15,
								"¿En qué capítulo de Dragon Ball GT alcanza Goku el estado de SSJ3 siendo un niño?",
								"imagen15", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						59,
						false,
						"Sobrino",
						new Pregunta(
								16,
								"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
								"imagen16", historia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						60,
						false,
						"Hijo",
						new Pregunta(
								16,
								"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
								"imagen16", historia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						61,
						false,
						"Hermano",
						new Pregunta(
								16,
								"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
								"imagen16", historia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						62,
						true,
						"Tataranieto",
						new Pregunta(
								16,
								"¿Qué parentesco comparte el personaje de la fotografía con Goku (Kakarotto)?",
								"imagen16", historia, 1)));
		getRespuestaDAO().create(
				new Respuesta(63, false, "No, no lo es", new Pregunta(17,
						"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
						"imagen17", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						64,
						true,
						"Es un personaje seleccionable en el Dragon Ball 'Final Bout' de PSX",
						new Pregunta(
								17,
								"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
								"imagen17", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						65,
						false,
						"En el primer capítulo de DBGT le podemos ver en este estado",
						new Pregunta(
								17,
								"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
								"imagen17", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						66,
						false,
						"En el último capítulo de DBGT le podemos ver en este estado",
						new Pregunta(
								17,
								"Goku adulto en DBGT convertido en SSJ, ¿es posible?",
								"imagen17", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						67,
						false,
						"Dragon Ball: Final Bout",
						new Pregunta(
								18,
								"¿Qué videojuego es el que se corresponde con el de la imagen?",
								"imagen18", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						68,
						true,
						"Dragon Ball: Revenge of King Piccolo",
						new Pregunta(
								18,
								"¿Qué videojuego es el que se corresponde con el de la imagen?",
								"imagen18", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						69,
						false,
						"Dragon Ball: Advance adventure",
						new Pregunta(
								18,
								"¿Qué videojuego es el que se corresponde con el de la imagen?",
								"imagen18", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						70,
						false,
						"Dragon Ball Z Budokai",
						new Pregunta(
								18,
								"¿Qué videojuego es el que se corresponde con el de la imagen?",
								"imagen18", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						71,
						false,
						"Chichi",
						new Pregunta(
								19,
								"¿Cómo se llama el curioso personaje de la fotografía?",
								"imagen19", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						72,
						false,
						"Marron",
						new Pregunta(
								19,
								"¿Cómo se llama el curioso personaje de la fotografía?",
								"imagen19", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						73,
						true,
						"Grey",
						new Pregunta(
								19,
								"¿Cómo se llama el curioso personaje de la fotografía?",
								"imagen19", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						74,
						false,
						"Snow",
						new Pregunta(
								19,
								"¿Cómo se llama el curioso personaje de la fotografía?",
								"imagen19", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						75,
						false,
						"Kaioh Sama",
						new Pregunta(
								20,
								"¿Cuál es el nombre del guardian de la puerta del otro mundo?",
								"imagen20", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						76,
						false,
						"Ro Kaioh Shin",
						new Pregunta(
								20,
								"¿Cuál es el nombre del guardian de la puerta del otro mundo?",
								"imagen20", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						77,
						false,
						"Kibito",
						new Pregunta(
								20,
								"¿Cuál es el nombre del guardian de la puerta del otro mundo?",
								"imagen20", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						78,
						true,
						"Enma Daioh",
						new Pregunta(
								20,
								"¿Cuál es el nombre del guardian de la puerta del otro mundo?",
								"imagen20", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						79,
						true,
						"Se debe al comportamiento de asimilación de un microchip inteligente",
						new Pregunta(
								21,
								"¿A qué se debe el aspecto que presenta Cooler en esta imagen?",
								"imagen21", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						80,
						false,
						"Se debe a la reconstrucción de su cuerpo tras su enfrentamiento con Goku",
						new Pregunta(
								21,
								"¿A qué se debe el aspecto que presenta Cooler en esta imagen?",
								"imagen21", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						81,
						false,
						"Se debe a los arreglos que el Dr. Gero introdujo en su cuerpo",
						new Pregunta(
								21,
								"¿A qué se debe el aspecto que presenta Cooler en esta imagen?",
								"imagen21", ciencia, 2)));
		getRespuestaDAO().create(
				new Respuesta(82, false, "Bulma", new Pregunta(22,
						"¿Cómo se llama la novia de Son Gohan?", "imagen22",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(83, true, "Videl", new Pregunta(22,
						"¿Cómo se llama la novia de Son Gohan?", "imagen22",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(84, false, "Marron", new Pregunta(22,
						"¿Cómo se llama la novia de Son Gohan?", "imagen22",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(85, false, "Pasta", new Pregunta(22,
						"¿Cómo se llama la novia de Son Gohan?", "imagen22",
						dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						86,
						false,
						"22º Tenkaichi Budokai",
						new Pregunta(
								23,
								"¿En qué edición del Tenkaichi Budokai participó Punta, el personaje de la imagen?",
								"imagen23", torneos, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						87,
						false,
						"23º Tenkaichi Budokai",
						new Pregunta(
								23,
								"¿En qué edición del Tenkaichi Budokai participó Punta, el personaje de la imagen?",
								"imagen23", torneos, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						88,
						false,
						"24º Tenkaichi Budokai",
						new Pregunta(
								23,
								"¿En qué edición del Tenkaichi Budokai participó Punta, el personaje de la imagen?",
								"imagen23", torneos, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						89,
						true,
						"25º Tenkaichi Budokai",
						new Pregunta(
								23,
								"¿En qué edición del Tenkaichi Budokai participó Punta, el personaje de la imagen?",
								"imagen23", torneos, 2)));
		getRespuestaDAO().create(
				new Respuesta(90, false, "Nappa", new Pregunta(24,
						"¿Cuál es el nombre del principe de los saiyans?",
						"imagen24", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(91, false, "Raditz", new Pregunta(24,
						"¿Cuál es el nombre del principe de los saiyans?",
						"imagen24", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(92, true, "Vegeta", new Pregunta(24,
						"¿Cuál es el nombre del principe de los saiyans?",
						"imagen24", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(93, false, "Bardock", new Pregunta(24,
						"¿Cuál es el nombre del principe de los saiyans?",
						"imagen24", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						94,
						true,
						"Tercera fase",
						new Pregunta(
								25,
								"¿En qué fase se encuentra Freezer en esta monstruosa forma?",
								"imagen25", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						95,
						false,
						"Segunda fase",
						new Pregunta(
								25,
								"¿En qué fase se encuentra Freezer en esta monstruosa forma?",
								"imagen25", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						96,
						false,
						"Cuarta fase",
						new Pregunta(
								25,
								"¿En qué fase se encuentra Freezer en esta monstruosa forma?",
								"imagen25", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						97,
						false,
						"Primera fase",
						new Pregunta(
								25,
								"¿En qué fase se encuentra Freezer en esta monstruosa forma?",
								"imagen25", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(98, false, "Goku + Vegeta", new Pregunta(26,
						"¿De qué dos luchadores es fusión este personaje?",
						"imagen26", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(99, true, "Uub + Buu", new Pregunta(26,
						"¿De qué dos luchadores es fusión este personaje?",
						"imagen26", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(100, false, "Baby + Vegeta", new Pregunta(26,
						"¿De qué dos luchadores es fusión este personaje?",
						"imagen26", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(101, false, "Kibito + Kaioh Shin", new Pregunta(
						26, "¿De qué dos luchadores es fusión este personaje?",
						"imagen26", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						102,
						false,
						"Super C17",
						new Pregunta(
								27,
								"¿Por qué personaje es poseído Vegeta en Dragon Ball GT?",
								"imagen27", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						103,
						false,
						"Redic",
						new Pregunta(
								27,
								"¿Por qué personaje es poseído Vegeta en Dragon Ball GT?",
								"imagen27", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						104,
						true,
						"Baby",
						new Pregunta(
								27,
								"¿Por qué personaje es poseído Vegeta en Dragon Ball GT?",
								"imagen27", ciencia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						105,
						false,
						"Dr. Myu",
						new Pregunta(
								27,
								"¿Por qué personaje es poseído Vegeta en Dragon Ball GT?",
								"imagen27", ciencia, 1)));
		getRespuestaDAO().create(
				new Respuesta(106, false, "Dos", new Pregunta(28,
						"¿Cuántas veces muere y resucita Chaoz en el manga?",
						"imagen28", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(107, false, "Una", new Pregunta(28,
						"¿Cuántas veces muere y resucita Chaoz en el manga?",
						"imagen28", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(108, false, "Cuatro", new Pregunta(28,
						"¿Cuántas veces muere y resucita Chaoz en el manga?",
						"imagen28", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(109, true, "Tres", new Pregunta(28,
						"¿Cuántas veces muere y resucita Chaoz en el manga?",
						"imagen28", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(110, true, "Pan", new Pregunta(29,
						"¿Cómo se llama la nieta de Goku?", "imagen29", dragon,
						1)));
		getRespuestaDAO().create(
				new Respuesta(111, false, "Bra", new Pregunta(29,
						"¿Cómo se llama la nieta de Goku?", "imagen29", dragon,
						1)));
		getRespuestaDAO().create(
				new Respuesta(112, false, "Videl", new Pregunta(29,
						"¿Cómo se llama la nieta de Goku?", "imagen29", dragon,
						1)));
		getRespuestaDAO().create(
				new Respuesta(113, false, "Marron", new Pregunta(29,
						"¿Cómo se llama la nieta de Goku?", "imagen29", dragon,
						1)));
		getRespuestaDAO()
				.create(new Respuesta(
						114,
						true,
						"Xbox, Play Station 2 y Game Cube",
						new Pregunta(
								30,
								"¿Para qué plataforma estuvo disponible el juego 'Dragon Ball Z: SAGAS'?",
								"imagen30", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						115,
						false,
						"Xbox 360, Play Station 3 y Wii",
						new Pregunta(
								30,
								"¿Para qué plataforma estuvo disponible el juego 'Dragon Ball Z: SAGAS'?",
								"imagen30", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						116,
						false,
						"Play Station 2 y Wii",
						new Pregunta(
								30,
								"¿Para qué plataforma estuvo disponible el juego 'Dragon Ball Z: SAGAS'?",
								"imagen30", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						117,
						false,
						"Game Cube y Xbox",
						new Pregunta(
								30,
								"¿Para qué plataforma estuvo disponible el juego 'Dragon Ball Z: SAGAS'?",
								"imagen30", ciencia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						118,
						false,
						"C13",
						new Pregunta(
								31,
								"¿Cómo se llama este androide completamente artificial?",
								"imagen31", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						119,
						true,
						"C16",
						new Pregunta(
								31,
								"¿Cómo se llama este androide completamente artificial?",
								"imagen31", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						120,
						false,
						"C15",
						new Pregunta(
								31,
								"¿Cómo se llama este androide completamente artificial?",
								"imagen31", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						121,
						false,
						"C14",
						new Pregunta(
								31,
								"¿Cómo se llama este androide completamente artificial?",
								"imagen31", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(122, false, "Muchi Mochi", new Pregunta(32,
						"¿Quién es este personaje?", "imagen32", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(123, true, "Loud", new Pregunta(32,
						"¿Quién es este personaje?", "imagen32", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(124, false, "Redic", new Pregunta(32,
						"¿Quién es este personaje?", "imagen32", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(125, false, "Sonpara", new Pregunta(32,
						"¿Quién es este personaje?", "imagen32", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(126, false, "Gyumaoh", new Pregunta(33,
						"¿Quién es este personaje?", "imagen33", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(127, false, "Kame Senin", new Pregunta(33,
						"¿Quién es este personaje?", "imagen33", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(128, true, "Son Gohan Ojichan", new Pregunta(33,
						"¿Quién es este personaje?", "imagen33", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(129, false, "Tsuru Senin", new Pregunta(33,
						"¿Quién es este personaje?", "imagen33", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(130, false, "Dentro de su mochila", new Pregunta(
						34,
						"¿Dónde esconde Bulma el diamante de la fotografía?",
						"imagen34", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						131,
						false,
						"En ningún momento lo lleva escondido",
						new Pregunta(
								34,
								"¿Dónde esconde Bulma el diamante de la fotografía?",
								"imagen34", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						132,
						true,
						"Se lo mete en la entrepierna",
						new Pregunta(
								34,
								"¿Dónde esconde Bulma el diamante de la fotografía?",
								"imagen34", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						133,
						false,
						"Lo lleva metido en el escote",
						new Pregunta(
								34,
								"¿Dónde esconde Bulma el diamante de la fotografía?",
								"imagen34", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(134, false, "La vida eterna", new Pregunta(35,
						"¿Qué deseo pidió Piccolo Daimaoh al dragon Shenron?",
						"imagen35", historia, 1)));
		getRespuestaDAO().create(
				new Respuesta(135, false, "Reinar en el mundo", new Pregunta(
						35,
						"¿Qué deseo pidió Piccolo Daimaoh al dragon Shenron?",
						"imagen35", historia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						136,
						false,
						"Reinar en el universo",
						new Pregunta(
								35,
								"¿Qué deseo pidió Piccolo Daimaoh al dragon Shenron?",
								"imagen35", historia, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						137,
						true,
						"Volver a recuperar su juventud",
						new Pregunta(
								35,
								"¿Qué deseo pidió Piccolo Daimaoh al dragon Shenron?",
								"imagen35", historia, 1)));
		getRespuestaDAO().create(
				new Respuesta(138, false, "Tao Pai Pai", new Pregunta(36,
						"¿Cómo se llama este personaje de tres ojos?",
						"imagen36", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(139, false, "Chaoz", new Pregunta(36,
						"¿Cómo se llama este personaje de tres ojos?",
						"imagen36", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(140, false, "Tsuru Senin", new Pregunta(36,
						"¿Cómo se llama este personaje de tres ojos?",
						"imagen36", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(141, true, "Ten Shin Han", new Pregunta(36,
						"¿Cómo se llama este personaje de tres ojos?",
						"imagen36", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						142,
						true,
						"Redic",
						new Pregunta(
								37,
								"¿Contra qué enemigo se transforma Goku por primera vez en SSJ en Dragon Ball GT?",
								"imagen37", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						143,
						false,
						"Don Kear",
						new Pregunta(
								37,
								"¿Contra qué enemigo se transforma Goku por primera vez en SSJ en Dragon Ball GT?",
								"imagen37", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						144,
						false,
						"Pilaf",
						new Pregunta(
								37,
								"¿Contra qué enemigo se transforma Goku por primera vez en SSJ en Dragon Ball GT?",
								"imagen37", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						145,
						false,
						"Muchi Mochi",
						new Pregunta(
								37,
								"¿Contra qué enemigo se transforma Goku por primera vez en SSJ en Dragon Ball GT?",
								"imagen37", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						146,
						true,
						"Zarbon",
						new Pregunta(
								38,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen38", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						147,
						false,
						"Dodoria",
						new Pregunta(
								38,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen38", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						148,
						false,
						"Ginew",
						new Pregunta(
								38,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen38", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						149,
						false,
						"Reecom",
						new Pregunta(
								38,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen38", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(150, false, "Kuriza", new Pregunta(39,
						"Si este personaje no es Freezer, ¿de quién se trata?",
						"imagen39", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(151, true, "Chilled", new Pregunta(39,
						"Si este personaje no es Freezer, ¿de quién se trata?",
						"imagen39", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(152, false, "Cooler", new Pregunta(39,
						"Si este personaje no es Freezer, ¿de quién se trata?",
						"imagen39", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(153, false, "King Cold", new Pregunta(39,
						"Si este personaje no es Freezer, ¿de quién se trata?",
						"imagen39", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						154,
						false,
						"Es su hermano pequeño",
						new Pregunta(
								40,
								"¿Qué (supuesto) parentesco comparte este personaje con Freezer?",
								"imagen40", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						155,
						true,
						"Es su hijo",
						new Pregunta(
								40,
								"¿Qué (supuesto) parentesco comparte este personaje con Freezer?",
								"imagen40", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						156,
						false,
						"Es su sobrino",
						new Pregunta(
								40,
								"¿Qué (supuesto) parentesco comparte este personaje con Freezer?",
								"imagen40", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						157,
						false,
						"No es familia suya",
						new Pregunta(
								40,
								"¿Qué (supuesto) parentesco comparte este personaje con Freezer?",
								"imagen40", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						158,
						false,
						"Zarbon",
						new Pregunta(
								41,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen41", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						159,
						false,
						"Ghourd",
						new Pregunta(
								41,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen41", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						160,
						true,
						"Dodoria",
						new Pregunta(
								41,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen41", dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						161,
						false,
						"Burter",
						new Pregunta(
								41,
								"¿Cómo se llama este importante personaje del ejército de Freezer?",
								"imagen41", dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(162, false, "Chih Shinron", new Pregunta(42,
						"¿Cuál es el nombre de este dragón maligno?",
						"imagen42", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(163, false, "Suu Shinron", new Pregunta(42,
						"¿Cuál es el nombre de este dragón maligno?",
						"imagen42", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(164, true, "Ryan Shinron", new Pregunta(42,
						"¿Cuál es el nombre de este dragón maligno?",
						"imagen42", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(165, false, "San Shinron", new Pregunta(42,
						"¿Cuál es el nombre de este dragón maligno?",
						"imagen42", dragon, 2)));
		getRespuestaDAO().create(
				new Respuesta(166, false, "Vegeta", new Pregunta(43,
						"¿Cómo se llama este poderoso enemigo?", "imagen43",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(167, false, "Baby", new Pregunta(43,
						"¿Cómo se llama este poderoso enemigo?", "imagen43",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(168, false, "Majin Buu", new Pregunta(43,
						"¿Cómo se llama este poderoso enemigo?", "imagen43",
						dragon, 1)));
		getRespuestaDAO().create(
				new Respuesta(169, true, "Vegeta-Baby", new Pregunta(43,
						"¿Cómo se llama este poderoso enemigo?", "imagen43",
						dragon, 1)));
		getRespuestaDAO()
				.create(new Respuesta(
						170,
						false,
						"Saiyanos",
						new Pregunta(
								44,
								"¿El odio de que antigua civilización tiene acumulado Hatchiyack?",
								"imagen44", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						171,
						false,
						"Namekianos",
						new Pregunta(
								44,
								"¿El odio de que antigua civilización tiene acumulado Hatchiyack?",
								"imagen44", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						172,
						false,
						"Terrícolas",
						new Pregunta(
								44,
								"¿El odio de que antigua civilización tiene acumulado Hatchiyack?",
								"imagen44", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						173,
						true,
						"Tsufurs",
						new Pregunta(
								44,
								"¿El odio de que antigua civilización tiene acumulado Hatchiyack?",
								"imagen44", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						174,
						true,
						"Kuriza",
						new Pregunta(
								45,
								"¿Cómo se llama este gracioso villano del manga Neko Majin Z?",
								"imagen45", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						175,
						false,
						"Neko Majin",
						new Pregunta(
								45,
								"¿Cómo se llama este gracioso villano del manga Neko Majin Z?",
								"imagen45", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						176,
						false,
						"Onio",
						new Pregunta(
								45,
								"¿Cómo se llama este gracioso villano del manga Neko Majin Z?",
								"imagen45", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						177,
						false,
						"Thunderbolt",
						new Pregunta(
								45,
								"¿Cómo se llama este gracioso villano del manga Neko Majin Z?",
								"imagen45", dragon, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						178,
						true,
						"Capítulo 54",
						new Pregunta(
								46,
								"¿En qué episodio de Dragon Ball Z aparece el personaje de la imagen?",
								"imagen46", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						179,
						false,
						"Capítulo 55",
						new Pregunta(
								46,
								"¿En qué episodio de Dragon Ball Z aparece el personaje de la imagen?",
								"imagen46", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						180,
						false,
						"Capítulo 56",
						new Pregunta(
								46,
								"¿En qué episodio de Dragon Ball Z aparece el personaje de la imagen?",
								"imagen46", historia, 2)));
		getRespuestaDAO()
				.create(new Respuesta(
						181,
						false,
						"Capítulo 57",
						new Pregunta(
								46,
								"¿En qué episodio de Dragon Ball Z aparece el personaje de la imagen?",
								"imagen46", historia, 2)));
		getRespuestaDAO().create(
				new Respuesta(182, true, "No", new Pregunta(47,
						"¿Comparte Turles algún vínculo familiar con Goku?",
						"imagen47", historia, 1)));
		getRespuestaDAO().create(
				new Respuesta(183, false, "Sí", new Pregunta(47,
						"¿Comparte Turles algún vínculo familiar con Goku?",
						"imagen47", historia, 1)));
		getRespuestaDAO().create(
				new Respuesta(184, false, "Raditz", pregunta48));
		getRespuestaDAO()
				.create(new Respuesta(185, true, "Tarble", pregunta48));
		getRespuestaDAO().create(
				new Respuesta(186, false, "Turles", pregunta48));
		getRespuestaDAO()
				.create(new Respuesta(187, false, "Broly", pregunta48));
		getRespuestaDAO().create(
				new Respuesta(188, false, "Marron", pregunta49));
		getRespuestaDAO().create(
				new Respuesta(189, false, "Misato", pregunta49));
		getRespuestaDAO().create(
				new Respuesta(190, false, "Ritsuko", pregunta49));
		getRespuestaDAO().create(
				new Respuesta(191, true,
						"Todas las respuestas anteriores son falsas",
						pregunta49));
		getRespuestaDAO().create(
				new Respuesta(184, false, "Chichi", pregunta50));
		getRespuestaDAO()
				.create(new Respuesta(185, false, "Lunch", pregunta50));
		getRespuestaDAO().create(new Respuesta(186, true, "Bulma", pregunta50));
		getRespuestaDAO().create(new Respuesta(187, false, "Bra", pregunta50));
		getRespuestaDAO().create(
				new Respuesta(188, false, "Lakasei", pregunta51));
		getRespuestaDAO()
				.create(new Respuesta(189, false, "Cacao", pregunta51));
		getRespuestaDAO().create(new Respuesta(190, true, "Daizu", pregunta51));
		getRespuestaDAO().create(
				new Respuesta(191, false, "Turles", pregunta51));
		getRespuestaDAO()
				.create(new Respuesta(192, false, "Dende", pregunta52));
		getRespuestaDAO()
				.create(new Respuesta(193, false, "Kargo", pregunta52));
		getRespuestaDAO().create(new Respuesta(194, false, "Slug", pregunta52));
		getRespuestaDAO().create(
				new Respuesta(195, true, "Piccolo", pregunta52));
		getRespuestaDAO().create(
				new Respuesta(196, false, "El camino hacia el más fuerte",
						pregunta53));
		getRespuestaDAO().create(
				new Respuesta(197, false, "Aventura Mística", pregunta53));
		getRespuestaDAO()
				.create(new Respuesta(198, false,
						"La Bella Durmiente en el Castillo del Mal", pregunta53));
		getRespuestaDAO().create(
				new Respuesta(199, true, "La Leyenda del dragón Shenlong",
						pregunta53));
		getRespuestaDAO()
				.create(new Respuesta(200, true,
						"La Bella Durmiente en el Castillo del Mal", pregunta54));
		getRespuestaDAO().create(
				new Respuesta(201, false, "La Leyenda del dragón Shenlong",
						pregunta54));
		getRespuestaDAO().create(
				new Respuesta(202, false, "El camino hacia el más fuerte",
						pregunta54));
		getRespuestaDAO().create(
				new Respuesta(203, false, "Aventura Mística", pregunta54));
		getRespuestaDAO().create(
				new Respuesta(204, true, "Aventura Mística", pregunta55));
		getRespuestaDAO()
				.create(new Respuesta(205, false,
						"La Bella Durmiente en el Castillo del Mal", pregunta55));
		getRespuestaDAO().create(
				new Respuesta(206, false, "El camino hacia el más fuerte",
						pregunta55));
		getRespuestaDAO().create(
				new Respuesta(207, false, "La Leyenda del dragón Shenlong",
						pregunta55));
		getRespuestaDAO().create(
				new Respuesta(208, false,
						"¡¡Arde!! Super batalla feroz, extrema y ardiente",
						pregunta56));
		getRespuestaDAO().create(
				new Respuesta(209, true, "El hombre más fuerte de este mundo",
						pregunta56));
		getRespuestaDAO()
				.create(new Respuesta(
						210,
						false,
						"¡¡La galaxia está en peligro!! Un guerrero Súper increíble",
						pregunta56));
		getRespuestaDAO().create(
				new Respuesta(211, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta56));
		getRespuestaDAO().create(
				new Respuesta(212, false, "Chica Umino", pregunta57));
		getRespuestaDAO().create(
				new Respuesta(213, true, "Naho Ooishi", pregunta57));
		getRespuestaDAO().create(
				new Respuesta(214, false, "Yukari Ichijo", pregunta57));
		getRespuestaDAO().create(
				new Respuesta(215, false, "Ai Yazawa", pregunta57));
		getRespuestaDAO()
				.create(new Respuesta(
						216,
						false,
						"¡¡La galaxia está en peligro!! Un guerrero Súper increíble",
						pregunta58));
		getRespuestaDAO().create(
				new Respuesta(217, false, "El hombre más fuerte de este mundo",
						pregunta58));
		getRespuestaDAO().create(
				new Respuesta(218, true,
						"Super batalla decisiva por el planeta Tierra",
						pregunta58));
		getRespuestaDAO().create(
				new Respuesta(219, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta58));
		getRespuestaDAO().create(
				new Respuesta(220, false,
						"¡¡Arde!! Super batalla feroz, extrema y ardiente",
						pregunta59));
		getRespuestaDAO()
				.create(new Respuesta(
						221,
						false,
						"¡Los dos en peligro! Los Súper guerreros nunca duermen",
						pregunta59));
		getRespuestaDAO().create(
				new Respuesta(222, true, "Goku es un Super Saiyajin",
						pregunta59));
		getRespuestaDAO()
				.create(new Respuesta(
						223,
						false,
						"¡¡La galaxia está en peligro!! Un guerrero Súper increíble",
						pregunta59));
		getRespuestaDAO()
				.create(new Respuesta(
						224,
						false,
						"¡Explota el golpe del dragón! Si Goku no lo logra ¿quién lo hará?",
						pregunta60));
		getRespuestaDAO().create(
				new Respuesta(225, false, "El hombre más fuerte de este mundo",
						pregunta60));
		getRespuestaDAO()
				.create(new Respuesta(
						226,
						false,
						"¡Los dos en peligro! Los Súper guerreros nunca duermen",
						pregunta60));
		getRespuestaDAO().create(
				new Respuesta(227, true,
						"Los increíbles más poderosos contra el más poderoso",
						pregunta60));
		getRespuestaDAO()
				.create(new Respuesta(
						228,
						false,
						"¡Los Super guerreros vencen! La victoria será para mi",
						pregunta61));
		getRespuestaDAO()
				.create(new Respuesta(
						229,
						false,
						"¡Explota el golpe del dragón! Si Goku no lo logra ¿quién lo hará?",
						pregunta61));
		getRespuestaDAO().create(
				new Respuesta(230, false, "El hombre más fuerte de este mundo",
						pregunta61));
		getRespuestaDAO().create(
				new Respuesta(231, true,
						"¡¡Choque!! Guerreros de 10.000.000.000 de poder",
						pregunta61));
		getRespuestaDAO().create(
				new Respuesta(232, true,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta62));
		getRespuestaDAO().create(
				new Respuesta(233, false,
						"¡El renacer de la fusión! Goku y Vegeta", pregunta62));
		getRespuestaDAO()
				.create(new Respuesta(
						234,
						false,
						"¡Los Super guerreros vencen! La victoria será para mi",
						pregunta62));
		getRespuestaDAO().create(
				new Respuesta(235, false,
						"¡¡Choque!! Guerreros de 10.000.000.000 de poder",
						pregunta62));
		getRespuestaDAO().create(
				new Respuesta(236, true,
						"¡¡Arde!! Super batalla feroz, extrema y ardiente",
						pregunta63));
		getRespuestaDAO().create(
				new Respuesta(237, false,
						"¡El renacer de la fusión! Goku y Vegeta", pregunta63));
		getRespuestaDAO()
				.create(new Respuesta(
						238,
						false,
						"¡Explota el golpe del dragón! Si Goku no lo logra ¿quién lo hará?",
						pregunta63));
		getRespuestaDAO()
				.create(new Respuesta(
						239,
						false,
						"¡Los Super guerreros vencen! La victoria será para mi",
						pregunta63));
		getRespuestaDAO().create(
				new Respuesta(240, false,
						"¡¡Choque!! Guerreros de 10.000.000.000 de poder",
						pregunta64));
		getRespuestaDAO()
				.create(new Respuesta(
						241,
						true,
						"¡¡La galaxia está en peligro!! Un guerrero Súper increíble",
						pregunta64));
		getRespuestaDAO()
				.create(new Respuesta(
						242,
						false,
						"¡Explota el golpe del dragón! Si Goku no lo logra ¿quién lo hará?",
						pregunta64));
		getRespuestaDAO().create(
				new Respuesta(243, false, "El hombre más fuerte de este mundo",
						pregunta64));
		getRespuestaDAO().create(
				new Respuesta(244, false, "El hombre más fuerte de este mundo",
						pregunta65));
		getRespuestaDAO().create(
				new Respuesta(245, true,
						"¡El renacer de la fusión! Goku y Vegeta", pregunta65));
		getRespuestaDAO().create(
				new Respuesta(246, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta65));
		getRespuestaDAO().create(
				new Respuesta(247, false,
						"Super batalla decisiva por el planeta Tierra",
						pregunta65));
		getRespuestaDAO().create(
				new Respuesta(248, false,
						"¡¡Choque!! Guerreros de 10.000.000.000 de poder",
						pregunta66));
		getRespuestaDAO()
				.create(new Respuesta(
						249,
						true,
						"¡Explota el golpe del dragón! Si Goku no lo logra ¿quién lo hará?",
						pregunta66));
		getRespuestaDAO().create(
				new Respuesta(250, false, "El hombre más fuerte de este mundo",
						pregunta66));
		getRespuestaDAO().create(
				new Respuesta(251, false,
						"Super batalla decisiva por el planeta Tierra",
						pregunta66));
		getRespuestaDAO().create(
				new Respuesta(252, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta67));
		getRespuestaDAO().create(
				new Respuesta(253, false,
						"Super batalla decisiva por el planeta Tierra",
						pregunta67));
		getRespuestaDAO()
				.create(new Respuesta(
						254,
						true,
						"¡Los dos en peligro! Los Súper guerreros nunca duermen",
						pregunta67));
		getRespuestaDAO().create(
				new Respuesta(255, false, "El hombre más fuerte de este mundo",
						pregunta67));
		getRespuestaDAO().create(
				new Respuesta(256, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta68));
		getRespuestaDAO().create(
				new Respuesta(257, false,
						"¡¡Choque!! Guerreros de 10.000.000.000 de poder",
						pregunta68));
		getRespuestaDAO()
				.create(new Respuesta(
						258,
						true,
						"¡Los Super guerreros vencen! La victoria será para mi",
						pregunta68));
		getRespuestaDAO().create(
				new Respuesta(259, false,
						"¡El renacer de la fusión! Goku y Vegeta", pregunta68));
		getRespuestaDAO().create(
				new Respuesta(260, false, "Dragon Ball Z Budokai Tenkaichi",
						pregunta69));
		getRespuestaDAO()
				.create(new Respuesta(261, false, "Dragon Ball Z Budokai 3",
						pregunta69));
		getRespuestaDAO().create(
				new Respuesta(262, false, "Dragon Ball Z Budokai", pregunta69));
		getRespuestaDAO()
				.create(new Respuesta(263, true, "Dragon Ball Z Budokai 2",
						pregunta69));
		getRespuestaDAO().create(
				new Respuesta(264, false, "Dragon Ball Ranging Blast",
						pregunta70));
		getRespuestaDAO().create(
				new Respuesta(265, false, "Dragon Ball Ranging Blast 2",
						pregunta70));
		getRespuestaDAO().create(
				new Respuesta(266, false, "Dragon Ball Ultimate Tenkaichi",
						pregunta70));
		getRespuestaDAO().create(
				new Respuesta(267, true, "Dragon Ball Online", pregunta70));
		getRespuestaDAO().create(
				new Respuesta(268, false, "Dragon Ball El legado de Goku",
						pregunta71));
		getRespuestaDAO().create(
				new Respuesta(269, false, "Dragon Ball GT Transformations",
						pregunta71));
		getRespuestaDAO().create(
				new Respuesta(270, true, "Dragon Ball El legado de Goku II",
						pregunta71));
		getRespuestaDAO()
				.create(new Respuesta(271, false, "Dragon Ball Buu's Fury",
						pregunta71));
		getRespuestaDAO().create(
				new Respuesta(272, true, "21º Tenkaichi Budokai", pregunta72));
		getRespuestaDAO().create(
				new Respuesta(273, false, "22º Tenkaichi Budokai", pregunta72));
		getRespuestaDAO().create(
				new Respuesta(274, false, "23º Tenkaichi Budokai", pregunta72));
		getRespuestaDAO().create(
				new Respuesta(275, false, "24º Tenkaichi Budokai", pregunta72));
		getRespuestaDAO().create(new Respuesta(276, false, "C8", pregunta73));
		getRespuestaDAO().create(new Respuesta(277, false, "C13", pregunta73));
		getRespuestaDAO().create(new Respuesta(278, false, "C14", pregunta73));
		getRespuestaDAO().create(new Respuesta(279, true, "C15", pregunta73));
		getRespuestaDAO()
				.create(new Respuesta(280, false,
						"Era luchadora de la escuela de Mr. Satán", pregunta74));
		getRespuestaDAO().create(
				new Respuesta(281, true, "Era profesora de autoescuela",
						pregunta74));
		getRespuestaDAO()
				.create(new Respuesta(282, false,
						"Era profesora del Orange High Star School", pregunta74));
		getRespuestaDAO().create(
				new Respuesta(283, false, "Con esta crisis estaba en paro",
						pregunta74));
		getRespuestaDAO().create(
				new Respuesta(284, false, "Buu +  Baby", pregunta75));
		getRespuestaDAO().create(
				new Respuesta(285, false, "Janemba + Buu", pregunta75));
		getRespuestaDAO().create(
				new Respuesta(286, false, "Cell + Buu", pregunta75));
		getRespuestaDAO().create(
				new Respuesta(287, true, "Janemba + Baby", pregunta75));
		getRespuestaDAO().create(
				new Respuesta(288, true, "Son Goku", pregunta76));
		getRespuestaDAO().create(
				new Respuesta(289, false, "Son Gohan", pregunta76));
		getRespuestaDAO().create(
				new Respuesta(290, false, "Son Goten", pregunta76));
		getRespuestaDAO().create(
				new Respuesta(291, false, "Goku Jr.", pregunta76));
		getRespuestaDAO().create(
				new Respuesta(292, false, "En la entrada al Otro Mundo",
						pregunta77));
		getRespuestaDAO().create(
				new Respuesta(293, true, "En el infierno", pregunta77));
		getRespuestaDAO().create(
				new Respuesta(294, false, "En el planeta de Kaioh Sama",
						pregunta77));
		getRespuestaDAO().create(
				new Respuesta(295, false,
						"En el planeta de los dioses Kaioh Shin", pregunta77));
		getRespuestaDAO()
				.create(new Respuesta(296, false, "Bulma", pregunta78));
		getRespuestaDAO().create(
				new Respuesta(297, false, "Chichi", pregunta78));
		getRespuestaDAO().create(new Respuesta(298, true, "Lunch", pregunta78));
		getRespuestaDAO().create(new Respuesta(299, false, "Pan", pregunta78));
		getRespuestaDAO().create(
				new Respuesta(300, false, "Kame Hame Ha", pregunta79));
		getRespuestaDAO().create(
				new Respuesta(301, false, "Shunkanido", pregunta79));
		getRespuestaDAO().create(
				new Respuesta(302, false, "Ma Sen Ko", pregunta79));
		getRespuestaDAO().create(
				new Respuesta(303, true, "Taiyoken", pregunta79));
		getRespuestaDAO().create(
				new Respuesta(304, true, "Goten y Trunks", pregunta80));
		getRespuestaDAO().create(
				new Respuesta(305, false, "Goku y Vegeta", pregunta80));
		getRespuestaDAO().create(
				new Respuesta(306, false, "Goku y Satán", pregunta80));
		getRespuestaDAO().create(
				new Respuesta(307, false, "Uub y Buu", pregunta80));
		getRespuestaDAO().create(
				new Respuesta(308, false, "Se ve explendido con ella",
						pregunta81));
		getRespuestaDAO()
				.create(new Respuesta(
						309,
						true,
						"Un amante de la belleza como él odia este aspecto monstruoso",
						pregunta81));
		getRespuestaDAO().create(
				new Respuesta(310, false, "Le es indiferente", pregunta81));
		getRespuestaDAO()
				.create(new Respuesta(
						311,
						false,
						"Le encanta demostrar esta forma monstruosa para intimidar",
						pregunta81));
		getRespuestaDAO().create(new Respuesta(312, false, "Frog", pregunta82));
		getRespuestaDAO().create(new Respuesta(313, false, "Cell", pregunta82));
		getRespuestaDAO().create(new Respuesta(314, true, "Gelto", pregunta82));
		getRespuestaDAO().create(
				new Respuesta(315, false, "Zarbon", pregunta82));
		getRespuestaDAO().create(
				new Respuesta(316, false, "Sargento", pregunta83));
		getRespuestaDAO().create(
				new Respuesta(317, false, "General", pregunta83));
		getRespuestaDAO().create(
				new Respuesta(318, false, "Soldado", pregunta83));
		getRespuestaDAO().create(
				new Respuesta(319, true, "Coronel", pregunta83));
		getRespuestaDAO().create(
				new Respuesta(320, true, "Serippa", pregunta84));
		getRespuestaDAO().create(new Respuesta(321, false, "Pan", pregunta84));
		getRespuestaDAO().create(new Respuesta(322, false, "Bra", pregunta84));
		getRespuestaDAO().create(
				new Respuesta(323, false, "Chichi", pregunta84));
		getRespuestaDAO().create(
				new Respuesta(324, false, "Son Goku", pregunta85));
		getRespuestaDAO().create(
				new Respuesta(325, true, "Son Gohan", pregunta85));
		getRespuestaDAO().create(
				new Respuesta(326, false, "Son Goten", pregunta85));
		getRespuestaDAO().create(
				new Respuesta(327, false, "Trunks", pregunta85));
		getRespuestaDAO().create(
				new Respuesta(328, false, "Vegeta + Gohan", pregunta86));
		getRespuestaDAO().create(
				new Respuesta(329, false, "Goku + Gohan", pregunta86));
		getRespuestaDAO().create(
				new Respuesta(330, true, "Vegeta + Kakarotto", pregunta86));
		getRespuestaDAO().create(
				new Respuesta(331, false, "Este personaje no es una fusión",
						pregunta86));
		getRespuestaDAO().create(
				new Respuesta(332, false,
						"Para resucitar a Bora, el padre de Upah", pregunta87));
		getRespuestaDAO()
				.create(new Respuesta(
						333,
						false,
						"Para resucitar a todos los caídos en la batalla con Piccolo Daimaoh",
						pregunta87));
		getRespuestaDAO()
				.create(new Respuesta(
						334,
						false,
						"Quería devolver a la vida a todas las víctimas de Vegeta",
						pregunta87));
		getRespuestaDAO()
				.create(new Respuesta(
						335,
						true,
						"Quería devolver a la vida al ejercito de La Tierra, aniquilado por Cell",
						pregunta87));
		getRespuestaDAO()
				.create(new Respuesta(336, true, "Trunks", pregunta88));
		getRespuestaDAO().create(
				new Respuesta(337, false, "Tapión", pregunta88));
		getRespuestaDAO().create(
				new Respuesta(338, false, "Son Goten", pregunta88));
		getRespuestaDAO().create(
				new Respuesta(339, false, "Son Gohan", pregunta88));
		getRespuestaDAO().create(
				new Respuesta(340, false, "Semifinalista", pregunta89));
		getRespuestaDAO().create(
				new Respuesta(341, false, "Finalista", pregunta89));
		getRespuestaDAO().create(
				new Respuesta(342, true, "Campeón", pregunta89));
		getRespuestaDAO().create(
				new Respuesta(343, false, "No participó", pregunta89));
		getRespuestaDAO().create(new Respuesta(344, true, "Una", pregunta90));
		getRespuestaDAO().create(new Respuesta(345, false, "Dos", pregunta90));
		getRespuestaDAO().create(new Respuesta(346, false, "Tres", pregunta90));
		getRespuestaDAO()
				.create(new Respuesta(347, false, "Siete", pregunta90));
		getRespuestaDAO().create(
				new Respuesta(348, true,
						"¡Adiós, mundo de las bolas del dragón!", pregunta91));
		getRespuestaDAO()
				.create(new Respuesta(349, false, "¡Ánimo, Súper-Gotenks!",
						pregunta91));
		getRespuestaDAO().create(
				new Respuesta(350, false,
						"Última arma secreta del ejército de la Tierra",
						pregunta91));
		getRespuestaDAO().create(
				new Respuesta(351, false, "¡Adiós, Luchadores!", pregunta91));
		getRespuestaDAO()
				.create(new Respuesta(352, false, "27 de noviembre de 1983",
						pregunta92));
		getRespuestaDAO()
				.create(new Respuesta(353, true, "27 de noviembre de 1986",
						pregunta92));
		getRespuestaDAO()
				.create(new Respuesta(354, false, "30 de noviembre de 1984",
						pregunta92));
		getRespuestaDAO()
				.create(new Respuesta(355, false, "1 de diciembre de 1984",
						pregunta93));
		getRespuestaDAO()
				.create(new Respuesta(356, false, "2 de diciembre de 1984",
						pregunta93));
		getRespuestaDAO().create(
				new Respuesta(357, true, "3 de diciembre de 1984", pregunta93));
		getRespuestaDAO()
				.create(new Respuesta(358, false, "4 de diciembre de 1984",
						pregunta93));
		getRespuestaDAO().create(
				new Respuesta(359, false, "26 de febrero de 1984", pregunta94));
		getRespuestaDAO().create(
				new Respuesta(360, false, "14 de abril de 1993", pregunta94));
		getRespuestaDAO().create(
				new Respuesta(361, false, "26 de mayo de 1985", pregunta94));
		getRespuestaDAO().create(
				new Respuesta(362, true, "26 de abril de 1989", pregunta94));
		getRespuestaDAO().create(
				new Respuesta(363, true, "5 de abril de 2009", pregunta95));
		getRespuestaDAO().create(
				new Respuesta(364, false, "5 de mayo de 2005", pregunta95));
		getRespuestaDAO().create(
				new Respuesta(365, false, "5 de abril de 2004", pregunta95));
		getRespuestaDAO().create(
				new Respuesta(366, false, "5 de abril de 1989", pregunta95));
		getRespuestaDAO().create(
				new Respuesta(367, false, "26 de mayo de 1985", pregunta96));
		getRespuestaDAO().create(
				new Respuesta(368, true, "26 de febrero de 1986", pregunta96));
		getRespuestaDAO().create(
				new Respuesta(369, false, "5 de abril de 2009", pregunta96));
		getRespuestaDAO()
				.create(new Respuesta(370, false, "3 de diciembre de 1984",
						pregunta96));
		getRespuestaDAO().create(
				new Respuesta(371, false,
						"¡El renacer de la fusión! Goku y Vegeta", pregunta97));
		getRespuestaDAO().create(
				new Respuesta(372, false,
						"Super batalla decisiva por el planeta Tierra",
						pregunta97));
		getRespuestaDAO().create(
				new Respuesta(373, true,
						"Dragon Ball Z: La batalla de los dioses", pregunta97));
		getRespuestaDAO().create(
				new Respuesta(374, false, "Aventura mística", pregunta97));
		getRespuestaDAO().create(
				new Respuesta(375, false, "Kaioken", pregunta98));
		getRespuestaDAO()
				.create(new Respuesta(376, false, "Super Saiyan Full Power",
						pregunta98));
		getRespuestaDAO()
				.create(new Respuesta(377, false, "Super Saiyan de nivel 4",
						pregunta98));
		getRespuestaDAO().create(
				new Respuesta(378, true, "Super Saiyan God", pregunta98));
		getRespuestaDAO().create(new Respuesta(379, true, "Pizza", pregunta99));
		getRespuestaDAO().create(
				new Respuesta(380, false, "Piroshki", pregunta99));
		getRespuestaDAO().create(
				new Respuesta(381, false, "Caroni", pregunta99));
		getRespuestaDAO().create(
				new Respuesta(382, false, "Zangya", pregunta99));
		getRespuestaDAO().create(
				new Respuesta(383, false, "Episodio de Bardock", pregunta100));
		getRespuestaDAO().create(
				new Respuesta(384, true, "¡Hey! Goku y sus amigos regresan",
						pregunta100));
		getRespuestaDAO()
				.create(new Respuesta(385, false,
						"Dragon Ball Z: La batalla de los dioses", pregunta100));
		getRespuestaDAO().create(
				new Respuesta(386, false, "Dragon Ball Heroes", pregunta100));
		getRespuestaDAO().create(new Respuesta(387, false, "80", pregunta101));
		getRespuestaDAO().create(new Respuesta(388, true, "85", pregunta101));
		getRespuestaDAO().create(new Respuesta(389, false, "90", pregunta101));
		getRespuestaDAO().create(new Respuesta(390, false, "95", pregunta101));
		getRespuestaDAO().create(
				new Respuesta(393, false, "Freezer", pregunta103));
		getRespuestaDAO().create(
				new Respuesta(394, false, "Dr. Willow", pregunta103));
		getRespuestaDAO().create(
				new Respuesta(395, false, "Broly", pregunta103));
		getRespuestaDAO().create(new Respuesta(396, true, "Cell", pregunta103));
		getRespuestaDAO().create(
				new Respuesta(397, true, "Hitler", pregunta104));
		getRespuestaDAO().create(
				new Respuesta(398, false, "Coronel Silver", pregunta104));
		getRespuestaDAO().create(
				new Respuesta(399, false, "Tao Pai Pai", pregunta104));
		getRespuestaDAO().create(
				new Respuesta(400, false, "Coronel Yellow", pregunta104));
		getRespuestaDAO().create(
				new Respuesta(401, false, "Pasta", pregunta105));
		getRespuestaDAO()
				.create(new Respuesta(402, true, "Husky", pregunta105));
		getRespuestaDAO().create(
				new Respuesta(403, false, "Princesa Serpiente", pregunta105));
		getRespuestaDAO().create(
				new Respuesta(404, false, "Annin", pregunta105));
		getRespuestaDAO().create(
				new Respuesta(405, false, "¡Son Goku en acción!", pregunta106));
		getRespuestaDAO().create(
				new Respuesta(406, false, "El joven que vino del futuro",
						pregunta106));
		getRespuestaDAO().create(
				new Respuesta(407, true, "La derrota de Goku", pregunta106));
		getRespuestaDAO()
				.create(new Respuesta(408, false, "Comienza el Cell Game",
						pregunta106));
		getRespuestaDAO().create(
				new Respuesta(409, false, "Dragon Ball Z: Budokai 3",
						pregunta107));
		getRespuestaDAO().create(
				new Respuesta(410, false, "Dragon Ball Z: Infinite World",
						pregunta107));
		getRespuestaDAO().create(
				new Respuesta(411, false, "Super Dragon Ball Z", pregunta107));
		getRespuestaDAO().create(
				new Respuesta(412, true,
						"Dragon Ball Z: Legend of Super Saiyan", pregunta107));
		getRespuestaDAO().create(
				new Respuesta(413, true, "Dragon Ball Z: Tap Battle",
						pregunta108));
		getRespuestaDAO().create(
				new Respuesta(414, false, "Dragon Ball Z: Hyper Dimension",
						pregunta108));
		getRespuestaDAO().create(
				new Respuesta(415, false, "Dragon Ball Z: Ultimate Tenkaichi",
						pregunta108));
		getRespuestaDAO().create(
				new Respuesta(416, false, "Dragon Ball Z: Shin Budokai",
						pregunta108));
		getRespuestaDAO().create(
				new Respuesta(417, false, "Dragon Ball Z: Super Butouden 1",
						pregunta109));
		getRespuestaDAO().create(
				new Respuesta(418, true, "Dragon Ball Z: Super Butouden 3",
						pregunta109));
		getRespuestaDAO().create(
				new Respuesta(419, false, "Dragon Ball Z: Super Butouden 2",
						pregunta109));
		getRespuestaDAO().create(
				new Respuesta(420, false, "Dragon Ball Z: Hyper Dimension",
						pregunta109));
		getRespuestaDAO().create(
				new Respuesta(421, false, "Bulma", pregunta110));
		getRespuestaDAO()
				.create(new Respuesta(422, false, "Puar", pregunta110));
		getRespuestaDAO().create(
				new Respuesta(423, true, "Oolong", pregunta110));
		getRespuestaDAO().create(
				new Respuesta(424, false, "Lunch", pregunta110));
		getRespuestaDAO().create(
				new Respuesta(425, false, "Imega", pregunta111));
		getRespuestaDAO()
				.create(new Respuesta(426, false, "Meat", pregunta111));
		getRespuestaDAO()
				.create(new Respuesta(427, false, "Litt", pregunta111));
		getRespuestaDAO()
				.create(new Respuesta(428, true, "Namek", pregunta111));
		getRespuestaDAO()
				.create(new Respuesta(429, true, "Imega", pregunta112));
		getRespuestaDAO().create(
				new Respuesta(430, false, "La Tierra", pregunta112));
		getRespuestaDAO().create(
				new Respuesta(431, false, "Namek", pregunta112));
		getRespuestaDAO().create(
				new Respuesta(432, false, "Vegeta", pregunta112));
		getRespuestaDAO().create(
				new Respuesta(433, false, "Pasta está enamorada de Yamcha",
						pregunta113));
		getRespuestaDAO().create(
				new Respuesta(434, true,
						"Yamcha ha tocado sin querer un pecho a Pasta",
						pregunta113));
		getRespuestaDAO().create(
				new Respuesta(435, false, "Yamcha está enamorado de Pasta",
						pregunta113));
		getRespuestaDAO().create(
				new Respuesta(436, false,
						"Han sufrido un enamoramiento conjunto", pregunta113));
		getRespuestaDAO().create(
				new Respuesta(437, false, "Turles", pregunta114));
		getRespuestaDAO().create(
				new Respuesta(438, false, "Tarble", pregunta114));
		getRespuestaDAO().create(
				new Respuesta(439, true, "Bardock", pregunta114));
		getRespuestaDAO().create(
				new Respuesta(440, false, "Raditz", pregunta114));
		getRespuestaDAO().create(
				new Respuesta(441, true, "Nuova/Suu Shinron", pregunta115));
		getRespuestaDAO().create(
				new Respuesta(442, false, "San Shinron", pregunta115));
		getRespuestaDAO().create(
				new Respuesta(443, false, "Uh Shinron", pregunta115));
		getRespuestaDAO().create(
				new Respuesta(444, false, "Ryan Shinron", pregunta115));
		getRespuestaDAO().create(
				new Respuesta(445, false, "Ryu Shinron", pregunta116));
		getRespuestaDAO().create(
				new Respuesta(446, true, "Ih/Omega/Li Shinron", pregunta116));
		getRespuestaDAO().create(
				new Respuesta(447, false, "San Shinron", pregunta116));
		getRespuestaDAO().create(
				new Respuesta(448, false, "Haiya Dragon", pregunta116));
		getRespuestaDAO().create(
				new Respuesta(449, false,
						"Nappa, Vegeta, Raditz, Turles y Broly", pregunta117));
		getRespuestaDAO().create(
				new Respuesta(450, false, "C13, C14, C15, C16 y C17",
						pregunta117));
		getRespuestaDAO().create(
				new Respuesta(451, true,
						"Ginew, Recoome, Burter, Jeice y Gurdo", pregunta117));
		getRespuestaDAO().create(
				new Respuesta(452, false, "Violet, Yellow, Blue, Red y Silver",
						pregunta117));
		getRespuestaDAO()
				.create(new Respuesta(453, false,
						"Dragon Ball Z: La batalla de los dioses", pregunta102));
		getRespuestaDAO().create(
				new Respuesta(454, false,
						"¡¡Batalla extrema!! Los Tres Grandes Super Saiyajin",
						pregunta102));
		getRespuestaDAO().create(
				new Respuesta(455, true, "Devolvedme a mi Gohan", pregunta102));
		getRespuestaDAO().create(
				new Respuesta(456, false, "Goku es un Super Saiyajin",
						pregunta102));
		getRespuestaDAO().create(
				new Respuesta(457, true, "Krilín", pregunta118));
		getRespuestaDAO().create(
				new Respuesta(458, false, "Yamcha", pregunta118));
		getRespuestaDAO().create(
				new Respuesta(459, false, "Yajirobee", pregunta118));
		getRespuestaDAO()
				.create(new Respuesta(460, false, "Giru", pregunta118));
		getRespuestaDAO().create(
				new Respuesta(461, false, "Oolong", pregunta119));
		getRespuestaDAO().create(new Respuesta(462, true, "Puar", pregunta119));
		getRespuestaDAO()
				.create(new Respuesta(463, false, "Giru", pregunta119));
		getRespuestaDAO().create(new Respuesta(464, false, "Bee", pregunta119));
		getRespuestaDAO().create(
				new Respuesta(465, false, "Bulma", pregunta120));
		getRespuestaDAO().create(
				new Respuesta(466, false, "Lunch", pregunta120));
		getRespuestaDAO().create(
				new Respuesta(467, true, "Chichi/Milk", pregunta120));
		getRespuestaDAO().create(new Respuesta(468, false, "C18", pregunta120));
		getRespuestaDAO().create(
				new Respuesta(469, false, "Son Gohan", pregunta121));
		getRespuestaDAO().create(new Respuesta(470, false, "Pan", pregunta121));
		getRespuestaDAO().create(
				new Respuesta(471, false, "Vegeta", pregunta121));
		getRespuestaDAO().create(
				new Respuesta(472, true, "Son Goten", pregunta121));
		getRespuestaDAO().create(
				new Respuesta(473, true, "Freezer", pregunta122));
		getRespuestaDAO().create(
				new Respuesta(474, false, "Kuriza", pregunta122));
		getRespuestaDAO().create(
				new Respuesta(475, false, "Chilled", pregunta122));
		getRespuestaDAO().create(
				new Respuesta(476, false, "Cooler", pregunta122));
		getRespuestaDAO()
				.create(new Respuesta(477, false, "Dragon Ball: Landmark",
						pregunta123));
		getRespuestaDAO().create(
				new Respuesta(478, true, "Dragon Ball: Bouken Special",
						pregunta123));
		getRespuestaDAO().create(
				new Respuesta(479, false, "Dragon Ball: Forever", pregunta123));
		getRespuestaDAO().create(
				new Respuesta(480, false, "Dragon Ball: The Golden Warrior",
						pregunta123));
		getRespuestaDAO().create(
				new Respuesta(481, false, "La terrorifica Muscle Tower",
						pregunta124));
		getRespuestaDAO().create(
				new Respuesta(482, false, "Nace un nuevo héroe", pregunta124));
		getRespuestaDAO().create(
				new Respuesta(483, true, "Un plan diabólico", pregunta124));
		getRespuestaDAO().create(
				new Respuesta(484, false, "El arma secreta de La Tierra",
						pregunta124));
		getRespuestaDAO().create(
				new Respuesta(485, false, "Violet", pregunta125));
		getRespuestaDAO().create(
				new Respuesta(486, false, "Yellow", pregunta125));
		getRespuestaDAO().create(
				new Respuesta(487, false, "White", pregunta125));
		getRespuestaDAO().create(
				new Respuesta(488, true, "Silver", pregunta125));
		getRespuestaDAO()
				.create(new Respuesta(489, true, "Dragon Ball: Final Bout",
						pregunta126));
		getRespuestaDAO().create(
				new Respuesta(490, false, "Dragon Ball Z: Ultimate Battle 22",
						pregunta126));
		getRespuestaDAO().create(
				new Respuesta(491, false, "Dragon Ball Z: Infinite World",
						pregunta126));
		getRespuestaDAO().create(
				new Respuesta(492, false, "Dragon Ball GT: Transformations",
						pregunta126));
		getRespuestaDAO().create(
				new Respuesta(493, false,
						"En realizar una ancestral técnica de lucha",
						pregunta127));
		getRespuestaDAO()
				.create(new Respuesta(
						494,
						true,
						"En restregar los senos de la mujer sobre la cara del hombre",
						pregunta127));
		getRespuestaDAO().create(
				new Respuesta(495, false, "En un hechizo milenario",
						pregunta127));
		getRespuestaDAO().create(
				new Respuesta(496, false, "En realizar los pasos de la fusión",
						pregunta127));
	}

	@Override
	public void close() {
		super.close();
		preguntaDAO = null;
		respuestaDAO = null;
	}

}