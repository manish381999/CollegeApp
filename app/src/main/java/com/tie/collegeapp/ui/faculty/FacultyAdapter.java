package com.tie.collegeapp.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.FacultyItemLayoutBinding;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>{

 Context context;
 List<FacultyModel> list;

    public FacultyAdapter(Context context, List<FacultyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.faculty_item_layout,parent,false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
FacultyModel facultyModel=list.get(position);

holder.binding.facultyName.setText(facultyModel.getName());
holder.binding.facultyPost.setText(facultyModel.getPost());
holder.binding.facultyEmail.setText(facultyModel.getEmail());

        try {
            Picasso.get()
                    .load(facultyModel.getImage())
                    .placeholder(R.drawable.place_holder)
                    .into(holder.binding.facultyImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FacultyViewHolder extends RecyclerView.ViewHolder{
FacultyItemLayoutBinding binding;
        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=FacultyItemLayoutBinding.bind(itemView);
        }
    }
}
