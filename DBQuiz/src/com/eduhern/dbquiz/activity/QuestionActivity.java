package com.eduhern.dbquiz.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.database.DatabaseHelper;
import com.eduhern.dbquiz.models.Pregunta;
import com.eduhern.dbquiz.models.Respuesta;
import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;

public class QuestionActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

	private RespuestaAdapter adapter;
	private int respuestasCorrectas = 0;
	private Random numeroPreguntaAleatorio;
	private Pregunta preguntaActual;
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	private List<Pregunta> preguntasYaJugadas = new ArrayList<Pregunta>();
	private List<Respuesta> respuestas;
	private int maximoPreguntas;
	private MediaPlayer correcto;
	private MediaPlayer fallo;
	private Integer posicionPreguntaActual = null;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		numeroPreguntaAleatorio = new Random();
		final SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(this);
		maximoPreguntas = Integer.parseInt(preferencias.getString(
				"numPreguntas", "30"));
		final String dificultad = preferencias.getBoolean("dificultad", false) ? "2"
				: "1";

		if (savedInstanceState == null) {
			preguntas = getHelper().getPreguntaDAO().queryForEq("dificultad",
					dificultad);
			Log.d("PREGUNTAS FACILES: ", "" + preguntas.size());
			recuperarSiguientePregunta();
		} else {
			preguntas = savedInstanceState.getParcelableArrayList("preguntas");
			preguntasYaJugadas = savedInstanceState
					.getParcelableArrayList("preguntasYaJugadas");
			posicionPreguntaActual = savedInstanceState
					.getInt("posicionPreguntaActual");
			respuestasCorrectas = savedInstanceState
					.getInt("respuestasCorrectas");
			((TextView) findViewById(R.id.puntuacion1)).setText(String
					.valueOf(respuestasCorrectas));
			preguntaActual = preguntas.get(posicionPreguntaActual);
			actualizarPantalla();
		}

		respuestas = preguntaActual.getRespuestas();
		adapter = new RespuestaAdapter(this, com.eduhern.dbquiz.R.layout.fila,
				respuestas);
		setListAdapter(adapter);

		correcto = MediaPlayer.create(this, R.raw.correcto);
		fallo = MediaPlayer.create(this, R.raw.fallo);

	}

	@Override
	protected void onListItemClick(final ListView l, final View v,
			final int posicion, final long id) {
		if (preguntaActual.getRespuestas().get(posicion).isCorrecta()) {
			correcto.start();
			respuestasCorrectas++;
			((TextView) findViewById(R.id.puntuacion1))
					.setBackgroundDrawable(getResources().getDrawable(
							getResources().getIdentifier("puntuacion_ok",
									"drawable", getPackageName())));
		} else {
			fallo.start();
			((TextView) findViewById(R.id.puntuacion1))
					.setBackgroundDrawable(getResources().getDrawable(
							getResources().getIdentifier("puntuacion_bad",
									"drawable", getPackageName())));
		}
		((TextView) findViewById(R.id.puntuacion1)).setText(String
				.valueOf(respuestasCorrectas));

		if (preguntasYaJugadas.size() == maximoPreguntas
				|| preguntasYaJugadas.size() >= preguntas.size()) {
			final Intent intent = new Intent(this, ResultadoActivity.class);
			intent.putExtra("resultado", String.valueOf(respuestasCorrectas));
			finish();
			startActivity(intent);
		} else {
			recuperarSiguientePregunta();
			respuestas.clear();
			respuestas.addAll(preguntaActual.getRespuestas());
			adapter.notifyDataSetChanged();
		}
	}

	private void recuperarSiguientePregunta() {
		do {
			posicionPreguntaActual = numeroPreguntaAleatorio.nextInt(preguntas
					.size());
		} while (preguntasYaJugadas.contains(preguntas
				.get(posicionPreguntaActual)));
		preguntaActual = preguntas.get(posicionPreguntaActual);
		preguntasYaJugadas.add(preguntaActual);
		actualizarPantalla();

		new Handler().postDelayed(new Runnable() {
			public void run() {
				((TextView) findViewById(R.id.puntuacion1))
						.setBackgroundDrawable(getResources().getDrawable(
								getResources().getIdentifier("puntuacion",
										"drawable", getPackageName())));
			}
		}, 200);

	}

	private void actualizarPantalla() {
		final int id = getResources().getIdentifier(preguntaActual.getImagen(),
				"drawable", getPackageName());

		final Drawable drawable = getResources().getDrawable(id);
		final ImageView imagen = (ImageView) findViewById(R.id.imageView2);
		imagen.setImageDrawable(drawable);

		final int id_c = getResources().getIdentifier(
				preguntaActual.getIdCategoria().getFondo(), "drawable",
				getPackageName());
		final Drawable drawable_c = getResources().getDrawable(id_c);
		final ImageView imagen_categoria = (ImageView) findViewById(R.id.categoria);

		imagen_categoria.setImageDrawable(drawable_c);

		final TextView textViewPregunta = (TextView) findViewById(R.id.texto_pregunta);
		textViewPregunta.setText(preguntaActual.getDescripcion());
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("posicionPreguntaActual", posicionPreguntaActual);
		outState.putInt("respuestasCorrectas", respuestasCorrectas);
		outState.putParcelableArrayList("preguntas", new ArrayList<Pregunta>(
				preguntas));
		outState.putParcelableArrayList("preguntasYaJugadas",
				new ArrayList<Pregunta>(preguntasYaJugadas));
	}

}
