package com.mohamed.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.data.model.notification.NotificationData;
import com.mohamed.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private BaseActivity activity;
    private List<NotificationData> notificationDataList = new ArrayList<>();

    public NotificationAdapter(Context context, Activity activity, List<NotificationData> notificationData) {
        this.context = context;
        this.activity = (BaseActivity) activity;
        this.notificationDataList = notificationData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        if (notificationDataList.get(position).getPivot().getIsRead().equals("0")) {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.ic_notifications);
        }else {
            holder.itemNotificationIvIcon.setImageResource(R.drawable.ic_notifications_none);

        }

        holder.itemNotificationTvTitle.setText(notificationDataList.get(position).getTitle());
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_notification_iv_icon)
        ImageView itemNotificationIvIcon;
        @BindView(R.id.item_notification_tv_title)
        TextView itemNotificationTvTitle;
        @BindView(R.id.item_notification_tv_time)
        TextView itemNotificationTvTime;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
