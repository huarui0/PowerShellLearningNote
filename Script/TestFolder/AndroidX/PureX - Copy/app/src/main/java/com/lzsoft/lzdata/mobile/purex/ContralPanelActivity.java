package com.lzsoft.lzdata.mobile.purex;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzsoft.lzdata.mobile.purex.anniinteropter.MyBounceInterpolator;
import com.lzsoft.lzdata.mobile.purex.database.mysql.puredata.MySQLDataPureActivity;

import java.util.List;

public class ContralPanelActivity extends Activity implements OnClickListener {
    private final String LOG_TAG = MySQLDataPureActivity.class.getSimpleName();

    private ViewPager viewPager;
    private List<View> viewList;
    //private int images[]={R.drawable.v1,R.drawable.v2,R.drawable.v3,R.drawable.v4};//导航图片资源
    private View view1, view2, view3, view4;
    private ImageView points[];//存放小圆圈数组

    private int currentIndex = 0;//当前页面,默认首页

    private Button startButton;

    private Button returnTestButton;
    private Button forwardTestButton;

    private Button bounceTestButton;
    private Button bounceTestImageButton;
    private Button recipeTestImageButton;
    private Button studentsTestButton;

    private Button r2l1_TestImageButton;
    private Button r2l2_TestImageButton;
    private Button r2l3_TestImageButton;
    private Button r2l4_TestImageButton;

    private Button r3l1_TestImageButton;
    private Button r3l2_TestImageButton;
    private Button r3l3_TestImageButton;
    private Button r3l4_TestImageButton;

    private Button r4l1_TestImageButton;
    private Button r4l2_TestImageButton;
    private Button r4l3_TestImageButton;
    private Button r4l4_TestImageButton;

    private Button r51_TestImageButton;
    private Button r52_TestImageButton;
    private Button r53_TestImageButton;
    private Button r54_TestImageButton;

    private Button r61_TestImageButton;
    private Button r62_TestImageButton;
    private Button r63_TestImageButton;
    private Button r64_TestImageButton;

    TextView blobText;
    public String[] text = new String[]{""};
    public int position = 0;

    Animation fadeInAnimationObject;
    Animation textDisplayAnimationObject;
    Animation delayBetweenAnimations;
    Animation fadeOutAnimationObject;
    Animation bounceAnimationObject;

    Animation bounceinAnimationObject;

    Animation defaultAnimation;

    Animation testAnimationObject;

    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    // 线程标志
    private boolean isStop = false;

    // 播放状态标志
    private boolean isPlaying = false;

    public ContralPanelActivity() {

    }

    public ContralPanelActivity(TextView textV, String[] textList) {
        this(textV, 700, 1000, 2000, textList);
    }

    public ContralPanelActivity(TextView textView, int fadeEffectDuration, int delayDuration, int displayLength, String[] textList) {
        blobText = textView;
        // tv = textView;
        text = textList;
        this.fadeEffectDuration = fadeEffectDuration;
        this.delayDuration = delayDuration;
        this.displayFor = displayLength;
        // 这里设置所谓的指针，指向fadeInAnimationObject所在的地址，
        // 当 fadeInAnimationObject 有变化时，defaultAnimation 也所之变化。
        defaultAnimation = fadeInAnimationObject;
        blobText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        setupAmplitudeSeekBar();
        setupFrequencySeekBar();
        setupDurationVar();

        returnTestButton = findViewById(R.id.control_panel_return_button_test);
        returnTestButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "返回的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.ani_to_main_button_test);
                didTapImageButton(returnTestButton);

/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                // startActivity(new Intent(ContralPanelActivity.this, MainActivity.class));

            }
        });

        forwardTestButton = findViewById(R.id.control_panel_forward_button_test);
        forwardTestButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "启动图片放大界面的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.ani_to_enlarge_button_test);
                didTapImageButton(forwardTestButton);

