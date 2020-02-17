package com.patrickiv.demo.enteranimationdemo.recyclerview;

import android.graphics.Rect;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Patrick Ivarsson on 7/18/17.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public ItemOffsetDecoration(int itemOffset) {
        mSpacing = itemOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mSpacing, mSpacing, mSpacing, mSpacing);
    }
}