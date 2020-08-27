package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_fragment_tab_layout)
    TabLayout homeFragmentTabLayout;
    @BindView(R.id.home_fragment_view_pager)
    ViewPager homeFragmentViewPager;
    private Unbinder unbinder;
    private ViewPagerWithFragmentAdapter tabAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
          setUpActivity();

        unbinder =  ButterKnife.bind(this,view);

        navigateTabs();


        return view;
    }
    private void navigateTabs(){
         tabAdapter = new ViewPagerWithFragmentAdapter(getChildFragmentManager());
         tabAdapter.addPager(new PostsFragment(),"Posts");
         tabAdapter.addPager(new DonationsListFragment(),"Donations");
         homeFragmentViewPager.setAdapter(tabAdapter);
        homeFragmentTabLayout.post(new Runnable() {
            @Override
            public void run() {
                homeFragmentTabLayout.setupWithViewPager(homeFragmentViewPager);

            }
        });


    }
    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
