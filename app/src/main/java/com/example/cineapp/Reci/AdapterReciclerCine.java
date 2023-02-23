package com.example.cineapp.Reci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineapp.R;
import com.example.cineapp.model.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;



public class AdapterReciclerCine extends RecyclerView.Adapter<AdapterReciclerCine.ViewHolder>  {
    private LayoutInflater inflater;
    private View.OnClickListener clickListe;
    private List<Pelicula> listaPeli;
    private Context context;

    public AdapterReciclerCine(Context context, List<Pelicula> listFilm) {
        this.context = context;
        this.listaPeli = listFilm;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vista_pelicula, parent, false);
        view.setOnClickListener(clickListe);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Pelicula peli = listaPeli.get(position);
        viewHolder.titulo.setText(peli.getTitulo());
        viewHolder.sipnosis.setText(peli.getSipnosis());
        viewHolder.tiempo.setText(peli.getTiempo());
        Picasso.get().load(peli.getCartelera()).into(viewHolder.imgPelicula);
    }

    @Override
    public int getItemCount() {
        return listaPeli.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clickListe = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;
        TextView titulo;
        TextView sipnosis;
        TextView tiempo;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgPeli);
            titulo = itemView.findViewById(R.id.nomPeli);
            sipnosis = itemView.findViewById(R.id.descripcionFilm);
            tiempo = itemView.findViewById(R.id.duracion);
            this.view = itemView;
        }
    }
}