package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance.GenreComponentAdapter;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.addfavorites.AddFavoritesFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.dragandswipe.DragAndSwipeListFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.dragandswipe.DragGridFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.expandable.ExpandableFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.horizontal.HorizontalFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.indexed.IndexedFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.sections.SectionFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.sectionwithline.SectionWithLineFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.snap.SnapFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.stickyheader.StickyHeaderFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.swipe.SwipeListFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.updateData.UpdateDataFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.vertical.VerticalFragment;

public class FullRecyclerViewActivity extends AppCompatActivity {

    Fragment mFragment;
    VerticalFragment mVerticalFragment;
    UpdateDataFragment mUpdateDataFragment;

    private static final int MULTIPLE = 0;
    private static final int SINGLE = 1;
    private int mMode = 0;

/*
   一个Activity类中只有无参的构造方法会被执行，定义有参数的构造方法是没有意义的。
    public FullRecyclerViewActivity(Fragment mFragment, int mMode) {
        this.mFragment = mFragment;
        this.mMode = mMode;
    }
*/

    public FullRecyclerViewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_recycler_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_flower);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
*/
        Intent intent = getIntent();
        if (intent.getExtras() == null) {
            if (savedInstanceState == null) {
                mFragment = new DragAndSwipeListFragment();
                addFragment();
            }
        } else {
            GenreComponentAdapter.FragmentItem fragmentItem = getIntent().getExtras().getParcelable("fragmentItem");
            switch (fragmentItem.getFragmentTitle()) {
                case "UpdateDataFragment":
                    mFragment = UpdateDataFragment.newInstance();
                    mUpdateDataFragment = (UpdateDataFragment) mFragment;
                    addFragment();
                    break;
                case "SnapFragment":
                    mFragment = SnapFragment.newInstance();
                    addFragment();
                    break;
                default:
                    if (savedInstanceState == null) {
                        mFragment = new UpdateDataFragment();
                        addFragment();
                    }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_full_recycler_view, menu);
        MenuItem addItem = menu.findItem(R.id.main_menu_add_item);
        MenuItem mode = menu.findItem(R.id.main_menu_changes_mode);

        if (mFragment instanceof DragAndSwipeListFragment) {
            addItem.setVisible(true);
        } else {
            addItem.setVisible(false);
        }

        if (mFragment instanceof UpdateDataFragment) {
            mode.setVisible(true);
        } else {
            mode.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        invalidateOptionsMenu();

        switch (item.getItemId()) {


            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

/*
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
*/
                // 需要设置 manifest file 麻烦，不用
                // NavUtils.navigateUpFromSameTask(this);
                // return true;
                break;
            case R.id.main_menu_list:
                mFragment = DragAndSwipeListFragment.newInstance();
                break;

            case R.id.main_menu_grid:
                mFragment = DragGridFragment.newInstance();
                break;

            case R.id.main_menu_swipe_list:
                mFragment = SwipeListFragment.newInstance();
                break;

            case R.id.main_menu_horizontal_list:
                mFragment = HorizontalFragment.newInstance();
                break;

            case R.id.main_menu_vertical_list:
                mFragment = VerticalFragment.newInstance();
                mVerticalFragment = (VerticalFragment) mFragment;
                break;

            case R.id.main_menu_expandable:
                mFragment = ExpandableFragment.newInstance();
                break;

            case R.id.main_menu_add_item:
                mVerticalFragment.addItem();
                break;

            case R.id.main_menu_multiple:
                // mFragment = MultipleFragment.newInstance();
                break;
            case R.id.main_menu_single:
                // mFragment = SingleFragment.newInstance();
                break;

            case R.id.main_menu_snap:
                mFragment = SnapFragment.newInstance();
                break;

            case R.id.main_menu_animation:
                // mFragment = AnimationFragment.newInstance();
                break;

            case R.id.main_menu_section:
                mFragment = SectionFragment.newInstance();
                break;

            case R.id.main_menu_indexed:
                mFragment = IndexedFragment.newInstance();
                break;

            case R.id.main_menu_add_favorites:
                mFragment = AddFavoritesFragment.newInstance();
                break;

            case R.id.main_menu_section_with_line:
                mFragment = SectionWithLineFragment.newInstance();
                break;

            case R.id.main_menu_sticky_header:
                mFragment = StickyHeaderFragment.newInstance();
                break;

            case R.id.main_menu_chat:
                // mFragment = ChatFragment.newInstance();
                break;

            case R.id.main_menu_multiple_clicks:
                // mFragment = MultipleClicksFragment.newInstance();
                break;

            case R.id.main_menu_update_data:
                mFragment = UpdateDataFragment.newInstance();
                mUpdateDataFragment = (UpdateDataFragment) mFragment;
                break;
            case R.id.main_menu_changes_mode:
                updateMenuTitles();
            default:
                return super.onOptionsItemSelected(item);
        }

        addFragment();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mode = menu.findItem(R.id.main_menu_changes_mode);
        if (mMode == MULTIPLE) {
            mode.setTitle(getResources().getString(R.string.main_menu_update_data_single));
        } else {
            mode.setTitle(getResources().getString(R.string.main_menu_update_data_multiple));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void updateMenuTitles() {
        invalidateOptionsMenu();
        if (mMode == MULTIPLE) {
            mMode = SINGLE;
        } else {
            mMode = MULTIPLE;
        }
        mUpdateDataFragment.changeMode(mMode);
    }

    public void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, mFragment)
                .disallowAddToBackStack()
                .commit();

    }

}