package com.sunil.bmptech.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sunil.bmptech.model.User;
import com.sunil.bmptech.rest.ApiInterface;
import com.sunil.bmptech.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    public MutableLiveData<Boolean> progressObserve ;
    private ApiInterface apiInterface;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            progressObserve= new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }



    private void loadUsers() {
        progressObserve.setValue(true);
        Retrofit retrofit = RestClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressObserve.setValue(false);
                if (response.isSuccessful()) {
                    Log.d("hero", "onResponse: " + response.body());
                    users.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressObserve.setValue(false);

            }
        });
    }
}