package com.sise.botonpanico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.botonpanico.R;
import com.sise.botonpanico.entities.Incidencia;

import java.text.SimpleDateFormat;
import java.util.List;

public class CardSerenazgoAlertaAdapter extends RecyclerView.Adapter<CardSerenazgoAlertaAdapter.ViewHolder> {

    private Context context;
    private List<Incidencia> incidencias;

    public CardSerenazgoAlertaAdapter(Context context, List<Incidencia> incidencias) {
        this.context = context;
        this.incidencias = incidencias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_serenazgo_alerta,parent,false);
        return new CardSerenazgoAlertaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Incidencia incidencia = incidencias.get(position);
        SimpleDateFormat formaFecha = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat formaHora = new SimpleDateFormat("hh:mm:ss");
        holder.tvTipoReporte.setText("Tipo Reporte: "+incidencia.getTipoIncidencia().getDescripcion());
        holder.tvDia.setText("Dia: "+formaFecha.format(incidencia.getFechaCreacionAuditoria()));
        holder.tvHora.setText("Hora: "+formaHora.format(incidencia.getFechaCreacionAuditoria()));
        holder.btnAtender.setOnClickListener(view -> {
            Toast.makeText(context,"Se ha iniciado la atención", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return this.incidencias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTipoReporte;
        private TextView tvDia;
        private TextView tvHora;
        private AppCompatButton btnAtender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipoReporte = itemView.findViewById(R.id.cardserealert_tiporeporte);
            tvDia = itemView.findViewById(R.id.cardserealert_dia);
            tvHora = itemView.findViewById(R.id.cardserealert_hora);
            btnAtender = itemView.findViewById(R.id.cardserealert_atender);
        }
    }

}
