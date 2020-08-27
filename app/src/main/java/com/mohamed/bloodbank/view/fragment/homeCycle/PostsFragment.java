package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.ArticlesAdapter;
import com.mohamed.bloodbank.data.model.articles.Articles;
import com.mohamed.bloodbank.data.model.articles.ArticlesData;
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

public class PostsFragment extends BaseFragment {

    @BindView(R.id.et_posts_search)
    EditText etPostsSearch;
    @BindView(R.id.posts_list_fragment_rv_articles)
    RecyclerView postsListFragmentRvArticles;


    private Unbinder unbinder;
    private List<ArticlesData> articlesDataList = new ArrayList<>();
    private LinearLayoutManager linearLayout;
    private OnEndLess onEndLess;
    private Integer maxPage = 0;
    private ArticlesAdapter articleAdapter;

    public PostsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        //  setUpActivity();
        unbinder = ButterKnife.bind(this, view);
        articlesInit();
        return view;
    }

    private void articlesInit() {
        linearLayout = new LinearLayoutManager(getActivity());
        postsListFragmentRvArticles.setLayoutManager(linearLayout);
        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        // if (Filter) {
                        //   onFilter(current_page);
                        //}else {
                        //   getDonations(current_page);
                        // }
                        getArticles(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;

                }
            }
        };
        postsListFragmentRvArticles.addOnScrollListener(onEndLess);
        articleAdapter = new ArticlesAdapter(getActivity(), articlesDataList);
        postsListFragmentRvArticles.setAdapter(articleAdapter);
//----------------------------------------------------------------------
        if (articlesDataList.size()==0) {

            getArticles(1);
        }

    }

    private void getArticles(int page) {
        Call<Articles> call = getClient().posts("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", 1);
        startCall(call, page);
    }

    private void startCall(Call<Articles> call, int page) {
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        articlesDataList.addAll(response.body().getData().getData());
                        articleAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

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
