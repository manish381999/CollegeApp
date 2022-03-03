package com.tie.collegeapp.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tie.collegeapp.FullImageViewActivity;
import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.GalleryImageLayoutBinding;


import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    Context context;
    List<String> list;

    public GalleryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gallery_image_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        Picasso.get()
                .load(list.get(position))
                .placeholder(R.drawable.place_holder)
                .into(holder.binding.image);

        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FullImageViewActivity.class);
                intent.putExtra("image",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
GalleryImageLayoutBinding binding;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=GalleryImageLayoutBinding.bind(itemView);
        }
    }
}
