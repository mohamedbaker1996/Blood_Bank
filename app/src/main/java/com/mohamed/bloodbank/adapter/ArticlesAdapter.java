package com.mohamed.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.data.model.articles.ArticlesData;
import com.mohamed.bloodbank.data.model.generalResponse.GeneralResponse;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {



    private Context context;
    private BaseActivity activity;
    private List<ArticlesData> articlesDataList = new ArrayList<>();
    private GeneralResponse generalResponse;


    public ArticlesAdapter(Activity activity, List<ArticlesData> articlesDataList) {
        this.context = activity;
        this.activity = (BaseActivity) activity;
        this.articlesDataList = articlesDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_articels,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

      String posImgURL = articlesDataList.get(position).getThumbnailFullPath();
        Glide.with(context)
                .load(posImgURL)

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.postProgressLoadPhoto.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.postProgressLoadPhoto.setVisibility(View.GONE);

                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.postImg);

        holder.postTitle.setText(articlesDataList.get(position).getTitle());



    }
    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return articlesDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.post_img)
        ImageView postImg;
        @BindView(R.id.shadow_bottom)
        ImageView shadowBottom;
        @BindView(R.id.post_progress_load_photo)
        ProgressBar postProgressLoadPhoto;
        @BindView(R.id.post_title)
        TextView postTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
