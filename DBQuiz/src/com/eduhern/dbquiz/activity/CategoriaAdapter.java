package com.eduhern.dbquiz.activity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eduhern.dbquiz.models.Categoria;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {

	public CategoriaAdapter(final AyudaActivity context, final int resource,
			final List<Categoria> transactions) {
		super(context, resource, transactions);
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		if (convertView == null) {
			final LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(com.eduhern.dbquiz.R.layout.fila_ayuda,
					null);
		}

		final Categoria categoria = getItem(position);

		final TextView ayuda = (TextView) convertView
				.findViewById(com.eduhern.dbquiz.R.id.fila_categoria);

		ayuda.setText(categoria.getDescripcion_ext());

		final TextView nombre = (TextView) convertView
				.findViewById(com.eduhern.dbquiz.R.id.tv_categoria);

		nombre.setText(categoria.getDescripcion());

		final int id = getContext().getResources()
				.getIdentifier(categoria.getFondo(), "drawable",
						getContext().getPackageName());
		nombre.setCompoundDrawablesWithIntrinsicBounds(getContext()
				.getResources().getDrawable(id), null, null, null);

		return convertView;
	}
}
