package com.example.noteapp.base;

import androidx.recyclerview.widget.RecyclerView;

public interface BaseAdapterListener {

    void onItemClick(int position);
    void onItemRemoveClick(int position);
    void onItemLongClick(int position);
}
