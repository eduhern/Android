package com.eduhern.dbquiz.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> implements
		OnClickListener {
	MediaPlayer musica;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button boton = (Button) findViewById(R.id.boton1);
		boton.setOnClickListener(this);

		musica = MediaPlayer.create(this, R.raw.dbquiz_music);
		musica.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onClick(final View arg0) {
		musica.stop();
		final Intent intent = new Intent(this, QuestionActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_preferencias:
			final Intent intent = new Intent(this, PreferenciasActivity.class);
			startActivity(intent);
			break;
		case R.id.action_settings:
			final Intent intent2 = new Intent(this, AyudaActivity.class);
			startActivity(intent2);
			break;
		case R.id.action_acerca_de:
			final Intent intent3 = new Intent(this, CreditosActivity.class);
			startActivity(intent3);
			break;
		}

		return super.onOptionsItemSelected(item);

	}
}
