package com.example.inazaruk.actionscontentview.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzsoft.lzdata.mobile.purex.R;

public class EffectsAdapter extends BaseAdapter {
  private final LayoutInflater mInflater;

  private final String[] mTitles;
  private final int[] mLayouts;
  private final String[][] mHtmls;

  public EffectsAdapter(Context context) {
    mInflater = LayoutInflater.from(context);

    final Resources res = context.getResources();
    mTitles = res.getStringArray(R.array.effects_name);

    final String[] actionsHtml = res.getStringArray(R.array.effects_actions_html);
    final String[] contentHtml = res.getStringArray(R.array.effects_content_html);
    mHtmls = new String[][] {
        actionsHtml, contentHtml
    };

    final TypedArray layoutsArray = res.obtainTypedArray(R.array.effect_layouts);
    final int count = layoutsArray.length();
    mLayouts = new int[count];
    for ( int i=0; i<count; ++i ) {
      mLayouts[i] = layoutsArray.getResourceId(i, 0);
    }
    layoutsArray.recycle();
  }

  public String getActionsHtml(int position) {
    return mHtmls[0][position];
  }

  public String getContentHtml(int position) {
    return mHtmls[1][position];
  }

  public String getEffectTitle(int position) {
    return mTitles[position];
  }

  @Override
  public int getCount() {
    return mLayouts.length;
  }

  @Override
  public Integer getItem(int position) {
    return mLayouts[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final ViewHolder holder;
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.inazaruk_actionscontentview_action_list_item, parent, false);

      holder = new ViewHolder();
      holder.text = (TextView) convertView.findViewById(android.R.id.text1);

      final Drawable icon = convertView.getContext().getResources().getDrawable(R.drawable.ic_action_effects);
      icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
      holder.text.setCompoundDrawables(icon, null, null, null);
      holder.text.setText(mTitles[position]);

      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    holder.text.setText(mTitles[position]);

    return convertView;
  }

  private static class ViewHolder {
    TextView text;
  }
}