/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                startActivity(new Intent(ContralPanelActivity.this, MySQLDataPureActivity.class));

            }
        });

        bounceTestImageButton = findViewById(R.id.control_panel_play_button_test);
        bounceTestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                didTapImageButton(bounceTestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, CountrySQLiteActivity.class));

            }
        });

        bounceTestButton = findViewById(R.id.control_panel_bounce_button_test);
        bounceTestButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                didTapButton(blobText);

                // startActivity(new Intent(ContralPanelActivity.this, RemoteMySQLActivity.class));

            }
        });



        recipeTestImageButton = findViewById(R.id.control_panel_recipe_button_test);
        recipeTestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(recipeTestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, RecipeListViewJsonActivity.class));

            }
        });

        studentsTestButton = findViewById(R.id.control_panel_students_button_test);
        studentsTestButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(studentsTestButton);

                // startActivity(new Intent(ContralPanelActivity.this, StudentsSQLiteActivity.class));
            }
        });






        r2l1_TestImageButton = findViewById(R.id.control_panel_r2l1_button_test);
        r2l1_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r2l1_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, RateSQLiteActivity.class));

            }
        });

        r2l2_TestImageButton = findViewById(R.id.control_panel_r2l2_button_test);
        r2l2_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapButton(r2l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, PostsSQLiteExampleActivity.class));

            }
        });



        r2l3_TestImageButton = findViewById(R.id.control_panel_r2l3_button_test);
        r2l3_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r2l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FoodSQLiteDemoActivity.class));

            }
        });

        r2l4_TestImageButton = findViewById(R.id.control_panel_r2l4_button_test);
        r2l4_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r2l4_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, AlbumsCardViewActivity.class));
            }
        });




        r3l1_TestImageButton = findViewById(R.id.control_panel_r3l1_button_test);
        r3l1_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r3l1_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, GridViewActivity.class));

            }
        });

        r3l2_TestImageButton = findViewById(R.id.control_panel_r3l2_button_test);
        r3l2_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapButton(r3l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, MySQLDataPureActivity.class));

            }
        });

        r3l3_TestImageButton = findViewById(R.id.control_panel_r3l3_button_test);
        r3l3_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r3l3_TestImageButton);

                //  startActivity(new Intent(ContralPanelActivity.this, MainConnDbActivity.class));

            }
        });

        r3l4_TestImageButton = findViewById(R.id.control_panel_r3l4_button_test);
        r3l4_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r3l4_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, MoviesTabsSwipeActivity.class));
            }
        });


        r4l1_TestImageButton = findViewById(R.id.control_panel_r4l1_button_test);
        r4l1_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l1_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, MaterialTabsPanelActivity.class));

            }
        });

        r4l2_TestImageButton = findViewById(R.id.control_panel_r4l2_button_test);
        r4l2_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, MaterialDesignPanelActivity.class));

            }
        });

        r4l3_TestImageButton = findViewById(R.id.control_panel_r4l3_button_test);
        r4l3_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentBasicActivity.class));

            }
        });

        r4l4_TestImageButton = findViewById(R.id.control_panel_r4l4_button_test);
        r4l4_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l4_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentVariableTestActivity.class));
            }
        });

        r51_TestImageButton = findViewById(R.id.control_panel_r51_button_test);
        r51_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l1_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, CallFragmentMathodActivity.class));

            }
        });

        r52_TestImageButton = findViewById(R.id.control_panel_r52_button_test);
        r52_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, SwipeRefreshListViewActivity.class));

            }
        });

        r53_TestImageButton = findViewById(R.id.control_panel_r53_button_test);
        r53_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentBasicActivity.class));

            }
        });

        r54_TestImageButton = findViewById(R.id.control_panel_r54_button_test);
        r54_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l4_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentVariableTestActivity.class));
            }
        });


        r61_TestImageButton = findViewById(R.id.control_panel_r61_button_test);
        r61_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l1_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, CallFragmentMathodActivity.class));

            }
        });

        r62_TestImageButton = findViewById(R.id.control_panel_r62_button_test);
        r62_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, MaterialDesignPanelActivity.class));

            }
        });

        r63_TestImageButton = findViewById(R.id.control_panel_r63_button_test);
        r63_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的图像按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l3_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentBasicActivity.class));

            }
        });

        r64_TestImageButton = findViewById(R.id.control_panel_r64_button_test);
        r64_TestImageButton.setOnClickListener(new OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(ContralPanelActivity.this, "动画的按钮", Toast.LENGTH_SHORT).show();
                // didTapImageButton(r4l4_TestImageButton);

                // startActivity(new Intent(ContralPanelActivity.this, FragmentVariableTestActivity.class));
            }
        });

    }

    public void startAnimation(TextView thisText, Animation animation) {
        thisText.startAnimation(animation);
    }

    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", 0, 25, 0);
        // animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.setStartDelay(500);
        animator.setDuration(2000);
        animator.start();
    }
    private void doEasingInterpolatorAnimationTest(View targetView) {
/*

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(new EasingInterpolator(Ease.CUBIC_IN));
        valueAnimator.start();
*/
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 0, 300);
        // animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.start();
    }

    private void InnitializeAnimation() {

        fadeInAnimationObject = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        bounceAnimationObject = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        bounceinAnimationObject = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_interpolator);

        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);
        delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);

        fadeOutAnimationObject = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        blobText.setVisibility(View.VISIBLE);

        blobText.startAnimation(fadeInAnimationObject);

        fadeInAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                position++;
                if (position >= text.length) {
                    position = 0;
                }
                blobText.setText(text[position]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                doBounceAnimation(blobText);
                doEasingInterpolatorAnimationTest(blobText);

                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
/*

        bounceAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
*/

/*
        textDisplayAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
*/

        // 不用delay
/*
        delayBetweenAnimations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
*/

        fadeOutAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeInAnimationObject);
            }
        });

