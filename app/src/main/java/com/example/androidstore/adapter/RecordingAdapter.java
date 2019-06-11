package com.example.androidstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidstore.R;
import com.example.androidstore.bean.Recording;
import com.example.androidstore.widget.PxxRoundOvalImageView;

import java.util.ArrayList;
import java.util.List;


public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.ViewHolder> {

    private Context context;
    private List<Recording> recordingItems;

    public RecordingAdapter(Context context) {
        this.context = context;
        this.recordingItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recording_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Recording recording = recordingItems.get(i);

        viewHolder.textname.setText(recording.getGoodsName());
        viewHolder.createTime.setText(recording.getCreateTime());
        viewHolder.imageView.setType(PxxRoundOvalImageView.TYPE_ROUND);
        viewHolder.imageView.setRoundRadius(8);
        Glide.with(context).load(recording.getGoodsImage())
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return recordingItems.size();
    }


    public List<Recording> getRecordingItems() {
        return recordingItems;
    }

    public void setRecordingItems(List<Recording> recordingItems) {
        this.recordingItems.addAll(recordingItems);
        notifyItemInserted(recordingItems.size() - 1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView createTime;
        PxxRoundOvalImageView imageView;
        TextView textname;

        ViewHolder(View view) {
            super(view);

            createTime = view.findViewById(R.id.create_time);
            imageView = view.findViewById(R.id.recording_item_image);
            textname = view.findViewById(R.id.recording_item_name);
        }
    }

}
