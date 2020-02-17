package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.dragandswipe.helper.SimpleItemTouchHelperCallback;
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem;
import com.patrickiv.demo.enteranimationdemo.recyclerview.ItemOffsetDecoration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeAnimationGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeArchitectureGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeCardViewGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeChartGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeGenreComponents;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeMySQLGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeNavigationGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makePagingGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeRecycleViewGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeSQLiteGenreComponent;
import static com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponentFactory.makeTabsGenreComponent;


public class ExpandControlPanelActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RadioGroup rg_contropanel_Operations;
    CheckBox cb_contropanel_Predictive;
    CheckBox cb_contropanel_CustomAnimator;

    private GenreComponentAdapter genreComponentAdapter;


    private final Handler mHandler = new Handler();

    private AnimationItem[] mAnimationItems;
    private AnimationItem mSelectedItem;

    private ItemTouchHelper mItemTouchHelper;

    protected AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Fall down", R.anim.layout_animation_fall_down),
                new AnimationItem("Slide from right", R.anim.layout_animation_from_right),
                new AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom)
        };
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlpanel_recyclerview_expand);


        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getClass().getSimpleName());

        // RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        rg_contropanel_Operations = findViewById(R.id.rg_contropanel_Operations);
        cb_contropanel_Predictive = findViewById(R.id.cb_contropanel_Predictive);
        cb_contropanel_CustomAnimator = findViewById(R.id.cb_contropanel_CustomAnimator);

        cb_contropanel_CustomAnimator.setChecked(true);

        setupComponentSamplesList();

        mAnimationItems = getAnimationItems();
        mSelectedItem = mAnimationItems[1];

        runLayoutAnimation(recyclerView, mSelectedItem);
    }


    private void setupComponentSamplesList() {

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return cb_contropanel_Predictive.isChecked();
            }
        };
        recyclerView.setLayoutManager(layoutManager);


        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));


        genreComponentAdapter = new GenreComponentAdapter(this,getBaseContext(), makeGenreComponents());
        recyclerView.setAdapter(genreComponentAdapter);

        ItemTouchHelper.Callback callback = new ExpandControlHelperCallback(genreComponentAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);


        toggleAllGroup();

        Button clearAll = (Button) findViewById(R.id.toggle_all_button);
        clearAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAllGroup();
            }
        });

        Button clear = findViewById(R.id.toggle_button);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSpecificGroup();
            }
        });

        setupRecyclerViewAnimator();

    }

    public void onComponentSamplesListItemClicked(View view) {
        int itemAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (itemAdapterPosition == RecyclerView.NO_POSITION) {
            return;
        }

        int checkedRadioButton = rg_contropanel_Operations.getCheckedRadioButtonId();
        if (checkedRadioButton == R.id.rb_contropanel_Delete) {
            // genreComponentAdapter.removeItemAtPosition(itemAdapterPosition);
        } else if (checkedRadioButton == R.id.rb_contropanel_Add) {
            // genreComponentAdapter.addItemAtPosition(itemAdapterPosition + 1);
        } else if (checkedRadioButton == R.id.rb_contropanel_Change) {
            genreComponentAdapter.changeItemAtPosition(itemAdapterPosition);
        }
    }

    public void onCustomAnimatorCheckedChange() {
        setupRecyclerViewAnimator();
    }

    public void setupRecyclerViewAnimator() {
        if (cb_contropanel_CustomAnimator.isChecked()) {
            recyclerView.setItemAnimator(new ChildItemAnimator());
        } else {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    private void setupRecyclerViewAnimator_old() {
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:

        /*
            RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
            if (animator instanceof DefaultItemAnimator) {
              ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
            }
        */

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getBaseContext(), resId);
        recyclerView.setLayoutAnimation(animation);

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        recyclerView.setBackgroundResource(outValue.resourceId);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        genreComponentAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        genreComponentAdapter.onRestoreInstanceState(savedInstanceState);
    }

    protected void toggleSpecificGroup() {
        // 这里要一个 for 循环 取数据。 //如何都是收拢，折展开，
        // 是不是要判断 多和少 ? 往多的靠？
        genreComponentAdapter.toggleGroup(makeRecycleViewGenreComponent());
        genreComponentAdapter.toggleGroup(makeCardViewGenreComponent());
        genreComponentAdapter.toggleGroup(makeTabsGenreComponent());
        genreComponentAdapter.toggleGroup(makeNavigationGenreComponent());
    }

    protected void toggleAllGroup() {
        // 这里要一个 for 循环 取数据。 //如何都是收拢，折展开，
        // 是不是要判断 多和少 ? 往多的靠？
        genreComponentAdapter.toggleGroup(makeRecycleViewGenreComponent());
        genreComponentAdapter.toggleGroup(makeCardViewGenreComponent());
        genreComponentAdapter.toggleGroup(makeTabsGenreComponent());
        genreComponentAdapter.toggleGroup(makeNavigationGenreComponent());
        genreComponentAdapter.toggleGroup(makePagingGenreComponent());
        genreComponentAdapter.toggleGroup(makeSQLiteGenreComponent());
        genreComponentAdapter.toggleGroup(makeMySQLGenreComponent());
        genreComponentAdapter.toggleGroup(makeArchitectureGenreComponent());
        genreComponentAdapter.toggleGroup(makeAnimationGenreComponent());
        genreComponentAdapter.toggleGroup(makeChartGenreComponent());
    }

    private void runLayoutAnimation(final RecyclerView recyclerView, final AnimationItem item) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, item.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
