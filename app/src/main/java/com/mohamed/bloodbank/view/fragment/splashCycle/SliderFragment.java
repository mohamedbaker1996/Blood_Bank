package com.mohamed.bloodbank.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.SlideAdapter;
import com.mohamed.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.activity.UserCycleActivity;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import java.util.Objects;

import butterknife.OnClick;

public class SliderFragment extends BaseFragment {

    private SlideAdapter sliderAdapter;
    private TextView[] mDots;
    private Button slideNextBtn;
    private Button slideBackBtn;
    private int slideCurrnentPage;

    ViewPager slideViewPager;
    //  @BindView(R.id.dots_layout)
    LinearLayout mDotsLayout;
    private ViewPagerWithFragmentAdapter viewPagerWithFragmentAdapter;


    public SliderFragment() {
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
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        slideViewPager = view.findViewById(R.id.slide_view_pager);
        mDotsLayout = view.findViewById(R.id.dots_layout);
        slideNextBtn = view.findViewById(R.id.btn_next_slide);
        slideBackBtn = view.findViewById(R.id.btn_prev_slide);
        sliderAdapter = new SlideAdapter(this.getActivity());
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

        slideNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(slideCurrnentPage + 1);
            }
        });
        slideBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(slideCurrnentPage - 1);
            }
        });
     /*
        @OnClick({R.id.btn_next_slide, R.id.btn_prev_slide})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_next_slide:
                    slideViewPager.setCurrentItem(slideCurrnentPage + 1);
                case R.id.btn_prev_slide:
                    slideViewPager.setCurrentItem(slideCurrnentPage - 1);
            }
        }*/
        return view;

        // return inflater.inflate(R.layout.fragment_slider, container, false);


    }


    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this.getActivity());
            mDots[i].setText(Html.fromHtml("&#9673;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPurple));
            mDotsLayout.addView(mDots[i]);

        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            slideCurrnentPage = i;
            if (i == 0) {
                slideNextBtn.setEnabled(true);
                slideBackBtn.setEnabled(false);
                slideBackBtn.setVisibility(View.INVISIBLE);
                slideNextBtn.setText("Next");
                slideBackBtn.setText("");
                slideNextBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                slideBackBtn.setTextColor(getResources().getColor(R.color.colorWhite));
            } else if (i == mDots.length - 1) {
                slideNextBtn.setEnabled(true);
                slideBackBtn.setEnabled(true);
                slideBackBtn.setVisibility(View.VISIBLE);
                slideNextBtn.setText("Finish");
                slideBackBtn.setText("Back");
                slideNextBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                slideBackBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                slideNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), UserCycleActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                slideNextBtn.setEnabled(true);
                slideBackBtn.setEnabled(true);
                slideBackBtn.setVisibility(View.VISIBLE);
                slideNextBtn.setText("Next");
                slideBackBtn.setText("Back");
                slideNextBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                slideBackBtn.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onBack() {

        Objects.requireNonNull(getActivity()).finish();
    }



}
