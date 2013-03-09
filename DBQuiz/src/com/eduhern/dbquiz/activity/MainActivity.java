package com.eduhern.dbquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.eduhern.dbquiz.R;
import com.eduhern.dbquiz.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> implements
		OnClickListener {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button boton = (Button) findViewById(R.id.boton1);
		boton.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(final View arg0) {
		startActivity(new Intent(this, QuestionActivity.class));
	}

}
