package com.example.cineapp.Reci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineapp.R;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Sesion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class AdapterComienzaPelicula extends RecyclerView.Adapter<AdapterComienzaPelicula.ViewHolder>{
    private LayoutInflater lInflateer;
    private View.OnClickListener clickListener;
    private Context context;

    private List<Sesion> listaSesion;

    public AdapterComienzaPelicula(Context context, List<Sesion> listFilm) {
        this.context = context;
        this.listaSesion = listFilm;
        lInflateer = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflateer.inflate(R.layout.vista_sesion, parent, false);
        view.setOnClickListener(clickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Sesion sesi = listaSesion.get(position);
        SimpleDateFormat hms;
        String horaEmpieza;
        Date dt = listaSesion.get(position).getSesionHora();
        hms = new SimpleDateFormat("HH:mm:ss");
        horaEmpieza = hms.format(dt);
        viewHolder.hora.setText("Formato "+ ControllerBD.getInstance(context).getTypeFromSala(ControllerBD.getInstance(context).getSalaCine(sesi.getIdSalaCine()))
        +" Comienza a las"+ horaEmpieza + "\n Su numero de sala es: " + listaSesion.get(position).getIdSalaCine());
    }

    @Override
    public int getItemCount() {
        return listaSesion.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clickListener = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hora;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hora = itemView.findViewById(R.id.horaSesionView);
            this.view = itemView;
        }

    }
}
