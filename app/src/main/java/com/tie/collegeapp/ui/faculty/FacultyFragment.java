package com.tie.collegeapp.ui.faculty;

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

import com.tie.collegeapp.databinding.FragmentFacultyBinding;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

FragmentFacultyBinding binding;
private List<FacultyModel> list1, list2,list3,list4;
private FacultyAdapter adapter;
FirebaseDatabase database;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFacultyBinding.inflate(inflater, container, false);

        database=FirebaseDatabase.getInstance();

        cseDepartment();
        csitDepartment();
        electricalDepartment();
        mechanicalDepartment();

        return binding.getRoot();
    }

    private void mechanicalDepartment(){
database.getReference().child("Faculty").child("Mechanical").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        list4=new ArrayList<>();
        if (!snapshot.exists()){
            binding.MechanicalNoData.setVisibility(View.VISIBLE);
            binding.rvMechanical.setVisibility(View.GONE);
            binding.pb.setVisibility(View.GONE);
        }else {
            binding.MechanicalNoData.setVisibility(View.GONE);
            binding.rvMechanical.setVisibility(View.VISIBLE);
            binding.pb.setVisibility(View.GONE);

            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                FacultyModel facultyModel=dataSnapshot.getValue(FacultyModel.class);
                list4.add(facultyModel);
            }

            binding.rvMechanical.setHasFixedSize(true);
            adapter=new FacultyAdapter(getContext(),list4);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            binding.rvMechanical.setAdapter(adapter);
            binding.rvMechanical.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    }


    private void electricalDepartment() {
        database.getReference().child("Faculty").child("Electrical").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    binding.ElectricalNoData.setVisibility(View.VISIBLE);
                    binding.rvElectrical.setVisibility(View.GONE);
                    binding.pb.setVisibility(View.GONE);
                }else {

                    binding.ElectricalNoData.setVisibility(View.GONE);
                    binding.rvElectrical.setVisibility(View.VISIBLE);
                    binding.pb.setVisibility(View.GONE);

                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        FacultyModel facultyModel=dataSnapshot.getValue(FacultyModel.class);
                        list3.add(facultyModel);
                    }

                    binding.rvElectrical.setHasFixedSize(true);
                    adapter=new FacultyAdapter(getContext(),list3);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.rvElectrical.setAdapter(adapter);
                    binding.rvElectrical.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void csitDepartment() {
        database.getReference().child("Faculty").child("CSIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if (!snapshot.exists()){
                    binding.csitNoData.setVisibility(View.VISIBLE);
                    binding.rvCsit.setVisibility(View.GONE);
                    binding.pb.setVisibility(View.GONE);
                }else {
                    binding.csitNoData.setVisibility(View.GONE);
                    binding.rvCsit.setVisibility(View.VISIBLE);
                    binding.pb.setVisibility(View.GONE);

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyModel facultyModel=dataSnapshot.getValue(FacultyModel.class);
                        list2.add(facultyModel);
                    }

                    binding.rvCsit.setHasFixedSize(true);
                    adapter=new FacultyAdapter(getContext(),list2);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.rvCsit.setAdapter(adapter);
                    binding.rvCsit.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage() ,Toast.LENGTH_SHORT).show();
                binding.pb.setVisibility(View.GONE);
            }
        });
    }


    private void cseDepartment() {
        database.getReference().child("Faculty").child("CSE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if (!snapshot.exists()){
                    binding.cseNoData.setVisibility(View.VISIBLE);
                    binding.rvCse.setVisibility(View.GONE);
                    binding.pb.setVisibility(View.GONE);
                }else {
                    binding.cseNoData.setVisibility(View.GONE);
                    binding.rvCse.setVisibility(View.VISIBLE);
                    binding.pb.setVisibility(View.GONE);

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyModel facultyModel=dataSnapshot.getValue(FacultyModel.class);
                        list1.add(facultyModel);
                    }

                    binding.rvCse.setHasFixedSize(true);
                    adapter=new FacultyAdapter(getContext(),list1);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    binding.rvCse.setAdapter(adapter);
                    binding.rvCse.setLayoutManager(layoutManager);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.pb.setVisibility(View.GONE);
            }
        });
    }
}