package com.example.a21l_5812_smd_a2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a21l_5812_smd_a2.Models.NoteImage;
import com.example.a21l_5812_smd_a2.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<NoteImage> imageList;
    private OnImageClickListener listener;
    private boolean showPreview;

    public interface OnImageClickListener {
        void onImageClick(NoteImage image);
        void onImageDelete(NoteImage image);
    }

    public ImageAdapter(ArrayList<NoteImage> imageList, OnImageClickListener listener, boolean showPreview) {
        this.imageList = imageList;
        this.listener = listener;
        this.showPreview = showPreview;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        NoteImage image = imageList.get(position);

        holder.txtName.setText(image.getName());

        if (showPreview) {
            holder.imgPreview.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(image.getImagePath()))
                    .centerCrop()
                    .into(holder.imgPreview);
        } else {
            holder.imgPreview.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageClick(image);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageDelete(image);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPreview;
        TextView txtName;
        Button btnDelete;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPreview = itemView.findViewById(R.id.imgPreview);
            txtName = itemView.findViewById(R.id.txtName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
