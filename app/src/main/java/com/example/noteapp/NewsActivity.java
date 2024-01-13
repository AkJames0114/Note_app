package com.example.noteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noteapp.adapter.NewsListAdapter;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNewsBinding;
import com.example.noteapp.model.News;
import com.example.noteapp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends BaseActivity<ActivityNewsBinding> {

    private ArrayList<News> newsArrayList = new ArrayList<>();

    private NewsListAdapter adapter;

    @Override
    protected ActivityNewsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNewsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("News");
        adapter = new NewsListAdapter(newsArrayList);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.newsRecyclerView.setLayoutManager(layoutManager);
        binding.newsRecyclerView.setAdapter(adapter);

        String access_token = "Bearer " + preferenceManager.getValue(String.class, "access_token", "");
        Call<ArrayList<News>> call = mainApi.getNews(access_token);

        call.enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                newsArrayList.clear();
                newsArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();

                Log.d("News ", newsArrayList.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean hasActionBar() {
        return true;
    }

    @Override
    public boolean hasBackButton() {
        return true;
    }

    @Override
    public boolean backButtonClickActivated() {
        return false;
    }

    @Override
    public int getBackButtonIcon() {
        return R.drawable.ic_home;
    }
}
