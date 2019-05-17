package com.example.webserviceconsumer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webserviceconsumer.R;
import com.example.webserviceconsumer.modelos.Track;

import java.util.ArrayList;

public class adapterTrackList extends RecyclerView.Adapter<adapterTrackList.CustomViewHolder>{


    ArrayList<Track> data;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public adapterTrackList(){
        this.data = new ArrayList<>();
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        adapterTrackList.CustomViewHolder vh = new adapterTrackList.CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        ((TextView) holder.root.findViewById(R.id.nombre_Cancion)).setText(data.get(position).getNombreCancion());
        ((TextView) holder.root.findViewById(R.id.nombre_Artista)).setText(data.get(position).getNombreArtista());
        ((TextView) holder.root.findViewById(R.id.numero_AñoLanzamiento)).setText(data.get(position).getAñoLanzamiento());

        ImageView img = holder.root.findViewById(R.id.img_track);
        Glide.with(holder.root.getContext()).load(data.get(position).getImagen()).into(img);
        holder.root.findViewById(R.id.itemTrack).setOnClickListener(new View.OnClickListener() {
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

    public void agregarTrackList(Track track){
        data.add(track);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener{
        void onItemClick(Track track);

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
