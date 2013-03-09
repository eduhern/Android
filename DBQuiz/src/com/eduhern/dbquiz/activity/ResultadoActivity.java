package com.eduhern.dbquiz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.eduhern.dbquiz.R;

public class ResultadoActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		final TextView textViewPregunta = (TextView) findViewById(R.id.texto_resultado);
		textViewPregunta.setText(getIntent().getStringExtra("resultado")
				+ " respuestas correctas");
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
