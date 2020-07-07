package com.sunil.bmptech.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunil.bmptech.R;
import com.sunil.bmptech.databinding.UsersFragmentBinding;
import com.sunil.bmptech.databinding.UsersListItemBinding;
import com.sunil.bmptech.model.User;
import com.sunil.bmptech.viewModel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private UsersViewModel mViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;


  /*  public static UsersFragment newInstance() {
        return new UsersFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UsersFragmentBinding usersFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.users_fragment,container,false);
        View view = usersFragmentBinding.getRoot();
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.users_recyler_view);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        mViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        mViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setUserLists(users);


            }
        });

    }


    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
        private List<User> userList;

        public UserAdapter() {
            this.userList = new ArrayList<>();
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            UsersListItemBinding usersListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.users_list_item, parent, false);
            return new UserViewHolder(usersListItemBinding);

        }

        public void setUserLists(List<User> userList) {
            this.userList = userList;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = userList.get(position);
            holder.usersListItemBinding.setUser(user);

        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            private UsersListItemBinding usersListItemBinding;

            public UserViewHolder(@NonNull UsersListItemBinding itemView) {
                super(itemView.getRoot());
                this.usersListItemBinding = itemView;
            }
        }
    }


}