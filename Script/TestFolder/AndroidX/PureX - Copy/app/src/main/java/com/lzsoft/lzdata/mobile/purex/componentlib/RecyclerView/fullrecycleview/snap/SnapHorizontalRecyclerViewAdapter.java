package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.snap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lzsoft.lzdata.mobile.purex.R;

import java.util.ArrayList;

/**
 * Created by javierg on 06/02/2017.
 */

public class SnapHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<SnapHorizontalRecyclerViewAdapter.DataObjectHolder> {

    private Context mContext;
    private ArrayList<SnapData> mDataSet;

    public SnapHorizontalRecyclerViewAdapter(ArrayList<SnapData> dataset, Context context) {
        this.mDataSet = dataset;
        this.mContext = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fullrecyclerview_snap_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position).getText());
        holder.mItemCountTextView.setText(mDataSet.get(position).getmItemCountText());
        holder.mImage.setImageDrawable(ContextCompat.getDrawable(mContext, mDataSet.get(position).getImage()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImage;
        TextView mTextView;
        TextView mItemCountTextView;

        public DataObjectHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.snap_list_item_image_view);
            mTextView = itemView.findViewById(R.id.snap_list_item_text_view);
            // mTextView.setBackgroundColor(Color.LTGRAY);
            // mTextView.setTextColor(Color.RED);

            mItemCountTextView = itemView.findViewById(R.id.snap_list_item_count);
            // mItemCountTextView.setBackgroundColor(Color.LTGRAY);
            mItemCountTextView.setTextColor(Color.RED);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
