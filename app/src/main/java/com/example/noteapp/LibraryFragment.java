package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.noteapp.adapter.BookNoteListAdapter;
import com.example.noteapp.adapter.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentLibraryBinding;
import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;

import java.util.ArrayList;

public class LibraryFragment extends BaseFragment<FragmentLibraryBinding> {

    private final ArrayList<BookNote> bookNoteArrayList = new ArrayList<>();
    private BookNoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookNoteListAdapter(bookNoteArrayList);

    }

    @Override
    protected FragmentLibraryBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentLibraryBinding.inflate(layoutInflater, container, toAttachParent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.bookNoteRecyclerView.setLayoutManager(gridLayoutManager);
        binding.bookNoteRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        bookNoteArrayList.clear();
        bookNoteArrayList.addAll(baseActivity.dataBaseHelper.getBookNote());
        adapter.notifyDataSetChanged();
    }

}
