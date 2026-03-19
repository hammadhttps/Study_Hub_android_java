package com.example.a21l_5812_smd_a2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a21l_5812_smd_a2.Models.Folder;
import com.example.a21l_5812_smd_a2.R;

import java.util.ArrayList;

public class FolderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Folder> folderList;
    private OnFolderClickListener listener;

    public interface OnFolderClickListener {
        void onFolderClick(Folder folder);
        void onFolderDelete(Folder folder);
    }

    public FolderAdapter(Context context, ArrayList<Folder> folderList, OnFolderClickListener listener) {
        this.context = context;
        this.folderList = folderList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
       return  folderList.size();
    }

    @Override
    public Object getItem(int position) {
        return folderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_folder, parent, false);
        }

        Folder folder = folderList.get(position);

        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtImageCount = convertView.findViewById(R.id.txtImageCount);
        Button btnOpen = convertView.findViewById(R.id.btnOpen);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        imgIcon.setImageResource(folder.getIconResId());
        txtName.setText(folder.getName());
        txtImageCount.setText(folder.getImageCount() + " images");

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFolderClick(folder);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFolderDelete(folder);
            }
        });

        return convertView;
    }

}
