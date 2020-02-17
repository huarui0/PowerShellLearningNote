package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.snap;

/**
 * Created by javierg on 06/02/2017.
 */

public class SnapData {
    private String mText;
    private String mItemCountText;
    private int mImage;

    public SnapData(String text, String ItemCountText, int image) {
        this.mText = text;
        this.mItemCountText = ItemCountText;
        this.mImage = image;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getmItemCountText() {
        return mItemCountText;
    }

    public void setmItemCountText(String mItemCountText) {
        this.mItemCountText = mItemCountText;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }
}
