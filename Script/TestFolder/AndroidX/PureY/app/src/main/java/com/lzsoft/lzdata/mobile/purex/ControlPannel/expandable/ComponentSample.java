package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable;

import android.os.Parcel;
import android.os.Parcelable;

public class ComponentSample implements Parcelable {

  private String name;
  private String desc;
  private String directory;
  private boolean isFavorite;

  public ComponentSample(String name, boolean isFavorite) {
    this.name = name;
    this.isFavorite = isFavorite;
  }

  public ComponentSample(String name, String desc, boolean isFavorite) {
    this.name = name;
    this.desc = desc;
    this.isFavorite = isFavorite;
  }

  public ComponentSample(String name, String desc, String directory, boolean isFavorite) {
    this.name = name;
    this.desc = desc;
    this.directory = directory;
    this.isFavorite = isFavorite;
  }

  protected ComponentSample(Parcel in) {
    name = in.readString();
    desc = in.readString();
  }

  public String getName() {
    return name;
  }

  public String getDescName() {
    return desc;
  }

  public String getDirectory() {
    return directory;
  }


  public boolean isFavorite() {
    return isFavorite;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ComponentSample)) return false;

    ComponentSample componentSample = (ComponentSample) o;

    if (isFavorite() != componentSample.isFavorite()) return false;
    return getName() != null ? getName().equals(componentSample.getName()) : componentSample.getName() == null;

  }

  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + (isFavorite() ? 1 : 0);
    return result;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(desc);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ComponentSample> CREATOR = new Creator<ComponentSample>() {
    @Override
    public ComponentSample createFromParcel(Parcel in) {
      return new ComponentSample(in);
    }

    @Override
    public ComponentSample[] newArray(int size) {
      return new ComponentSample[size];
    }
  };
}