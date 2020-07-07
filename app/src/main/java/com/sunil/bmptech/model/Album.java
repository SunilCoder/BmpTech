package com.sunil.bmptech.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class Album implements Serializable {
    private int  albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @BindingAdapter({ "thumbnailUrl" })
    public static void loadImage(ImageView imageView, String imageURL) {
        Picasso.get().load(imageURL).into(imageView);
    }

    @BindingAdapter({"url"})
    public static  void loadUrlImage(ImageView bigImage,String Url){
        Picasso.get().load(Url).into(bigImage);
    }
}
