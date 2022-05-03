package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Photos;

//1. Despues de crear la clase agregar el extends
//2. Implementar todos los metodos ¿y crear clase

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    //3. Se crea el list y el constructor
    private List<Photos> data;

    public ListAdapter(List<Photos> data){
        this.data=data;
    }

    @NonNull
    @Override
    public ListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //6. creamos la vista (inflamos los elementos)
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.viewHolder holder, int position) {
        /*8. Llamaos al metodo asignarDatos para enlazar la vistac con los datos*/
        holder.asignarDatos(data.get(position));
    }

    @Override
    public int getItemCount() {
        //7. retornamos el tamaño de la lista
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        /*4. Colocar los elementos de la vista que se van a inflar con datos*/
        TextView id,title,url;
        ImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            /* 5. enlazar los elementos que se tienen en la vista*/
            id=itemView.findViewById(R.id.txtId);
            title=itemView.findViewById(R.id.txtTitle);
            url=itemView.findViewById(R.id.txtUrl);
            img=itemView.findViewById(R.id.imagenPhoto);
        }
        /*Asignamos los valores*/
        public void asignarDatos(Photos photos) {
            id.setText(photos.getId()+"");
            title.setText(photos.getTitle()+"");
            url.setText(photos.getUrl()+"");
            Picasso.get().load(photos.getThumbnailUrl()).resize(200,200)
                    .centerCrop().into(img);
        }
    }
}
