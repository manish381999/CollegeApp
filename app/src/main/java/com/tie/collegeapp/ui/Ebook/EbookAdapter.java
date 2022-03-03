package com.tie.collegeapp.ui.Ebook;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.EbookItemLayoutBinding;
import com.tie.collegeapp.ui.about_us.BranchModel;

import java.util.ArrayList;
import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder>{

    Context context;
    List<EbookModel>list;

    public EbookAdapter(Context context, List<EbookModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, @SuppressLint("RecyclerView") int position) {


holder.binding.ebookName.setText(list.get(position).getEbookTitle());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,PdfViewerActivity.class);
        intent.putExtra("pdfUrl", list.get(position).getEbookUrl());
        intent.putExtra("pdfTitle",list.get(position).getEbookTitle());
        context.startActivity(intent);
    }
});

       holder.binding.ebookDownload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(list.get(position).getEbookUrl()));
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void FilteredList(ArrayList<EbookModel> filterList) {
        list=filterList;
        notifyDataSetChanged();
    }


    public static class EbookViewHolder extends RecyclerView.ViewHolder{
        EbookItemLayoutBinding binding;
        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=EbookItemLayoutBinding.bind(itemView);
        }
    }
}
