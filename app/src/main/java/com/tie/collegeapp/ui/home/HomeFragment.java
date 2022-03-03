package com.tie.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.tie.collegeapp.databinding.FragmentHomeBinding;


import java.util.ArrayList;



public class HomeFragment extends Fragment {
FragmentHomeBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding=FragmentHomeBinding.inflate(inflater, container, false);

       ArrayList<SlideModel> slideModels=new ArrayList<>();

       slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-7de0c.appspot.com/o/Auto%20image%20slider%2F1597150278unnamed.jpg?alt=media&token=709a162a-dd45-4b25-9cd3-2cc7de5e9f20",ScaleTypes.FIT));
       slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-7de0c.appspot.com/o/Auto%20image%20slider%2F1548315846phpH2o4Nv.jpeg?alt=media&token=187a4846-38a6-4167-a011-33a3db8be3a5", ScaleTypes.FIT));
       slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-7de0c.appspot.com/o/Auto%20image%20slider%2FDSC_0100_1.jpg?alt=media&token=e77c90ed-a9b8-416f-a8fb-827c74d3e0d4",ScaleTypes.FIT));
       slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/my-college-app-7de0c.appspot.com/o/Auto%20image%20slider%2Flibrary%2Bnew%2Bcropped%2Blow%2Bres.jpg?alt=media&token=cae9d4b0-854a-498f-b784-c2bac6794bab",ScaleTypes.FIT));

       binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT);

binding.map.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openMap();
    }
});

       return binding.getRoot();
    }

private void openMap(){
    Uri uri=Uri.parse("geo:0,0?q=ITER, Siksha 'O' Anusandhan");
    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
    intent.setPackage("com.google.android.apps.maps");
    startActivity(intent);
}
}