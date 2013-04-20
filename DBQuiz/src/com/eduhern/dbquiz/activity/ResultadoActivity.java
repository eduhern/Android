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
	private ImageView imagen_fin;
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

		final TextView textViewPregunta = (TextView) findViewById(R.id.texto_resultado);
		respuestasCorrectas = Integer.parseInt(getIntent().getStringExtra(
				"resultado"));
		textViewPregunta.setText(respuestasCorrectas + " de " + maximoPreguntas
				+ " respuestas correctas");

		imagen_fin = (ImageView) findViewById(R.id.imagen_resultado);

		if (!genero) {
			final int id_imagen_resultado = getResources().getIdentifier(
					"foto_camara_principal_g", "drawable", getPackageName());
			final Drawable drawable = getResources().getDrawable(
					id_imagen_resultado);
			imagen_fin.setImageDrawable(drawable);
		}

		imagen_fin.setOnLongClickListener(this);

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
		// TODO Auto-generated method stub
		MediaPlayer musica;

		if (fin) {
			musica = MediaPlayer.create(this, R.raw.teletransporte);
		} else {
			if (genero) {
				musica = MediaPlayer.create(this, R.raw.foto);
			} else {
				musica = MediaPlayer.create(this, R.raw.radar);

			}
		}

		musica.start();

		int id_imagen_final;

		final int porcentaje = Math.round(Float.parseFloat(String
				.valueOf(respuestasCorrectas))
				/ Float.parseFloat(String.valueOf(maximoPreguntas)) * 100);

		if (genero) {
			if (porcentaje < 25) {
				id_imagen_final = getResources().getIdentifier("foto_camara_1",
						"drawable", getPackageName());
			} else if (25 <= porcentaje && porcentaje < 50) {
				id_imagen_final = getResources().getIdentifier("foto_camara_2",
						"drawable", getPackageName());
			} else if (50 <= porcentaje && porcentaje < 75) {
				id_imagen_final = getResources().getIdentifier("foto_camara_3",
						"drawable", getPackageName());
			} else if (75 <= porcentaje && porcentaje < 100) {
				id_imagen_final = getResources().getIdentifier("foto_camara_4",
						"drawable", getPackageName());
			} else {
				if (maximoPreguntas == 50 && dificultad) {
					id_imagen_final = getResources().getIdentifier(
							"foto_camara_god", "drawable", getPackageName());
				} else {
					id_imagen_final = getResources().getIdentifier(
							"foto_camara_5", "drawable", getPackageName());

				}
			}
		} else {
			if (porcentaje < 25) {
				id_imagen_final = getResources().getIdentifier(
						"foto_camara_1_g", "drawable", getPackageName());
			} else if (25 <= porcentaje && porcentaje < 50) {
				id_imagen_final = getResources().getIdentifier(
						"foto_camara_2_g", "drawable", getPackageName());
			} else if (50 <= porcentaje && porcentaje < 75) {
				id_imagen_final = getResources().getIdentifier(
						"foto_camara_3_g", "drawable", getPackageName());
			} else if (75 <= porcentaje && porcentaje < 100) {
				id_imagen_final = getResources().getIdentifier(
						"foto_camara_4_g", "drawable", getPackageName());
			} else {
				if (maximoPreguntas == 50 && dificultad) {
					id_imagen_final = getResources().getIdentifier(
							"foto_camara_god", "drawable", getPackageName());
				} else {
					id_imagen_final = getResources().getIdentifier(
							"foto_camara_5", "drawable", getPackageName());

				}
			}
		}

		final Drawable drawable = getResources().getDrawable(id_imagen_final);
		imagen_fin.setImageDrawable(drawable);

		fin = true;
		return true;
	}
}