/*
        delayBetweenAnimations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeInAnimationObject);
            }
        });
*/

    }

    @Override
    public void onClick(View v) {
        //利用刚设置的标识符跳转页面
        //Log.i("tuzi",v.getTag()+"");
        viewPager.setCurrentItem((int) v.getTag());
        animateButton();
    }


    public int getCount() {//返回页面数量
        return 0;
    }


    public boolean isViewFromObject(View arg0, Object arg1) {//判断是否是view对象
        return false;
    }



    @Override
    protected void onDestroy() {
        // 关闭定时器
        isStop = true;

        super.onDestroy();

    }

    void animateButton() {
        // Load the animation
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);
        double animationDuration = getDurationValue() * 1000;
        myAnim.setDuration((long)animationDuration);

        // Use custom animation interpolator to achieve the bounce effect
        MyBounceInterpolator interpolator = new MyBounceInterpolator(getAmplitudeValue(), getFrequencyValue());

        myAnim.setInterpolator(interpolator);

        // Animate the button
        // Button button = findViewById(R.id.ani_play_button_test);
        Button button = findViewById(R.id.control_panel_play_button_test);
        button.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton();
            }
        });
    }

    void animateButton(final Button thisButton) {
        // Load the animation
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);
        double animationDuration = getDurationValue() * 1000;
        myAnim.setDuration((long)animationDuration);

        // Use custom animation interpolator to achieve the bounce effect
        MyBounceInterpolator interpolator = new MyBounceInterpolator(getAmplitudeValue(), getFrequencyValue());

        myAnim.setInterpolator(interpolator);

        // Animate the button
        // Button button = thisButton;
        thisButton.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton(thisButton);
            }
        });
    }

    public void didTapButton(View view) {
        Button button = findViewById(R.id.control_panel_bounce_button_test);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.4, 20);  // 0.2 改为 0.5 跳得就很激烈
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    public void didTapImageButton(Button thisButton) {
        Button button = thisButton;
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.3, 20);  // 0.2 改为 0.5 跳得就很激烈
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    void updateTextViewValue(TextView textView, String prefix, double value) {
        String text = String.format("%s: %.2f", prefix, value);
        textView.setText(text);
    }

    double getSeekBarValue(SeekBar seekBar, double step) {
        return ((double)seekBar.getProgress() + 1.0) / ( 1.0 / step);
    }

    // Duration controls
    // ---------------
    void setupDurationVar() {
        final SeekBar seekBar =(SeekBar) findViewById(R.id.control_panel_seek_bar_duration);
        seekBar.setProgress(19);
        updateDurationLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                updateDurationLabel();
            }
        });
    }

    void updateDurationLabel() {
        TextView textView = (TextView) findViewById(R.id.control_panel_text_view_duration);
        updateTextViewValue(textView, "Duration", getDurationValue());
    }

    double getDurationValue() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.control_panel_seek_bar_duration);
        return getSeekBarValue(seekBar, 0.1);
    }

    // Amplitude controls
    // ---------------
    void setupAmplitudeSeekBar() {
        final SeekBar dampingSeekBar =(SeekBar) findViewById(R.id.control_panel_seek_bar_amplitude);
        dampingSeekBar.setProgress(19);
        updateAmplitudeLabel();

        dampingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                updateAmplitudeLabel();
            }
        });
    }

    void updateAmplitudeLabel() {
        TextView textViewDamping = (TextView) findViewById(R.id.control_panel_text_view_amplitude);
        updateTextViewValue(textViewDamping, "Amplitude", getAmplitudeValue());
    }

    double getAmplitudeValue() {
        final SeekBar dampingSeekBar = (SeekBar) findViewById(R.id.control_panel_seek_bar_amplitude);
        return getSeekBarValue(dampingSeekBar, 0.01);
    }

    // Frequency controls
    // ---------------
    void setupFrequencySeekBar() {
        final SeekBar seekBar =(SeekBar) findViewById(R.id.control_panel_seek_bar_frequency);
        seekBar.setProgress(39);
        updateFrequencyLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                updateFrequencyLabel();
            }
        });
    }

    void updateFrequencyLabel() {
        TextView textView = (TextView) findViewById(R.id.control_panel_text_view_frequency);
        updateTextViewValue(textView, "Frequency", getFrequencyValue());
    }

    double getFrequencyValue() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.control_panel_seek_bar_frequency);
        return getSeekBarValue(seekBar, 0.5);
    }

}
