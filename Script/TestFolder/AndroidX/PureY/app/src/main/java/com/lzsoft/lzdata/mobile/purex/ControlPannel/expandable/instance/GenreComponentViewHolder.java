package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance;

import android.graphics.Color;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponent;
import com.lzsoft.lzdata.mobile.purex.R;

import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class GenreComponentViewHolder extends GroupViewHolder {

  private TextView genreComponentName;
  private ImageView arrow;
  private ImageView icon;

  public GenreComponentViewHolder(View itemView) {
    super(itemView);

    itemView.setBackgroundColor(Color.rgb(255,245,240));

    genreComponentName = itemView.findViewById(R.id.thoughtbot_recyclerview_list_item_genre_name);
    arrow = itemView.findViewById(R.id.thoughtbot_recyclerview_list_item_genre_arrow);
    icon = itemView.findViewById(R.id.thoughtbot_recyclerview_list_item_genre_icon);
  }

  public void setGenreComponentTitle(ExpandableGroup genreComponent) {
    if (genreComponent instanceof GenreComponent) {
      genreComponentName.setText(genreComponent.getTitle());
      icon.setBackgroundResource(((GenreComponent) genreComponent).getIconResId());
    }
/*
    if (genreComponent instanceof MultiCheckGenre) {
      genreComponentName.setText(genreComponent.getTitle());
      icon.setBackgroundResource(((MultiCheckGenre) genreComponent).getIconResId());
    }
*/
/*
    if (genreComponent instanceof SingleCheckGenre) {
      genreComponentName.setText(genreComponent.getTitle());
      icon.setBackgroundResource(((SingleCheckGenre) genreComponent).getIconResId());
    }
*/

  }


  @Override
  public void setOnGroupClickListener(OnGroupClickListener listener) {
    super.setOnGroupClickListener(listener);
  }

  @Override
  public void expand() {
    animateExpand();
  }

  @Override
  public void collapse() {
    animateCollapse();
  }

  private void animateExpand() {
    RotateAnimation rotate =
        new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }

  private void animateCollapse() {
    RotateAnimation rotate =
        new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }

  public TextView getGenreComponentName() {
    return genreComponentName;
  }

  public void setGenreComponentName(TextView genreComponentName) {
    this.genreComponentName = genreComponentName;
  }

  public ImageView getArrow() {
    return arrow;
  }

  public void setArrow(ImageView arrow) {
    this.arrow = arrow;
  }

  public ImageView getIcon() {
    return icon;
  }

  public void setIcon(ImageView icon) {
    this.icon = icon;
  }
}
