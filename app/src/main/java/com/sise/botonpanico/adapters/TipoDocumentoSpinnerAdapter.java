package com.sise.botonpanico.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.sise.botonpanico.dto.TipoDocumento;
import java.util.List;

public class TipoDocumentoSpinnerAdapter  extends ArrayAdapter<TipoDocumento> {

    private List<TipoDocumento> tipoDocumentos;

    public TipoDocumentoSpinnerAdapter(@NonNull Context context, List<TipoDocumento> tipoDocumentos) {
        super(context, android.R.layout.simple_spinner_item ,tipoDocumentos);
        this.tipoDocumentos = tipoDocumentos;
    }

    @Override
    public int getCount() {
        return this.tipoDocumentos != null ? this.tipoDocumentos.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //spinner
        View view = super.getView(position, convertView, parent);

        //textview dentro de spinner
        TextView textView = view.findViewById(android.R.id.text1);
        TipoDocumento tipoDocumento = getItem(position);
        if(tipoDocumento != null){
            textView.setText(tipoDocumento.getDescripcion());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //spinner
        View view = super.getView(position, convertView, parent);

        //textview dentro de spinner
        TextView textView = view.findViewById(android.R.id.text1);
        TipoDocumento tipoDocumento = getItem(position);
        if(tipoDocumento != null){
            textView.setText(tipoDocumento.getDescripcion());
        }
        return view;
    }
}
