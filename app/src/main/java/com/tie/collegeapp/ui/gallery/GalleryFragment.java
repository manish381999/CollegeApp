package com.tie.collegeapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tie.collegeapp.databinding.FragmentGalleryBinding;


import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
FragmentGalleryBinding binding;
GalleryAdapter adapter;
List<String> list;
FirebaseDatabase database;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentGalleryBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();

        getConvocationImage();
        getCollegeFestImage();
        getOtherEventsImage();

        return binding.getRoot();
    }

    private void getOtherEventsImage() {
        database.getReference().child("Gallery").child("Other Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for (DataSnapshot  dataSnapshot: snapshot.getChildren()){
                    String data=(String) dataSnapshot.getValue();
                    list.add(data);
                }

                binding.rvOtherEvents.setHasFixedSize(true);
                adapter=new GalleryAdapter(getContext(),list);
                binding.rvOtherEvents.setLayoutManager(new GridLayoutManager(getContext(),3));
                binding.rvOtherEvents.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getCollegeFestImage() {
        database.getReference().child("Gallery").child("College fest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String data= (String) dataSnapshot.getValue();
                    list.add(data);
                }
                binding.rvCollegeFest.setHasFixedSize(true);
                adapter=new GalleryAdapter(getContext(),list);
                binding.rvCollegeFest.setLayoutManager(new GridLayoutManager(getContext(),3));
                binding.rvCollegeFest.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getConvocationImage(){
        database.getReference().child("Gallery").child("Convocation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String data= (String) dataSnapshot.getValue();
                    list.add(data);
                }

                binding.rvConvocation.setHasFixedSize(true);
                adapter=new GalleryAdapter(getContext(),list);
               binding.rvConvocation.setLayoutManager(new GridLayoutManager(getContext(),3));
               binding.rvConvocation.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}