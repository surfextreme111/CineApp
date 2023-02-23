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
import com.example.cineapp.model.AsientoComprado;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.types.Type;

import java.util.List;


public class AdapterReciclerAsientos extends RecyclerView.Adapter<AdapterReciclerAsientos.ViewHolder>{
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<AsientoComprado> asientoCompradoList;
    private Context context;

    public AdapterReciclerAsientos(Context context, List<AsientoComprado> asientoCompradoList) {
        this.context = context;
        this.asientoCompradoList = asientoCompradoList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vista_comprar, parent, false);
        view.setOnClickListener(clicker);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        AsientoComprado ac = asientoCompradoList.get(position);
        SalaCine s = ControllerBD.getInstance(context).getSalaCine(ac.getIdSala());

        if(Type.TRESD == s.getTypeSala()){
            viewHolder.precio.setText("9€");
        }else if(Type.FOURDX == s.getTypeSala()){
            viewHolder.precio.setText("11€");
        }else if(Type.NORMAL == s.getTypeSala()){
            viewHolder.precio.setText("7€");
        }

        viewHolder.columna.setText(ac.getAsientoX()+"");
        viewHolder.fila.setText(ac.getAsientoY()+"");
    }

    @Override
    public int getItemCount() {
        return asientoCompradoList.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fila, columna, precio;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fila = itemView.findViewById(R.id.filaBut);
            columna = itemView.findViewById(R.id.numBut);
            precio = itemView.findViewById(R.id.precioBut);
            this.view = itemView;
        }
    }
}
