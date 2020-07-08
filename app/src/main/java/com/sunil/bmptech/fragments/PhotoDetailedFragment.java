package com.sunil.bmptech.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunil.bmptech.MainActivity;
import com.sunil.bmptech.R;
import com.sunil.bmptech.model.Album;
import com.sunil.bmptech.databinding.FragmentPhotoDetailedBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoDetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoDetailedFragment extends Fragment {

    private Bundle bundle;
    private FragmentPhotoDetailedBinding fragmentPhotoDetailedBinding;
    private Album album;
    private Toolbar toolbar;

    public PhotoDetailedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotoDetailedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoDetailedFragment newInstance(String param1, String param2) {
        PhotoDetailedFragment fragment = new PhotoDetailedFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (getArguments() != null) {
            album = (Album) bundle.getSerializable("Album");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPhotoDetailedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_detailed, container, false);
        View view = fragmentPhotoDetailedBinding.getRoot();
        initView(view);
        return view;
    }

    private void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setUpNavigation(toolbar);
        fragmentPhotoDetailedBinding.setAlbum(album);

    }
}