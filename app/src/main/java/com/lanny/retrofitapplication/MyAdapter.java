package com.lanny.retrofitapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanny.retrofitapplication.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Photo> myPhoto;

    public MyAdapter(List<Photo> myPhoto) {
        this.myPhoto = myPhoto;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
        Photo list = myPhoto.get(i);

        myViewHolder.vAlbumId.setText("albumId: " + Integer.toString(list.getAlbumId()));
        myViewHolder.vId.setText("ID: " + Integer.toString(list.getId()));
        myViewHolder.vTitle.setText("Title: "+list.getTitle());
        myViewHolder.vUrl.setText("Url: "+list.getUrl());
//        myViewHolder.vThumbnailUrl.setText(list.getThumbnailUrl());
        Picasso.get().load(list.getThumbnailUrl()).fit().into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return myPhoto.size();
    }
/*
"albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
 */
    public class MyViewHolder extends ViewHolder {
        public TextView vAlbumId, vId, vTitle, vUrl;
        //vThumbnailUrl;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vAlbumId = itemView.findViewById(R.id.textViewAlbumID);
            vId = itemView.findViewById(R.id.textViewID);
            vTitle = itemView.findViewById(R.id.textViewTitle);
            vUrl = itemView.findViewById(R.id.textViewUrl);
            imageView = itemView.findViewById(R.id.imageViewThumbnailUrl);
        }
    }
}
