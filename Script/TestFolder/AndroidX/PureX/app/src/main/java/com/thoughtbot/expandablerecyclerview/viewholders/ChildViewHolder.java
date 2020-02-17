package com.thoughtbot.expandablerecyclerview.viewholders;

import android.view.View;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for {@link ExpandableGroup#items}
 */
public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {

  public ChildViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    itemView.setOnLongClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Toast.makeText(v.getContext(), "Rippled item: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean onLongClick(View v) {
    if (getAdapterPosition() % 2 == 0) {
      Toast.makeText(v.getContext(), "long item: " + getAdapterPosition() + " and not consumed",
              Toast.LENGTH_SHORT)
              .show();
      return false;
    }
    Toast.makeText(v.getContext(), "long item: " + getAdapterPosition() + " and consumed", Toast.LENGTH_SHORT)
            .show();
    return true;
  }
}
