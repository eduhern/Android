package com.eduhern.dbquiz.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduhern.dbquiz.R;

public class ResultadoActivity extends Activity implements OnLongClickListener,
		OnClickListener {
	private ImageView imagenFin;
	private boolean fin = false;
	private int respuestasCorrectas;
	private int maximoPreguntas;
	private boolean genero = false;
	private boolean dificultad = false;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);

		final SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(this);
		maximoPreguntas = Integer.parseInt(preferencias.getString(
				"numPreguntas", "30"));
		genero = !preferencias.getBoolean("genero", false);
		dificultad = preferencias.getBoolean("dificultad", false);

		final TextView pregunta = (TextView) findViewById(R.id.texto_resultado);
		respuestasCorrectas = Integer.parseInt(getIntent().getStringExtra(
				"resultado"));
		pregunta.setText(respuestasCorrectas + " de " + maximoPreguntas
				+ " respuestas correctas");

		imagenFin = (ImageView) findViewById(R.id.imagen_resultado);

		if (!genero) {
			final int idImagenResultado = getResources().getIdentifier(
					"foto_camara_principal_g", "drawable", getPackageName());
			final Drawable drawable = getResources().getDrawable(
					idImagenResultado);
			imagenFin.setImageDrawable(drawable);
		}

		imagenFin.setOnLongClickListener(this);

		final Button boton = (Button) findViewById(R.id.boton1);
		boton.setOnClickListener(this);

		final ImageView imagenTwitter = (ImageView) findViewById(R.id.twitter);
		imagenTwitter.setOnClickListener(this);
	}

	public void onClick(final View v) {

		switch (v.getId()) {

		case R.id.twitter:
			final String tweetUrl = "https://twitter.com/intent/tweet?text=He acertado "
					+ respuestasCorrectas
					+ " de "
					+ maximoPreguntas
					+ " preguntas en el Dragon Ball QuiZ para Android"
					+ (dificultad ? " (nivel experto)" : " (nivel fácil)")
					+ ". Ya disponible en Google Play:&url="
					+ "https://play.google.com/store/apps/details?id=com.eduhern.dbquiz";
			final Uri uri = Uri.parse(tweetUrl);
			startActivity(new Intent(Intent.ACTION_VIEW, uri));
			break;
		default:
			finish();
		}

	}

	public boolean onLongClick(final View v) {
		final MediaPlayer musica = MediaPlayer.create(this,
				fin ? R.raw.teletransporte : genero ? R.raw.foto : R.raw.radar);
		musica.start();

		final int porcentaje = Math.round(Float.parseFloat(String
				.valueOf(respuestasCorrectas))
				/ Float.parseFloat(String.valueOf(maximoPreguntas)) * 100);

		String id = null;
		if (porcentaje < 25) {
			id = genero ? "foto_camara_1" : "foto_camara_1_g";
		} else if (porcentaje < 50) {
			id = genero ? "foto_camara_2" : "foto_camara_2_g";
		} else if (porcentaje < 75) {
			id = genero ? "foto_camara_3" : "foto_camara_3_g";
		} else if (porcentaje < 100) {
			id = genero ? "foto_camara_4" : "foto_camara_4_g";
		} else if (maximoPreguntas == 50 && dificultad) {
			id = "foto_camara_god";
		} else {
			id = "foto_camara_5";
		}

		imagenFin
				.setImageDrawable(getResources().getDrawable(
						getResources().getIdentifier(id, "drawable",
								getPackageName())));

		fin = true;
		return true;
	}
}
