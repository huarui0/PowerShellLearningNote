package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.stickyheader;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lzsoft.lzdata.mobile.purex.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by javierg on 18/09/2017.
 */

public class AnimalsHeadersAdapter extends AnimalsAdapter<AnimalsHeadersAdapter.DataObjectHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    Context mContext;

    public AnimalsHeadersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView mName;
        TextView mLetterName;

        DataObjectHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.contact_name_textView);
            mLetterName = (TextView) itemView.findViewById(R.id.contact_letter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public AnimalsHeadersAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fullrecyclerview_sticky_item, parent, false);
        return new AnimalsHeadersAdapter.DataObjectHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(AnimalsHeadersAdapter.DataObjectHolder holder, int position) {

        holder.mName.setText(getItem(position));
        holder.mLetterName.setText(String.valueOf(getItem(position).charAt(0)));
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fullrecyclerview_sticky_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(String.valueOf(getItem(position).charAt(0)));
        holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
    }

}
