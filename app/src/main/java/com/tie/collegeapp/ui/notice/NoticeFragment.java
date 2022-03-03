package com.tie.collegeapp.ui.notice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.FragmentNoticeBinding;


import java.util.ArrayList;


public class NoticeFragment extends Fragment {

FragmentNoticeBinding binding;
private ArrayList<NoticeModel> list;
private NoticeAdapter adapter;

FirebaseDatabase database;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentNoticeBinding.inflate(inflater, container, false);

database=FirebaseDatabase.getInstance();

getNotice();

        return binding.getRoot();
    }

    private void getNotice() {

        database.getReference().child("Notice").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    NoticeModel noticeModel=dataSnapshot.getValue(NoticeModel.class);
                    list.add(0,noticeModel);
                }
               binding.rvNotice.setHasFixedSize(true);
                adapter=new NoticeAdapter(getContext(),list);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                binding.rvNotice.setAdapter(adapter);
                binding.rvNotice.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
                binding.pb.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.pb.setVisibility(View.GONE);
            }
        });
    }
}