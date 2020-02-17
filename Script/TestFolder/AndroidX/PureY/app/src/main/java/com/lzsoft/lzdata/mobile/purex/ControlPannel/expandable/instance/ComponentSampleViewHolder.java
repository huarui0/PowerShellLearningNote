package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.lzsoft.lzdata.mobile.purex.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ComponentSampleViewHolder extends ChildViewHolder {

  private TextView childTextView;
  private TextView childDescView;
  private TextView childDirectoryView;

  public ComponentSampleViewHolder(View itemView) {
    super(itemView);

    int color = Color.WHITE;
    itemView.setBackgroundColor(color);

    childTextView = itemView.findViewById(R.id.list_item_sample_name);
    childDescView = itemView.findViewById(R.id.list_item_sample_desc_name);
    childDirectoryView = itemView.findViewById(R.id.list_item_sample_dir_name);

  }


  @Override
  public void onClick(View v) {
    super.onClick(v);
  }

  @Override
  public boolean onLongClick(View v) {
    return super.onLongClick(v);
  }

  public void setComponentSampleName(String name) {
    childTextView.setText(name);
  }

  public void setComponentSampleDescName(String descName) {
    childDescView.setText(descName);
  }

  public void setComponentSampleDirectoryName(String directoryName) {
    childDirectoryView.setText(directoryName);
  }

  public TextView getChildTextView() {
    return childTextView;
  }

  public void setChildTextView(View itemView) {
    this.childTextView = itemView.findViewById(R.id.list_item_sample_name);
  }

  public TextView getChildDescView() {
    return childDescView;
  }

  public void setChildDescView(View itemView) {
    this.childDescView = itemView.findViewById(R.id.list_item_sample_desc_name);
  }

  public TextView getChildDirectoryView() {
    return childDirectoryView;
  }

  public void setChildDirectoryView(TextView itemView) {
    this.childDirectoryView = itemView.findViewById(R.id.list_item_sample_dir_name);
  }
}
