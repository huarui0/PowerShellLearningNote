package com.lzsoft.lzdata.mobile.purex.componentlib.ListView.Recipes;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzsoft.lzdata.mobile.purex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wanglai on 11/27/2016.
 */

public class RecipeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;

    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("Low-Carb", R.color.colorLowCarb);
        put("Low-Fat", R.color.colorLowFat);
        put("Low-Sodium", R.color.colorLowSodium);
        put("Medium-Carb", R.color.colorMediumCarb);
        put("Vegetarian", R.color.colorVegetarian);
        put("Balanced", R.color.colorBalanced);
    }};

    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean byViewHolder = true;

        ViewHolder holder = null;

        TextView titleTextView = null;
        TextView subtitleTextView = null;
        TextView detailTextView = null;
        ImageView thumbnailImageView = null;

        View rowView = null;
        if (byViewHolder == false) {

            // Get view for row item
            // View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
            rowView = mInflater.inflate(R.layout.recipe_list_item, parent, false);

            // Get title element
            // TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);
            titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

            // Get subtitle element
            // TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);
            subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

            // Get detail element
            // TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);
            detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);

            // Get thumbnail element
            // ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);
            thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

        } else {
            // 1
            if (convertView == null) {

                // 2
                convertView = mInflater.inflate(R.layout.recipe_list_item, parent, false);

                // 3
                holder = new ViewHolder();
                holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.recipe_list_thumbnail);
                holder.titleTextView = (TextView) convertView.findViewById(R.id.recipe_list_title);
                holder.subtitleTextView = (TextView) convertView.findViewById(R.id.recipe_list_subtitle);
                holder.detailTextView = (TextView) convertView.findViewById(R.id.recipe_list_detail);

                // 4
                convertView.setTag(holder);
            } else {
                // 5
                holder = (ViewHolder) convertView.getTag();
            }

            // 6
            /*
            TextView titleTextView = holder.titleTextView;
            TextView subtitleTextView = holder.subtitleTextView;
            TextView detailTextView = holder.detailTextView;
            ImageView thumbnailImageView = holder.thumbnailImageView;
            */
            titleTextView = holder.titleTextView;
            subtitleTextView = holder.subtitleTextView;
            detailTextView = holder.detailTextView;
            thumbnailImageView = holder.thumbnailImageView;
        }


        // 1
        Recipe recipe = (Recipe) getItem(position);

// 2
        titleTextView.setText(recipe.title);
        subtitleTextView.setText(recipe.description);
        detailTextView.setText(recipe.label);

// 3
        Picasso.get().load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);


        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        titleTextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace =
                Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);

        /*
        // 【Android】getColor(int id)在API23时过时
        http://blog.csdn.net/Crazy_zihao/article/details/50393193

        http://www.androiddesignpatterns.com/2016/08/contextcompat-getcolor-getdrawable.html
        */

        // detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(recipe.label)));
        detailTextView.setTextColor(mContext.getResources().getColor(LABEL_COLORS.get(recipe.label), null));

        if (byViewHolder == false) {
            return rowView;
        } else {
            return convertView;
        }
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}