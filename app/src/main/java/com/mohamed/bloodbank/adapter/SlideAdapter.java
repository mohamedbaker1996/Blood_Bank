package com.mohamed.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.mohamed.bloodbank.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    private int[] GalImages = new int[]{R.drawable.intro_slider_1, R.drawable.intro_slider_2,R.drawable.intro_slider_3};

    LayoutInflater mLayoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {

            View itemView = mLayoutInflater.inflate(R.layout.fragment_intro_slider1, container, false);

            ImageView imageView = itemView.findViewById(R.id.img_intro_slider_1);
            imageView.setImageResource(GalImages[position]);

            container.addView(itemView);

            return itemView;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}

