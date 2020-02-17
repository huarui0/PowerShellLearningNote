package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.multicheck;

import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;

import com.lzsoft.lzdata.mobile.purex.R;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;

public class MultiCheckArtistViewHolder extends CheckableChildViewHolder {

  private CheckedTextView childCheckedTextView;

  public MultiCheckArtistViewHolder(View itemView) {
    super(itemView);
    childCheckedTextView =
        (CheckedTextView) itemView.findViewById(R.id.list_item_multicheck_artist_name);
  }

  @Override
  public Checkable getCheckable() {
    return childCheckedTextView;
  }

  public void setArtistName(String artistName) {
    childCheckedTextView.setText(artistName);
  }
}
