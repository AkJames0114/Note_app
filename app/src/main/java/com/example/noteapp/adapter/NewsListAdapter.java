package com.example.noteapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresOptIn;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.noteapp.R;
import com.example.noteapp.base.BaseAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.databinding.ItemNewsBinding;
import com.example.noteapp.model.News;

import java.util.ArrayList;

public class NewsListAdapter extends BaseAdapter {

    private ArrayList<News> newsArrayList;

    public NewsListAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    class NewsViewHolder extends BaseViewHolder{
        private ItemNewsBinding binding;
        public NewsViewHolder(ItemNewsBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            News news = newsArrayList.get(position);
            binding.newsTitle.setText(news.getTitle());
            binding.newsSource.setText(news.getSource());
            binding.newsPublishedAt.setText(news.getPublishedAt());

            RequestOptions options = new RequestOptions();
            options.centerCrop();

            Glide.with(binding.getRoot()).load(news.getImage_url()).apply(options).placeholder(R.drawable.ic_image).into(binding.newsImageView);
        }
    }
}
