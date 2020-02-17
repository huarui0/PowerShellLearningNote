package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.expandable;

import android.view.View;
import android.widget.TextView;

import com.lzsoft.lzdata.mobile.purex.R;

/**
 * Created by javierg on 16/01/2017.
 */

public class KidViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {

    private TextView childTextView;

    public KidViewHolder(View itemView) {
        super(itemView);
        childTextView = itemView.findViewById(R.id.list_item_artist_name);
    }

    public void setArtistName(String name) {
        childTextView.setText(name);
    }
}
