package com.eduhern.dbquiz.activity;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.database.DatabaseHelper;
import com.eduhern.dbquiz.models.Categoria;
import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;

public class AyudaActivity extends OrmLiteBaseListActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ayuda);

		List<Categoria> categorias = getHelper().getCategoriaDAO()
				.queryForAll();
		CategoriaAdapter adapter = new CategoriaAdapter(this,
				com.eduhern.dbquiz.R.layout.fila_ayuda, categorias);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ayuda, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}

}
