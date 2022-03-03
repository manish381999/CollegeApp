package com.tie.collegeapp.ui.about_us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.FragmentAboutUsBinding;

import java.util.ArrayList;
import java.util.List;

public class About_usFragment extends Fragment {
FragmentAboutUsBinding binding;

private BranchAdapter   adapter;
private List<BranchModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
binding=FragmentAboutUsBinding.inflate(inflater, container, false);

list=new ArrayList<>();

list.add(new BranchModel(R.drawable.ic_comp,"computer Science Engineering", "Computer Science Engineering (CSE) is an engineering discipline that covers several topics related to computation, programming languages, program design, computer hardware and software and integrates several fields of computer science."));
list.add(new BranchModel(R.drawable.ic_mech,"Mechanical Engineering","Mechanical engineering is the study, design, development, construction, and testing of mechanical and thermal sensors and devices, including tools, engines, and machines. Mechanical engineering careers center on creating technologies to meet a wide range of human needs."));
list.add(new BranchModel(R.drawable.ic_electrical,"Electrical Engineering","Electrical engineering is an engineering discipline concerned with the study, design, and application of equipment, devices, and systems which use electricity, electronics, and electromagnetism."));

adapter=new BranchAdapter(getContext(),list);
binding.viewPager.setAdapter(adapter);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/my-college-app-7de0c.appspot.com/o/Auto%20image%20slider%2F1-5.jpg?alt=media&token=118b982d-17ae-434a-9be5-a1734a7f8b44")
                .placeholder(R.drawable.place_holder)
                .into(binding.collegeImage);

        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });


return binding.getRoot();

    }

    private void openMap() {
        Uri uri=Uri.parse("geo:0,0?q=ITER, Siksha 'O' Anusandhan");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}