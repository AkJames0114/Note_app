package com.example.noteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noteapp.adapter.NewsListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNewsBinding;
import com.example.noteapp.model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    private ArrayList<News> newsArrayList = new ArrayList<>();

    private NewsListAdapter adapter;


    @Override
    protected FragmentNewsBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentNewsBinding.inflate(layoutInflater, container, toAttachParent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity.setTitle("News");
        adapter = new NewsListAdapter(newsArrayList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.newsRecyclerView.setLayoutManager(layoutManager);
        binding.newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        String access_token = "Bearer " + baseActivity.preferenceManager.getValue(String.class, "access_token", "");
        Call<ArrayList<News>> call = baseActivity.mainApi.getNews(access_token);

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

}
