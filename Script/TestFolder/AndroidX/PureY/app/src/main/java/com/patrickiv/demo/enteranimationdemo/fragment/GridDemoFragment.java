package com.patrickiv.demo.enteranimationdemo.fragment;

import android.content.Context;

import com.lzsoft.lzdata.mobile.purex.R;
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem;
import com.patrickiv.demo.enteranimationdemo.recyclerview.CardAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
public class GridDemoFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.enter_anim_recyclerview_frag_grid;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 4);
    }

    @Override
    protected AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Slide from bottom", R.anim.grid_layout_animation_from_bottom),
                new AnimationItem("Scale", R.anim.grid_layout_animation_scale),
                new AnimationItem("Scale random", R.anim.grid_layout_animation_scale_random)
        };
    }

}
