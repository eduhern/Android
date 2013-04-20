package com.eduhern.dbquiz.activity;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eduhern.dbquiz.models.Categoria;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {

	public CategoriaAdapter(AyudaActivity context, final int resource,
			final List<Categoria> transactions) {
		super(context, resource, transactions);
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(com.eduhern.dbquiz.R.layout.fila_ayuda,
					null);
		}

		Categoria categoria = getItem(position);

		TextView fila_ayuda = (TextView) convertView
				.findViewById(com.eduhern.dbquiz.R.id.fila_categoria);

		fila_ayuda.setText(categoria.getDescripcion_ext());

		TextView nom_categoria = (TextView) convertView
				.findViewById(com.eduhern.dbquiz.R.id.tv_categoria);

		nom_categoria.setText(categoria.getDescripcion());

		final int id_c = getContext().getResources()
				.getIdentifier(categoria.getFondo(), "drawable",
						getContext().getPackageName());
		Log.d("ID C", id_c + "");
		nom_categoria.setCompoundDrawablesWithIntrinsicBounds(getContext()
				.getResources().getDrawable(id_c), null, null, null);

		if (getContext().getResources().getDrawable(id_c) == null)
			Log.d("ID C", "NULL");
		else
			Log.d("ID C", "no nULL");

		return convertView;
	}
}
