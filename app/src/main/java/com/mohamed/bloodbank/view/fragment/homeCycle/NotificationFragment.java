package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.NotificationAdapter;
import com.mohamed.bloodbank.data.model.notification.Notification;
import com.mohamed.bloodbank.data.model.notification.NotificationData;
import com.mohamed.bloodbank.helper.OnEndLess;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;

public class NotificationFragment extends BaseFragment {

    @BindView(R.id.notification_fragment_rv_notification_list)
    RecyclerView notificationFragmentRvNotificationList;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private List<NotificationData> notificationDataList = new ArrayList<>();
    private NotificationAdapter notificationAdapter;
    private int maxPage = 0;
    private OnEndLess onEndLess;

    public NotificationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        
        //  setUpActivity();
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationFragmentRvNotificationList.setLayoutManager(linearLayoutManager);
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {

                if (maxPage != 0 && current_page != 1) {
                    onEndLess.previous_page = current_page;

                    getNotification(current_page);
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }else{
                onEndLess.current_page = onEndLess.previous_page;
            }
        }

    };
        notificationFragmentRvNotificationList.addOnScrollListener(onEndLess);
         notificationAdapter = new NotificationAdapter(getContext(),getActivity(),notificationDataList);
         notificationFragmentRvNotificationList.setAdapter(notificationAdapter);
         getNotification(1);

    }

    private void getNotification(int page) {
        getClient().getNotification("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",page).enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
               try {

                   if (response.body().getStatus() == 1) {
                       maxPage = response.body().getData().getLastPage();
                        notificationDataList.addAll(response.body().getData().getData());
                        notificationAdapter.notifyDataSetChanged();
                   }
               }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
