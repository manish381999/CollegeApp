package com.tie.collegeapp.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tie.collegeapp.FullImageViewActivity;
import com.tie.collegeapp.R;
import com.tie.collegeapp.databinding.NoticefeedItemLayoutBinding;


import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    Context context;
    ArrayList<NoticeModel> list;

    public NoticeAdapter(Context context, ArrayList<NoticeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.noticefeed_item_layout,parent,false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
NoticeModel noticeModel=list.get(position);

holder.binding.noticeTitle.setText(noticeModel.getTitle());
holder.binding.noticeDate.setText(noticeModel.getDate());
holder.binding.noticeTime.setText(noticeModel.getTime());

        try {
            Picasso.get()
                    .load(noticeModel.getImage())
                    .placeholder(R.drawable.place_holder)
                    .into(holder.binding.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.binding.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FullImageViewActivity.class);
                intent.putExtra("image",noticeModel.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder{
      NoticefeedItemLayoutBinding binding;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=NoticefeedItemLayoutBinding.bind(itemView);
        }
    }
}
