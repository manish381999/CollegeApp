package com.tie.collegeapp.ui.Ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.tie.collegeapp.databinding.ActivityEbookBinding;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class EbookActivity extends AppCompatActivity {
ActivityEbookBinding binding;
private EbookAdapter adapter;
private List<EbookModel> list;
FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");

        database=FirebaseDatabase.getInstance();

        getEbook();
    }

    private void getEbook() {
        database.getReference().child("Ebook").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    EbookModel ebookModel=dataSnapshot.getValue(EbookModel.class);
                    list.add(0,ebookModel);
                }
                binding.rvEbook.setHasFixedSize(true);
                adapter=new EbookAdapter(EbookActivity.this,list);
                binding.rvEbook.setAdapter(adapter);
                binding.rvEbook.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                binding.shimmerViewContainer.stopShimmer();
                binding.shimmerLayout.setVisibility(View.GONE);
                binding.etSearch.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.shimmerViewContainer.stopShimmer();
                binding.shimmerLayout.setVisibility(View.GONE);
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             filter(s.toString());
            }
        });

    }

    private void filter(String s) {

    ArrayList<EbookModel> filterList=new ArrayList<>();
    for (EbookModel item : list){
        if (item.getEbookTitle().toLowerCase().contains(s.toLowerCase())){
            filterList.add(item);
        }
    }

adapter.FilteredList(filterList);

    }



    @Override
    protected void onPause() {
        binding.shimmerViewContainer.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        binding.shimmerViewContainer.startShimmer();
        super.onResume();
    }
}