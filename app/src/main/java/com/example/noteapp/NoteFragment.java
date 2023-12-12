package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.adapter.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;
import com.example.noteapp.model.Note;

import java.util.ArrayList;

public class NoteFragment extends BaseFragment<FragmentNotesBinding> {

    private final ArrayList<Note> noteArrayList = new ArrayList<>();
    private NoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new NoteListAdapter(noteArrayList);

    }

    @Override
    protected FragmentNotesBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentNotesBinding.inflate(layoutInflater, container, toAttachParent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.noteRecyclerView.setLayoutManager(gridLayoutManager);
        binding.noteRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        noteArrayList.clear();
        noteArrayList.addAll(baseActivity.dataBaseHelper.getNotes());
        adapter.notifyDataSetChanged();
    }
}

