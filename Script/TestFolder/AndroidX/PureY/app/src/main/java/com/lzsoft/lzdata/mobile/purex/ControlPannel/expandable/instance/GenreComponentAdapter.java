package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

// import com.example.android.navigationsample.NavigationBasicDemoActivity;
// import com.example.android.pagingsample.PagingSampleActivity;
// import com.example.android.persistence.ui.BasicSamplePersistenceActivity;
import com.balysv.materialripple.MaterialRippleLayout;
import com.example.android.persistencecontentprovider.PersistenceContentProviderActivity;
import com.example.android.io2014.Activity_Main_IO2014;
import com.example.android.observability.ui.UserActivity;
import com.example.android.persistence.ui.BasicSamplePersistenceActivity;
import com.example.android_kotlin.livedatabuilder.LiveDataActivity;
import com.example.android_kotlin.observability.ui.UserActivityKotlin;
import com.example.athbk.ultimatetablayout.UltimateTabLayoutActivity;
import com.example.balysv.materialripple.demo.MaterialRippleDemoActivity;
import com.example.balysv.materialripple.demo.MaterialRippleDemoListActivity;
import com.example.balysv.materialripple.demo.MaterialRippleDemoRecyclerActivity;
import com.example.daimajia.animationdemo.AnimationDemoActivity;
import com.example.daimajia.animationdemo.ExampleActivity;
import com.example.etsy.EtsySampleActivity;
import com.example.inazaruk.actionscontentview.ExamplesActivity;
import com.example.inazaruk.navigationsample.NavigationSampleActivity;
import com.example.ozodrukh.dreamers.sample.RippleDrawableSampleActivity;
import com.google.android.material.snackbar.Snackbar;
import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.ComponentSample;
import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.ControlPanelExpandActivity;
import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.GenreComponent;
import com.lzsoft.lzdata.mobile.purex.R;

import com.lzsoft.lzdata.mobile.purex.componentlib.CardView.albumcardviewdemo.AlbumsCardViewActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.ListView.Recipes.RecipeListViewJsonActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.FullRecyclerViewActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.dragandswipe.helper.ItemTouchHelperAdapter;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.expand.ExpandActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.activity.MaterialTabsPanelActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.MoviesTabsSwipeActivity;
import com.lzsoft.lzdata.mobile.purex.componentlib.chart.mpchart.mpchartexample.notimportant.MPChartExampleActivity;
import com.lzsoft.lzdata.mobile.purex.database.mysql.puredata.MySQLDataPureActivity;
import com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn.MainConnDbActivity;
import com.lzsoft.lzdata.mobile.purex.database.mysql.remotedb.RemoteMySQLActivity;
import com.patrickiv.demo.continuousanimationdemo.ContinuousAnimDemoActivity;
import com.patrickiv.demo.enteranimationdemo.AnimRecyclerViewActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.waynechen.rippleeffectusage.RippleEffectUsageActivity;

import java.util.List;

import androidx.annotation.AnimRes;

public class GenreComponentAdapter extends ExpandableRecyclerViewAdapter<GenreComponentViewHolder, ComponentSampleViewHolder> implements ItemTouchHelperAdapter {

    private Context mContext;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private ExpandControlPanelActivity expandControlPanelActivity;

