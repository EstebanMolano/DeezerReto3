package com.example.webserviceconsumer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.webserviceconsumer.R;
import com.example.webserviceconsumer.modelos.playList;

import java.util.ArrayList;

public class adapterPlayList extends RecyclerView.Adapter<adapterPlayList.CustomViewHolder> {

    ArrayList<playList> data;


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public adapterPlayList(){
        this.data = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        ((TextView) holder.root.findViewById(R.id.lista_nombre)).setText(data.get(position).getNombreCreador());
        ((TextView) holder.root.findViewById(R.id.nombre_creador)).setText(data.get(position).getNombreLista());
        ((TextView) holder.root.findViewById(R.id.numero_items)).setText(data.get(position).getNumeroCanciones());

        ImageView img = holder.root.findViewById(R.id.img_lista);
        Glide.with(holder.root.getContext()).load(data.get(position).getImagen()).into(img);
        holder.root.findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(data.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void limpiarPlayList(){
        data = new ArrayList<>();
        notifyDataSetChanged();

    }

    public void agregarPlayList(playList play){
        data.add(play);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener{
        void onItemClick(playList playlist);

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }



}
