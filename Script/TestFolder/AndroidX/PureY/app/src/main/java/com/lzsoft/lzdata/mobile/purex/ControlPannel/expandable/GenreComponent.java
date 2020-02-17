package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class GenreComponent extends ExpandableGroup<ComponentSample> {

  private int iconResId;

  public GenreComponent(String title, List<ComponentSample> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
  }

  public int getIconResId() {
    return iconResId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GenreComponent)) return false;

    GenreComponent genreComponent = (GenreComponent) o;

    return getIconResId() == genreComponent.getIconResId();

  }

  @Override
  public int hashCode() {
    return getIconResId();
  }
}

