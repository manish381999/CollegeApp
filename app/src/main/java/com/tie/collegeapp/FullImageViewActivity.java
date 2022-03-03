package com.tie.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squareup.picasso.Picasso;
import com.tie.collegeapp.databinding.ActivityFullImageViewBinding;

import java.util.Objects;


public class FullImageViewActivity extends AppCompatActivity {
ActivityFullImageViewBinding binding;

String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFullImageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        data=getIntent().getStringExtra("image");

        Picasso.get()
                .load(data)
                .placeholder(R.drawable.place_holder)
                .into(binding.imageView);

    }
}