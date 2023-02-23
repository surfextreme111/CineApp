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

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private View.OnClickListener clickListe;
    private List<Pelicula> listaPeli;
    private Context context;



    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vista_pelicula, parent, false);
        view.setOnClickListener(clickListe);
        return new MoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder viewHolder, int position) {
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
