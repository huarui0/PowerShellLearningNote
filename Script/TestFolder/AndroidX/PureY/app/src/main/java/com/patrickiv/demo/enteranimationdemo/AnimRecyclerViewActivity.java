package com.patrickiv.demo.enteranimationdemo;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;

import com.lzsoft.lzdata.mobile.purex.R;
import com.patrickiv.demo.enteranimationdemo.fragment.GridDemoFragment;
import com.patrickiv.demo.enteranimationdemo.fragment.ListDemoFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
public class AnimRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewGroup mButtonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim_recyclerview);

        mButtonContainer = findViewById(R.id.button_container);
        findViewById(R.id.btn_list_fragment).setOnClickListener(this);
        findViewById(R.id.btn_grid_fragment).setOnClickListener(this);

        if (savedInstanceState != null) {
            final int count = getSupportFragmentManager().getBackStackEntryCount();
            mButtonContainer.setAlpha(count > 0 ? 0f : 1f);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            mButtonContainer.animate().alpha(1f).start();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_fragment:
                showFragment(new ListDemoFragment());
                break;
            case R.id.btn_grid_fragment:
                showFragment(new GridDemoFragment());
                break;
            default:
                break;
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        mButtonContainer.animate().alpha(0f).start();
    }

}