    public GenreComponentAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.mContext = context;
    }

    public GenreComponentAdapter(ExpandControlPanelActivity expandControlPanelActivity,Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.expandControlPanelActivity = expandControlPanelActivity;
        this.mContext = context;

    }

    @Override
    public GenreComponentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thoughtbot_recyclerview_list_item_genre, parent, false);

        return new GenreComponentViewHolder(view);
    }

    @Override
    public ComponentSampleViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {

/*
        // 其中R.layout 从thoughtbot_recyclerview_list_item_artist拷贝过来
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandcontrolpanel_recyclerview_list_item_sample, parent, false);

        ComponentSampleViewHolder componentSampleViewHolder = new ComponentSampleViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // 说明：放在这里无效，应该放在 onBindChildViewHolder 的 onClickListener！
                // Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anim_move_left_in_activity);
                // v.startAnimation(hyperspaceJumpAnimation);

                expandControlPanelActivity.onComponentSamplesListItemClicked(v);
            }
        });

*/


        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expandcontrolpanel_recyclerview_list_item_sample, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 说明：放在这里无效，应该放在 onBindChildViewHolder 的 onClickListener！
                 */
/*
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anim_move_left_in_activity);
                v.startAnimation(hyperspaceJumpAnimation);
*/
                expandControlPanelActivity.onComponentSamplesListItemClicked(v);
            }
        });
        ComponentSampleViewHolder componentSampleViewHolder = new ComponentSampleViewHolder(
                MaterialRippleLayout.on(view)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(0xFF585858)
                .rippleHover(true)
                .create());

        return componentSampleViewHolder;
    }

    @Override
    public void onBindChildViewHolder(ComponentSampleViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ComponentSample componentSample = ((GenreComponent) group).getItems().get(childIndex);
        holder.setComponentSampleName(componentSample.getName());
        holder.setComponentSampleDescName(componentSample.getDescName());
        holder.setComponentSampleDirectoryName(componentSample.getDirectory());

        // Here you apply the animation when the view is bound
/*
        setAnimation(holder.itemView, flatPosition, android.R.anim.slide_in_left);
*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // holder.itemView.startAnimation(new BGColorAnimation(holder.itemView));

                // overridePendingTransition(R.anim.anim_move_right_in_activity, R.anim.anim_move_left_out_activity);

                setAnimation(view, flatPosition, R.anim.anim_move_right_in_activity, true);
                setAnimation(view, flatPosition, R.anim.anim_move_left_out_activity, true);

/*
                Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.anim_move_left_in_activity);
                holder.itemView.setAnimation(animAnticipateOvershoot);
*/

                // expandControlPanelActivity.onComponentSamplesListItemClicked(view);

                TextView childTextView = view.findViewById(R.id.list_item_sample_name);
                Snackbar.make(view, childTextView.getText(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent;


                switch (group.toString()) {
                    case "ThoughtbotExapendRecyclerview":
                        intent = new Intent(mContext, ExpandActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ExpandActivity.class));
                        break;

                    default:
                        TextView groupTextView = view.findViewById(R.id.list_item_sample_name);
                        Snackbar.make(view, groupTextView.getText(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

//                          intent = new Intent(mContext, BasicSamplePersistenceActivity.class);
//                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //  mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, BasicSamplePersistenceActivity.class));
                }

                FragmentItem fragmentItem;
                switch (childTextView.getText().toString()) {
                    case "ThoughtbotExapendRecyclerview":
                        intent = new Intent(mContext, ExpandActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ExpandActivity.class));
                        break;
                    case "FullExapendRecyclerview":
                        intent = new Intent(mContext, FullRecyclerViewActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, FullRecyclerViewActivity.class));
                        break;
                    case "ExpandableControPanel":
                        intent = new Intent(mContext, ControlPanelExpandActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    case "EnterAnimationRecyclerView":
                        intent = new Intent(mContext, AnimRecyclerViewActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Full Recyclerview Snap Demo":
/*
                        Parcelable fragmentItem;
                        fragmentItem.writeParcelable(author,flags);
*/
                        fragmentItem = new FragmentItem("SnapFragment");
                        intent = new Intent(mContext, FullRecyclerViewActivity.class);
                        intent.putExtra("fragmentItem", fragmentItem);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Full Recyclerview Update Data Demo":
/*
                        Parcelable fragmentItem;
                        fragmentItem.writeParcelable(author,flags);
*/
                        fragmentItem = new FragmentItem("UpdateDataFragment");
                        intent = new Intent(mContext, FullRecyclerViewActivity.class);
                        intent.putExtra("fragmentItem", fragmentItem);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;


                    /** 以上 为 RecyclerView Demo
                     * --------------------------------*
                     */

                    case "Albums CardView Demo":
                        intent = new Intent(mContext, AlbumsCardViewActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    /** 以上 为 CardView Demo
                     * --------------------------------*
                     */

                    case "Recipes ListView Demo":
                        intent = new Intent(mContext, RecipeListViewJsonActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Etsy Staggered Grid View Demo":
                        intent = new Intent(mContext, EtsySampleActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;


                    /** 以上 为 ListView Demo
                     * --------------------------------*
                     */

                    case "Material Design Tabs":
                        intent = new Intent(mContext, MaterialTabsPanelActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    case "Movies Tabs Swipe Demo":
                        intent = new Intent(mContext, MoviesTabsSwipeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "NavigationSample Demo":
                        intent = new Intent(mContext, NavigationSampleActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "ActionsContentView Demo":
                        intent = new Intent(mContext, ExamplesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "UltimateTabLayout for ViewPager":
                        intent = new Intent(mContext, UltimateTabLayoutActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 Taps Demo
                     * --------------------------------*
                     */

                    case "Android Navigation Basic Demo":
//                        intent = new Intent(mContext, NavigationBasicDemoActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 Navigation Demo
                     * --------------------------------*
                     */
                    case "Android Paging Sample":
//                        intent = new Intent(mContext, PagingSampleActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    /** 以上 为 Paging Demo
                     * --------------------------------*
                     */


                    case "ContinuousAnimDemo":
                        intent = new Intent(mContext, ContinuousAnimDemoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Daimajia Animation Demo":
                        intent = new Intent(mContext, AnimationDemoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Daimajia Animation Example":
                        intent = new Intent(mContext, ExampleActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Android IO 2014 Demo":
                        intent = new Intent(mContext, Activity_Main_IO2014.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Ripple Effect Usage Demo":
                        intent = new Intent(mContext, RippleEffectUsageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Ripple Drawable Sample":
                        intent = new Intent(mContext, RippleDrawableSampleActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Material Ripple Demo":
                        intent = new Intent(mContext, MaterialRippleDemoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Material Ripple ListView Demo":
                        intent = new Intent(mContext, MaterialRippleDemoListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Material Ripple RecyclerView Demo":
                        intent = new Intent(mContext, MaterialRippleDemoRecyclerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 Animation Demo
                     * --------------------------------*
                     */

                    case "SQLite Room Sample":
                        intent = new Intent(mContext, BasicSamplePersistenceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 SQLite Demo
                     * --------------------------------*
                     */


                    case "MySQL Data Pure Demo":
                        intent = new Intent(mContext, MySQLDataPureActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    case "Ravi MySQL Conn Demo":
                        intent = new Intent(mContext, MainConnDbActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Remote MySQL DB Demo":
                        intent = new Intent(mContext, RemoteMySQLActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 MySQL Demo
                     * --------------------------------*
                     */


                    case "Basic Sample Pesistence":
                        intent = new Intent(mContext, BasicSamplePersistenceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Persistence ContentProvider Sample":
                        intent = new Intent(mContext, PersistenceContentProviderActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;
                    case "Basic RxJava Sample":
                        intent = new Intent(mContext, UserActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "Basic RxJava Kotlin Sample":
                        intent = new Intent(mContext, UserActivityKotlin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    case "LiveData Kotlin Sample":
                        intent = new Intent(mContext, LiveDataActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    /** 以上 为 Architecture Components Demo
                     * --------------------------------*
                     */



                    case "MPChartExampleDemo":
                        intent = new Intent(mContext, MPChartExampleActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, ControlPanelExpandActivity.class));
                        break;

                    default:
//                          intent = new Intent(mContext, BasicSamplePersistenceActivity.class);
//                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //  mContext.startActivity(intent);
                        // mContext.startActivity(new Intent(mContext, BasicSamplePersistenceActivity.class));
                }


            }
        });

    }

    @Override
    public void onBindGroupViewHolder(GenreComponentViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setGenreComponentTitle(group);
        // 未验证 先注释掉吧
        // holder.itemView.setAlpha(flatPosition > 0 ? 0f : 1f);

    }

    public void removeItemAtPosition(int position) {
        // colors.remove(position);
        notifyItemRemoved(position);
    }

    public void addItemAtPosition(int position) {
        // colors.add(position, ColorsHelper.COLORS.get(position));
        notifyItemInserted(position);
    }

    public void changeItemAtPosition(int position) {
        // colors.set(position, ColorsHelper.getRandomColor());
        notifyItemChanged(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public class BGColorAnimation extends Animation implements
            Animation.AnimationListener {

        private View view;
        private int currentRedColor;

        // The steps to skip between colors
        private int STEP_SIZE = 60;
        private int ANIMATION_DURATION = 200;

        public BGColorAnimation(View view) {
            this.view = view;
            setDuration(ANIMATION_DURATION);
            setRepeatCount(255 / STEP_SIZE);
            setFillAfter(true);
            setInterpolator(new AccelerateInterpolator());

            setAnimationListener(this);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {


            int curColor;

            // sRGB
            Color thisOpaqueRed = Color.valueOf(Color.CYAN); // from a color int
            // Color translucentRed = Color.valueOf(1.0f, 0.0f, 0.0f, 0.5f);
            // curColor =Color.rgb(currentRedColor += STEP_SIZE, 0, 0);

            curColor = thisOpaqueRed.toArgb();
            view.setBackgroundColor(curColor);
        }

        @Override
        public void onAnimationStart(Animation animation) {
            view.setBackgroundColor(Color.WHITE);
            currentRedColor = 0;
        }

    }


    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_once);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setAnimation(View viewToAnimate, int position, @AnimRes int AnimResId) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, AnimResId);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setAnimation(View viewToAnimate, int position, @AnimRes int AnimResId,boolean positionLessFlag) {
        // 说明：positionLessFlag 是真，那么每次都执行，否则 跳过。
        // If the bound view wasn't previously displayed on screen, it's animated
        if ((position > lastPosition) || (positionLessFlag)) {
            Animation animation = AnimationUtils.loadAnimation(mContext, AnimResId);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }



    public static class FragmentItem implements Parcelable {
        public String getFragmentTitle() {
            return fragmentTitle;
        }

        public void setFragmentTitle(String fragmentTitle) {
            this.fragmentTitle = fragmentTitle;
        }

        String fragmentTitle;

        @Override
        public int describeContents() {
            return 0;
        }

        // Storing the FragmentItem data to Parcel object
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(fragmentTitle);
        }

        // A constructor that initializes the FragmentItem object
        public FragmentItem(String fragmentTitle) {
            this.fragmentTitle = fragmentTitle;
        }

        /**
         * Retrieving FragmentItem data from Parcel object
         * This constructor is invoked by the method createFromParcel(Parcel source) of
         * the object CREATOR
         **/
        private FragmentItem(Parcel in){
            this.fragmentTitle = in.readString();
        }

        public static final Creator<FragmentItem> CREATOR = new Creator<FragmentItem>() {
            @Override
            public FragmentItem createFromParcel(Parcel source) {
                return new FragmentItem(source);
            }
/*
            @Override
            public FragmentItem createFromParcel(Parcel source) {
                FragmentItem fragmentItem=new FragmentItem();
                fragmentItem.setTitle(source.readString());
                return fragmentItem;
            }
*/
            @Override
            public FragmentItem[] newArray(int size) {
                return new FragmentItem[size];
            }
        };
    }



}
