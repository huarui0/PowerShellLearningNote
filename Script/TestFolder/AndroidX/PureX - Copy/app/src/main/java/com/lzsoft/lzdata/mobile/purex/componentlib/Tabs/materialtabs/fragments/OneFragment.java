package com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lzsoft.lzdata.mobile.purex.ContralPanelActivity;
import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance.ExpandControlPanelActivity;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.anniinteropter.MyBounceInterpolator;
import com.lzsoft.lzdata.mobile.purex.database.mysql.puredata.MySQLDataPureActivity;


public class OneFragment extends Fragment {

    private Button returnTestButton;
    private Button forwardTestButton;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_material_tabs_one, container, false);
        View view = inflater.inflate(R.layout.activity_mysql_pure_data, container, false);

        returnTestButton = view.findViewById(R.id.mySql_return_button_test);
        returnTestButton.setOnClickListener(new View.OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "返回的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.ani_to_main_button_test);
                didTapImageButton(returnTestButton);

/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                startActivity(new Intent(getActivity(), ContralPanelActivity.class));

            }
        });

        forwardTestButton = view.findViewById(R.id.mySql_forward_button_test);
        forwardTestButton.setOnClickListener(new View.OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "启动图片放大界面的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.ani_to_enlarge_button_test);
                didTapImageButton(forwardTestButton);

/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                startActivity(new Intent(getActivity(), MySQLDataPureActivity.class));

            }
        });

        return view;
    }

    void animateButton(final Button thisButton) {
        // Load the animation
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_button);
        double animationDuration = getDurationValue() * 1000;
        myAnim.setDuration((long) animationDuration);

        // Use custom animation interpolator to achieve the bounce effect
        MyBounceInterpolator interpolator = new MyBounceInterpolator(getAmplitudeValue(), getFrequencyValue());

        myAnim.setInterpolator(interpolator);

        // Animate the button
        // Button button = thisButton;
        thisButton.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton(thisButton);
            }
        });
    }

    public void didTapButton(View view) {
        Button button = view.findViewById(R.id.control_panel_bounce_button_test);
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_button);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.4, 20);  // 0.2 改为 0.5 跳得就很激烈
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    public void didTapImageButton(Button thisButton) {
        Button button = thisButton;
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_button);

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
        return ((double) seekBar.getProgress() + 1.0) / (1.0 / step);
    }

    // Duration controls
    // ---------------
    void setupDurationVar() {
        final SeekBar seekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_duration);
        seekBar.setProgress(19);
        updateDurationLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = getView().findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateDurationLabel();
            }
        });
    }

    void updateDurationLabel() {
        TextView textView = (TextView) getView().findViewById(R.id.control_panel_text_view_duration);
        updateTextViewValue(textView, "Duration", getDurationValue());
    }

    double getDurationValue() {
        final SeekBar seekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_duration);
        return getSeekBarValue(seekBar, 0.1);
    }

    // Amplitude controls
    // ---------------
    void setupAmplitudeSeekBar() {
        final SeekBar dampingSeekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_amplitude);
        dampingSeekBar.setProgress(19);
        updateAmplitudeLabel();

        dampingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = getView().findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateAmplitudeLabel();
            }
        });
    }

    void updateAmplitudeLabel() {
        TextView textViewDamping = (TextView) getView().findViewById(R.id.control_panel_text_view_amplitude);
        updateTextViewValue(textViewDamping, "Amplitude", getAmplitudeValue());
    }

    double getAmplitudeValue() {
        final SeekBar dampingSeekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_amplitude);
        return getSeekBarValue(dampingSeekBar, 0.01);
    }

    // Frequency controls
    // ---------------
    void setupFrequencySeekBar() {
        final SeekBar seekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_frequency);
        seekBar.setProgress(39);
        updateFrequencyLabel();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Button button = getView().findViewById(R.id.control_panel_bounce_button_test);
                animateButton(button);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateFrequencyLabel();
            }
        });
    }

    void updateFrequencyLabel() {
        TextView textView = (TextView) getView().findViewById(R.id.control_panel_text_view_frequency);
        updateTextViewValue(textView, "Frequency", getFrequencyValue());
    }

    double getFrequencyValue() {
        final SeekBar seekBar = (SeekBar) getView().findViewById(R.id.control_panel_seek_bar_frequency);
        return getSeekBarValue(seekBar, 0.5);
    }
}
