package com.sunil.bmptech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunil.bmptech.MainActivity;
import com.sunil.bmptech.R;
import com.sunil.bmptech.databinding.UserAlbumListBinding;
import com.sunil.bmptech.databinding.FragmentAlbumBinding;
import com.sunil.bmptech.model.Album;
import com.sunil.bmptech.model.User;
import com.sunil.bmptech.viewModel.AlbumViewModal;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AlbumViewModal albumViewModal;
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private Bundle bundle;
    private User user;
    private int user_id;
    private FragmentAlbumBinding fragmentAlbumBinding;
    private Toolbar toolbar;
    private ProgressBar progressBar;


    public AlbumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlbumFragment newInstance(String param1, String param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (getArguments() != null) {
            user = (User) bundle.getSerializable("User");
            user_id = user.getId();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAlbumBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false);
        View view = fragmentAlbumBinding.getRoot();
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.album_recycler);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setUpNavigation(toolbar);
        fragmentAlbumBinding.setUser(user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        albumAdapter = new AlbumAdapter();
        recyclerView.setAdapter(albumAdapter);
        albumViewModal = new ViewModelProvider(this).get(AlbumViewModal.class);
        // albumViewModal.loadAlbum(user_id);
        albumViewModal.getAlbum(user_id).observe(getViewLifecycleOwner(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumAdapter.setAlbumsList(albums);
            }
        });

        albumViewModal.progressObserve.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
        private List<Album> albums;

        public AlbumAdapter() {
            albums = new ArrayList<>();
        }

        @NonNull
        @Override
        public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            UserAlbumListBinding userAlbumListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_album_list, parent, false);
            return new AlbumAdapter.AlbumViewHolder(userAlbumListBinding.getRoot());

        }

        public void setAlbumsList(List<Album> albums) {
            this.albums = albums;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
            Album album = albums.get(position);
            holder.userAlbumListBinding.setAlbum(album);
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }

        class AlbumViewHolder extends RecyclerView.ViewHolder {
            private UserAlbumListBinding userAlbumListBinding;

            public AlbumViewHolder(@NonNull View itemView) {
                super(itemView);
                userAlbumListBinding = DataBindingUtil.getBinding(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Album album = userAlbumListBinding.getAlbum();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Album",album);
                        Navigation.findNavController(view).navigate(R.id.photoDetailedFragment,bundle);
                    }
                });
            }
        }
    }
}