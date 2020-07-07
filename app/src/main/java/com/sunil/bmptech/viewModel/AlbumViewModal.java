package com.sunil.bmptech.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sunil.bmptech.model.Album;
import com.sunil.bmptech.rest.ApiInterface;
import com.sunil.bmptech.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumViewModal extends ViewModel {
    private MutableLiveData<List<Album>>album;
    private ApiInterface apiInterface;
    public MutableLiveData<Boolean>progressObserve;

    public LiveData<List<Album>> getAlbum(int id){
        if (album==null){
            album = new MutableLiveData<>();
            progressObserve = new MutableLiveData<>();
            loadAlbum(id);
        }
        return album;
    }

    public void loadAlbum(int id) {
        progressObserve.setValue(true);
        apiInterface = RestClient.getClient().create(ApiInterface.class);
        Call<List<Album>> call = apiInterface.getPhotos(id);
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                progressObserve.setValue(false);
                if(response.isSuccessful()){
                    album.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                progressObserve.setValue(false);
            }
        });

    }
}
