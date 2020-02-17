package com.lzsoft.lzdata.mobile.purex.persistencedata.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;

import androidx.annotation.AnimRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import com.lzsoft.lzdata.mobile.purex.R;

// import com.lzsoft.lzdata.mobile.purex.common.utils.GlideApp;

import com.lzsoft.lzdata.mobile.purex.glide.custom.GlideApp;
import com.lzsoft.lzdata.mobile.purex.persistencedata.entity.Product;

import java.util.List;

/**
 * Created by javierg on 12/10/2016.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mContext;
    private List<Product> productList;

    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public ProductAdapter(Context context, List<Product> objects) {
        this.mContext = context;
        this.productList = objects;
    }

    @Override
    public ProductViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_list, null);
        view.setVisibility(View.INVISIBLE);  // 先在这里设置 隐藏，在 设置 动画的时候在 显示
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_list_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder  viewHolder, final int position) {

        final Product product = productList.get(position);
        //loading the image
        Glide.with(mContext)
                .load(product.getImage())
                .into(viewHolder.imageView);

        // Here you apply the animation when the view is bound
        // setAnimation(viewHolder.imageView, position);

        // Hook up clicks on the imageView.
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(viewHolder.imageView, viewHolder.imageView);
            }
        });

        viewHolder.imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                {
                    ResizeAnimation anim = new ResizeAnimation(view, 600, view.getWidth());
                    anim.setDuration(200);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    view.startAnimation(anim);
                }
                else
                {
                    ResizeAnimation anim = new ResizeAnimation(view, 400, view.getWidth());
                    anim.setDuration(200);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    view.startAnimation(anim);
                }

            }
        });

        viewHolder.textViewTitle.setText(product.getTitle());
        viewHolder.textViewShortDesc.setText(product.getShortdesc());
        viewHolder.textViewRating.setText(String.valueOf(product.getRating()));
        viewHolder.textViewPrice.setText(String.valueOf(product.getPrice()));

        // Here you apply the animation when the view is bound
        setAnimation(viewHolder.textViewTitle, position);
        viewHolder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.textViewTitle.startAnimation(new BGColorAnimation(viewHolder.textViewTitle));
                Snackbar.make(view, viewHolder.textViewTitle.getText().toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        viewHolder.textViewTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                {
                    BGColorAnimation anim = new BGColorAnimation(view);
                    view.startAnimation(anim);
                }
                else
                {
                    BGColorAnimation anim = new BGColorAnimation(view);
                    view.startAnimation(anim);
                }

            }
        });

        viewHolder.textViewShortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, viewHolder.textViewShortDesc.getText().toString() , Snackbar.LENGTH_LONG).show();
            }
        });

        // mItemManger is member in RecyclerSwipeAdapter Class
//        mItemManger.bindView(viewHolder.itemView, position);
        // 无效果？
        // setAnimation(viewHolder.itemView, position, android.R.anim.slide_in_left);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    //  ViewHolder Class

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            //This piece of code will animate your itemview inside recyclerview
            new Handler().postDelayed(() -> {
                itemView.setVisibility(View.VISIBLE);
                itemView.startAnimation(AnimationUtils.loadAnimation(itemView.getContext(), R.anim.anim_pop));  // Change your anim file here
            }, 100);
            setIsRecyclable(true);// Just to ensure that everytime view gets invisible, it should be recycled for next time recreation.

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public static class EnlargedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public EnlargedViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_sql_data_expanded_image);
        }
    }

    private void zoomImageFromThumb(final View thumbView, final ImageView expandedImageView) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        expandedImageView.getGlobalVisibleRect(startBounds);
        expandedImageView.findViewById(R.id.imageView).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        // thumbView.setAlpha(0f);
        // expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    public class BGColorAnimation extends Animation implements
            Animation.AnimationListener {

        private View view;
        private int currentRedColor;

        // The steps to skip between colors
        private int STEP_SIZE= 60;
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
            view.setBackgroundColor(Color.YELLOW);
            currentRedColor = 0;
        }

    }

    public class SlideAnimation extends Animation implements
            Animation.AnimationListener {

        private View view;
        private int currentRedColor;

        // The steps to skip between colors
        private int STEP_SIZE= 60;
        private int ANIMATION_DURATION = 200;


        private static final float SPEED = 0.5f;

        private float mStart;
        private float mEnd;

        public SlideAnimation(float fromX, float toX) {
            mStart = fromX;
            mEnd = toX;

            setInterpolator(new LinearInterpolator());

            float duration = Math.abs(mEnd - mStart) / SPEED;
            setDuration((long) duration);
        }

        public SlideAnimation(View view) {
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


        }

        @Override
        public void onAnimationStart(Animation animation) {


        }

    }




    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_once);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private class ResizeAnimation extends Animation {
        public ResizeAnimation(View view, int i, int width) {

        }


        private static final float SPEED = 0.5f;

        private float mStart;
        private float mEnd;

        public ResizeAnimation(float fromX, float toX) {
            mStart = fromX;
            mEnd = toX;

            setInterpolator(new LinearInterpolator());

            float duration = Math.abs(mEnd - mStart) / SPEED;
            setDuration((long) duration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            float offset = (mEnd - mStart) * interpolatedTime + mStart;
            // mOffset = (int) offset;
            // postInvalidate();
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
}
