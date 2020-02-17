package com.patrickiv.demo.enteranimationdemo.recyclerview;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balysv.materialripple.MaterialRippleLayout;
import com.lzsoft.lzdata.mobile.purex.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static final int ITEM_COUNT = 64;

    private static int sPosition;
    private static SparseBooleanArray sSelectedItems;
    private static CardClickListener sClickListener;
    private static final int MULTIPLE = 0;
    private static final int SINGLE = 1;
    private static int sModo = 0;

    public CardAdapter() {
    }

    public CardAdapter(int modo) {
        sSelectedItems = new SparseBooleanArray();
        sModo = modo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.enter_anim_recyclerview_row_empty_card, parent, false));

/*
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.enter_anim_recyclerview_row_empty_card, parent, false);

        ViewHolder cardViewHolder = new ViewHolder(
                MaterialRippleLayout.on(view)
                        .rippleOverlay(true)
                        .rippleAlpha(0.2f)
                        .rippleColor(0xFF585858)
                        .rippleHover(true)
                        .create());
        return cardViewHolder;
*/

    }

    public void setOnItemClickListener(CardClickListener clickListener) {
        sClickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mBackground.setSelected(sSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() { return ITEM_COUNT; }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,
            View.OnLongClickListener {


        CardView mBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            mBackground = itemView.findViewById(R.id.enter_anim_recyclerview_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (sSelectedItems.get(getAdapterPosition(), false)) {
                sSelectedItems.delete(getAdapterPosition());
                mBackground.setSelected(false);
                // mLabel.setTextColor(ContextCompat.getColor(sContext, android.R.color.black));
            } else {
                switch (sModo) {
                    case SINGLE:
                        sSelectedItems.put(sPosition, false);
                        break;
                    case MULTIPLE:
                    default:
                        break;
                }
                // mLabel.setTextColor(ContextCompat.getColor(sContext, R.color.colorAccent_red));
                sSelectedItems.put(getAdapterPosition(), true);
                mBackground.setSelected(true);
            }
            sClickListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void selected(int position) {
        switch (sModo) {
            case SINGLE:
                sPosition = position;
                notifyDataSetChanged();
                break;
            case MULTIPLE:
            default:
                break;
        }
    }

    public void changeMode(int modo) {
        sModo = modo;
        sSelectedItems.clear();
        notifyDataSetChanged();
    }

    public interface CardClickListener {
        void onItemClick(int position);
    }

}
